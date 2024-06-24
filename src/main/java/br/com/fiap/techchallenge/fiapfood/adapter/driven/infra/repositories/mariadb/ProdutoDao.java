package br.com.fiap.techchallenge.fiapfood.adapter.driven.infra.repositories.mariadb;

import br.com.fiap.techchallenge.fiapfood.adapter.driven.infra.repositories.mariadb.mapper.ProdutoMapper;
import br.com.fiap.techchallenge.fiapfood.core.domain.dto.CategoriaDto;
import br.com.fiap.techchallenge.fiapfood.core.domain.dto.ProdutoDto;
import br.com.fiap.techchallenge.fiapfood.core.domain.entities.Categoria;
import br.com.fiap.techchallenge.fiapfood.core.domain.entities.Produto;
import br.com.fiap.techchallenge.fiapfood.core.domain.ports.output.ProdutoRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;
import java.util.Optional;


public class ProdutoDao extends ConnectionPoolManager implements ProdutoRepository {

    private EntityManager entityManager;

    @Override
    public Optional<ProdutoDto> inserir(ProdutoDto produto) {
        entityManager = getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        Produto entity = ProdutoMapper.mapToEntity(produto);
        Categoria categoria = entityManager.find(Categoria.class, entity.getCategoria().getId());
        entity.setCategoria(categoria);
        entityManager.persist(entity);
        entityManager.flush();
        entityManager.getTransaction().commit();
        entityManager.close();
        return Optional.ofNullable(ProdutoMapper.mapToEntity(entity));
    }

    @Override
    public Optional<ProdutoDto> buscarPorId(Long id) {
        entityManager = getEntityManagerFactory().createEntityManager();
        Produto entity = entityManager.find(Produto.class, id);
        entityManager.close();
        return Optional.ofNullable(ProdutoMapper.mapToEntity(entity));
    }

    @Override
    public Optional<List<ProdutoDto>> listarPorCategoria(CategoriaDto categoria) {
        entityManager = getEntityManagerFactory().createEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Produto> criteriaQuery = criteriaBuilder.createQuery(Produto.class);
        Root<Produto> root = criteriaQuery.from(Produto.class);

        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.equal(root.get("categoria").get("id"), categoria.getId()));

        List<Produto> resultList = entityManager.createQuery(criteriaQuery).getResultList();
        entityManager.close();
        return Optional.ofNullable(ProdutoMapper.mapListToEntity(resultList));
    }

    @Override
    public Optional<List<ProdutoDto>> listarTudo() {
        entityManager = getEntityManagerFactory().createEntityManager();
        Query query = entityManager.createNamedQuery("findAllProdutos");
        List<Produto> list = query.getResultList();
        entityManager.close();
        return Optional.ofNullable(ProdutoMapper.mapListToEntity(list));
    }

    @Override
    public Boolean excluir(ProdutoDto produto) {
        entityManager = getEntityManagerFactory().createEntityManager();
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
    public Optional<ProdutoDto> atualizar(ProdutoDto produto) {
        entityManager = getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        Produto entity = ProdutoMapper.mapToEntity(produto);
        Categoria categoria = entityManager.find(Categoria.class, entity.getCategoria().getId());
        entity.setCategoria(categoria);
        entityManager.merge(entity);
        entityManager.flush();
        entityManager.getTransaction().commit();
        entityManager.close();
        return Optional.ofNullable(ProdutoMapper.mapToEntity(entity));
    }
}

