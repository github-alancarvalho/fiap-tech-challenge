package br.com.fiap.techchallenge.fiapfood.core.applications.ports;

import br.com.fiap.techchallenge.fiapfood.core.domain.base.StatusPedido;
import br.com.fiap.techchallenge.fiapfood.core.domain.entities.Categoria;
import br.com.fiap.techchallenge.fiapfood.core.domain.entities.Pedido;
import br.com.fiap.techchallenge.fiapfood.core.domain.entities.Produto;

import java.util.List;
import java.util.Optional;

public interface PedidoPort {

    Optional<Pedido> inserir(Pedido pedido);

    Optional<Pedido> atualizarProgresso(Pedido pedido, StatusPedido novoStatus);

    Boolean excluir(Pedido pedido);

    Optional<Pedido> buscarPorId(Long id);

    Optional<Pedido> buscarPedidoCompletoPorId(Long id);

    Optional<List<Pedido>> listarTudo();

    Optional<List<Pedido>> listarPedidosPorStatus(StatusPedido status);

    Optional<List<Pedido>> listarPedidosEmAberto();
}