package br.com.fiap.techchallenge.fiapfood.core.domain.ports.output;

import br.com.fiap.techchallenge.fiapfood.core.domain.base.StatusPagamento;
import br.com.fiap.techchallenge.fiapfood.core.domain.dto.PagamentoORM;

import java.util.List;
import java.util.Optional;

public interface PagamentoRepository {


    Optional<PagamentoORM> processarPagamento(PagamentoORM pagamento);

    Optional<PagamentoORM> atualizarStatusPagamento(PagamentoORM pagamento, StatusPagamento status);

    Optional<PagamentoORM> buscarPagamentoPorId(Long id);

    Optional<List<PagamentoORM>> listarPagamentos();
}