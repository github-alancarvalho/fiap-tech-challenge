package br.com.fiap.techchallenge.fiapfood.adapter.driven.infra.repositories.mariadb;

import br.com.fiap.techchallenge.fiapfood.adapter.driven.infra.repositories.mariadb.mapper.PagamentoMapper;
import br.com.fiap.techchallenge.fiapfood.core.domain.base.StatusPagamento;
import br.com.fiap.techchallenge.fiapfood.core.domain.dto.PagamentoDto;
import br.com.fiap.techchallenge.fiapfood.core.domain.entities.Pagamento;
import br.com.fiap.techchallenge.fiapfood.core.domain.ports.output.PagamentoRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.List;
import java.util.Optional;


public class PagamentoDao extends ConnectionPoolManager implements PagamentoRepository {

    private EntityManager entityManager;

    public PagamentoDao(){

    }

    @Override
    public Optional<PagamentoDto> processarPagamento(PagamentoDto pagamento) {
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Pagamento entity = PagamentoMapper.mapToEntity(pagamento);
        entityManager.persist(entity);
        entityManager.flush();
        entityManager.getTransaction().commit();
        entityManager.close();
        return Optional.ofNullable(PagamentoMapper.mapToEntity(entity));
    }

    @Override
    public Optional<PagamentoDto> atualizarStatusPagamento(PagamentoDto pagamento, StatusPagamento status) {
        Pagamento entity = PagamentoMapper.mapToEntity(buscarPagamentoPorId(pagamento.getId()).get());
        entity.setStatus(status);
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(entity);
        entityManager.flush();
        entityManager.getTransaction().commit();
        entityManager.close();
        return Optional.ofNullable(PagamentoMapper.mapToEntity(entity));
    }

    @Override
    public Optional<PagamentoDto> buscarPagamentoPorId(Long id) {
        entityManager = entityManagerFactory.createEntityManager();
        Pagamento entity = entityManager.find(Pagamento.class, id);
        entityManager.close();
        return Optional.ofNullable(PagamentoMapper.mapToEntity(entity));
    }

    @Override
    public Optional<List<PagamentoDto>> listarPagamentos() {
        entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createNamedQuery("findAllPagamentos");
        List<Pagamento> list = query.getResultList();
        entityManager.close();
        return Optional.ofNullable(PagamentoMapper.mapListToEntity(list));
    }
}

