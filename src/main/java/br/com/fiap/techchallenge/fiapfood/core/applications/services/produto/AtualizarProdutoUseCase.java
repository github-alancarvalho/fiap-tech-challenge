package br.com.fiap.techchallenge.fiapfood.core.applications.services.produto;


import br.com.fiap.techchallenge.fiapfood.adapter.driven.infra.repositories.DaoFactory;
import br.com.fiap.techchallenge.fiapfood.core.domain.dto.ProdutoORM;
import br.com.fiap.techchallenge.fiapfood.core.domain.ports.output.ProdutoRepository;

import java.util.Optional;


public class AtualizarProdutoUseCase {

    private ProdutoRepository produtoRepository;


    public AtualizarProdutoUseCase() {
        ProdutoRepository produtoRepository = DaoFactory.getInstance().getProdutoRepositoryORM();
        this.produtoRepository = produtoRepository;
    }

    public Optional<ProdutoORM> atualizar(ProdutoORM produtoORM) {
        return this.produtoRepository.atualizar(produtoORM);
    }
}
