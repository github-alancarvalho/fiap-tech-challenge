package br.com.fiap.techchallenge.fiapfood.core.applications.services.produto;


import br.com.fiap.techchallenge.fiapfood.adapter.driven.infra.repositories.DaoFactory;
import br.com.fiap.techchallenge.fiapfood.core.domain.dto.ProdutoDto;
import br.com.fiap.techchallenge.fiapfood.core.domain.ports.output.ProdutoRepository;

import java.util.Optional;


public class InserirProdutoUseCase {

    private ProdutoRepository produtoRepository;


    public InserirProdutoUseCase() {
        ProdutoRepository produtoRepository = DaoFactory.getInstance().getProdutoRepositoryORM();
        this.produtoRepository = produtoRepository;
    }

    public Optional<ProdutoDto> inserir(ProdutoDto produtoDto) {
        return this.produtoRepository.inserir(produtoDto);
    }
}
