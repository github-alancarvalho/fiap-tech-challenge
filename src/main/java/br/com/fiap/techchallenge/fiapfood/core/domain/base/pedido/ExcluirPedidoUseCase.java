package br.com.fiap.techchallenge.fiapfood.core.domain.base.pedido;


import br.com.fiap.techchallenge.fiapfood.adapter.driven.infra.repositories.DaoFactory;
import br.com.fiap.techchallenge.fiapfood.core.domain.entity.Pedido;
import br.com.fiap.techchallenge.fiapfood.core.domain.ports.output.PedidoRepository;


public class ExcluirPedidoUseCase {

    private PedidoRepository pedidoRepository;

    public ExcluirPedidoUseCase() {
        this.pedidoRepository = DaoFactory.getInstance().getPedidoRepositoryORM();
    }

    public Boolean excluir(Pedido pedido) {
        return this.pedidoRepository.excluir(pedido);
    }


}
