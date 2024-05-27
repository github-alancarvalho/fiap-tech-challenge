package br.com.fiap.techchallenge.fiapfood.adapter.driven.infra.repositories.mariadb;

import br.com.fiap.techchallenge.fiapfood.core.domain.entities.ClienteEntity;
import br.com.fiap.techchallenge.fiapfood.core.domain.dto.ClienteORM;
import br.com.fiap.techchallenge.fiapfood.core.domain.ports.output.ClienteRepositoryORM;
import br.com.fiap.techchallenge.fiapfood.core.domain.valueobject.Cpf;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.List;
import java.util.Optional;


public class ClienteDaoORM extends ConnectionPoolManagerORM implements ClienteRepositoryORM{


    private EntityManager entityManager;

    public ClienteDaoORM(){
        this.entityManager = entityManagerFactory.createEntityManager();
    }


    public Optional<ClienteORM> inserirClienteORM(ClienteORM cliente) {

        entityManager.getTransaction().begin();
        ClienteEntity entity = ClienteMapperORM.mapToEntity(cliente);
        entityManager.persist(entity);
        entityManager.flush();
        entityManager.getTransaction().commit();
        return Optional.ofNullable(ClienteMapperORM.mapToEntity(entity));

    }

    public Optional<ClienteORM> buscarPorCpf(Cpf cpf) {
        ClienteEntity entity = entityManager.find(ClienteEntity.class, cpf.getCpfSomenteNumero());
        return Optional.ofNullable(ClienteMapperORM.mapToEntity(entity));
    }


    public Optional<ClienteORM> atualizar(ClienteORM cliente) {
        entityManager.getTransaction().begin();
        ClienteEntity entity = ClienteMapperORM.mapToEntity(cliente);
        entityManager.merge(entity);
        entityManager.flush();
        entityManager.getTransaction().commit();
        return Optional.ofNullable(ClienteMapperORM.mapToEntity(entity));

    }


    public Boolean excluir(Cpf cpf) {
        ClienteEntity entity = entityManager.find(ClienteEntity.class, cpf.getCpfSomenteNumero());
        if (entity != null){
            entityManager.getTransaction().begin();
            entityManager.remove(entity);
            entityManager.getTransaction().commit();
            return true;
        } else {
            return false;
        }
    }


    public Optional<List<ClienteORM>> listarTudo() {
        Query query = entityManager.createNamedQuery("findAllClientes");
        List<ClienteEntity> list = query.getResultList();
        return Optional.ofNullable(ClienteMapperORM.mapListToEntity(list));
    }
}

