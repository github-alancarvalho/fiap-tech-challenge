package br.com.fiap.techchallenge.fiapfood.core.applications.services.pedido;


import br.com.fiap.techchallenge.fiapfood.adapter.driven.infra.repositories.DaoFactory;
import br.com.fiap.techchallenge.fiapfood.core.domain.base.StatusPedido;
import br.com.fiap.techchallenge.fiapfood.core.domain.dto.PedidoORM;
import br.com.fiap.techchallenge.fiapfood.core.domain.ports.output.PedidoRepositoryORM;

import java.util.Optional;


public class AtualizarPedidoUseCase {

    private PedidoRepositoryORM pedidoRepository;


    public AtualizarPedidoUseCase() {
        PedidoRepositoryORM pedidoRepository = DaoFactory.getInstance().getPedidoRepositoryORM();
        this.pedidoRepository = pedidoRepository;
    }

    public Optional<PedidoORM> atualizarProgresso(PedidoORM pedidoORM, StatusPedido statusPedido) {
        return this.pedidoRepository.atualizarProgresso(pedidoORM, statusPedido);
    }
}
