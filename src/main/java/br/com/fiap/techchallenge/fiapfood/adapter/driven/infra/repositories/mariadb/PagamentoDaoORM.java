package br.com.fiap.techchallenge.fiapfood.adapter.driven.infra.repositories.mariadb;

import br.com.fiap.techchallenge.fiapfood.core.domain.base.StatusPagamento;
import br.com.fiap.techchallenge.fiapfood.core.domain.dto.PagamentoORM;
import br.com.fiap.techchallenge.fiapfood.core.domain.entities.Pagamento;
import br.com.fiap.techchallenge.fiapfood.core.domain.ports.output.PagamentoRepositoryORM;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.List;
import java.util.Optional;


public class PagamentoDaoORM extends ConnectionPoolManagerORM implements PagamentoRepositoryORM {

    private EntityManager entityManager;

    public PagamentoDaoORM(){

    }

    @Override
    public Optional<PagamentoORM> processarPagamento(PagamentoORM pagamento) {
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Pagamento entity = PagamentoMapperORM.mapToEntity(pagamento);
        entityManager.persist(entity);
        entityManager.flush();
        entityManager.getTransaction().commit();
        entityManager.close();
        return Optional.ofNullable(PagamentoMapperORM.mapToEntity(entity));
    }

    @Override
    public Optional<PagamentoORM> atualizarStatusPagamento(PagamentoORM pagamento, StatusPagamento status) {
        Pagamento entity = PagamentoMapperORM.mapToEntity(buscarPagamentoPorId(pagamento.getId()).get());
        entity.setStatus(status);
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(entity);
        entityManager.flush();
        entityManager.getTransaction().commit();
        entityManager.close();
        return Optional.ofNullable(PagamentoMapperORM.mapToEntity(entity));
    }

    @Override
    public Optional<PagamentoORM> buscarPagamentoPorId(Long id) {
        entityManager = entityManagerFactory.createEntityManager();
        Pagamento entity = entityManager.find(Pagamento.class, id);
        entityManager.close();
        return Optional.ofNullable(PagamentoMapperORM.mapToEntity(entity));
    }

    @Override
    public Optional<List<PagamentoORM>> listarPagamentos() {
        entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createNamedQuery("findAllPagamentos");
        List<Pagamento> list = query.getResultList();
        entityManager.close();
        return Optional.ofNullable(PagamentoMapperORM.mapListToEntity(list));
    }
}

