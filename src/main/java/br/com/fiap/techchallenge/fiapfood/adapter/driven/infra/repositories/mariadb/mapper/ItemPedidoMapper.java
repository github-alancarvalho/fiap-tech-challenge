package br.com.fiap.techchallenge.fiapfood.adapter.driven.infra.repositories.mariadb.mapper;

import br.com.fiap.techchallenge.fiapfood.core.domain.dto.ItemPedidoDto;
import br.com.fiap.techchallenge.fiapfood.core.domain.dto.PedidoDto;
import br.com.fiap.techchallenge.fiapfood.core.domain.entities.ItemPedido;
import br.com.fiap.techchallenge.fiapfood.core.domain.entities.Pedido;

import java.util.ArrayList;
import java.util.List;

public class ItemPedidoMapper {

    private ItemPedidoMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static ItemPedidoDto mapToEntity(ItemPedido entity) {
        if (entity == null) {
            return null;
        }

        return new ItemPedidoDto(
                PedidoMapper.mapToEntity(entity.getPedido()),
                ProdutoMapper.mapToEntity(entity.getProduto()),
                entity.getQuantidade()
        );
    }

    public static ItemPedido mapToEntity(ItemPedidoDto itemPedido) {
        if (itemPedido == null) {
            return null;
        }

        return new ItemPedido(
                PedidoMapper.mapToEntity(itemPedido.getPedido()),
                ProdutoMapper.mapToEntity(itemPedido.getProduto()),
                itemPedido.getQuantidade()
        );
    }

    public static List<ItemPedidoDto> mapListToORM(List<ItemPedido> listEntity) {
        List<ItemPedidoDto> list = new ArrayList<>();
        for (ItemPedido itemPedido : listEntity) {
            list.add(ItemPedidoDto.builder()
                    .pedido(PedidoDto.builder().id(itemPedido.getPedido().getId()).build())
                    .produto(ProdutoMapper.mapToEntity(itemPedido.getProduto()))
                    .quantidade(itemPedido.getQuantidade())
                    .build()
            );
        }
        return list;
    }

    public static List<ItemPedido> mapListToEntity(List<ItemPedidoDto> listItemPedidoDto) {
        List<ItemPedido> list = new ArrayList<>();
        for (ItemPedidoDto itemPedido : listItemPedidoDto) {
            ItemPedido itemPedidoEntity = new ItemPedido(
                    PedidoMapper.mapToEntity(itemPedido.getPedido()),
                    ProdutoMapper.mapToEntity(itemPedido.getProduto()),
                    itemPedido.getQuantidade()
            );
            list.add(itemPedidoEntity);
        }
        return list;
    }

    public static List<ItemPedido> mapListaSimplesToEntity(List<ItemPedidoDto> listItemPedidoDto) {
        List<ItemPedido> list = new ArrayList<>();
        for (ItemPedidoDto itemPedido : listItemPedidoDto) {
            Pedido pedidoEntity = new Pedido();
            if(itemPedido.getPedido()!=null && itemPedido.getPedido().getId() != null)
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