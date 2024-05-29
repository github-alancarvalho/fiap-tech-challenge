package br.com.fiap.techchallenge.fiapfood.adapter.driver.controllers;

import br.com.fiap.techchallenge.fiapfood.adapter.driver.web.ClienteRequest;
import br.com.fiap.techchallenge.fiapfood.adapter.driver.web.ClienteResponse;
import br.com.fiap.techchallenge.fiapfood.core.applications.services.cliente.AtualizarClienteUseCase;
import br.com.fiap.techchallenge.fiapfood.core.applications.services.cliente.BuscarClienteUseCase;
import br.com.fiap.techchallenge.fiapfood.core.applications.services.cliente.ExcluirClienteUseCase;
import br.com.fiap.techchallenge.fiapfood.core.applications.services.cliente.InserirClienteUseCase;
import br.com.fiap.techchallenge.fiapfood.core.domain.dto.ClienteDto;
import br.com.fiap.techchallenge.fiapfood.core.domain.valueobject.Cpf;
import br.com.fiap.techchallenge.fiapfood.core.domain.valueobject.Telefone;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Tag(name = "Cliente API")
@RestController
@RequestMapping("/api/v1/ClientesORM")
public class ClienteController {

    private final InserirClienteUseCase inserirClienteUseCase;
    private final BuscarClienteUseCase buscarClienteUseCase;
    private final AtualizarClienteUseCase atualizarClienteUseCase;
    private final ExcluirClienteUseCase excluirClienteUseCase;

    public ClienteController() {

        this.inserirClienteUseCase = new InserirClienteUseCase();
        this.buscarClienteUseCase = new BuscarClienteUseCase();
        this.atualizarClienteUseCase = new AtualizarClienteUseCase();
        this.excluirClienteUseCase = new ExcluirClienteUseCase();
    }

    @Operation(summary = "Inserir Cliente", description = "Inserir novo Cliente")
    @PostMapping("/{inserir}")
    public ResponseEntity<Optional<ClienteResponse>> inserir(@Valid @RequestBody ClienteRequest clienteRequest) {

        ClienteDto cliente = ClienteDto.builder()
                .cpf(new Cpf(clienteRequest.getCpf()))
                .nome(clienteRequest.getNome())
                .email(clienteRequest.getEmail())
                .telefone(new Telefone(clienteRequest.getTelefone())).build();

        Optional<ClienteDto> savedProduto = inserirClienteUseCase.inserirClienteORM(cliente);

        if (!savedProduto.isEmpty()) {
            ClienteResponse response = ClienteResponse.builder()
                    .cpf(savedProduto.get().getCpf().getCpfSomenteNumero())
                    .nome(savedProduto.get().getNome())
                    .email(savedProduto.get().getEmail())
                    .telefone(savedProduto.get().getTelefone().getTelefone()).build();

            return ResponseEntity.ok(Optional.ofNullable(response));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Buscar Cliente por Cpf", description = "Buscar Cliente por Cpf")
    @GetMapping("/buscarProdutoPorCpf")
    public ResponseEntity<Optional<ClienteResponse>> buscarProdutoPorCpf(@RequestParam("cpf") String cpf) {

        Optional<ClienteDto> cliente = buscarClienteUseCase.buscarClientePorCpfORM(new Cpf(cpf));
        if (!cliente.isEmpty()) {
            ClienteResponse response = ClienteResponse.builder()
                    .cpf(cliente.get().getCpf().getCpfSomenteNumero())
                    .nome(cliente.get().getNome())
                    .email(cliente.get().getEmail())
                    .telefone(cliente.get().getTelefone().getTelefone()).build();

            return ResponseEntity.ok(Optional.ofNullable(response));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Alterar cliente", description = "Alterar Cliente. Cpf é mandatório")
    @PutMapping("/{alterar}")
    public ResponseEntity<Optional<ClienteResponse>> alterar(@Valid @RequestBody ClienteRequest clienteRequest) {
        ClienteDto cliente = ClienteDto.builder()
                .cpf(new Cpf(clienteRequest.getCpf()))
                .nome(clienteRequest.getNome())
                .email(clienteRequest.getEmail())
                .telefone(new Telefone(clienteRequest.getTelefone())).build();

        Optional<ClienteDto> savedProduto = atualizarClienteUseCase.atualizar(cliente);

        if (!savedProduto.isEmpty()) {
            ClienteResponse response = ClienteResponse.builder()
                    .cpf(savedProduto.get().getCpf().getCpfSomenteNumero())
                    .nome(savedProduto.get().getNome())
                    .email(savedProduto.get().getEmail())
                    .telefone(savedProduto.get().getTelefone().getTelefone()).build();

            return ResponseEntity.ok(Optional.ofNullable(response));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Excluir Cliente por Cpf", description = "Excluir Cliente por Cpf, sem pontuação")
    @DeleteMapping("/{excluir}")
    public ResponseEntity<Optional<Boolean>> excluir(@RequestParam("cpf") String cpf) {

        if(excluirClienteUseCase.excluir(new Cpf(cpf)))
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.badRequest().build();
    }

    @Operation(summary = "Buscar todos os clientes", description = "Buscar todos os clientes")
    @GetMapping("/buscarTudo")
    public ResponseEntity<Optional<List<ClienteResponse>>> buscarTudo() {
        Optional<List<ClienteDto>> clientes = buscarClienteUseCase.buscarTodosClientes();
        if (!clientes.isEmpty()) {

            List<ClienteResponse> list = new ArrayList<>();
            for (ClienteDto cliente : clientes.get()) {
                ClienteResponse response = ClienteResponse.builder()
                        .cpf(cliente.getCpf().getCpfSomenteNumero())
                        .nome(cliente.getNome())
                        .email(cliente.getEmail())
                        .telefone(cliente.getTelefone().getTelefone()).build();
                list.add(response);
            }
            return ResponseEntity.ok(Optional.ofNullable(list));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
