package br.com.fiap.techchallenge.fiapfood.adapter.driver.controllers;

import br.com.fiap.techchallenge.fiapfood.adapter.driver.web.ClienteRequest;
import br.com.fiap.techchallenge.fiapfood.adapter.driver.web.ClienteResponse;
import br.com.fiap.techchallenge.fiapfood.core.applications.services.cliente.AtualizarClienteUseCase;
import br.com.fiap.techchallenge.fiapfood.core.applications.services.cliente.BuscarClienteUseCase;
import br.com.fiap.techchallenge.fiapfood.core.applications.services.cliente.ExcluirClienteUseCase;
import br.com.fiap.techchallenge.fiapfood.core.applications.services.cliente.InserirClienteUseCase;
import br.com.fiap.techchallenge.fiapfood.core.domain.dto.ClienteORM;
import br.com.fiap.techchallenge.fiapfood.core.domain.dto.ProdutoORM;
import br.com.fiap.techchallenge.fiapfood.core.domain.valueobject.Cpf;
import br.com.fiap.techchallenge.fiapfood.core.domain.valueobject.Telefone;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1/ClientesORM")
public class ClienteORMController {

    private final InserirClienteUseCase inserirClienteUseCase;
    private final BuscarClienteUseCase buscarClienteUseCase;
    private final AtualizarClienteUseCase atualizarClienteUseCase;
    private final ExcluirClienteUseCase excluirClienteUseCase;

    public ClienteORMController() {

        this.inserirClienteUseCase = new InserirClienteUseCase();
        this.buscarClienteUseCase = new BuscarClienteUseCase();
        this.atualizarClienteUseCase = new AtualizarClienteUseCase();
        this.excluirClienteUseCase = new ExcluirClienteUseCase();
    }

    @PostMapping("/{inserir}")
    public ResponseEntity<Optional<ClienteResponse>> inserir(@Valid @RequestBody ClienteRequest clienteRequest) {

        ClienteORM cliente = ClienteORM.builder()
                .cpf(new Cpf(clienteRequest.getCpf()))
                .nome(clienteRequest.getNome())
                .email(clienteRequest.getEmail())
                .telefone(new Telefone(clienteRequest.getTelefone())).build();

        Optional<ClienteORM> savedProduto = inserirClienteUseCase.inserirClienteORM(cliente);

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

    @GetMapping("/buscarProdutoPorCpf")
    public ResponseEntity<Optional<ClienteResponse>> buscarProdutoPorCpf(@RequestParam("cpf") String cpf) {

        Optional<ClienteORM> cliente = buscarClienteUseCase.buscarClientePorCpfORM(new Cpf(cpf));
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

    @PutMapping("/{alterar}")
    public ResponseEntity<Optional<ClienteResponse>> alterar(@Valid @RequestBody ClienteRequest clienteRequest) {
        ClienteORM cliente = ClienteORM.builder()
                .cpf(new Cpf(clienteRequest.getCpf()))
                .nome(clienteRequest.getNome())
                .email(clienteRequest.getEmail())
                .telefone(new Telefone(clienteRequest.getTelefone())).build();

        Optional<ClienteORM> savedProduto = atualizarClienteUseCase.atualizar(cliente);

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

    @DeleteMapping("/{excluir}")
    public ResponseEntity<Optional<Boolean>> excluir(@RequestParam("cpf") String cpf) {

        if(excluirClienteUseCase.excluir(new Cpf(cpf)))
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.badRequest().build();
    }

    @GetMapping("/buscarTudo")
    public ResponseEntity<Optional<List<ClienteResponse>>> buscarTudo() {
        Optional<List<ClienteORM>> clientes = buscarClienteUseCase.buscarTodosClientes();
        if (!clientes.isEmpty()) {

            List<ClienteResponse> list = new ArrayList<>();
            for (ClienteORM cliente : clientes.get()) {
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

