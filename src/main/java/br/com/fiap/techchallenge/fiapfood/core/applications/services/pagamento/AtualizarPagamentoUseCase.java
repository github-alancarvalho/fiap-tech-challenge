package br.com.fiap.techchallenge.fiapfood.core.applications.services.pagamento;


import br.com.fiap.techchallenge.fiapfood.adapter.driven.infra.repositories.DaoFactory;
import br.com.fiap.techchallenge.fiapfood.core.domain.base.StatusPagamento;
import br.com.fiap.techchallenge.fiapfood.core.domain.dto.PagamentoDto;
import br.com.fiap.techchallenge.fiapfood.core.domain.ports.output.PagamentoRepository;

import java.util.Optional;


public class AtualizarPagamentoUseCase {

    private PagamentoRepository pagamentoRepository;

    public AtualizarPagamentoUseCase() {
        this.pagamentoRepository =  DaoFactory.getInstance().getPagamentoRepositoryORM();
    }

    public Optional<PagamentoDto> atualizarProgressoPagamento(PagamentoDto pagamentoDto, StatusPagamento status) {
        return this.pagamentoRepository.atualizarStatusPagamento(pagamentoDto, status);
    }

}
