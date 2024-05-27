package br.com.fiap.techchallenge.fiapfood.adapter.driven.infra.repositories.mariadb;

import br.com.fiap.techchallenge.fiapfood.core.domain.dto.CategoriaORM;
import br.com.fiap.techchallenge.fiapfood.core.domain.dto.ProdutoORM;
import br.com.fiap.techchallenge.fiapfood.core.domain.entities.CategoriaEntity;
import br.com.fiap.techchallenge.fiapfood.core.domain.entities.ClienteEntity;
import br.com.fiap.techchallenge.fiapfood.core.domain.entities.ProdutoEntity;
import br.com.fiap.techchallenge.fiapfood.core.domain.ports.output.ProdutoRepositoryORM;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;
import java.util.Optional;


public class ProdutoDaoORM extends ConnectionPoolManagerORM implements ProdutoRepositoryORM {


    private EntityManager entityManager;

    public ProdutoDaoORM(){

    }


    @Override
    public Optional<ProdutoORM> inserir(ProdutoORM produto) {
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        ProdutoEntity entity = ProdutoMapperORM.mapToEntity(produto);
        CategoriaEntity categoria = entityManager.find(CategoriaEntity.class, entity.getCategoria().getId());
        entity.setCategoria(categoria);
        entityManager.persist(entity);
        entityManager.flush();
        entityManager.getTransaction().commit();
        entityManager.close();
        return Optional.ofNullable(ProdutoMapperORM.mapToEntity(entity));
    }

    @Override
    public Optional<ProdutoORM> buscarPorId(Long id) {
        entityManager = entityManagerFactory.createEntityManager();
        ProdutoEntity entity = entityManager.find(ProdutoEntity.class, id);
        entityManager.close();
        return Optional.ofNullable(ProdutoMapperORM.mapToEntity(entity));
    }

    @Override
    public Optional<List<ProdutoORM>> listarPorCategoria(CategoriaORM categoria) {
        entityManager = entityManagerFactory.createEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<ProdutoEntity> criteriaQuery = criteriaBuilder.createQuery(ProdutoEntity.class);
        Root<ProdutoEntity> root = criteriaQuery.from(ProdutoEntity.class);

        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.equal(root.get("categoria").get("id"), categoria.getId()));

        List<ProdutoEntity> resultList = entityManager.createQuery(criteriaQuery).getResultList();
        entityManager.close();
        return Optional.ofNullable(ProdutoMapperORM.mapListToEntity(resultList));
    }

    @Override
    public Optional<List<ProdutoORM>> listarTudo() {
        entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createNamedQuery("findAllProdutos");
        List<ProdutoEntity> list = query.getResultList();
        entityManager.close();
        return Optional.ofNullable(ProdutoMapperORM.mapListToEntity(list));
    }

    @Override
    public Boolean excluir(ProdutoORM produto) {
        entityManager = entityManagerFactory.createEntityManager();
        ProdutoEntity entity = entityManager.find(ProdutoEntity.class, produto.getId());
        if (entity != null){
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
    public Optional<ProdutoORM> atualizar(ProdutoORM produto) {
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        ProdutoEntity entity = ProdutoMapperORM.mapToEntity(produto);
        CategoriaEntity categoria = entityManager.find(CategoriaEntity.class, entity.getCategoria().getId());
        entity.setCategoria(categoria);
        entityManager.merge(entity);
        entityManager.flush();
        entityManager.getTransaction().commit();
        entityManager.close();
        return Optional.ofNullable(ProdutoMapperORM.mapToEntity(entity));
    }
}

