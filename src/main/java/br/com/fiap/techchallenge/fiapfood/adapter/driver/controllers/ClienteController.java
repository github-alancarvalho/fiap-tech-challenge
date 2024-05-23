package br.com.fiap.techchallenge.fiapfood.adapter.driver.controllers;

import br.com.fiap.techchallenge.fiapfood.core.applications.services.ClienteService;
import br.com.fiap.techchallenge.fiapfood.core.domain.entities.Cliente;
import br.com.fiap.techchallenge.fiapfood.core.domain.entities.Produto;
import br.com.fiap.techchallenge.fiapfood.core.domain.valueobject.Cpf;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/Clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController()  {
        ClienteService clienteService = new ClienteService();
        this.clienteService = clienteService;
    }

    @PostMapping("/{inserir}")
    public ResponseEntity<Optional<Cliente>> inserir(@Valid @RequestBody Cliente cliente) {
        Optional<Cliente> savedCliente = clienteService.inserirCliente(cliente);
        return ResponseEntity.of(Optional.ofNullable(savedCliente));
    }

    @PutMapping("/{alterar}")
    public ResponseEntity<Optional<Cliente>> alterar(@Valid @RequestBody Cliente cliente) {
        Optional<Cliente> savedCliente = clienteService.atualizar(cliente);
        return ResponseEntity.ok(savedCliente);
    }

    @DeleteMapping("/{excluir}")
    public ResponseEntity<Optional<Cliente>> excluir(@RequestParam("cpf") String cpf) {
        Cliente cliente = new Cliente();
        Cpf cpfObj = new Cpf(cpf);
        cliente.setCpf(cpfObj);
        if(clienteService.excluir(cliente))
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.badRequest().build();
    }

    @GetMapping("/buscarClientePorCpf")
    public ResponseEntity<Optional<Cliente>> buscarClientePorCpf(@RequestParam("cpf") String cpfString) {
        Cpf cpf = new Cpf(cpfString);
        Optional<Cliente> cliente = clienteService.buscarPorCpf(cpf);
        return ResponseEntity.of(Optional.ofNullable(cliente));
    }

    @GetMapping("/buscarTudo")
    public ResponseEntity<Optional<List<Cliente>>> buscarTudo() {
        Optional<List<Cliente>> listCliente = clienteService.buscarTudo();
        if (listCliente.isEmpty())
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.ok(listCliente);
    }
}

