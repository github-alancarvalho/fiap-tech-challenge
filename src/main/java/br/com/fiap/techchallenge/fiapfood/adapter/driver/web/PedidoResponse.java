package br.com.fiap.techchallenge.fiapfood.adapter.driver.web;

import br.com.fiap.techchallenge.fiapfood.core.domain.base.StatusPedido;
import br.com.fiap.techchallenge.fiapfood.core.domain.dto.ClienteORM;
import br.com.fiap.techchallenge.fiapfood.core.domain.dto.ItemPedidoORM;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PedidoResponse {

    private Long id;
    private ClienteORM cliente;
    private StatusPedido status;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<ItemPedidoORM> listItens = new ArrayList<ItemPedidoORM>();

    public PedidoResponse() {
    }

    public PedidoResponse(Long id, ClienteORM cliente, StatusPedido status, List<ItemPedidoORM> listItens) {
        this.id = id;
        this.cliente = cliente;
        this.status = status;
        this.listItens = listItens;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClienteORM getCliente() {
        return cliente;
    }

    public void setCliente(ClienteORM cliente) {
        this.cliente = cliente;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }

    public List<ItemPedidoORM> getListItens() {
        return listItens;
    }

    public void setListItens(List<ItemPedidoORM> listItens) {
        this.listItens = listItens;
    }
}
