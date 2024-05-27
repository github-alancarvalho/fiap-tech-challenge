package br.com.fiap.techchallenge.fiapfood.adapter.driven.infra.repositories.mariadb;

import br.com.fiap.techchallenge.fiapfood.core.domain.entities.ClienteEntity;
import br.com.fiap.techchallenge.fiapfood.core.domain.dto.ClienteORM;
import br.com.fiap.techchallenge.fiapfood.core.domain.valueobject.Cpf;
import br.com.fiap.techchallenge.fiapfood.core.domain.valueobject.Telefone;

import java.util.ArrayList;
import java.util.List;

public class ClienteMapperORM {

    public static ClienteORM mapToEntity(ClienteEntity entity) {
        if (entity == null) {
            return null;
        }
        return new ClienteORM(
                new Cpf(entity.getCpf()),
                entity.getNome(),
                entity.getEmail(),
                new Telefone(entity.getTelefone())
        );
    }

    public static ClienteEntity mapToEntity(ClienteORM cliente) {
        if (cliente == null) {
            return null;
        }
        return new ClienteEntity(
                cliente.getCpf() == null ? null : cliente.getCpf().getCpfSomenteNumero(),
                cliente.getNome(),
                cliente.getEmail(),
                cliente.getTelefone() == null ? null : cliente.getTelefone().getTelefone()
        );
    }

    public static List<ClienteORM> mapListToEntity(List<ClienteEntity> listEntity) {
        List<ClienteORM> list = new ArrayList<>();
        for ( ClienteEntity cliente : listEntity ){
            list.add(ClienteORM.builder()
                    .nome(cliente.getNome())
                    .cpf(new Cpf(cliente.getCpf()))
                            .telefone(new Telefone(cliente.getTelefone()))
                            .email(cliente.getEmail()).build()
                    );
        }
        return list;
    }


}