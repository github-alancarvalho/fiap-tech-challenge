package br.com.fiap.techchallenge.fiapfood.adapter.driven.infra.repositories.mariadb;

import br.com.fiap.techchallenge.fiapfood.core.domain.dto.ProdutoORM;
import br.com.fiap.techchallenge.fiapfood.core.domain.entities.ProdutoEntity;

import java.util.ArrayList;
import java.util.List;

public class ProdutoMapperORM {

    public static ProdutoORM mapToEntity(ProdutoEntity entity) {
        if (entity == null) {
            return null;
        }

        return new ProdutoORM(
                entity.getId(),
                entity.getNome(),
                entity.getDescricao(),
                CategoriaMapperORM.mapToEntity(entity.getCategoria()),
                entity.getPreco()
        );
    }

    public static ProdutoEntity mapToEntity(ProdutoORM produto) {
        if (produto == null) {
            return null;
        }

        return new ProdutoEntity(
                produto.getId(),
                produto.getNome(),
                produto.getDescricao(),
                CategoriaMapperORM.mapToEntity(produto.getCategoria()),
                produto.getPreco()
        );
    }

    public static List<ProdutoORM> mapListToEntity(List<ProdutoEntity> listEntity) {
        List<ProdutoORM> list = new ArrayList<>();
        for (ProdutoEntity produto : listEntity) {
            list.add(ProdutoORM.builder()
                    .id(produto.getId())
                    .nome(produto.getNome())
                    .descricao(produto.getDescricao())
                    .categoria(CategoriaMapperORM.mapToEntity(produto.getCategoria()))
                            .preco(produto.getPreco()).build()
            );
        }
        return list;
    }

}