package br.com.fiap.techchallenge.fiapfood.core.domain.ports.output;

import br.com.fiap.techchallenge.fiapfood.core.domain.base.StatusPedido;
import br.com.fiap.techchallenge.fiapfood.core.domain.dto.PedidoORM;

import java.util.List;
import java.util.Optional;

public interface PedidoRepository {

    Optional<PedidoORM> inserir(PedidoORM pedido);

    Optional<PedidoORM> atualizarProgresso(PedidoORM pedido, StatusPedido novoStatus);

    Boolean excluir(PedidoORM pedido);

    Optional<PedidoORM> buscarPorId(Long id);

//    Optional<PedidoORM> buscarPedidoCompletoPorId(Long id);

    Optional<List<PedidoORM>> listarTudo();

    Optional<List<PedidoORM>> listarPedidosPorStatus(StatusPedido status);

    Optional<List<PedidoORM>> listarPedidosEmAberto();
}