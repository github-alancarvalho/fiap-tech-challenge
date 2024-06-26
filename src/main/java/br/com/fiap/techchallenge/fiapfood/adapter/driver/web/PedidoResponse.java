package br.com.fiap.techchallenge.fiapfood.adapter.driver.web;

import br.com.fiap.techchallenge.fiapfood.core.domain.base.StatusPedido;
import br.com.fiap.techchallenge.fiapfood.core.domain.entity.Cliente;
import br.com.fiap.techchallenge.fiapfood.core.domain.entity.ItemPedido;
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
    private Cliente cliente;
    private StatusPedido status;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<ItemPedido> listItens = new ArrayList<>();

    public PedidoResponse() {
    }

    public PedidoResponse(Long id, Cliente cliente, StatusPedido status, List<ItemPedido> listItens) {
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }

    public List<ItemPedido> getListItens() {
        return listItens;
    }

    public void setListItens(List<ItemPedido> listItens) {
        this.listItens = listItens;
    }
}
