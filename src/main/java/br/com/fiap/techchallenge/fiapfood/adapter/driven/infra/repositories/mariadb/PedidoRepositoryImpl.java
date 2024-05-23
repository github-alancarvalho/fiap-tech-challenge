package br.com.fiap.techchallenge.fiapfood.adapter.driven.infra.repositories.mariadb;

import br.com.fiap.techchallenge.fiapfood.adapter.driven.infra.repositories.ConnectionPoolManager;
import br.com.fiap.techchallenge.fiapfood.core.applications.ports.PedidoPort;
import br.com.fiap.techchallenge.fiapfood.core.domain.base.StatusPedido;
import br.com.fiap.techchallenge.fiapfood.core.domain.entities.*;
import br.com.fiap.techchallenge.fiapfood.core.domain.valueobject.Cpf;
import br.com.fiap.techchallenge.fiapfood.core.domain.valueobject.Telefone;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class PedidoRepositoryImpl extends ConnectionPoolManager implements PedidoPort {

    public PedidoRepositoryImpl() {

    }

    @Override
    public Optional<Pedido> inserir(Pedido pedido) {
        try (Connection connection = ConnectionPoolManager.getDataSource().getConnection()) {

            String sql = "INSERT INTO pedido (cliente_cpf, status) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, pedido.getCliente().getCpf().getCpfSomenteNumero());
            statement.setString(2, StatusPedido.RECEBIDO.toString());

            Integer retInsert = statement.executeUpdate();

            if (retInsert > -1) {
                ResultSet generatedKeys = statement.getGeneratedKeys();

                if (generatedKeys.next()) {
                    long newId = generatedKeys.getLong(1);
                    pedido.setId(newId);
                    pedido.setStatus(StatusPedido.RECEBIDO);

                    if(pedido.getListItens().size() > 0){

                        String sqlItem = "INSERT INTO item_pedido (pedido_id, produto_id, quantidade) VALUES (?, ?, ?)";
                        PreparedStatement statementItem = null;
                        Integer retInsertItem = null;

                        for(ItemPedido ip : pedido.getListItens()){
                            statementItem = connection.prepareStatement(sqlItem,
                                    Statement.RETURN_GENERATED_KEYS);
                            statementItem.setLong(1, pedido.getId());
                            statementItem.setLong(2, ip.getProduto().getId());
                            statementItem.setInt(3, ip.getQuantidade());

                            retInsertItem = statementItem.executeUpdate();

                            if (retInsertItem > -1) {
                                continue;
                            } else{
                                break;
                            }

                        }
                    }
                }
                return Optional.of(pedido);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Pedido> atualizarProgresso(Pedido pedido, StatusPedido novoStatus) {
        try (Connection connection = ConnectionPoolManager.getDataSource().getConnection()) {

            String sql = "Update pedido p set p.status = ? where p.id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, String.valueOf(novoStatus));
            statement.setLong(2, pedido.getId());

            Integer retUpdate = statement.executeUpdate();

            if (retUpdate > -1) {
                return buscarPorId(pedido.getId());
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Boolean excluir(Pedido pedido) {
        try (Connection connection = ConnectionPoolManager.getDataSource().getConnection()) {
            String sql = "delete FROM pedido where id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, pedido.getId());

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
    public Optional<Pedido> buscarPorId(Long id) {
        try (Connection connection = ConnectionPoolManager.getDataSource().getConnection()) {
            String sql = "SELECT p.*, c.* FROM pedido p, cliente c WHERE p.id = ? and p.cliente_cpf = c.cpf";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Pedido pedido = mapRowToPedido(resultSet);
                return Optional.of(pedido);
            }else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public Optional<Pedido> buscarPedidoCompletoPorId(Long id) {
        try (Connection connection = ConnectionPoolManager.getDataSource().getConnection()) {

            Optional<Pedido> pedidoOptional = buscarPorId(id);

            if (!pedidoOptional.isEmpty()) {

                String sql = "SELECT ip.*, p.*, cat.* FROM item_pedido ip, produto p, categoria cat WHERE ip.pedido_id = ? and ip.produto_id = p.id and p.categoria_id = cat.id";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setLong(1, id);
                ResultSet resultSet = statement.executeQuery();

                ItemPedido ip = null;
                List<ItemPedido> list = new ArrayList<>();

                while (resultSet.next()) {
                    ip = mapRowToItemPedido(resultSet);
                    list.add(ip);
                }
                pedidoOptional.get().setListItens(list);
                resultSet.close();
                return pedidoOptional;
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<List<Pedido>> listarTudo() {
        try (Connection connection = ConnectionPoolManager.getDataSource().getConnection()) {
            String sql = "SELECT p.id FROM pedido p";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            List<Pedido> pedidosList = new ArrayList<>();
            Pedido pedido = null;
            while (resultSet.next()) {
                pedido = new Pedido();
                pedido = buscarPedidoCompletoPorId(resultSet.getLong("p.id")).get();
                pedidosList.add(pedido);
            }
            if (pedidosList.size() > 0){
                return Optional.of(pedidosList);
            }else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

//    @Override
//    public Optional<List<Pedido>> listarTudo() {
//        try (Connection connection = ConnectionPoolManager.getDataSource().getConnection()) {
//            String sql = "SELECT p.*, c.* FROM pedido p, cliente c where p.cliente_cpf = c.cpf";
//            PreparedStatement statement = connection.prepareStatement(sql);
//            ResultSet resultSet = statement.executeQuery();
//
//            List<Pedido> pedidosList = new ArrayList<>();
//            Pedido pedido = null;
//            while (resultSet.next()) {
//                pedido = new Pedido();
//                pedido = mapRowToPedido(resultSet);
//                pedidosList.add(pedido);
//            }
//            if (pedidosList.size() > 0){
//                return Optional.of(pedidosList);
//            }else {
//                return Optional.empty();
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }

//    @Override
//    public Optional<List<Pedido>> listarPedidosPorStatus(StatusPedido status) {
//        try (Connection connection = ConnectionPoolManager.getDataSource().getConnection()) {
//            String sql = "SELECT p.*, c.* FROM pedido p, cliente c WHERE p.status = ? and p.cliente_cpf = c.cpf";
//            PreparedStatement statement = connection.prepareStatement(sql);
//            statement.setString(1, status.toString());
//            ResultSet resultSet = statement.executeQuery();
//
//            List<Pedido> pedidosList = new ArrayList<>();
//            Pedido pedido = null;
//            while (resultSet.next()) {
//                pedido = new Pedido();
//                pedido = mapRowToPedido(resultSet);
//                pedidosList.add(pedido);
//            }
//            if (pedidosList.size() > 0){
//                return Optional.of(pedidosList);
//            }else {
//                return Optional.empty();
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }

    @Override
    public Optional<List<Pedido>> listarPedidosPorStatus(StatusPedido status) {
        try (Connection connection = ConnectionPoolManager.getDataSource().getConnection()) {
            String sql = "SELECT p.id FROM pedido p WHERE (p.status = ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, status.toString());
            ResultSet resultSet = statement.executeQuery();

            List<Pedido> pedidosList = new ArrayList<>();
            Pedido pedido = null;
            while (resultSet.next()) {
                pedido = new Pedido();
                pedido = buscarPedidoCompletoPorId(resultSet.getLong("p.id")).get();
                pedidosList.add(pedido);
            }
            if (pedidosList.size() > 0){
                return Optional.of(pedidosList);
            }else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<List<Pedido>> listarPedidosEmAberto() {
        try (Connection connection = ConnectionPoolManager.getDataSource().getConnection()) {
            String sql = "SELECT p.id FROM pedido p WHERE (p.status = ? OR p.status = ? OR p.status = ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, StatusPedido.RECEBIDO.toString());
            statement.setString(2, StatusPedido.EM_PREPARACAO.toString());
            statement.setString(3, StatusPedido.PRONTO.toString());
            ResultSet resultSet = statement.executeQuery();

            List<Pedido> pedidosList = new ArrayList<>();
            Pedido pedido = null;
            while (resultSet.next()) {
                pedido = new Pedido();
                pedido = buscarPedidoCompletoPorId(resultSet.getLong("p.id")).get();
                pedidosList.add(pedido);
            }
            if (pedidosList.size() > 0){
                return Optional.of(pedidosList);
            }else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Pedido mapRowToPedido(ResultSet resultSet) {

        String status = null;
        Pedido p = new Pedido();
        Cliente cliente = new Cliente();

        try {
            p.setId(resultSet.getLong("p.id"));
            status = resultSet.getString("p.status");

            Cpf cpf = new Cpf(resultSet.getString("p.cliente_cpf"));
            cliente.setCpf(cpf);
            cliente.setNome(resultSet.getString("c.nome"));
            cliente.setEmail(resultSet.getString("c.email"));

            Telefone tel = new Telefone(resultSet.getString("c.telefone"));
            cliente.setTelefone(tel);
            p.setCliente(cliente);

        if(status != null)
            p.setStatus(StatusPedido.valueOf(status));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return p;
    }

    private ItemPedido mapRowToItemPedido(ResultSet resultSet) {

        ItemPedido ip = new ItemPedido();

        try {
                Categoria cat = new Categoria();
                cat.setId(resultSet.getLong("p.categoria_id"));
                cat.setNome(resultSet.getString("cat.nome"));
                cat.setDescricao(resultSet.getString("cat.descricao"));

                Produto prod = new Produto();
                prod.setId(resultSet.getLong("ip.produto_id"));
                prod.setNome(resultSet.getString("p.nome"));
                prod.setDescricao(resultSet.getString("p.descricao"));
                prod.setPreco(resultSet.getDouble("p.preco"));
                prod.setCategoria(cat);

                ip.setProduto(prod);
                ip.setQuantidade(resultSet.getInt("ip.quantidade"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ip;
    }
}
