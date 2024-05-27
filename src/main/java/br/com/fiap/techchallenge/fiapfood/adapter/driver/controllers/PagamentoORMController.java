package br.com.fiap.techchallenge.fiapfood.adapter.driver.controllers;

import br.com.fiap.techchallenge.fiapfood.adapter.driver.web.PagamentoRequest;
import br.com.fiap.techchallenge.fiapfood.adapter.driver.web.PagamentoResponse;
import br.com.fiap.techchallenge.fiapfood.core.applications.services.pagamento.AtualizarPagamentoUseCase;
import br.com.fiap.techchallenge.fiapfood.core.applications.services.pagamento.BuscarPagamentoUseCase;
import br.com.fiap.techchallenge.fiapfood.core.applications.services.pagamento.ProcessarPagamentoUseCase;
import br.com.fiap.techchallenge.fiapfood.core.domain.base.StatusPagamento;
import br.com.fiap.techchallenge.fiapfood.core.domain.dto.PagamentoORM;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1/PagamentosORM")
public class PagamentoORMController {

    private final ProcessarPagamentoUseCase processarPagamentoUseCase;
    private final BuscarPagamentoUseCase buscarPagamentoUseCase;
    private final AtualizarPagamentoUseCase atualizarPagamentoUseCase;

    public PagamentoORMController() {
        this.buscarPagamentoUseCase = new BuscarPagamentoUseCase();
        this.atualizarPagamentoUseCase = new AtualizarPagamentoUseCase();
        this.processarPagamentoUseCase = new ProcessarPagamentoUseCase();
    }

    @PostMapping("/{processarPagamento}")
    public ResponseEntity<Optional<PagamentoResponse>> processarPagamento(@Valid @RequestBody PagamentoRequest pagamentoRequest) {

        PagamentoORM pagamento = PagamentoORM.builder()
                .idPedido(pagamentoRequest.getIdPedido())
                .valor(pagamentoRequest.getValor())
                .build();

        Optional<PagamentoORM> savedPagamento = processarPagamentoUseCase.processarPagamento(pagamento);
        if (!savedPagamento.isEmpty()) {
            PagamentoResponse response = PagamentoResponse.builder()
                    .id(savedPagamento.get().getId())
                    .idPedido(savedPagamento.get().getIdPedido())
                    .valor(savedPagamento.get().getValor())
                    .status(savedPagamento.get().getStatus().toString())
                    .build();

            return ResponseEntity.ok(Optional.ofNullable(response));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/buscarPagamentoPorId")
    public ResponseEntity<Optional<PagamentoResponse>> buscarPagamentoPorId(@RequestParam("id") Long id) {

        Optional<PagamentoORM> savedPagamento = buscarPagamentoUseCase.buscarPagamentoPorId(id);
        if (!savedPagamento.isEmpty()) {
            PagamentoResponse response = PagamentoResponse.builder()
                    .id(savedPagamento.get().getId())
                    .idPedido(savedPagamento.get().getIdPedido())
                    .valor(savedPagamento.get().getValor())
                    .status(savedPagamento.get().getStatus().toString())
                    .build();

            return ResponseEntity.ok(Optional.ofNullable(response));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{atualizarProgressoPagamento}")
    public ResponseEntity<Optional<PagamentoResponse>> atualizarProgressoPagamento(@Valid @RequestBody PagamentoRequest pagamentoRequest, @RequestParam("status") String status) {
        PagamentoORM pagamento = PagamentoORM.builder()
                .id(pagamentoRequest.getId())
                .status(StatusPagamento.valueOf(status))
                .build();

        Optional<PagamentoORM> savedPagamento = atualizarPagamentoUseCase.atualizarProgressoPagamento(pagamento, StatusPagamento.valueOf(status));

        if (!savedPagamento.isEmpty()) {
            PagamentoResponse response = PagamentoResponse.builder()
                    .id(savedPagamento.get().getId())
                    .idPedido(savedPagamento.get().getIdPedido())
                    .valor(savedPagamento.get().getValor())
                    .status(savedPagamento.get().getStatus().toString())
                    .build();

            return ResponseEntity.ok(Optional.ofNullable(response));
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/buscarTodosPagamentos")
    public ResponseEntity<Optional<List<PagamentoResponse>>> buscarTodosPagamentos() {
        Optional<List<PagamentoORM>> pagamentos = buscarPagamentoUseCase.buscarTodosPagamentos();
        if (!pagamentos.isEmpty()) {

            List<PagamentoResponse> list = new ArrayList<>();
            for (PagamentoORM pagamento : pagamentos.get()) {
                PagamentoResponse response = PagamentoResponse.builder()
                        .id(pagamento.getId())
                        .idPedido(pagamento.getIdPedido())
                        .valor(pagamento.getValor())
                        .status(pagamento.getStatus().toString())
                        .build();
                list.add(response);
            }
            return ResponseEntity.ok(Optional.ofNullable(list));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

