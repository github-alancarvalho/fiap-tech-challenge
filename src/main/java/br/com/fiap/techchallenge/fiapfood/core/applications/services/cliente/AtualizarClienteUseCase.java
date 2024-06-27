package br.com.fiap.techchallenge.fiapfood.core.applications.services.cliente;


import br.com.fiap.techchallenge.fiapfood.adapter.driven.infra.repositories.DaoFactory;
import br.com.fiap.techchallenge.fiapfood.core.domain.entity.Cliente;
import br.com.fiap.techchallenge.fiapfood.core.domain.ports.output.ClienteRepository;

import java.util.Optional;


public class AtualizarClienteUseCase {

    private ClienteRepository clienteRepository;


    public AtualizarClienteUseCase() {
        this.clienteRepository = DaoFactory.getInstance().getClienteRepositoryORM();
    }

    public Optional<Cliente> atualizar(Cliente cliente) {
        return this.clienteRepository.atualizar(cliente);
    }

}
