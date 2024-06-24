package br.com.fiap.techchallenge.fiapfood.core.domain.base.pedido;


import br.com.fiap.techchallenge.fiapfood.adapter.driven.infra.repositories.DaoFactory;
import br.com.fiap.techchallenge.fiapfood.core.applications.services.pagamento.ProcessarPagamentoUseCase;
import br.com.fiap.techchallenge.fiapfood.core.domain.base.StatusPagamento;
import br.com.fiap.techchallenge.fiapfood.core.domain.dto.ItemPedidoDto;
import br.com.fiap.techchallenge.fiapfood.core.domain.dto.PagamentoDto;
import br.com.fiap.techchallenge.fiapfood.core.domain.dto.PedidoDto;
import br.com.fiap.techchallenge.fiapfood.core.domain.ports.output.PedidoRepository;

import java.util.Optional;


public class CheckoutUseCase {

    private PedidoRepository pedidoRepository;
    private ProcessarPagamentoUseCase processarPagamentoUseCase;


    public CheckoutUseCase() {
        this.pedidoRepository = DaoFactory.getInstance().getPedidoRepositoryORM();
        this.processarPagamentoUseCase = new ProcessarPagamentoUseCase();
    }

    public Optional<PedidoDto> checkout(PedidoDto pedidoDto) {
        Optional<PedidoDto> pedido = this.pedidoRepository.inserir(pedidoDto);

        PagamentoDto pagamento = new PagamentoDto();
        pagamento.setIdPedido(pedido.isPresent() ? pedido.get().getId() : null);
        pagamento.setStatus(StatusPagamento.EM_PROCESSAMENTO);
        pagamento.setValor(calcularValorTotalDoPedido(pedido.isPresent() ? pedido.get() : null));
        processarPagamentoUseCase.processarPagamento(pagamento);

        return pedido;
    }

    public Double calcularValorTotalDoPedido(PedidoDto pedido) {

        Double valorTotalPedido = 0D;
        Double valorProduto;

        for(ItemPedidoDto ip : pedido.getListItens()){
            valorProduto = ip.getProduto().getPreco();
            valorTotalPedido+= valorProduto * ip.getQuantidade();
        }
        return valorTotalPedido;
    }
}
