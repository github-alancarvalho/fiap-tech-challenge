package br.com.fiap.techchallenge.fiapfood.core.applications.services.pagamento;


import br.com.fiap.techchallenge.fiapfood.adapter.driven.infra.repositories.DaoFactory;
import br.com.fiap.techchallenge.fiapfood.core.domain.dto.PagamentoDto;
import br.com.fiap.techchallenge.fiapfood.core.domain.ports.output.PagamentoRepository;

import java.util.List;
import java.util.Optional;


public class BuscarPagamentoUseCase {

    private PagamentoRepository pagamentoRepository;


    public BuscarPagamentoUseCase() {
        PagamentoRepository pagamentoRepository = DaoFactory.getInstance().getPagamentoRepositoryORM();
        this.pagamentoRepository = pagamentoRepository;
    }

    public Optional<PagamentoDto> buscarPagamentoPorId(Long id) {        ;
        return this.pagamentoRepository.buscarPagamentoPorId(id);
    }

    public Optional<List<PagamentoDto>> buscarTodosPagamentos() {        ;
        return this.pagamentoRepository.listarPagamentos();
    }

}
