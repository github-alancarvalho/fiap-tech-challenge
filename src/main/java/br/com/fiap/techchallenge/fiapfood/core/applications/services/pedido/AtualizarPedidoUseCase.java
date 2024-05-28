package br.com.fiap.techchallenge.fiapfood.core.applications.services.pedido;


import br.com.fiap.techchallenge.fiapfood.adapter.driven.infra.repositories.DaoFactory;
import br.com.fiap.techchallenge.fiapfood.core.domain.base.StatusPedido;
import br.com.fiap.techchallenge.fiapfood.core.domain.dto.PedidoDto;
import br.com.fiap.techchallenge.fiapfood.core.domain.ports.output.PedidoRepository;

import java.util.Optional;


public class AtualizarPedidoUseCase {

    private PedidoRepository pedidoRepository;


    public AtualizarPedidoUseCase() {
        PedidoRepository pedidoRepository = DaoFactory.getInstance().getPedidoRepositoryORM();
        this.pedidoRepository = pedidoRepository;
    }

    public Optional<PedidoDto> atualizarProgresso(PedidoDto pedidoDto, StatusPedido statusPedido) {
        return this.pedidoRepository.atualizarProgresso(pedidoDto, statusPedido);
    }
}
