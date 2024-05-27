package br.com.fiap.techchallenge.fiapfood.adapter.driver.web;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PagamentoRequest {

    @NotEmpty(message = "Id não pode ser vazio")
    @NotNull(message = "Id não pode ser nulo")
    @Schema(description = "Id do Pagamento,", example = "123")
    private Long id;

    @NotEmpty(message = "Id do Pedido não pode ser vazio")
    @NotNull(message = "Id do Pedido não pode ser nulo")
    @Schema(description = "Código do Pedido,", example = "788")
    private Long idPedido;

    @NotEmpty(message = "Status do Pagamento não pode ser vazio")
    @NotNull(message = "Status do Pagamento não pode ser nulo")
    @Schema(description = "Status do Pagamento do cliente,", example = "joao@teste.com.br")
    @Email
    private String status;

    @NotEmpty(message = "Valor não pode ser vazio")
    @NotNull(message = "Valor não pode ser nulo")
    @Schema(description = "Valor total do Pagamento,", example = "181.59")
    private Double valor;


    public PagamentoRequest() {
    }

    public PagamentoRequest(Long id, Long idPedido, String status, Double valor) {
        this.id = id;
        this.idPedido = idPedido;
        this.status = status;
        this.valor = valor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Long idPedido) {
        this.idPedido = idPedido;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
