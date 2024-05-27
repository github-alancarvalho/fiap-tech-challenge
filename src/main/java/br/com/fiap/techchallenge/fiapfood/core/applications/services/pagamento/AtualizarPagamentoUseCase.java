package br.com.fiap.techchallenge.fiapfood.core.applications.services.pagamento;


import br.com.fiap.techchallenge.fiapfood.adapter.driven.infra.repositories.DaoFactory;
import br.com.fiap.techchallenge.fiapfood.core.domain.base.StatusPagamento;
import br.com.fiap.techchallenge.fiapfood.core.domain.dto.PagamentoORM;
import br.com.fiap.techchallenge.fiapfood.core.domain.ports.output.PagamentoRepositoryORM;

import java.util.Optional;


public class AtualizarPagamentoUseCase {

    private PagamentoRepositoryORM pagamentoRepository;


    public AtualizarPagamentoUseCase() {
        PagamentoRepositoryORM pagamentoRepository = DaoFactory.getInstance().getPagamentoRepositoryORM();
        this.pagamentoRepository = pagamentoRepository;
    }

    public Optional<PagamentoORM> atualizarProgressoPagamento(PagamentoORM pagamentoORM, StatusPagamento status) {        ;
        return this.pagamentoRepository.atualizarStatusPagamento(pagamentoORM, status);
    }

}
