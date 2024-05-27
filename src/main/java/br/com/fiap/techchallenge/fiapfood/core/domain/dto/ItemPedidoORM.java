package br.com.fiap.techchallenge.fiapfood.core.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ItemPedidoORM {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private PedidoORM pedido;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ProdutoORM produto;

    private int quantidade;

    public ItemPedidoORM() {
    }

    public ItemPedidoORM(PedidoORM pedido, ProdutoORM produto, int quantidade) {
        this.pedido = pedido;
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public PedidoORM getPedido() {
        return pedido;
    }

    public void setPedido(PedidoORM pedido) {
        this.pedido = pedido;
    }

    public ProdutoORM getProduto() {
        return produto;
    }

    public void setProduto(ProdutoORM produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
