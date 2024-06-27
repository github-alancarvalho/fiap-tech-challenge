package br.com.fiap.techchallenge.fiapfood.core.applications.services.produto;


import br.com.fiap.techchallenge.fiapfood.adapter.driven.infra.repositories.DaoFactory;
import br.com.fiap.techchallenge.fiapfood.core.domain.entity.Categoria;
import br.com.fiap.techchallenge.fiapfood.core.domain.entity.Produto;
import br.com.fiap.techchallenge.fiapfood.core.domain.ports.output.ProdutoRepository;

import java.util.List;
import java.util.Optional;


public class BuscarProdutoUseCase {

    private ProdutoRepository produtoRepository;

    public BuscarProdutoUseCase() {
        this.produtoRepository = DaoFactory.getInstance().getProdutoRepositoryORM();
    }

    public Optional<Produto> buscarProdutoPorId(Long id) {
        return this.produtoRepository.buscarPorId(id);
    }

    public Optional<List<Produto>> buscarTodosProdutos() {
        return this.produtoRepository.listarTudo();
    }

    public Optional<List<Produto>> buscarProdutosPorCategoria(Categoria categoria) {
        return this.produtoRepository.listarPorCategoria(categoria);
    }

}
