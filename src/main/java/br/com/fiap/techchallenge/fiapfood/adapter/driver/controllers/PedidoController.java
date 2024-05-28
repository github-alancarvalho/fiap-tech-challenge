package br.com.fiap.techchallenge.fiapfood.adapter.driver.controllers;

import br.com.fiap.techchallenge.fiapfood.adapter.driver.web.PedidoRequest;
import br.com.fiap.techchallenge.fiapfood.adapter.driver.web.PedidoResponse;
import br.com.fiap.techchallenge.fiapfood.core.applications.services.pedido.AtualizarPedidoUseCase;
import br.com.fiap.techchallenge.fiapfood.core.applications.services.pedido.BuscarPedidoUseCase;
import br.com.fiap.techchallenge.fiapfood.core.applications.services.pedido.ExcluirPedidoUseCase;
import br.com.fiap.techchallenge.fiapfood.core.applications.services.pedido.InserirPedidoUseCase;
import br.com.fiap.techchallenge.fiapfood.core.domain.base.StatusPedido;
import br.com.fiap.techchallenge.fiapfood.core.domain.dto.ClienteDto;
import br.com.fiap.techchallenge.fiapfood.core.domain.dto.PedidoDto;
import br.com.fiap.techchallenge.fiapfood.core.domain.valueobject.Cpf;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Tag(name = "Pedido API")
@RestController
@RequestMapping("/api/v1/PedidosORM")
public class PedidoController {

    private final InserirPedidoUseCase inserirPedidoUseCase;
    private final BuscarPedidoUseCase buscarPedidoUseCase;
    private final AtualizarPedidoUseCase atualizarPedidoUseCase;
    private final ExcluirPedidoUseCase excluirPedidoUseCase;

    public PedidoController() {

        this.inserirPedidoUseCase = new InserirPedidoUseCase();
        this.buscarPedidoUseCase = new BuscarPedidoUseCase();
        this.atualizarPedidoUseCase = new AtualizarPedidoUseCase();
        this.excluirPedidoUseCase = new ExcluirPedidoUseCase();
    }

    @Operation(summary = "Inserir Pedido", description = "Inserir novo Pedido")
    @PostMapping("/{inserir}")
    public ResponseEntity<Optional<PedidoResponse>> inserir(@Valid @RequestBody PedidoRequest pedidoRequest) {

        PedidoDto pedido = PedidoDto.builder()
                .cliente(new ClienteDto(new Cpf(pedidoRequest.getCpfCliente()), null, null, null))
                .listItens(pedidoRequest.getListItens())
                .build();

        Optional<PedidoDto> savedPedido = inserirPedidoUseCase.inserir(pedido);
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

    @Operation(summary = "Buscar Pedido por Id", description = "Buscar Pedido por Id")
    @GetMapping("/buscarPedidoPorId")
    public ResponseEntity<Optional<PedidoResponse>> buscarPedidoPorId(@RequestParam("id") Long id) {

        Optional<PedidoDto> savedPedido = buscarPedidoUseCase.buscarPedidoPorId(id);
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

    @Operation(summary = "Alterar pedido", description = "Alterar pedido. Id é mandatório")
    @PutMapping("/{alterarProgresso}")
    public ResponseEntity<Optional<PedidoResponse>> alterar(@Valid @RequestBody PedidoRequest pedidoRequest, @RequestParam("status") String status) {
        PedidoDto pedido = PedidoDto.builder()
                .id(pedidoRequest.getId())
                .status(StatusPedido.valueOf(status))
                .build();

        Optional<PedidoDto> savedPedido = atualizarPedidoUseCase.atualizarProgresso(pedido, StatusPedido.valueOf(status));

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

    @Operation(summary = "Excluir pedido por id", description = "Excluir pedido por id, sem pontuação")
    @DeleteMapping("/{excluir}")
    public ResponseEntity<Optional<Boolean>> excluir(@RequestParam("id") Long id) {
        PedidoDto pedido = PedidoDto.builder()
                .id(id).build();

        if (excluirPedidoUseCase.excluir(pedido))
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.badRequest().build();
    }

    @Operation(summary = "Buscar todos os pedidos", description = "Buscar todos os pedidos")
    @GetMapping("/buscarTudo")
    public ResponseEntity<Optional<List<PedidoResponse>>> buscarTudo() {
        Optional<List<PedidoDto>> pedidos = buscarPedidoUseCase.buscarTodosPedidos();
        if (!pedidos.isEmpty()) {

            List<PedidoResponse> list = new ArrayList<>();
            for (PedidoDto pedido : pedidos.get()) {
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

    @Operation(summary = "Buscar pedidos por status", description = "Buscar pedidos por status")
    @GetMapping("/buscarPedidosPorStatus")
    public ResponseEntity<Optional<List<PedidoResponse>>> buscarPedidosPorStatus(@RequestParam("status") String status) {
        Optional<List<PedidoDto>> pedidos = buscarPedidoUseCase.buscarPedidosPorStatus(
                StatusPedido.valueOf(status));

        if (!pedidos.isEmpty()) {

            List<PedidoResponse> list = new ArrayList<>();
            for (PedidoDto pedido : pedidos.get()) {
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

    @Operation(summary = "Buscar pedidos em aberto", description = "Buscar pedidos em aberto")
    @GetMapping("/buscarPedidosEmAberto")
    public ResponseEntity<Optional<List<PedidoResponse>>> buscarPedidosEmAberto() {
        Optional<List<PedidoDto>> pedidos = buscarPedidoUseCase.buscarPedidosEmAberto();

        if (!pedidos.isEmpty()) {

            List<PedidoResponse> list = new ArrayList<>();
            for (PedidoDto pedido : pedidos.get()) {
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

