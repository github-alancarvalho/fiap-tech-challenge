package br.com.fiap.techchallenge.fiapfood.adapter.driven.infra.repositories.mariadb;

import br.com.fiap.techchallenge.fiapfood.adapter.driven.infra.repositories.mariadb.mapper.ClienteMapper;
import br.com.fiap.techchallenge.fiapfood.adapter.driven.infra.repositories.mariadb.mapper.ItemPedidoMapper;
import br.com.fiap.techchallenge.fiapfood.adapter.driven.infra.repositories.mariadb.mapper.PedidoMapper;
import br.com.fiap.techchallenge.fiapfood.adapter.driven.infra.repositories.mariadb.mapper.ProdutoMapper;
import br.com.fiap.techchallenge.fiapfood.core.applications.services.cliente.BuscarClienteUseCase;
import br.com.fiap.techchallenge.fiapfood.core.applications.services.produto.BuscarProdutoUseCase;
import br.com.fiap.techchallenge.fiapfood.core.domain.base.StatusPedido;
import br.com.fiap.techchallenge.fiapfood.core.domain.dto.ClienteDto;
import br.com.fiap.techchallenge.fiapfood.core.domain.dto.PedidoDto;
import br.com.fiap.techchallenge.fiapfood.core.domain.entities.ItemPedido;
import br.com.fiap.techchallenge.fiapfood.core.domain.entities.Pedido;
import br.com.fiap.techchallenge.fiapfood.core.domain.ports.output.PedidoRepository;
import br.com.fiap.techchallenge.fiapfood.core.domain.valueobject.Cpf;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;
import java.util.Optional;


public class PedidoDao extends ConnectionPoolManager implements PedidoRepository {

    private EntityManager entityManager;

    public PedidoDao() {

    }

    @Override
    public Optional<PedidoDto> inserir(PedidoDto pedido) {
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Pedido entity = PedidoMapper.mapToEntity(pedido);
        BuscarClienteUseCase buscarClienteUseCase = new BuscarClienteUseCase();
        ClienteDto cliente = buscarClienteUseCase.buscarClientePorCpfORM(new Cpf(entity.getCliente().getCpf())).get();
        entity.setCliente(ClienteMapper.mapToEntity(cliente));
        entity.setListItens(null);
        entityManager.persist(entity);
        entityManager.flush();

        BuscarProdutoUseCase buscarProdutoUseCase = new BuscarProdutoUseCase();

        List<ItemPedido> itens = ItemPedidoMapper.mapListToEntity(pedido.getListItens());
        for (ItemPedido item : itens) {
            item.setPedido(entity);
            entityManager.persist(item);
            entityManager.flush();
            item.setProduto(ProdutoMapper.mapToEntity(buscarProdutoUseCase.buscarProdutoPorId(item.getProduto().getId()).get()));
        }

        entityManager.getTransaction().commit();
        entityManager.close();
        entity.setListItens(itens);
        return Optional.ofNullable(PedidoMapper.mapToEntity(entity));
    }

    @Override
    public Optional<PedidoDto> buscarPorId(Long id) {
        entityManager = entityManagerFactory.createEntityManager();
        Pedido entity = entityManager.find(Pedido.class, id);
        entityManager.close();
        return Optional.ofNullable(PedidoMapper.mapToEntity(entity));
    }

    @Override
    public Optional<List<PedidoDto>> listarTudo() {
        entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createNamedQuery("findAllPedidos");
        List<Pedido> list = query.getResultList();
        entityManager.close();
        return Optional.ofNullable(PedidoMapper.mapListToEntity(list));
    }

    @Override
    public Optional<List<PedidoDto>> listarPedidosPorStatus(StatusPedido status) {
        entityManager = entityManagerFactory.createEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Pedido> criteriaQuery = criteriaBuilder.createQuery(Pedido.class);
        Root<Pedido> root = criteriaQuery.from(Pedido.class);

        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.equal(root.get("status"), status.toString()));

        List<Pedido> resultList = entityManager.createQuery(criteriaQuery).getResultList();
        entityManager.close();
        return Optional.ofNullable(PedidoMapper.mapListToEntity(resultList));
    }

    @Override
    public Optional<List<PedidoDto>> listarPedidosEmAberto() {
        entityManager = entityManagerFactory.createEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Pedido> criteriaQuery = criteriaBuilder.createQuery(Pedido.class);
        Root<Pedido> root = criteriaQuery.from(Pedido.class);

        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.notEqual(root.get("status"), StatusPedido.ENTREGUE));

        List<Pedido> resultList = entityManager.createQuery(criteriaQuery).getResultList();
        entityManager.close();
        return Optional.ofNullable(PedidoMapper.mapListToEntity(resultList));
    }

    @Override
    public Boolean excluir(PedidoDto pedido) {
        entityManager = entityManagerFactory.createEntityManager();
        Pedido entity = entityManager.find(Pedido.class, pedido.getId());
        if (entity != null) {
            entityManager.getTransaction().begin();
            entityManager.remove(entity);
            entityManager.getTransaction().commit();
            entityManager.close();
            return true;
        } else {
            entityManager.close();
            return false;
        }
    }

    @Override
    public Optional<PedidoDto> atualizarProgresso(PedidoDto pedido, StatusPedido novoStatus) {

        PedidoDto pedidoDto = buscarPorId(pedido.getId()).get();

        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Pedido entity = PedidoMapper.mapToEntity(pedidoDto);
        entity.setStatus(novoStatus);
        entityManager.merge(entity);
        entityManager.flush();
        entityManager.getTransaction().commit();
        entityManager.close();
        return Optional.ofNullable(PedidoMapper.mapToEntity(entity));
    }
}

