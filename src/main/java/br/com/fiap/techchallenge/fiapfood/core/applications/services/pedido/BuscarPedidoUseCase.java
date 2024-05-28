package br.com.fiap.techchallenge.fiapfood.core.applications.services.pedido;


import br.com.fiap.techchallenge.fiapfood.adapter.driven.infra.repositories.DaoFactory;
import br.com.fiap.techchallenge.fiapfood.core.domain.base.StatusPedido;
import br.com.fiap.techchallenge.fiapfood.core.domain.dto.PedidoDto;
import br.com.fiap.techchallenge.fiapfood.core.domain.ports.output.PedidoRepository;

import java.util.List;
import java.util.Optional;


public class BuscarPedidoUseCase {

    private PedidoRepository pedidoRepository;


    public BuscarPedidoUseCase() {
        PedidoRepository pedidoRepository = DaoFactory.getInstance().getPedidoRepositoryORM();
        this.pedidoRepository = pedidoRepository;
    }

    public Optional<PedidoDto> buscarPedidoPorId(Long id) {        ;
        return this.pedidoRepository.buscarPorId(id);
    }

    public Optional<List<PedidoDto>> buscarTodosPedidos() {        ;
        return this.pedidoRepository.listarTudo();
    }

    public Optional<List<PedidoDto>> buscarPedidosPorStatus(StatusPedido statusPedido) {        ;
        return this.pedidoRepository.listarPedidosPorStatus(statusPedido);
    }

    public Optional<List<PedidoDto>> buscarPedidosEmAberto() {        ;
        return this.pedidoRepository.listarPedidosEmAberto();
    }
}
