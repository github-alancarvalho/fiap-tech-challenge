package br.com.fiap.techchallenge.fiapfood.adapter.driven.infra.repositories.mariadb.mapper;

import br.com.fiap.techchallenge.fiapfood.core.domain.dto.PedidoORM;
import br.com.fiap.techchallenge.fiapfood.core.domain.entities.Pedido;

import java.util.ArrayList;
import java.util.List;

public class PedidoMapper {

    public static PedidoORM mapToEntity(Pedido entity) {
        if (entity == null) {
            return null;
        }

        return new PedidoORM(
                entity.getId(),
                ClienteMapper.mapToEntity(entity.getCliente()),
                entity.getStatus(),
                ItemPedidoMapper.mapListToORM(entity.getListItens())
        );
    }

    public static Pedido mapToEntity(PedidoORM pedido) {
        if (pedido == null) {
            return null;
        }

        return new Pedido(
                pedido.getId(),
                ClienteMapper.mapToEntity(pedido.getCliente()),
                pedido.getStatus(),
                ItemPedidoMapper.mapListaSimplesToEntity(pedido.getListItens())
        );
    }

    public static List<PedidoORM> mapListToEntity(List<Pedido> listEntity) {
        List<PedidoORM> list = new ArrayList<>();
        for (Pedido pedido : listEntity) {
            list.add(PedidoORM.builder()
                    .id(pedido.getId())
                    .cliente(ClienteMapper.mapToEntity(pedido.getCliente()))
                    .status(pedido.getStatus())
                    .listItens(ItemPedidoMapper.mapListToORM(pedido.getListItens())).build()
            );
        }
        return list;
    }

}