package br.com.fiap.techchallenge.fiapfood.core.domain.ports.output;

import br.com.fiap.techchallenge.fiapfood.core.domain.dto.ClienteDto;
import br.com.fiap.techchallenge.fiapfood.core.domain.valueobject.Cpf;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository {

    Optional<ClienteDto> inserirClienteORM(ClienteDto cliente);

    Optional<ClienteDto> buscarPorCpf(Cpf cpf);

    Optional<ClienteDto> atualizar(ClienteDto cliente);

    Boolean excluir(Cpf cpf);

    Optional<List<ClienteDto>> listarTudo();
}