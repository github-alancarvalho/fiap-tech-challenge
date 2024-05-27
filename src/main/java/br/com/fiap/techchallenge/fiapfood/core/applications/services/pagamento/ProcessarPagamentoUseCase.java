package br.com.fiap.techchallenge.fiapfood.core.applications.services.pagamento;


import br.com.fiap.techchallenge.fiapfood.adapter.driven.infra.repositories.DaoFactory;
import br.com.fiap.techchallenge.fiapfood.core.domain.base.StatusPagamento;
import br.com.fiap.techchallenge.fiapfood.core.domain.dto.PagamentoORM;
import br.com.fiap.techchallenge.fiapfood.core.domain.ports.output.PagamentoRepositoryORM;

import java.util.List;
import java.util.Optional;


public class ProcessarPagamentoUseCase {

    private PagamentoRepositoryORM pagamentoRepository;


    public ProcessarPagamentoUseCase() {
        PagamentoRepositoryORM pagamentoRepository = DaoFactory.getInstance().getPagamentoRepositoryORM();
        this.pagamentoRepository = pagamentoRepository;
    }

    public Optional<PagamentoORM> processarPagamento(PagamentoORM pagamentoORM) {
        pagamentoORM.setStatus(StatusPagamento.EM_PROCESSAMENTO);
        return this.pagamentoRepository.processarPagamento(pagamentoORM);
    }

    public Optional<List<PagamentoORM>> buscarTodosPagamentos() {        ;
        return this.pagamentoRepository.listarPagamentos();
    }

}
