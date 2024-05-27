package br.com.fiap.techchallenge.fiapfood.core.applications.services.produto;


import br.com.fiap.techchallenge.fiapfood.adapter.driven.infra.repositories.DaoFactory;
import br.com.fiap.techchallenge.fiapfood.core.domain.dto.ProdutoORM;
import br.com.fiap.techchallenge.fiapfood.core.domain.ports.output.ProdutoRepositoryORM;

import java.util.Optional;


public class InserirProdutoUseCase {

    private ProdutoRepositoryORM produtoRepository;


    public InserirProdutoUseCase() {
        ProdutoRepositoryORM produtoRepository = DaoFactory.getInstance().getProdutoRepositoryORM();
        this.produtoRepository = produtoRepository;
    }

    public Optional<ProdutoORM> inserir(ProdutoORM produtoORM) {
        return this.produtoRepository.inserir(produtoORM);
    }
}
