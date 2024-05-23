package br.com.fiap.techchallenge.fiapfood.core.applications.services;


import br.com.fiap.techchallenge.fiapfood.adapter.driven.infra.repositories.DaoFactory;
import br.com.fiap.techchallenge.fiapfood.core.applications.ports.PagamentoPort;
import br.com.fiap.techchallenge.fiapfood.core.applications.ports.PedidoPort;
import br.com.fiap.techchallenge.fiapfood.core.domain.base.StatusPagamento;
import br.com.fiap.techchallenge.fiapfood.core.domain.base.StatusPedido;
import br.com.fiap.techchallenge.fiapfood.core.domain.entities.Pagamento;
import br.com.fiap.techchallenge.fiapfood.core.domain.entities.Pedido;

import java.util.List;
import java.util.Optional;

public class PagamentoService {

    private final PagamentoPort pagamentoRepository;

    public PagamentoService() {
        this.pagamentoRepository = DaoFactory.getInstance().getPagamentoRepository();
    }

    public Optional<List<Pagamento>> listarPagamentos() {
        return this.pagamentoRepository.listarPagamentos();
    }

    public Boolean processarPagamento(Long idPedido, Double valorPedido) {
        Pagamento pagamento = new Pagamento();
        pagamento.setValor(valorPedido);
        pagamento.setIdPedido(idPedido);

        if(!this.pagamentoRepository.processarPagamento(pagamento).isEmpty())
            return true;
        else
            return false;
    }

    public Optional<Pagamento> atualizarStatusPagamento(Pagamento pagamento, StatusPagamento status) {
        return this.pagamentoRepository.atualizarStatusPagamento(pagamento, status);
    }

}
