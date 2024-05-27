package br.com.fiap.techchallenge.fiapfood.core.applications.services.pedido;


import br.com.fiap.techchallenge.fiapfood.adapter.driven.infra.repositories.DaoFactory;
import br.com.fiap.techchallenge.fiapfood.core.domain.dto.PedidoORM;
import br.com.fiap.techchallenge.fiapfood.core.domain.ports.output.PedidoRepositoryORM;


public class ExcluirPedidoUseCase {

    private PedidoRepositoryORM pedidoRepository;


    public ExcluirPedidoUseCase() {
        PedidoRepositoryORM pedidoRepository = DaoFactory.getInstance().getPedidoRepositoryORM();
        this.pedidoRepository = pedidoRepository;
    }

    public Boolean excluir(PedidoORM pedido) {        ;
        return this.pedidoRepository.excluir(pedido);
    }


}
