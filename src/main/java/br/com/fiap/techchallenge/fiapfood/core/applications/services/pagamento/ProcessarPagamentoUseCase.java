package br.com.fiap.techchallenge.fiapfood.core.applications.services.pagamento;


import br.com.fiap.techchallenge.fiapfood.adapter.driven.infra.repositories.DaoFactory;
import br.com.fiap.techchallenge.fiapfood.core.domain.base.StatusPagamento;
import br.com.fiap.techchallenge.fiapfood.core.domain.entity.Pagamento;
import br.com.fiap.techchallenge.fiapfood.core.domain.ports.output.PagamentoRepository;

import java.util.List;
import java.util.Optional;


public class ProcessarPagamentoUseCase {

    private PagamentoRepository pagamentoRepository;

    public ProcessarPagamentoUseCase() {
        this.pagamentoRepository = DaoFactory.getInstance().getPagamentoRepositoryORM();
    }

    public Optional<Pagamento> processarPagamento(Pagamento pagamento) {
        pagamento.setStatus(StatusPagamento.EM_PROCESSAMENTO);
        return this.pagamentoRepository.processarPagamento(pagamento);
    }

    public Optional<List<Pagamento>> buscarTodosPagamentos() {
        return this.pagamentoRepository.listarPagamentos();
    }

}
