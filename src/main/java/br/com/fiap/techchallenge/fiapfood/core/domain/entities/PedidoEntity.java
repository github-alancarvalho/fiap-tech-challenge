package br.com.fiap.techchallenge.fiapfood.core.domain.entities;

import br.com.fiap.techchallenge.fiapfood.core.domain.base.StatusPedido;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.util.ArrayList;
import java.util.List;

@Entity
@Cache(usage = CacheConcurrencyStrategy.NONE)
@Table(name = "pedidoorm")
@NamedQuery(name = "findAllPedidos", query = "SELECT p FROM PedidoEntity p")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PedidoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente_cpf")
    private ClienteEntity cliente;

    @Enumerated(EnumType.STRING)
    private StatusPedido status;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ItemPedidoEntity> listItens = new ArrayList<ItemPedidoEntity>();

    public PedidoEntity() {
    }

    public PedidoEntity(Long id, ClienteEntity cliente, StatusPedido status, List<ItemPedidoEntity> listItens) {
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

    public ClienteEntity getCliente() {
        return cliente;
    }

    public void setCliente(ClienteEntity cliente) {
        this.cliente = cliente;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }

    public List<ItemPedidoEntity> getListItens() {
        return listItens;
    }

    public void setListItens(List<ItemPedidoEntity> listItens) {
        this.listItens = listItens;
    }
}
