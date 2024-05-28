package br.com.fiap.techchallenge.fiapfood.core.domain.ports.output;

import br.com.fiap.techchallenge.fiapfood.core.domain.dto.ClienteORM;
import br.com.fiap.techchallenge.fiapfood.core.domain.valueobject.Cpf;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository {

    Optional<ClienteORM> inserirClienteORM(ClienteORM cliente);

    Optional<ClienteORM> buscarPorCpf(Cpf cpf);

    Optional<ClienteORM> atualizar(ClienteORM cliente);

    Boolean excluir(Cpf cpf);

    Optional<List<ClienteORM>> listarTudo();
}