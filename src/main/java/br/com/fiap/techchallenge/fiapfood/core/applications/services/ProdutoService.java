package br.com.fiap.techchallenge.fiapfood.core.applications.services;


import br.com.fiap.techchallenge.fiapfood.adapter.driven.infra.repositories.DaoFactory;
import br.com.fiap.techchallenge.fiapfood.core.applications.ports.ProdutoPort;
import br.com.fiap.techchallenge.fiapfood.core.domain.entities.Categoria;
import br.com.fiap.techchallenge.fiapfood.core.domain.entities.Produto;

import java.util.List;
import java.util.Optional;

//Regras de negócio
public class ProdutoService {

    private final ProdutoPort produtoRepository;

    public ProdutoService() {
        ProdutoPort produtoRepository = DaoFactory.getInstance().getProdutoRepository();
        this.produtoRepository = produtoRepository;
    }

    public Optional<List<Produto>> buscarTudo() {
        return this.produtoRepository.listarTudo();
    }

    public Optional<Produto> inserir(Produto produto) {
        return this.produtoRepository.inserir(produto);
    }

    public Optional<Produto> atualizar(Produto produto) {
        return this.produtoRepository.atualizar(produto);
    }

    public Optional<Produto> buscarPorId(Long id) {
        return this.produtoRepository.buscarPorId(id);
    }

    public Optional<List<Produto>> buscarPorCategoria(Categoria categoria) {
        return this.produtoRepository.listarPorCategoria(categoria);
    }

    public Boolean excluir(Produto produto) {
        return this.produtoRepository.excluir(produto);
    }
}
