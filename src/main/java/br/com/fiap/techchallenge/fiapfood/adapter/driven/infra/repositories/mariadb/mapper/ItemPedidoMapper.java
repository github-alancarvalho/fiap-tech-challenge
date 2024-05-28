package br.com.fiap.techchallenge.fiapfood.adapter.driven.infra.repositories.mariadb.mapper;

import br.com.fiap.techchallenge.fiapfood.core.domain.dto.ItemPedidoORM;
import br.com.fiap.techchallenge.fiapfood.core.domain.dto.PedidoORM;
import br.com.fiap.techchallenge.fiapfood.core.domain.entities.ItemPedido;
import br.com.fiap.techchallenge.fiapfood.core.domain.entities.Pedido;

import java.util.ArrayList;
import java.util.List;

public class ItemPedidoMapper {

    public static ItemPedidoORM mapToEntity(ItemPedido entity) {
        if (entity == null) {
            return null;
        }

        return new ItemPedidoORM(
                PedidoMapper.mapToEntity(entity.getPedido()),
                ProdutoMapper.mapToEntity(entity.getProduto()),
                entity.getQuantidade()
        );
    }

    public static ItemPedido mapToEntity(ItemPedidoORM itemPedido) {
        if (itemPedido == null) {
            return null;
        }

        return new ItemPedido(
                PedidoMapper.mapToEntity(itemPedido.getPedido()),
                ProdutoMapper.mapToEntity(itemPedido.getProduto()),
                itemPedido.getQuantidade()
        );
    }

    public static List<ItemPedidoORM> mapListToORM(List<ItemPedido> listEntity) {
        List<ItemPedidoORM> list = new ArrayList<>();
        for (ItemPedido itemPedido : listEntity) {
            list.add(ItemPedidoORM.builder()
                    .pedido(PedidoORM.builder().id(itemPedido.getPedido().getId()).build())
                    .produto(ProdutoMapper.mapToEntity(itemPedido.getProduto()))
                    .quantidade(itemPedido.getQuantidade())
                    .build()
            );
        }
        return list;
    }

    public static List<ItemPedido> mapListToEntity(List<ItemPedidoORM> listItemPedidoORM) {
        List<ItemPedido> list = new ArrayList<>();
        for (ItemPedidoORM itemPedido : listItemPedidoORM) {
            ItemPedido itemPedidoEntity = new ItemPedido(
                    PedidoMapper.mapToEntity(itemPedido.getPedido()),
                    ProdutoMapper.mapToEntity(itemPedido.getProduto()),
                    itemPedido.getQuantidade()
            );
            list.add(itemPedidoEntity);
        }
        return list;
    }

    public static List<ItemPedido> mapListaSimplesToEntity(List<ItemPedidoORM> listItemPedidoORM) {
        List<ItemPedido> list = new ArrayList<>();
        for (ItemPedidoORM itemPedido : listItemPedidoORM) {
            Pedido pedidoEntity = new Pedido();
            pedidoEntity.setId(itemPedido.getPedido().getId());


            ItemPedido itemPedidoEntity = new ItemPedido(
                    pedidoEntity,
                    ProdutoMapper.mapToEntity(itemPedido.getProduto()),
                    itemPedido.getQuantidade()
            );
            list.add(itemPedidoEntity);
        }
        return list;
    }

}