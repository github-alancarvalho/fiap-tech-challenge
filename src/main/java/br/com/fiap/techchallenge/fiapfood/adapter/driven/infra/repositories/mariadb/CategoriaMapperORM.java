package br.com.fiap.techchallenge.fiapfood.adapter.driven.infra.repositories.mariadb;

import br.com.fiap.techchallenge.fiapfood.core.domain.dto.CategoriaORM;
import br.com.fiap.techchallenge.fiapfood.core.domain.dto.CategoriaORM;
import br.com.fiap.techchallenge.fiapfood.core.domain.entities.CategoriaEntity;
import br.com.fiap.techchallenge.fiapfood.core.domain.valueobject.Cpf;
import br.com.fiap.techchallenge.fiapfood.core.domain.valueobject.Telefone;

import java.util.ArrayList;
import java.util.List;

public class CategoriaMapperORM {

    public static CategoriaORM mapToEntity(CategoriaEntity entity) {
        if (entity == null) {
            return null;
        }
        return new CategoriaORM(
                entity.getId(),
                entity.getNome(),
                entity.getDescricao()
        );
    }

    public static CategoriaEntity mapToEntity(CategoriaORM categoriaORM) {
        if (categoriaORM == null) {
            return null;
        }
        return new CategoriaEntity(
                categoriaORM.getId(),
                categoriaORM.getNome(),
                categoriaORM.getDescricao()
        );
    }

    public static List<CategoriaORM> mapListToEntity(List<CategoriaEntity> listEntity) {
        List<CategoriaORM> list = new ArrayList<>();
        for ( CategoriaEntity categoriaORM : listEntity ){
            list.add(CategoriaORM.builder()
                            .id(categoriaORM.getId())
                    .nome(categoriaORM.getNome())
                            .descricao(categoriaORM.getDescricao()).build()
                    );
        }
        return list;
    }


}