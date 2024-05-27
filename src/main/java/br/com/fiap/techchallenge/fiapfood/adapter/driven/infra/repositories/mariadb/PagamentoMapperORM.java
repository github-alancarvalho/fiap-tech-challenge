package br.com.fiap.techchallenge.fiapfood.adapter.driven.infra.repositories.mariadb;

import br.com.fiap.techchallenge.fiapfood.core.domain.dto.PagamentoORM;
import br.com.fiap.techchallenge.fiapfood.core.domain.entities.PagamentoEntity;

import java.util.ArrayList;
import java.util.List;

public class PagamentoMapperORM {

    public static PagamentoORM mapToEntity(PagamentoEntity entity) {
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

    public static PagamentoEntity mapToEntity(PagamentoORM pagamento) {
        if (pagamento == null) {
            return null;
        }
        return new PagamentoEntity(
                pagamento.getId(),
                pagamento.getIdPedido(),
                pagamento.getStatus(),
                pagamento.getValor()
        );
    }

    public static List<PagamentoORM> mapListToEntity(List<PagamentoEntity> listEntity) {
        List<PagamentoORM> list = new ArrayList<>();
        for (PagamentoEntity pagamento : listEntity) {
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