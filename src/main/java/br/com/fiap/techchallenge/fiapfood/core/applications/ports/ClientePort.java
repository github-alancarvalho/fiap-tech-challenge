package br.com.fiap.techchallenge.fiapfood.core.applications.ports;

import br.com.fiap.techchallenge.fiapfood.core.domain.entities.Cliente;
import br.com.fiap.techchallenge.fiapfood.core.domain.entities.Produto;
import br.com.fiap.techchallenge.fiapfood.core.domain.valueobject.Cpf;

import java.util.List;
import java.util.Optional;

public interface ClientePort {

    Optional<Cliente> inserirCliente(Cliente cliente);

    Optional<Cliente> buscarPorCpf(Cpf cpf);

    Optional<Cliente> atualizar(Cliente cliente);

    Boolean excluir(Cliente cliente);

    Optional<List<Cliente>> listarTudo();
}