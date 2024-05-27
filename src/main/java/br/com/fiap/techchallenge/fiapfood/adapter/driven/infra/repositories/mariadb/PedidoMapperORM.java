package br.com.fiap.techchallenge.fiapfood.adapter.driven.infra.repositories.mariadb;

import br.com.fiap.techchallenge.fiapfood.core.domain.dto.PedidoORM;
import br.com.fiap.techchallenge.fiapfood.core.domain.entities.PedidoEntity;

import java.util.ArrayList;
import java.util.List;

public class PedidoMapperORM {

    public static PedidoORM mapToEntity(PedidoEntity entity) {
        if (entity == null) {
            return null;
        }

        return new PedidoORM(
                entity.getId(),
                ClienteMapperORM.mapToEntity(entity.getCliente()),
                entity.getStatus(),
                ItemPedidoMapperORM.mapListToORM(entity.getListItens())
        );
    }

    public static PedidoEntity mapToEntity(PedidoORM pedido) {
        if (pedido == null) {
            return null;
        }

        return new PedidoEntity(
                pedido.getId(),
                ClienteMapperORM.mapToEntity(pedido.getCliente()),
                pedido.getStatus(),
                ItemPedidoMapperORM.mapListaSimplesToEntity(pedido.getListItens())
        );
    }

    public static List<PedidoORM> mapListToEntity(List<PedidoEntity> listEntity) {
        List<PedidoORM> list = new ArrayList<>();
        for (PedidoEntity pedido : listEntity) {
            list.add(PedidoORM.builder()
                    .id(pedido.getId())
                    .cliente(ClienteMapperORM.mapToEntity(pedido.getCliente()))
                    .status(pedido.getStatus())
                    .listItens(ItemPedidoMapperORM.mapListToORM(pedido.getListItens())).build()
            );
        }
        return list;
    }

}