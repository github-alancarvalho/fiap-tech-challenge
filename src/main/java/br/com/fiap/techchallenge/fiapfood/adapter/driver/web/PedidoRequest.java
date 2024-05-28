package br.com.fiap.techchallenge.fiapfood.adapter.driver.web;

import br.com.fiap.techchallenge.fiapfood.core.domain.dto.ItemPedidoDto;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class PedidoRequest {

    private Long id;
    private String cpfCliente;
    private String status;
    private List<ItemPedidoDto> listItens = new ArrayList<ItemPedidoDto>();

    public PedidoRequest() {
    }

    public PedidoRequest(Long id, String cpfCliente, String status, List<ItemPedidoDto> listItens) {
        this.id = id;
        this.cpfCliente = cpfCliente;
        this.status = status;
        this.listItens = listItens;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpfCliente() {
        return cpfCliente;
    }

    public void setCpfCliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ItemPedidoDto> getListItens() {
        return listItens;
    }

    public void setListItens(List<ItemPedidoDto> listItens) {
        this.listItens = listItens;
    }
}
