package br.com.fiap.techchallenge.fiapfood.adapter.driven.infra.repositories.mariadb.mapper;

import br.com.fiap.techchallenge.fiapfood.core.domain.entities.Cliente;
import br.com.fiap.techchallenge.fiapfood.core.domain.dto.ClienteDto;
import br.com.fiap.techchallenge.fiapfood.core.domain.valueobject.Cpf;
import br.com.fiap.techchallenge.fiapfood.core.domain.valueobject.Telefone;

import java.util.ArrayList;
import java.util.List;

public class ClienteMapper {

    private ClienteMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static ClienteDto mapToEntity(Cliente entity) {
        if (entity == null) {
            return null;
        }
        return new ClienteDto(
                new Cpf(entity.getCpf()),
                entity.getNome(),
                entity.getEmail(),
                new Telefone(entity.getTelefone())
        );
    }

    public static Cliente mapToEntity(ClienteDto cliente) {
        if (cliente == null) {
            return null;
        }
        return new Cliente(
                cliente.getCpf() == null ? null : cliente.getCpf().getCpfSomenteNumero(),
                cliente.getNome(),
                cliente.getEmail(),
                cliente.getTelefone() == null ? null : cliente.getTelefone().getTelefone()
        );
    }

    public static List<ClienteDto> mapListToEntity(List<Cliente> listEntity) {
        List<ClienteDto> list = new ArrayList<>();
        for ( Cliente cliente : listEntity ){
            list.add(ClienteDto.builder()
                    .nome(cliente.getNome())
                    .cpf(new Cpf(cliente.getCpf()))
                            .telefone(new Telefone(cliente.getTelefone()))
                            .email(cliente.getEmail()).build()
                    );
        }
        return list;
    }


}