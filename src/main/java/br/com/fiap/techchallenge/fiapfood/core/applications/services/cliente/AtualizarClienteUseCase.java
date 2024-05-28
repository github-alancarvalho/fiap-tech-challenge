package br.com.fiap.techchallenge.fiapfood.core.applications.services.cliente;


import br.com.fiap.techchallenge.fiapfood.adapter.driven.infra.repositories.DaoFactory;
import br.com.fiap.techchallenge.fiapfood.core.domain.dto.ClienteDto;
import br.com.fiap.techchallenge.fiapfood.core.domain.ports.output.ClienteRepository;

import java.util.Optional;


public class AtualizarClienteUseCase {

    private ClienteRepository clienteRepository;


    public AtualizarClienteUseCase() {
        ClienteRepository clienteRepository = DaoFactory.getInstance().getClienteRepositoryORM();
        this.clienteRepository = clienteRepository;
    }

    public Optional<ClienteDto> atualizar(ClienteDto clienteDto) {        ;
        return this.clienteRepository.atualizar(clienteDto);
    }


}
