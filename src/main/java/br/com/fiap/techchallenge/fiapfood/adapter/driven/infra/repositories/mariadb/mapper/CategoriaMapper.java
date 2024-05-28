package br.com.fiap.techchallenge.fiapfood.adapter.driven.infra.repositories.mariadb.mapper;

import br.com.fiap.techchallenge.fiapfood.core.domain.dto.CategoriaORM;
import br.com.fiap.techchallenge.fiapfood.core.domain.entities.Categoria;

import java.util.ArrayList;
import java.util.List;

public class CategoriaMapper {

    public static CategoriaORM mapToEntity(Categoria entity) {
        if (entity == null) {
            return null;
        }
        return new CategoriaORM(
                entity.getId(),
                entity.getNome(),
                entity.getDescricao()
        );
    }

    public static Categoria mapToEntity(CategoriaORM categoriaORM) {
        if (categoriaORM == null) {
            return null;
        }
        return new Categoria(
                categoriaORM.getId(),
                categoriaORM.getNome(),
                categoriaORM.getDescricao()
        );
    }

    public static List<CategoriaORM> mapListToEntity(List<Categoria> listEntity) {
        List<CategoriaORM> list = new ArrayList<>();
        for ( Categoria categoriaORM : listEntity ){
            list.add(CategoriaORM.builder()
                            .id(categoriaORM.getId())
                    .nome(categoriaORM.getNome())
                            .descricao(categoriaORM.getDescricao()).build()
                    );
        }
        return list;
    }


}