package br.com.fiap.techchallenge.fiapfood.core.applications.services.produto;


import br.com.fiap.techchallenge.fiapfood.adapter.driven.infra.repositories.DaoFactory;
import br.com.fiap.techchallenge.fiapfood.core.domain.entity.Produto;
import br.com.fiap.techchallenge.fiapfood.core.domain.ports.output.ProdutoRepository;


public class ExcluirProdutoUseCase {

    private ProdutoRepository produtoRepository;


    public ExcluirProdutoUseCase() {
        this.produtoRepository = DaoFactory.getInstance().getProdutoRepositoryORM();
    }

    public Boolean excluir(Produto produto) {
        return this.produtoRepository.excluir(produto);
    }


}
