package br.com.fiap.techchallenge.fiapfood.adapter.driven.infra.repositories.mariadb;

import br.com.fiap.techchallenge.fiapfood.adapter.driven.infra.repositories.ConnectionPoolManager;
import br.com.fiap.techchallenge.fiapfood.core.applications.ports.ClientePort;
import br.com.fiap.techchallenge.fiapfood.core.domain.entities.Cliente;
import br.com.fiap.techchallenge.fiapfood.core.domain.entities.Produto;
import br.com.fiap.techchallenge.fiapfood.core.domain.valueobject.Cpf;
import br.com.fiap.techchallenge.fiapfood.core.domain.valueobject.Telefone;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class ClienteRepositoryImpl extends ConnectionPoolManager implements ClientePort {

    public ClienteRepositoryImpl() {

    }

    @Override
    public Optional<Cliente> inserirCliente(Cliente cliente) {
        try (Connection connection = ConnectionPoolManager.getDataSource().getConnection()) {


            String sql = "INSERT INTO cliente (cpf, nome, email, telefone) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, cliente.getCpf().getCpfSomenteNumero());
            statement.setString(2, cliente.getNome());
            statement.setString(3, cliente.getEmail());
            statement.setString(4, cliente.getTelefone().getTelefone());

            Integer retInsert = statement.executeUpdate();

            if (retInsert > -1) {
                return Optional.of(cliente);
            } else {
                return Optional.empty();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Cliente> buscarPorCpf(Cpf cpf) {
        try (Connection connection = ConnectionPoolManager.getDataSource().getConnection()) {
            String sql = "SELECT * FROM cliente WHERE cpf = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, cpf.getCpfSomenteNumero());
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Cliente cliente = mapRowToCliente(resultSet); // Implement method to map result set to Client object
                return Optional.of(cliente);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            // Handle SQLException appropriately (e.g., log the exception)
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Cliente> atualizar(Cliente cliente) {
        try (Connection connection = ConnectionPoolManager.getDataSource().getConnection()) {

            String sql = "Update cliente c set c.nome = ? , c.email = ? , c.telefone = ? where c.cpf = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, cliente.getNome());
            statement.setString(2, cliente.getEmail());
            statement.setString(3, cliente.getTelefone().getTelefone());
            statement.setString(4, cliente.getCpf().getCpfSomenteNumero());


            Integer retUpdate = statement.executeUpdate();

            if (retUpdate > -1) {
                return Optional.of(cliente);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Boolean excluir(Cliente cliente) {
        try (Connection connection = ConnectionPoolManager.getDataSource().getConnection()) {
            String sql = "delete FROM cliente where cpf = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, cliente.getCpf().getCpfSomenteNumero());

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
    public Optional<List<Cliente>> listarTudo() {
        try (Connection connection = ConnectionPoolManager.getDataSource().getConnection()) {
            String sql = "SELECT * FROM cliente";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            List<Cliente> clienteList = new ArrayList<>();
            Cliente cliente = null;
            while (resultSet.next()) {
                cliente = new Cliente();
                cliente = mapRowToCliente(resultSet);
                clienteList.add(cliente);
            }
            if (clienteList.size() > 0){
                return Optional.of(clienteList);
            }else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Cliente mapRowToCliente(ResultSet resultSet) {
        String nome = null;
        String cpf = null;
        String email = null;
        String telefone = null;
        try {
            nome = resultSet.getString("nome");
            cpf = resultSet.getString("cpf");
            email = resultSet.getString("email");
            telefone = resultSet.getString("telefone");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Cliente c = new Cliente();
        c.setCpf(new Cpf(cpf));
        c.setNome(nome);
        c.setEmail(email);
        c.setTelefone(new Telefone(Integer.valueOf(telefone.substring(0, 2)), Integer.valueOf(telefone.substring(2, 11))));
        return c;
    }

}
