package br.com.fiap.techchallenge.fiapfood.adapter.driven.infra.repositories.mariadb.mapper;

import br.com.fiap.techchallenge.fiapfood.adapter.driven.infra.repositories.mariadb.entities.CategoriaORM;
import br.com.fiap.techchallenge.fiapfood.core.domain.entity.Categoria;

import java.util.ArrayList;
import java.util.List;

public class CategoriaMapper {

    private CategoriaMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static Categoria mapToEntity(CategoriaORM entity) {
        if (entity == null) {
            return null;
        }
        return new Categoria(
                entity.getId(),
                entity.getNome(),
                entity.getDescricao()
        );
    }

    public static CategoriaORM mapToEntity(Categoria categoria) {
        if (categoria == null) {
            return null;
        }
        return new CategoriaORM(
                categoria.getId(),
                categoria.getNome(),
                categoria.getDescricao()
        );
    }

    public static List<Categoria> mapListToEntity(List<CategoriaORM> listEntity) {
        List<Categoria> list = new ArrayList<>();
        for ( CategoriaORM categoriaORM : listEntity ){
            list.add(Categoria.builder()
                            .id(categoriaORM.getId())
                    .nome(categoriaORM.getNome())
                            .descricao(categoriaORM.getDescricao()).build()
                    );
        }
        return list;
    }


}