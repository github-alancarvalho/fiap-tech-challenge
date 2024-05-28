package br.com.fiap.techchallenge.fiapfood.core.applications.services.cliente;


import br.com.fiap.techchallenge.fiapfood.adapter.driven.infra.repositories.DaoFactory;
import br.com.fiap.techchallenge.fiapfood.core.domain.dto.ClienteDto;
import br.com.fiap.techchallenge.fiapfood.core.domain.ports.output.ClienteRepository;

import java.util.Optional;


public class InserirClienteUseCase {

    private ClienteRepository clienteRepository;


    public InserirClienteUseCase() {
        ClienteRepository clienteRepository = DaoFactory.getInstance().getClienteRepositoryORM();
        this.clienteRepository = clienteRepository;
    }

    public Optional<ClienteDto> inserirClienteORM(ClienteDto clienteDto) {        ;
        return this.clienteRepository.inserirClienteORM(clienteDto);
//        return null;
    }


}
