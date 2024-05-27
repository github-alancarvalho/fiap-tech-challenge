package br.com.fiap.techchallenge.fiapfood.core.applications.services.produto;


import br.com.fiap.techchallenge.fiapfood.adapter.driven.infra.repositories.DaoFactory;
import br.com.fiap.techchallenge.fiapfood.core.domain.dto.CategoriaORM;
import br.com.fiap.techchallenge.fiapfood.core.domain.dto.ProdutoORM;
import br.com.fiap.techchallenge.fiapfood.core.domain.ports.output.ProdutoRepositoryORM;
import br.com.fiap.techchallenge.fiapfood.core.domain.valueobject.Cpf;

import java.util.List;
import java.util.Optional;


public class BuscarProdutoUseCase {

    private ProdutoRepositoryORM produtoRepository;


    public BuscarProdutoUseCase() {
        ProdutoRepositoryORM produtoRepository = DaoFactory.getInstance().getProdutoRepositoryORM();
        this.produtoRepository = produtoRepository;
    }

    public Optional<ProdutoORM> buscarProdutoPorId(Long id) {        ;
        return this.produtoRepository.buscarPorId(id);
    }

    public Optional<List<ProdutoORM>> buscarTodosProdutos() {        ;
        return this.produtoRepository.listarTudo();
    }

    public Optional<List<ProdutoORM>> buscarProdutosPorCategoria(CategoriaORM categoria) {        ;
        return this.produtoRepository.listarPorCategoria(categoria);
    }

}
