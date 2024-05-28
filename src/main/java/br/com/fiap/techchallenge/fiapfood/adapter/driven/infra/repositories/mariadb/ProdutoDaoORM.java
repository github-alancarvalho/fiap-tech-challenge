package br.com.fiap.techchallenge.fiapfood.adapter.driven.infra.repositories.mariadb;

import br.com.fiap.techchallenge.fiapfood.core.domain.dto.CategoriaORM;
import br.com.fiap.techchallenge.fiapfood.core.domain.dto.ProdutoORM;
import br.com.fiap.techchallenge.fiapfood.core.domain.entities.Categoria;
import br.com.fiap.techchallenge.fiapfood.core.domain.entities.Produto;
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
        Produto entity = ProdutoMapperORM.mapToEntity(produto);
        Categoria categoria = entityManager.find(Categoria.class, entity.getCategoria().getId());
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
        Produto entity = entityManager.find(Produto.class, id);
        entityManager.close();
        return Optional.ofNullable(ProdutoMapperORM.mapToEntity(entity));
    }

    @Override
    public Optional<List<ProdutoORM>> listarPorCategoria(CategoriaORM categoria) {
        entityManager = entityManagerFactory.createEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Produto> criteriaQuery = criteriaBuilder.createQuery(Produto.class);
        Root<Produto> root = criteriaQuery.from(Produto.class);

        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.equal(root.get("categoria").get("id"), categoria.getId()));

        List<Produto> resultList = entityManager.createQuery(criteriaQuery).getResultList();
        entityManager.close();
        return Optional.ofNullable(ProdutoMapperORM.mapListToEntity(resultList));
    }

    @Override
    public Optional<List<ProdutoORM>> listarTudo() {
        entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createNamedQuery("findAllProdutos");
        List<Produto> list = query.getResultList();
        entityManager.close();
        return Optional.ofNullable(ProdutoMapperORM.mapListToEntity(list));
    }

    @Override
    public Boolean excluir(ProdutoORM produto) {
        entityManager = entityManagerFactory.createEntityManager();
        Produto entity = entityManager.find(Produto.class, produto.getId());
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
        Produto entity = ProdutoMapperORM.mapToEntity(produto);
        Categoria categoria = entityManager.find(Categoria.class, entity.getCategoria().getId());
        entity.setCategoria(categoria);
        entityManager.merge(entity);
        entityManager.flush();
        entityManager.getTransaction().commit();
        entityManager.close();
        return Optional.ofNullable(ProdutoMapperORM.mapToEntity(entity));
    }
}

