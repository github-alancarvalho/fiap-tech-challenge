package br.com.fiap.techchallenge.fiapfood.adapter.driven.infra.repositories.mariadb.mapper;

import br.com.fiap.techchallenge.fiapfood.core.domain.dto.CategoriaDto;
import br.com.fiap.techchallenge.fiapfood.core.domain.entities.Categoria;

import java.util.ArrayList;
import java.util.List;

public class CategoriaMapper {

    private CategoriaMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static CategoriaDto mapToEntity(Categoria entity) {
        if (entity == null) {
            return null;
        }
        return new CategoriaDto(
                entity.getId(),
                entity.getNome(),
                entity.getDescricao()
        );
    }

    public static Categoria mapToEntity(CategoriaDto categoriaDto) {
        if (categoriaDto == null) {
            return null;
        }
        return new Categoria(
                categoriaDto.getId(),
                categoriaDto.getNome(),
                categoriaDto.getDescricao()
        );
    }

    public static List<CategoriaDto> mapListToEntity(List<Categoria> listEntity) {
        List<CategoriaDto> list = new ArrayList<>();
        for ( Categoria categoriaORM : listEntity ){
            list.add(CategoriaDto.builder()
                            .id(categoriaORM.getId())
                    .nome(categoriaORM.getNome())
                            .descricao(categoriaORM.getDescricao()).build()
                    );
        }
        return list;
    }


}