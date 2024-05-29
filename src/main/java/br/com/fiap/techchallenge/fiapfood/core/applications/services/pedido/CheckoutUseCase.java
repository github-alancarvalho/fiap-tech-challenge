package br.com.fiap.techchallenge.fiapfood.core.applications.services.pedido;


import br.com.fiap.techchallenge.fiapfood.adapter.driven.infra.repositories.DaoFactory;
import br.com.fiap.techchallenge.fiapfood.core.applications.services.pagamento.ProcessarPagamentoUseCase;
import br.com.fiap.techchallenge.fiapfood.core.domain.base.StatusPagamento;
import br.com.fiap.techchallenge.fiapfood.core.domain.dto.ItemPedidoDto;
import br.com.fiap.techchallenge.fiapfood.core.domain.dto.PagamentoDto;
import br.com.fiap.techchallenge.fiapfood.core.domain.dto.PedidoDto;
import br.com.fiap.techchallenge.fiapfood.core.domain.entities.ItemPedido;
import br.com.fiap.techchallenge.fiapfood.core.domain.entities.Pedido;
import br.com.fiap.techchallenge.fiapfood.core.domain.ports.output.PedidoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class CheckoutUseCase {

    private PedidoRepository pedidoRepository;
    private ProcessarPagamentoUseCase processarPagamentoUseCase;


    public CheckoutUseCase() {
        PedidoRepository pedidoRepository = DaoFactory.getInstance().getPedidoRepositoryORM();
        this.pedidoRepository = pedidoRepository;
        this.processarPagamentoUseCase = new ProcessarPagamentoUseCase();
    }

    public Optional<PedidoDto> checkout(PedidoDto pedidoDto) {
//        pedidoDto.setStatus(StatusPedido.RECEBIDO);
        Optional<PedidoDto> pedido = this.pedidoRepository.inserir(pedidoDto);

        PagamentoDto pagamento = new PagamentoDto();
        pagamento.setIdPedido(pedido.get().getId());
        pagamento.setStatus(StatusPagamento.EM_PROCESSAMENTO);
        pagamento.setValor(calcularValorTotalDoPedido(pedido.get()));
        processarPagamentoUseCase.processarPagamento(pagamento);

        return pedido;
    }

    public Double calcularValorTotalDoPedido(PedidoDto pedido) {

        Double valorTotalPedido = 0D;
        Double valorProduto = 0D;

        for(ItemPedidoDto ip : pedido.getListItens()){
            valorProduto = ip.getProduto().getPreco();
            valorTotalPedido+= valorProduto * ip.getQuantidade();
        }
        return valorTotalPedido;
    }
}
