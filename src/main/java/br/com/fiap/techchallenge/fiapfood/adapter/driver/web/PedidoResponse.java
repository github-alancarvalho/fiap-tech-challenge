package br.com.fiap.techchallenge.fiapfood.adapter.driver.web;

import br.com.fiap.techchallenge.fiapfood.core.domain.base.StatusPedido;
import br.com.fiap.techchallenge.fiapfood.core.domain.dto.ClienteDto;
import br.com.fiap.techchallenge.fiapfood.core.domain.dto.ItemPedidoDto;
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
    private ClienteDto cliente;
    private StatusPedido status;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<ItemPedidoDto> listItens = new ArrayList<>();

    public PedidoResponse() {
    }

    public PedidoResponse(Long id, ClienteDto cliente, StatusPedido status, List<ItemPedidoDto> listItens) {
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

    public ClienteDto getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDto cliente) {
        this.cliente = cliente;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }

    public List<ItemPedidoDto> getListItens() {
        return listItens;
    }

    public void setListItens(List<ItemPedidoDto> listItens) {
        this.listItens = listItens;
    }
}
