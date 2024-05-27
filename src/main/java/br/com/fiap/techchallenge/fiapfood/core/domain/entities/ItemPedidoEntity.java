package br.com.fiap.techchallenge.fiapfood.core.domain.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;

@Entity
@Table(name = "item_pedidoorm")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ItemPedidoEntity {

    @Id
    @ManyToOne(cascade = CascadeType.PERSIST)
    private PedidoEntity pedido;

    @Id
    @ManyToOne
    private ProdutoEntity produto;

    private int quantidade;

    public ItemPedidoEntity() {
    }

    public ItemPedidoEntity(PedidoEntity pedido, ProdutoEntity produto, int quantidade) {
        this.pedido = pedido;
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public PedidoEntity getPedido() {
        return pedido;
    }

    public void setPedido(PedidoEntity pedido) {
        this.pedido = pedido;
    }

    public ProdutoEntity getProduto() {
        return produto;
    }

    public void setProduto(ProdutoEntity produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
