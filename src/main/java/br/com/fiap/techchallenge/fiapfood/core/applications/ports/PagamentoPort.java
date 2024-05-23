package br.com.fiap.techchallenge.fiapfood.core.applications.ports;

import br.com.fiap.techchallenge.fiapfood.core.domain.base.StatusPagamento;
import br.com.fiap.techchallenge.fiapfood.core.domain.entities.Pagamento;

import java.util.List;
import java.util.Optional;

public interface PagamentoPort {


    Optional<Pagamento> processarPagamento(Pagamento pagamento);

    Optional<Pagamento> atualizarStatusPagamento(Pagamento pagamento, StatusPagamento status);

    Optional<List<Pagamento>> listarPagamentos();
}