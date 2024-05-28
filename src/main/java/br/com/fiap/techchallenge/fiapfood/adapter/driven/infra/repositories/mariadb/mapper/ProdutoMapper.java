package br.com.fiap.techchallenge.fiapfood.adapter.driven.infra.repositories.mariadb.mapper;

import br.com.fiap.techchallenge.fiapfood.core.domain.dto.ProdutoORM;
import br.com.fiap.techchallenge.fiapfood.core.domain.entities.Produto;

import java.util.ArrayList;
import java.util.List;

public class ProdutoMapper {

    public static ProdutoORM mapToEntity(Produto entity) {
        if (entity == null) {
            return null;
        }

        return new ProdutoORM(
                entity.getId(),
                entity.getNome(),
                entity.getDescricao(),
                CategoriaMapper.mapToEntity(entity.getCategoria()),
                entity.getPreco()
        );
    }

    public static Produto mapToEntity(ProdutoORM produto) {
        if (produto == null) {
            return null;
        }

        return new Produto(
                produto.getId(),
                produto.getNome(),
                produto.getDescricao(),
                CategoriaMapper.mapToEntity(produto.getCategoria()),
                produto.getPreco()
        );
    }

    public static List<ProdutoORM> mapListToEntity(List<Produto> listEntity) {
        List<ProdutoORM> list = new ArrayList<>();
        for (Produto produto : listEntity) {
            list.add(ProdutoORM.builder()
                    .id(produto.getId())
                    .nome(produto.getNome())
                    .descricao(produto.getDescricao())
                    .categoria(CategoriaMapper.mapToEntity(produto.getCategoria()))
                            .preco(produto.getPreco()).build()
            );
        }
        return list;
    }

}