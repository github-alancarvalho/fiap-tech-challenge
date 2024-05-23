package br.com.fiap.techchallenge.fiapfood.adapter.driver.controllers;

import br.com.fiap.techchallenge.fiapfood.core.applications.services.ProdutoService;
import br.com.fiap.techchallenge.fiapfood.core.domain.entities.Categoria;
import br.com.fiap.techchallenge.fiapfood.core.domain.entities.Produto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/Produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController()  {
        ProdutoService produtoService = new ProdutoService();
        this.produtoService = produtoService;
    }

    @PostMapping("/{inserir}")
    public ResponseEntity<Optional<Produto>> inserir(@Valid @RequestBody Produto produto) {
        Optional<Produto> savedProduto = produtoService.inserir(produto);
        return ResponseEntity.ok(savedProduto);
    }

    @PutMapping("/{alterar}")
    public ResponseEntity<Optional<Produto>> alterar(@Valid @RequestBody Produto produto) {
        Optional<Produto> savedProduto = produtoService.atualizar(produto);
        return ResponseEntity.ok(savedProduto);
    }

    @DeleteMapping("/{excluir}")
    public ResponseEntity<Optional<Produto>> excluir(@RequestParam("id") Long id) {
        Produto produto = new Produto();
        produto.setId(id);
        if(produtoService.excluir(produto))
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.badRequest().build();
    }

    @GetMapping("/buscarPorId")
    public ResponseEntity<Optional<Produto>> buscarPorId(@RequestParam("id") Long id) {
        Optional<Produto> produto = produtoService.buscarPorId(id);
        if (produto.isEmpty())
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(produto);
    }

    @GetMapping("/listarPorCategoria")
    public ResponseEntity <Optional<List<Produto>>> listarPorCategoria(@RequestParam("categoria") Long categoria_id) {
        Categoria cat = new Categoria();
        cat.setId(categoria_id);
        Optional<List<Produto>> listProduto = produtoService.buscarPorCategoria(cat);
        if (listProduto.isEmpty())
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.ok(listProduto);
    }

    @GetMapping("/buscarTudo")
    public ResponseEntity <Optional<List<Produto>>> buscarTudo() {
        Optional<List<Produto>> listProduto = produtoService.buscarTudo();
        if (listProduto.isEmpty())
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.ok(listProduto);
    }
}

