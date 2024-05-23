package br.com.fiap.techchallenge.fiapfood.adapter.driven.infra.repositories.mariadb;

import br.com.fiap.techchallenge.fiapfood.adapter.driven.infra.repositories.ConnectionPoolManager;
import br.com.fiap.techchallenge.fiapfood.core.applications.ports.PagamentoPort;
import br.com.fiap.techchallenge.fiapfood.core.domain.base.StatusPagamento;
import br.com.fiap.techchallenge.fiapfood.core.domain.entities.Pagamento;
import br.com.fiap.techchallenge.fiapfood.core.domain.entities.Pedido;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class PagamentoRepositoryImpl extends ConnectionPoolManager implements PagamentoPort {

    public PagamentoRepositoryImpl() {

    }

    @Override
    public Optional<Pagamento> processarPagamento(Pagamento pagamento) {
        try (Connection connection = ConnectionPoolManager.getDataSource().getConnection()) {

            String sql = "INSERT INTO pagamento (pedido_id, status, valor) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, pagamento.getIdPedido());
            statement.setString(2, StatusPagamento.EM_PROCESSAMENTO.toString());
            statement.setDouble(3, pagamento.getValor());

            Integer retInsert = statement.executeUpdate();

            if (retInsert > -1) {
                ResultSet generatedKeys = statement.getGeneratedKeys();

                if (generatedKeys.next()) {
                    long newId = generatedKeys.getLong(1);
                    pagamento.setId(newId);
                }
                return Optional.of(pagamento);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Pagamento> atualizarStatusPagamento(Pagamento pagamento, StatusPagamento status) {
        try (Connection connection = ConnectionPoolManager.getDataSource().getConnection()) {

            String sql = "Update pagamento p set p.status = ? where p.id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, status.toString());
            statement.setLong(2, pagamento.getId());

            Integer retUpdate = statement.executeUpdate();

            if (retUpdate > -1) {
                return Optional.of(pagamento);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public Optional<List<Pagamento>> listarPagamentos() {
        try (Connection connection = ConnectionPoolManager.getDataSource().getConnection()) {
            String sql = "SELECT p.* FROM pagamento p";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            List<Pagamento> pagamentosList = new ArrayList<>();
            Pagamento pagamento = null;
            while (resultSet.next()) {
                pagamento = new Pagamento();
                pagamento = mapRowToPagamento(resultSet);
                pagamentosList.add(pagamento);
            }
            if (pagamentosList.size() > 0){
                return Optional.of(pagamentosList);
            }else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Pagamento mapRowToPagamento(ResultSet resultSet) {

        Pagamento pagamento = new Pagamento();
        try {
            pagamento.setId(resultSet.getLong("p.id"));
            pagamento.setValor(resultSet.getDouble("p.valor"));
            pagamento.setStatus(StatusPagamento.valueOf(resultSet.getString("p.status")));

            pagamento.setIdPedido(resultSet.getLong("p.pedido_id"));

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pagamento;
    }

}
