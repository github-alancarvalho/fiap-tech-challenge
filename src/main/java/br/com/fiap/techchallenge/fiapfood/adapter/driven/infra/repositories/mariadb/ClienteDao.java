package br.com.fiap.techchallenge.fiapfood.adapter.driven.infra.repositories.mariadb;

import br.com.fiap.techchallenge.fiapfood.adapter.driven.infra.repositories.mariadb.mapper.ClienteMapper;
import br.com.fiap.techchallenge.fiapfood.core.domain.entities.Cliente;
import br.com.fiap.techchallenge.fiapfood.core.domain.dto.ClienteDto;
import br.com.fiap.techchallenge.fiapfood.core.domain.ports.output.ClienteRepository;
import br.com.fiap.techchallenge.fiapfood.core.domain.valueobject.Cpf;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.List;
import java.util.Optional;


public class ClienteDao extends ConnectionPoolManager implements ClienteRepository {


    private EntityManager entityManager;

    public ClienteDao(){
        this.entityManager = getEntityManagerFactory().createEntityManager();
    }


    public Optional<ClienteDto> inserirClienteORM(ClienteDto cliente) {

        entityManager.getTransaction().begin();
        Cliente entity = ClienteMapper.mapToEntity(cliente);
        entityManager.persist(entity);
        entityManager.flush();
        entityManager.getTransaction().commit();
        return Optional.ofNullable(ClienteMapper.mapToEntity(entity));

    }

    public Optional<ClienteDto> buscarPorCpf(Cpf cpf) {
        Cliente entity = entityManager.find(Cliente.class, cpf.getCpfSomenteNumero());
        return Optional.ofNullable(ClienteMapper.mapToEntity(entity));
    }


    public Optional<ClienteDto> atualizar(ClienteDto cliente) {
        entityManager.getTransaction().begin();
        Cliente entity = ClienteMapper.mapToEntity(cliente);
        entityManager.merge(entity);
        entityManager.flush();
        entityManager.getTransaction().commit();
        return Optional.ofNullable(ClienteMapper.mapToEntity(entity));

    }


    public Boolean excluir(Cpf cpf) {
        Cliente entity = entityManager.find(Cliente.class, cpf.getCpfSomenteNumero());
        if (entity != null){
            entityManager.getTransaction().begin();
            entityManager.remove(entity);
            entityManager.getTransaction().commit();
            return true;
        } else {
            return false;
        }
    }


    public Optional<List<ClienteDto>> listarTudo() {
        Query query = entityManager.createNamedQuery("findAllClientes");
        List<Cliente> list = query.getResultList();
        return Optional.ofNullable(ClienteMapper.mapListToEntity(list));
    }
}

