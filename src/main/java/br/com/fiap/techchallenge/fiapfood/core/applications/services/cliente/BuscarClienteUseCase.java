package br.com.fiap.techchallenge.fiapfood.core.applications.services.cliente;


import br.com.fiap.techchallenge.fiapfood.adapter.driven.infra.repositories.DaoFactory;
import br.com.fiap.techchallenge.fiapfood.core.domain.dto.ClienteORM;
import br.com.fiap.techchallenge.fiapfood.core.domain.ports.output.ClienteRepositoryORM;
import br.com.fiap.techchallenge.fiapfood.core.domain.valueobject.Cpf;

import java.util.List;
import java.util.Optional;


public class BuscarClienteUseCase {

    private ClienteRepositoryORM clienteRepository;


    public BuscarClienteUseCase() {
        ClienteRepositoryORM clienteRepository = DaoFactory.getInstance().getClienteRepositoryORM();
        this.clienteRepository = clienteRepository;
    }

    public Optional<ClienteORM> buscarClientePorCpfORM(Cpf cpf) {        ;
        return this.clienteRepository.buscarPorCpf(cpf);
    }

    public Optional<List<ClienteORM>> buscarTodosClientes() {        ;
        return this.clienteRepository.listarTudo();
    }

}
