package br.com.fiap.techchallenge.fiapfood.core.applications.services.cliente;


import br.com.fiap.techchallenge.fiapfood.adapter.driven.infra.repositories.DaoFactory;
import br.com.fiap.techchallenge.fiapfood.core.domain.dto.ClienteORM;
import br.com.fiap.techchallenge.fiapfood.core.domain.ports.output.ClienteRepositoryORM;

import java.util.Optional;


public class InserirClienteUseCase {

    private ClienteRepositoryORM clienteRepository;


    public InserirClienteUseCase() {
        ClienteRepositoryORM clienteRepository = DaoFactory.getInstance().getClienteRepositoryORM();
        this.clienteRepository = clienteRepository;
    }

    public Optional<ClienteORM> inserirClienteORM(ClienteORM clienteORM) {        ;
        return this.clienteRepository.inserirClienteORM(clienteORM);
//        return null;
    }


}
