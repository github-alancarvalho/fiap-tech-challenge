package br.com.fiap.techchallenge.fiapfood.core.applications.services.produto;


import br.com.fiap.techchallenge.fiapfood.adapter.driven.infra.repositories.DaoFactory;
import br.com.fiap.techchallenge.fiapfood.core.domain.entity.Produto;
import br.com.fiap.techchallenge.fiapfood.core.domain.ports.output.ProdutoRepository;

import java.util.Optional;


public class AtualizarProdutoUseCase {

    private ProdutoRepository produtoRepository;

    public AtualizarProdutoUseCase() {
        this.produtoRepository = DaoFactory.getInstance().getProdutoRepositoryORM();
    }

    public Optional<Produto> atualizar(Produto produto) {
        return this.produtoRepository.atualizar(produto);
    }
}
