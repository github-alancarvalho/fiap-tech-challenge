package br.com.fiap.techchallenge.fiapfood.core.domain.ports.output;

import br.com.fiap.techchallenge.fiapfood.core.domain.dto.CategoriaORM;
import br.com.fiap.techchallenge.fiapfood.core.domain.dto.ProdutoORM;

import java.util.List;
import java.util.Optional;

public interface ProdutoRepository {

    Optional<ProdutoORM> inserir(ProdutoORM produto);

    Optional<ProdutoORM> buscarPorId(Long id);

    Optional<List<ProdutoORM>> listarPorCategoria(CategoriaORM categoria);

    Optional<List<ProdutoORM>> listarTudo();

    Boolean excluir(ProdutoORM produto);

    Optional<ProdutoORM> atualizar(ProdutoORM produto);
}