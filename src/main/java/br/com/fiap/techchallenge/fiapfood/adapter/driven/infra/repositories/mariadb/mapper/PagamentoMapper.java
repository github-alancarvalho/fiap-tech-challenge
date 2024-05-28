package br.com.fiap.techchallenge.fiapfood.adapter.driven.infra.repositories.mariadb.mapper;

import br.com.fiap.techchallenge.fiapfood.core.domain.dto.PagamentoDto;
import br.com.fiap.techchallenge.fiapfood.core.domain.entities.Pagamento;

import java.util.ArrayList;
import java.util.List;

public class PagamentoMapper {

    public static PagamentoDto mapToEntity(Pagamento entity) {
        if (entity == null) {
            return null;
        }
        return new PagamentoDto(
                entity.getId(),
                entity.getIdPedido(),
                entity.getStatus(),
                entity.getValor()
        );
    }

    public static Pagamento mapToEntity(PagamentoDto pagamento) {
        if (pagamento == null) {
            return null;
        }
        return new Pagamento(
                pagamento.getId(),
                pagamento.getIdPedido(),
                pagamento.getStatus(),
                pagamento.getValor()
        );
    }

    public static List<PagamentoDto> mapListToEntity(List<Pagamento> listEntity) {
        List<PagamentoDto> list = new ArrayList<>();
        for (Pagamento pagamento : listEntity) {
            list.add(PagamentoDto.builder()
                    .id(pagamento.getId())
                    .idPedido(pagamento.getIdPedido())
                    .status(pagamento.getStatus())
                    .valor(pagamento.getValor())
                    .build()
            );
        }
        return list;
    }


}