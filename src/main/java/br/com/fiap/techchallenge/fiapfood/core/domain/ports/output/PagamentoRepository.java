package br.com.fiap.techchallenge.fiapfood.core.domain.ports.output;

import br.com.fiap.techchallenge.fiapfood.core.domain.base.StatusPagamento;
import br.com.fiap.techchallenge.fiapfood.core.domain.entity.Pagamento;

import java.util.List;
import java.util.Optional;

public interface PagamentoRepository {


    Optional<Pagamento> processarPagamento(Pagamento pagamento);

    Optional<Pagamento> atualizarStatusPagamento(Pagamento pagamento, StatusPagamento status);

    Optional<Pagamento> buscarPagamentoPorId(Long id);

    Optional<List<Pagamento>> listarPagamentos();
}