package br.com.fiap.techchallenge.fiapfood.adapter.driver.controllers;

import br.com.fiap.techchallenge.fiapfood.core.applications.services.PedidoService;
import br.com.fiap.techchallenge.fiapfood.core.domain.base.StatusPedido;
import br.com.fiap.techchallenge.fiapfood.core.domain.entities.Categoria;
import br.com.fiap.techchallenge.fiapfood.core.domain.entities.Pedido;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/Pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController()  {
        this.pedidoService =  new PedidoService();
    }

    @PostMapping("/{inserir}")
    public ResponseEntity<Optional<Pedido>> inserir(@Valid @RequestBody Pedido pedido) {
        Optional<Pedido> savedPedido = pedidoService.inserir(pedido);
        return ResponseEntity.ok(savedPedido);
    }

    @PutMapping("/{alterarProgresso}")
    public ResponseEntity<Optional<Pedido>> alterarProgresso(@Valid @RequestBody Pedido pedido, @RequestParam("novoStatus") String novoStatus) {
        Optional<Pedido> savedPedido = pedidoService.atualizarProgresso(pedido, StatusPedido.valueOf(novoStatus));
        return ResponseEntity.ok(savedPedido);
    }

    @DeleteMapping("/{excluir}")
    public ResponseEntity<Optional<Pedido>> excluir(@RequestParam("id") Long id) {
        Pedido pedido = new Pedido();
        pedido.setId(id);
        if(pedidoService.excluir(pedido))
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.badRequest().build();
    }

    @GetMapping("/buscarPorId")
    public ResponseEntity<Optional<Pedido>> buscarPorId(@RequestParam("id") Long id) {
        Optional<Pedido> pedido = pedidoService.buscarPorId(id);
        if (pedido.isEmpty())
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(pedido);
    }

    @GetMapping("/buscarPedidoCompletoPorId")
    public ResponseEntity<Optional<Pedido>> buscarPedidoCompletoPorId(@RequestParam("id") Long id) {
        Optional<Pedido> pedido = pedidoService.buscarPedidoCompletoPorId(id);
        if (pedido.isEmpty())
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(pedido);
    }


    @GetMapping("/buscarTudo")
    public ResponseEntity <Optional<List<Pedido>>> buscarTudo() {
        Optional<List<Pedido>> listPedido = pedidoService.buscarTudo();
        if (listPedido.isEmpty())
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.ok(listPedido);
    }

    @GetMapping("/buscarPedidosEmAberto")
    public ResponseEntity <Optional<List<Pedido>>> buscarPedidosEmAberto() {
        Optional<List<Pedido>> listPedido = pedidoService.buscarPedidosEmAberto();
        if (listPedido.isEmpty())
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.ok(listPedido);
    }

    @GetMapping("/buscarPedidosPorStatus")
    public ResponseEntity <Optional<List<Pedido>>> buscarPedidosPorStatus(@RequestParam("status") String status) {
        Optional<List<Pedido>> listPedido = pedidoService.buscarPedidosPorStatus(StatusPedido.valueOf(status));
        if (listPedido.isEmpty())
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.ok(listPedido);
    }

    @GetMapping("/calcularValorTotalPedido")
    public ResponseEntity <Optional<Double>> calcularValorTotalPedido(@Valid @RequestBody Pedido pedido) {

        Double valorTotalDoPedido = pedidoService.calcularValorTotalDoPedido(pedido);
        if (valorTotalDoPedido == 0D)
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.ok(Optional.of(valorTotalDoPedido));
    }
}

