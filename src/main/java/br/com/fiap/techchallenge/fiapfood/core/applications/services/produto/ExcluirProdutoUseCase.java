package br.com.fiap.techchallenge.fiapfood.core.applications.services.produto;


import br.com.fiap.techchallenge.fiapfood.adapter.driven.infra.repositories.DaoFactory;
import br.com.fiap.techchallenge.fiapfood.core.domain.dto.ProdutoDto;
import br.com.fiap.techchallenge.fiapfood.core.domain.ports.output.ProdutoRepository;


public class ExcluirProdutoUseCase {

    private ProdutoRepository produtoRepository;


    public ExcluirProdutoUseCase() {
        ProdutoRepository produtoRepository = DaoFactory.getInstance().getProdutoRepositoryORM();
        this.produtoRepository = produtoRepository;
    }

    public Boolean excluir(ProdutoDto produto) {        ;
        return this.produtoRepository.excluir(produto);
    }


}
