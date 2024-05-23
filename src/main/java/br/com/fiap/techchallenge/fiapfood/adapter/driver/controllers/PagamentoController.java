package br.com.fiap.techchallenge.fiapfood.adapter.driver.controllers;

import br.com.fiap.techchallenge.fiapfood.core.applications.services.PagamentoService;
import br.com.fiap.techchallenge.fiapfood.core.applications.services.PedidoService;
import br.com.fiap.techchallenge.fiapfood.core.domain.base.StatusPagamento;
import br.com.fiap.techchallenge.fiapfood.core.domain.base.StatusPedido;
import br.com.fiap.techchallenge.fiapfood.core.domain.entities.Pagamento;
import br.com.fiap.techchallenge.fiapfood.core.domain.entities.Pedido;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/Pagamentos")
public class PagamentoController {

    private final PagamentoService pagamentoService;

    public PagamentoController()  {
        this.pagamentoService =  new PagamentoService();
    }

        @PostMapping("processarPagamento")
    public ResponseEntity processarPagamento(@Valid @RequestParam Long idPedido, @Valid @RequestParam Double valorPedido) {

        if (pagamentoService.processarPagamento(idPedido, valorPedido))
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.badRequest().build();
    }

    @PutMapping("atualizarStatusPagamento")
    public ResponseEntity atualizarStatusPagamento(@Valid @RequestBody Pagamento pagamento, @Valid @RequestParam String novoStatus) {

        if (!pagamentoService.atualizarStatusPagamento(pagamento, StatusPagamento.valueOf(novoStatus)).isEmpty())
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.badRequest().build();
    }

    @GetMapping("listarPagamentos")
    public ResponseEntity<Optional<List<Pagamento>>> listarPagamentos() {
         Optional<List<Pagamento>> lista = pagamentoService.listarPagamentos();

        if (lista.isEmpty())
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.ok(lista);
    }
}

