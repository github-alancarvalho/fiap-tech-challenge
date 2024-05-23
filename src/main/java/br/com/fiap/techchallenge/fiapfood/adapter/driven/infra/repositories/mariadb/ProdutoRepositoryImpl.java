package br.com.fiap.techchallenge.fiapfood.adapter.driven.infra.repositories.mariadb;

import br.com.fiap.techchallenge.fiapfood.adapter.driven.infra.repositories.ConnectionPoolManager;
import br.com.fiap.techchallenge.fiapfood.core.applications.ports.ProdutoPort;
import br.com.fiap.techchallenge.fiapfood.core.domain.entities.Categoria;
import br.com.fiap.techchallenge.fiapfood.core.domain.entities.Produto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class ProdutoRepositoryImpl extends ConnectionPoolManager implements ProdutoPort {

    public ProdutoRepositoryImpl() {

    }

    @Override
    public Optional<Produto> inserir(Produto produto) {
        try (Connection connection = ConnectionPoolManager.getDataSource().getConnection()) {

            String sql = "INSERT INTO produto (nome, descricao, categoria_id, preco) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, produto.getNome());
            statement.setString(2, produto.getDescricao());
            statement.setLong(3, produto.getCategoria().getId());
            statement.setDouble(4, produto.getPreco());
            Integer retInsert = statement.executeUpdate();

            if (retInsert > -1) {
                // Retrieve the ResultSet containing the generated keys
                ResultSet generatedKeys = statement.getGeneratedKeys();

                if (generatedKeys.next()) {
                    long newId = generatedKeys.getLong(1);
                    produto.setId(newId);
                }
                return Optional.of(produto);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            // Handle SQLException appropriately (e.g., log the exception)
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Produto> atualizar(Produto produto) {
        try (Connection connection = ConnectionPoolManager.getDataSource().getConnection()) {

            String sql = "Update produto p set p.nome = ? , p.descricao = ? , p.categoria_id = ?, p.preco = ? where p.id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, produto.getNome());
            statement.setString(2, produto.getDescricao());
            statement.setLong(3, produto.getCategoria().getId());
            statement.setDouble(4, produto.getPreco());
            statement.setLong(5, produto.getId());

            Integer retUpdate = statement.executeUpdate();

            if (retUpdate > -1) {
                return Optional.of(produto);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Boolean excluir(Produto produto) {
        try (Connection connection = ConnectionPoolManager.getDataSource().getConnection()) {
            String sql = "delete FROM produto where id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, produto.getId());

            if (statement.executeUpdate() == 1) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public Optional<Produto> buscarPorId(Long id) {
        try (Connection connection = ConnectionPoolManager.getDataSource().getConnection()) {
            String sql = "SELECT p.*, c.* FROM produto p, categoria c WHERE p.id = ? and p.categoria_id = c.id";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Produto produto = mapRowToProduto(resultSet);
                return Optional.of(produto);
            }else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<List<Produto>> listarPorCategoria(Categoria categoria) {
        try (Connection connection = ConnectionPoolManager.getDataSource().getConnection()) {
            String sql = "SELECT p.*, c.* FROM produto p, categoria c WHERE p.categoria_id = ? and p.categoria_id = c.id";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, categoria.getId());
            ResultSet resultSet = statement.executeQuery();

            List<Produto> produtosList = new ArrayList<>();
            while (resultSet.next()) {
                Produto produto = mapRowToProduto(resultSet);
                produtosList.add(produto);
            }
            if (produtosList.size() > 0){
                return Optional.of(produtosList);
            }else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<List<Produto>> listarTudo() {
        try (Connection connection = ConnectionPoolManager.getDataSource().getConnection()) {
            String sql = "SELECT p.*, c.* FROM produto p, categoria c where p.categoria_id = c.id";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            List<Produto> produtosList = new ArrayList<>();
            Produto produto = null;
            while (resultSet.next()) {
                produto = new Produto();
                produto = mapRowToProduto(resultSet);
                produtosList.add(produto);
            }
            if (produtosList.size() > 0){
                return Optional.of(produtosList);
            }else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Produto mapRowToProduto(ResultSet resultSet) {
        String nome = null;
        String descricao = null;
        Double preco = null;
        Long categoriaId = null;
        Long prodId = null;
        String nomeCategoria = null;
        String descricaoCategoria = null;

        try {
            prodId = resultSet.getLong("p.id");
            nome = resultSet.getString("p.nome");
            descricao = resultSet.getString("p.descricao");
            categoriaId = resultSet.getLong("p.categoria_id");
            preco = resultSet.getDouble("p.preco");
            descricaoCategoria = resultSet.getString("c.descricao");
            nomeCategoria = resultSet.getString("c.nome");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Categoria cat = new Categoria();
        cat.setId(categoriaId);
        cat.setNome(nomeCategoria);
        cat.setDescricao(descricaoCategoria);

        Produto p = new Produto();
        p.setId(prodId);
        p.setNome(nome);
        p.setDescricao(descricao);
        p.setPreco(preco);
        p.setCategoria(cat);

        return p;
    }

}
