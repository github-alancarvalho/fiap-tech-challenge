package br.com.fiap.techchallenge.fiapfood.core.domain.ports.output;

import br.com.fiap.techchallenge.fiapfood.core.domain.base.StatusPagamento;
import br.com.fiap.techchallenge.fiapfood.core.domain.dto.PagamentoDto;

import java.util.List;
import java.util.Optional;

public interface PagamentoRepository {


    Optional<PagamentoDto> processarPagamento(PagamentoDto pagamento);

    Optional<PagamentoDto> atualizarStatusPagamento(PagamentoDto pagamento, StatusPagamento status);

    Optional<PagamentoDto> buscarPagamentoPorId(Long id);

    Optional<List<PagamentoDto>> listarPagamentos();
}