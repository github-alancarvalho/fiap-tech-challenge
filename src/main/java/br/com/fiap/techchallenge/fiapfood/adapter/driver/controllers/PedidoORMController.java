package br.com.fiap.techchallenge.fiapfood.adapter.driver.controllers;

import br.com.fiap.techchallenge.fiapfood.adapter.driver.web.PedidoRequest;
import br.com.fiap.techchallenge.fiapfood.adapter.driver.web.PedidoResponse;
import br.com.fiap.techchallenge.fiapfood.core.applications.services.pedido.AtualizarPedidoUseCase;
import br.com.fiap.techchallenge.fiapfood.core.applications.services.pedido.BuscarPedidoUseCase;
import br.com.fiap.techchallenge.fiapfood.core.applications.services.pedido.ExcluirPedidoUseCase;
import br.com.fiap.techchallenge.fiapfood.core.applications.services.pedido.InserirPedidoUseCase;
import br.com.fiap.techchallenge.fiapfood.core.domain.base.StatusPedido;
import br.com.fiap.techchallenge.fiapfood.core.domain.dto.CategoriaORM;
import br.com.fiap.techchallenge.fiapfood.core.domain.dto.ClienteORM;
import br.com.fiap.techchallenge.fiapfood.core.domain.dto.PedidoORM;
import br.com.fiap.techchallenge.fiapfood.core.domain.entities.Pedido;
import br.com.fiap.techchallenge.fiapfood.core.domain.valueobject.Cpf;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1/PedidosORM")
public class PedidoORMController {

    private final InserirPedidoUseCase inserirPedidoUseCase;
    private final BuscarPedidoUseCase buscarPedidoUseCase;
    private final AtualizarPedidoUseCase atualizarPedidoUseCase;
    private final ExcluirPedidoUseCase excluirPedidoUseCase;

    public PedidoORMController() {

        this.inserirPedidoUseCase = new InserirPedidoUseCase();
        this.buscarPedidoUseCase = new BuscarPedidoUseCase();
        this.atualizarPedidoUseCase = new AtualizarPedidoUseCase();
        this.excluirPedidoUseCase = new ExcluirPedidoUseCase();
    }

    @PostMapping("/{inserir}")
    public ResponseEntity<Optional<PedidoResponse>> inserir(@Valid @RequestBody PedidoRequest pedidoRequest) {

        PedidoORM pedido = PedidoORM.builder()
                .cliente(new ClienteORM(new Cpf(pedidoRequest.getCpfCliente()), null, null, null))
                .listItens(pedidoRequest.getListItens())
                .build();

        Optional<PedidoORM> savedPedido = inserirPedidoUseCase.inserir(pedido);
        if (!savedPedido.isEmpty()) {
            PedidoResponse response = PedidoResponse.builder()
                    .id(savedPedido.get().getId())
                    .cliente(savedPedido.get().getCliente())
                    .status(savedPedido.get().getStatus())
                    .listItens(savedPedido.get().getListItens())
                    .build();

            return ResponseEntity.ok(Optional.ofNullable(response));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/buscarPedidoPorId")
    public ResponseEntity<Optional<PedidoResponse>> buscarPedidoPorId(@RequestParam("id") Long id) {

        Optional<PedidoORM> savedPedido = buscarPedidoUseCase.buscarPedidoPorId(id);
        if (!savedPedido.isEmpty()) {
            PedidoResponse response = PedidoResponse.builder()
                    .id(savedPedido.get().getId())
                    .cliente(savedPedido.get().getCliente())
                    .status(savedPedido.get().getStatus())
                    .listItens(savedPedido.get().getListItens())
                    .build();

            return ResponseEntity.ok(Optional.ofNullable(response));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{alterarProgresso}")
    public ResponseEntity<Optional<PedidoResponse>> alterar(@Valid @RequestBody PedidoRequest pedidoRequest, @RequestParam("status") String status) {
        PedidoORM pedido = PedidoORM.builder()
                .id(pedidoRequest.getId())
                .status(StatusPedido.valueOf(status))
                .build();

        Optional<PedidoORM> savedPedido = atualizarPedidoUseCase.atualizarProgresso(pedido, StatusPedido.valueOf(status));

        if (!savedPedido.isEmpty()) {
            PedidoResponse response = PedidoResponse.builder()
                    .id(savedPedido.get().getId())
                    .cliente(savedPedido.get().getCliente())
                    .status(savedPedido.get().getStatus())
                    .listItens(savedPedido.get().getListItens())
                    .build();

            return ResponseEntity.ok(Optional.ofNullable(response));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{excluir}")
    public ResponseEntity<Optional<Pedido>> excluir(@RequestParam("id") Long id) {
        PedidoORM pedido = PedidoORM.builder()
                .id(id).build();

        if (excluirPedidoUseCase.excluir(pedido))
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.badRequest().build();
    }

    @GetMapping("/buscarTudo")
    public ResponseEntity<Optional<List<PedidoResponse>>> buscarTudo() {
        Optional<List<PedidoORM>> pedidos = buscarPedidoUseCase.buscarTodosPedidos();
        if (!pedidos.isEmpty()) {

            List<PedidoResponse> list = new ArrayList<>();
            for (PedidoORM pedido : pedidos.get()) {
                PedidoResponse response = PedidoResponse.builder()
                        .id(pedido.getId())
                        .cliente(pedido.getCliente())
                        .status(pedido.getStatus())
                        .listItens(pedido.getListItens())
                        .build();
                list.add(response);
            }
            return ResponseEntity.ok(Optional.ofNullable(list));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/buscarPedidosPorStatus")
    public ResponseEntity<Optional<List<PedidoResponse>>> buscarPedidosPorStatus(@RequestParam("status") String status) {
        Optional<List<PedidoORM>> pedidos = buscarPedidoUseCase.buscarPedidosPorStatus(
                StatusPedido.valueOf(status));

        if (!pedidos.isEmpty()) {

            List<PedidoResponse> list = new ArrayList<>();
            for (PedidoORM pedido : pedidos.get()) {
                PedidoResponse response = PedidoResponse.builder()
                        .id(pedido.getId())
                        .cliente(pedido.getCliente())
                        .status(pedido.getStatus())
                        .listItens(pedido.getListItens())
                        .build();
                list.add(response);
            }
            if (list.size() > 0)
                return ResponseEntity.ok(Optional.ofNullable(list));
            else
                return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/buscarPedidosEmAberto")
    public ResponseEntity<Optional<List<PedidoResponse>>> buscarPedidosEmAberto() {
        Optional<List<PedidoORM>> pedidos = buscarPedidoUseCase.buscarPedidosEmAberto();

        if (!pedidos.isEmpty()) {

            List<PedidoResponse> list = new ArrayList<>();
            for (PedidoORM pedido : pedidos.get()) {
                PedidoResponse response = PedidoResponse.builder()
                        .id(pedido.getId())
                        .cliente(pedido.getCliente())
                        .status(pedido.getStatus())
                        .listItens(pedido.getListItens())
                        .build();
                list.add(response);
            }
            if (list.size() > 0)
                return ResponseEntity.ok(Optional.ofNullable(list));
            else
                return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

