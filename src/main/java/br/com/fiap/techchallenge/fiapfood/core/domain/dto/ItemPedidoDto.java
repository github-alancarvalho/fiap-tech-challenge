package br.com.fiap.techchallenge.fiapfood.core.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ItemPedidoDto {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private PedidoDto pedido;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ProdutoDto produto;

    private int quantidade;

    public ItemPedidoDto() {
    }

    public ItemPedidoDto(PedidoDto pedido, ProdutoDto produto, int quantidade) {
        this.pedido = pedido;
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public PedidoDto getPedido() {
        return pedido;
    }

    public void setPedido(PedidoDto pedido) {
        this.pedido = pedido;
    }

    public ProdutoDto getProduto() {
        return produto;
    }

    public void setProduto(ProdutoDto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
