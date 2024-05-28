package br.com.fiap.techchallenge.fiapfood.core.domain.ports.output;

import br.com.fiap.techchallenge.fiapfood.core.domain.dto.CategoriaDto;
import br.com.fiap.techchallenge.fiapfood.core.domain.dto.ProdutoDto;

import java.util.List;
import java.util.Optional;

public interface ProdutoRepository {

    Optional<ProdutoDto> inserir(ProdutoDto produto);

    Optional<ProdutoDto> buscarPorId(Long id);

    Optional<List<ProdutoDto>> listarPorCategoria(CategoriaDto categoria);

    Optional<List<ProdutoDto>> listarTudo();

    Boolean excluir(ProdutoDto produto);

    Optional<ProdutoDto> atualizar(ProdutoDto produto);
}