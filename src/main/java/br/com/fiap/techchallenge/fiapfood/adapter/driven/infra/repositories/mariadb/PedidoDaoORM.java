package br.com.fiap.techchallenge.fiapfood.adapter.driven.infra.repositories.mariadb;

import br.com.fiap.techchallenge.fiapfood.core.applications.services.cliente.BuscarClienteUseCase;
import br.com.fiap.techchallenge.fiapfood.core.applications.services.produto.BuscarProdutoUseCase;
import br.com.fiap.techchallenge.fiapfood.core.domain.base.StatusPedido;
import br.com.fiap.techchallenge.fiapfood.core.domain.dto.ClienteORM;
import br.com.fiap.techchallenge.fiapfood.core.domain.dto.PedidoORM;
import br.com.fiap.techchallenge.fiapfood.core.domain.entities.ItemPedidoEntity;
import br.com.fiap.techchallenge.fiapfood.core.domain.entities.PedidoEntity;
import br.com.fiap.techchallenge.fiapfood.core.domain.ports.output.PedidoRepositoryORM;
import br.com.fiap.techchallenge.fiapfood.core.domain.valueobject.Cpf;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.List;
import java.util.Optional;


public class PedidoDaoORM extends ConnectionPoolManagerORM implements PedidoRepositoryORM {

    private EntityManager entityManager;

    public PedidoDaoORM() {

    }

    @Override
    public Optional<PedidoORM> inserir(PedidoORM pedido) {
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        PedidoEntity entity = PedidoMapperORM.mapToEntity(pedido);
        BuscarClienteUseCase buscarClienteUseCase = new BuscarClienteUseCase();
        ClienteORM cliente = buscarClienteUseCase.buscarClientePorCpfORM(new Cpf(entity.getCliente().getCpf())).get();
        entity.setCliente(ClienteMapperORM.mapToEntity(cliente));
        entity.setListItens(null);
        entityManager.persist(entity);
        entityManager.flush();

        BuscarProdutoUseCase buscarProdutoUseCase = new BuscarProdutoUseCase();

        List<ItemPedidoEntity> itens = ItemPedidoMapperORM.mapListToEntity(pedido.getListItens());
        for (ItemPedidoEntity item : itens) {
            item.setPedido(entity);
            entityManager.persist(item);
            entityManager.flush();
            item.setProduto(ProdutoMapperORM.mapToEntity(buscarProdutoUseCase.buscarProdutoPorId(item.getProduto().getId()).get()));
        }

        entityManager.getTransaction().commit();
        entityManager.close();
        entity.setListItens(itens);
        return Optional.ofNullable(PedidoMapperORM.mapToEntity(entity));
    }

    @Override
    public Optional<PedidoORM> buscarPorId(Long id) {
        entityManager = entityManagerFactory.createEntityManager();
        PedidoEntity entity = entityManager.find(PedidoEntity.class, id);
        entityManager.close();
        return Optional.ofNullable(PedidoMapperORM.mapToEntity(entity));
    }

    @Override
    public Optional<List<PedidoORM>> listarTudo() {
        entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createNamedQuery("findAllPedidos");
        List<PedidoEntity> list = query.getResultList();
        entityManager.close();
        return Optional.ofNullable(PedidoMapperORM.mapListToEntity(list));
    }

    @Override
    public Optional<List<PedidoORM>> listarPedidosPorStatus(StatusPedido status) {
        entityManager = entityManagerFactory.createEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<PedidoEntity> criteriaQuery = criteriaBuilder.createQuery(PedidoEntity.class);
        Root<PedidoEntity> root = criteriaQuery.from(PedidoEntity.class);

        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.equal(root.get("status"), status.toString()));

        List<PedidoEntity> resultList = entityManager.createQuery(criteriaQuery).getResultList();
        entityManager.close();
        return Optional.ofNullable(PedidoMapperORM.mapListToEntity(resultList));
    }

    @Override
    public Optional<List<PedidoORM>> listarPedidosEmAberto() {
        entityManager = entityManagerFactory.createEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<PedidoEntity> criteriaQuery = criteriaBuilder.createQuery(PedidoEntity.class);
        Root<PedidoEntity> root = criteriaQuery.from(PedidoEntity.class);

        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.notEqual(root.get("status"), StatusPedido.ENTREGUE));

        List<PedidoEntity> resultList = entityManager.createQuery(criteriaQuery).getResultList();
        entityManager.close();
        return Optional.ofNullable(PedidoMapperORM.mapListToEntity(resultList));
    }

    @Override
    public Boolean excluir(PedidoORM pedido) {
        entityManager = entityManagerFactory.createEntityManager();
        PedidoEntity entity = entityManager.find(PedidoEntity.class, pedido.getId());
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
    public Optional<PedidoORM> atualizarProgresso(PedidoORM pedido, StatusPedido novoStatus) {

        PedidoORM pedidoORM = buscarPorId(pedido.getId()).get();

        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        PedidoEntity entity = PedidoMapperORM.mapToEntity(pedidoORM);
        entity.setStatus(novoStatus);
        entityManager.merge(entity);
        entityManager.flush();
        entityManager.getTransaction().commit();
        entityManager.close();
        return Optional.ofNullable(PedidoMapperORM.mapToEntity(entity));
    }
}
