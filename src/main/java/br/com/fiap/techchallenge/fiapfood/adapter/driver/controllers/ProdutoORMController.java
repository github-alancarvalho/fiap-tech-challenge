package br.com.fiap.techchallenge.fiapfood.adapter.driver.controllers;

import br.com.fiap.techchallenge.fiapfood.adapter.driver.web.ProdutoPostRequest;
import br.com.fiap.techchallenge.fiapfood.adapter.driver.web.ProdutoPutRequest;
import br.com.fiap.techchallenge.fiapfood.adapter.driver.web.ProdutoResponse;
import br.com.fiap.techchallenge.fiapfood.core.applications.services.produto.AtualizarProdutoUseCase;
import br.com.fiap.techchallenge.fiapfood.core.applications.services.produto.BuscarProdutoUseCase;
import br.com.fiap.techchallenge.fiapfood.core.applications.services.produto.ExcluirProdutoUseCase;
import br.com.fiap.techchallenge.fiapfood.core.applications.services.produto.InserirProdutoUseCase;
import br.com.fiap.techchallenge.fiapfood.core.domain.dto.CategoriaORM;
import br.com.fiap.techchallenge.fiapfood.core.domain.dto.ProdutoORM;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1/ProdutosORM")
public class ProdutoORMController {

    private final InserirProdutoUseCase inserirProdutoUseCase;
    private final BuscarProdutoUseCase buscarProdutoUseCase;
    private final AtualizarProdutoUseCase atualizarProdutoUseCase;
    private final ExcluirProdutoUseCase excluirProdutoUseCase;

    public ProdutoORMController() {

        this.inserirProdutoUseCase = new InserirProdutoUseCase();
        this.buscarProdutoUseCase = new BuscarProdutoUseCase();
        this.atualizarProdutoUseCase = new AtualizarProdutoUseCase();
        this.excluirProdutoUseCase = new ExcluirProdutoUseCase();
    }

    @PostMapping("/{inserir}")
    public ResponseEntity<Optional<ProdutoResponse>> inserir(@Valid @RequestBody ProdutoPostRequest produtoRequest) {

        ProdutoORM produto = ProdutoORM.builder()
                .nome(produtoRequest.getNome())
                .descricao(produtoRequest.getDescricao())
                .preco(produtoRequest.getPreco())
                .categoria(CategoriaORM.builder().id(produtoRequest.getCategoriaId()).build())
                .build();

        Optional<ProdutoORM> savedProduto = inserirProdutoUseCase.inserir(produto);
        if (!savedProduto.isEmpty()) {
            ProdutoResponse response = ProdutoResponse.builder()
                    .id(savedProduto.get().getId())
                    .nome(savedProduto.get().getNome())
                    .descricao(savedProduto.get().getDescricao())
                    .preco(savedProduto.get().getPreco())
                    .categoria(savedProduto.get().getCategoria()).build();

            return ResponseEntity.ok(Optional.ofNullable(response));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/buscarProdutoPorId")
    public ResponseEntity<Optional<ProdutoResponse>> buscarProdutoPorId(@RequestParam("id") Long id) {

        Optional<ProdutoORM> produto = buscarProdutoUseCase.buscarProdutoPorId(id);
        if (!produto.isEmpty()) {
            ProdutoResponse response = ProdutoResponse.builder()
                    .id(produto.get().getId())
                    .nome(produto.get().getNome())
                    .descricao(produto.get().getDescricao())
                    .preco(produto.get().getPreco())
                    .categoria(produto.get().getCategoria()).build();

            return ResponseEntity.ok(Optional.ofNullable(response));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{alterar}")
    public ResponseEntity<Optional<ProdutoResponse>> alterar(@Valid @RequestBody ProdutoPutRequest produtoRequest) {
        ProdutoORM produto = ProdutoORM.builder()
                .id(produtoRequest.getId())
                .nome(produtoRequest.getNome())
                .descricao(produtoRequest.getDescricao())
                .preco(produtoRequest.getPreco())
                .categoria(CategoriaORM.builder().id(produtoRequest.getCategoriaId()).build())
                .build();

        Optional<ProdutoORM> savedProduto = atualizarProdutoUseCase.atualizar(produto);

        if (!savedProduto.isEmpty()) {
            ProdutoResponse response = ProdutoResponse.builder()
                    .id(savedProduto.get().getId())
                    .nome(savedProduto.get().getNome())
                    .descricao(savedProduto.get().getDescricao())
                    .preco(savedProduto.get().getPreco())
                    .categoria(savedProduto.get().getCategoria()).build();

            return ResponseEntity.ok(Optional.ofNullable(response));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{excluir}")
    public ResponseEntity<Optional<Boolean>> excluir(@RequestParam("id") Long id) {
        ProdutoORM produto = ProdutoORM.builder()
                .id(id).build();

        if (excluirProdutoUseCase.excluir(produto))
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.badRequest().build();
    }

    @GetMapping("/buscarTudo")
    public ResponseEntity<Optional<List<ProdutoResponse>>> buscarTudo() {
        Optional<List<ProdutoORM>> produtos = buscarProdutoUseCase.buscarTodosProdutos();
        if (!produtos.isEmpty()) {

            List<ProdutoResponse> list = new ArrayList<>();
            for (ProdutoORM produto : produtos.get()) {
                ProdutoResponse response = ProdutoResponse.builder()
                        .id(produto.getId())
                        .nome(produto.getNome())
                        .descricao(produto.getDescricao())
                        .preco(produto.getPreco())
                        .categoria(produto.getCategoria()).build();
                list.add(response);
            }
            return ResponseEntity.ok(Optional.ofNullable(list));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/buscarProdutosPorCategoria")
    public ResponseEntity<Optional<List<ProdutoResponse>>> buscarProdutosPorCategoria(@RequestParam("id") Long id) {
        Optional<List<ProdutoORM>> produtos = buscarProdutoUseCase.buscarProdutosPorCategoria(
                CategoriaORM.builder().id(id).build());
        if (!produtos.isEmpty()) {

            List<ProdutoResponse> list = new ArrayList<>();
            for (ProdutoORM produto : produtos.get()) {
                ProdutoResponse response = ProdutoResponse.builder()
                        .id(produto.getId())
                        .nome(produto.getNome())
                        .descricao(produto.getDescricao())
                        .preco(produto.getPreco())
                        .categoria(produto.getCategoria()).build();
                list.add(response);
            }
            if(list.size() > 0)
                return ResponseEntity.ok(Optional.ofNullable(list));
            else
                return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

