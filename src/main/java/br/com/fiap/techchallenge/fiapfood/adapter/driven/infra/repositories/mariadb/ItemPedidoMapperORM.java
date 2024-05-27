package br.com.fiap.techchallenge.fiapfood.adapter.driven.infra.repositories.mariadb;

import br.com.fiap.techchallenge.fiapfood.core.domain.dto.ItemPedidoORM;
import br.com.fiap.techchallenge.fiapfood.core.domain.dto.PedidoORM;
import br.com.fiap.techchallenge.fiapfood.core.domain.entities.ItemPedidoEntity;
import br.com.fiap.techchallenge.fiapfood.core.domain.entities.PedidoEntity;

import java.util.ArrayList;
import java.util.List;

public class ItemPedidoMapperORM {

    public static ItemPedidoORM mapToEntity(ItemPedidoEntity entity) {
        if (entity == null) {
            return null;
        }

        return new ItemPedidoORM(
                PedidoMapperORM.mapToEntity(entity.getPedido()),
                ProdutoMapperORM.mapToEntity(entity.getProduto()),
                entity.getQuantidade()
        );
    }

    public static ItemPedidoEntity mapToEntity(ItemPedidoORM itemPedido) {
        if (itemPedido == null) {
            return null;
        }

        return new ItemPedidoEntity(
                PedidoMapperORM.mapToEntity(itemPedido.getPedido()),
                ProdutoMapperORM.mapToEntity(itemPedido.getProduto()),
                itemPedido.getQuantidade()
        );
    }

    public static List<ItemPedidoORM> mapListToORM(List<ItemPedidoEntity> listEntity) {
        List<ItemPedidoORM> list = new ArrayList<>();
        for (ItemPedidoEntity itemPedido : listEntity) {
            list.add(ItemPedidoORM.builder()
                    .pedido(PedidoORM.builder().id(itemPedido.getPedido().getId()).build())
                    .produto(ProdutoMapperORM.mapToEntity(itemPedido.getProduto()))
                    .quantidade(itemPedido.getQuantidade())
                    .build()
            );
        }
        return list;
    }

    public static List<ItemPedidoEntity> mapListToEntity(List<ItemPedidoORM> listItemPedidoORM) {
        List<ItemPedidoEntity> list = new ArrayList<>();
        for (ItemPedidoORM itemPedido : listItemPedidoORM) {
            ItemPedidoEntity itemPedidoEntity = new ItemPedidoEntity(
                    PedidoMapperORM.mapToEntity(itemPedido.getPedido()),
                    ProdutoMapperORM.mapToEntity(itemPedido.getProduto()),
                    itemPedido.getQuantidade()
            );
            list.add(itemPedidoEntity);
        }
        return list;
    }

    public static List<ItemPedidoEntity> mapListaSimplesToEntity(List<ItemPedidoORM> listItemPedidoORM) {
        List<ItemPedidoEntity> list = new ArrayList<>();
        for (ItemPedidoORM itemPedido : listItemPedidoORM) {
            PedidoEntity pedidoEntity = new PedidoEntity();
            pedidoEntity.setId(itemPedido.getPedido().getId());


            ItemPedidoEntity itemPedidoEntity = new ItemPedidoEntity(
                    pedidoEntity,
                    ProdutoMapperORM.mapToEntity(itemPedido.getProduto()),
                    itemPedido.getQuantidade()
            );
            list.add(itemPedidoEntity);
        }
        return list;
    }

}