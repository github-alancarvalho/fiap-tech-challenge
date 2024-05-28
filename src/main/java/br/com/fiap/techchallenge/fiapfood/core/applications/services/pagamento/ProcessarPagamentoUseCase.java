package br.com.fiap.techchallenge.fiapfood.core.applications.services.pagamento;


import br.com.fiap.techchallenge.fiapfood.adapter.driven.infra.repositories.DaoFactory;
import br.com.fiap.techchallenge.fiapfood.core.domain.base.StatusPagamento;
import br.com.fiap.techchallenge.fiapfood.core.domain.dto.PagamentoDto;
import br.com.fiap.techchallenge.fiapfood.core.domain.ports.output.PagamentoRepository;

import java.util.List;
import java.util.Optional;


public class ProcessarPagamentoUseCase {

    private PagamentoRepository pagamentoRepository;


    public ProcessarPagamentoUseCase() {
        PagamentoRepository pagamentoRepository = DaoFactory.getInstance().getPagamentoRepositoryORM();
        this.pagamentoRepository = pagamentoRepository;
    }

    public Optional<PagamentoDto> processarPagamento(PagamentoDto pagamentoDto) {
        pagamentoDto.setStatus(StatusPagamento.EM_PROCESSAMENTO);
        return this.pagamentoRepository.processarPagamento(pagamentoDto);
    }

    public Optional<List<PagamentoDto>> buscarTodosPagamentos() {        ;
        return this.pagamentoRepository.listarPagamentos();
    }

}
