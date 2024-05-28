package br.com.fiap.techchallenge.fiapfood.core.applications.services.produto;


import br.com.fiap.techchallenge.fiapfood.adapter.driven.infra.repositories.DaoFactory;
import br.com.fiap.techchallenge.fiapfood.core.domain.dto.CategoriaDto;
import br.com.fiap.techchallenge.fiapfood.core.domain.dto.ProdutoDto;
import br.com.fiap.techchallenge.fiapfood.core.domain.ports.output.ProdutoRepository;

import java.util.List;
import java.util.Optional;


public class BuscarProdutoUseCase {

    private ProdutoRepository produtoRepository;


    public BuscarProdutoUseCase() {
        ProdutoRepository produtoRepository = DaoFactory.getInstance().getProdutoRepositoryORM();
        this.produtoRepository = produtoRepository;
    }

    public Optional<ProdutoDto> buscarProdutoPorId(Long id) {        ;
        return this.produtoRepository.buscarPorId(id);
    }

    public Optional<List<ProdutoDto>> buscarTodosProdutos() {        ;
        return this.produtoRepository.listarTudo();
    }

    public Optional<List<ProdutoDto>> buscarProdutosPorCategoria(CategoriaDto categoria) {        ;
        return this.produtoRepository.listarPorCategoria(categoria);
    }

}
