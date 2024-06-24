package br.com.fiap.techchallenge.fiapfood.core.domain.ports.output;

import br.com.fiap.techchallenge.fiapfood.core.domain.base.StatusPedido;
import br.com.fiap.techchallenge.fiapfood.core.domain.dto.PedidoDto;

import java.util.List;
import java.util.Optional;

public interface PedidoRepository {

    Optional<PedidoDto> inserir(PedidoDto pedido);

    Optional<PedidoDto> atualizarProgresso(PedidoDto pedido, StatusPedido novoStatus);

    Boolean excluir(PedidoDto pedido);

    Optional<PedidoDto> buscarPorId(Long id);

    Optional<List<PedidoDto>> listarTudo();

    Optional<List<PedidoDto>> listarPedidosPorStatus(StatusPedido status);

    Optional<List<PedidoDto>> listarPedidosEmAberto();
}