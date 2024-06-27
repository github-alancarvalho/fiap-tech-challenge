package br.com.fiap.techchallenge.fiapfood.core.applications.services.produto;


import br.com.fiap.techchallenge.fiapfood.adapter.driven.infra.repositories.DaoFactory;
import br.com.fiap.techchallenge.fiapfood.core.domain.entity.Produto;
import br.com.fiap.techchallenge.fiapfood.core.domain.ports.output.ProdutoRepository;

import java.util.Optional;


public class InserirProdutoUseCase {

    private ProdutoRepository produtoRepository;


    public InserirProdutoUseCase() {
        this.produtoRepository = DaoFactory.getInstance().getProdutoRepositoryORM();
    }

    public Optional<Produto> inserir(Produto produto) {
        return this.produtoRepository.inserir(produto);
    }
}
