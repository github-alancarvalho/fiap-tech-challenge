package br.com.fiap.techchallenge.fiapfood.core.applications.services.cliente;


import br.com.fiap.techchallenge.fiapfood.adapter.driven.infra.repositories.DaoFactory;
import br.com.fiap.techchallenge.fiapfood.core.domain.entity.Cliente;
import br.com.fiap.techchallenge.fiapfood.core.domain.ports.output.ClienteRepository;

import java.util.Optional;


public class InserirClienteUseCase {

    private ClienteRepository clienteRepository;

    public InserirClienteUseCase() {
        this.clienteRepository = DaoFactory.getInstance().getClienteRepositoryORM();
    }

    public Optional<Cliente> inserirClienteORM(Cliente cliente) {
        return this.clienteRepository.inserirClienteORM(cliente);
    }
}
