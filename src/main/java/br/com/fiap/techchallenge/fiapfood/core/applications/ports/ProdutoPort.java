package br.com.fiap.techchallenge.fiapfood.core.applications.ports;

import br.com.fiap.techchallenge.fiapfood.core.domain.entities.Categoria;
import br.com.fiap.techchallenge.fiapfood.core.domain.entities.Cliente;
import br.com.fiap.techchallenge.fiapfood.core.domain.entities.Produto;
import br.com.fiap.techchallenge.fiapfood.core.domain.valueobject.Cpf;

import java.util.List;
import java.util.Optional;

public interface ProdutoPort {

    Optional<Produto> inserir(Produto produto);

    Optional<Produto> buscarPorId(Long id);

    Optional<List<Produto>> listarPorCategoria(Categoria categoria);

    Optional<List<Produto>> listarTudo();

    Boolean excluir(Produto produto);

    Optional<Produto> atualizar(Produto produto);
}