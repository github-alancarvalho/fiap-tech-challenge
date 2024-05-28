package br.com.fiap.techchallenge.fiapfood.adapter.driven.infra.repositories.mariadb.mapper;

import br.com.fiap.techchallenge.fiapfood.core.domain.dto.PagamentoORM;
import br.com.fiap.techchallenge.fiapfood.core.domain.entities.Pagamento;

import java.util.ArrayList;
import java.util.List;

public class PagamentoMapper {

    public static PagamentoORM mapToEntity(Pagamento entity) {
        if (entity == null) {
            return null;
        }
        return new PagamentoORM(
                entity.getId(),
                entity.getIdPedido(),
                entity.getStatus(),
                entity.getValor()
        );
    }

    public static Pagamento mapToEntity(PagamentoORM pagamento) {
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

    public static List<PagamentoORM> mapListToEntity(List<Pagamento> listEntity) {
        List<PagamentoORM> list = new ArrayList<>();
        for (Pagamento pagamento : listEntity) {
            list.add(PagamentoORM.builder()
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