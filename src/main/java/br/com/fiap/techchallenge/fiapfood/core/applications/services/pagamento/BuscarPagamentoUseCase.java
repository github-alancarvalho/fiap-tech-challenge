package br.com.fiap.techchallenge.fiapfood.core.applications.services.pagamento;


import br.com.fiap.techchallenge.fiapfood.adapter.driven.infra.repositories.DaoFactory;
import br.com.fiap.techchallenge.fiapfood.core.domain.dto.PagamentoORM;
import br.com.fiap.techchallenge.fiapfood.core.domain.ports.output.PagamentoRepository;

import java.util.List;
import java.util.Optional;


public class BuscarPagamentoUseCase {

    private PagamentoRepository pagamentoRepository;


    public BuscarPagamentoUseCase() {
        PagamentoRepository pagamentoRepository = DaoFactory.getInstance().getPagamentoRepositoryORM();
        this.pagamentoRepository = pagamentoRepository;
    }

    public Optional<PagamentoORM> buscarPagamentoPorId(Long id) {        ;
        return this.pagamentoRepository.buscarPagamentoPorId(id);
    }

    public Optional<List<PagamentoORM>> buscarTodosPagamentos() {        ;
        return this.pagamentoRepository.listarPagamentos();
    }

}
