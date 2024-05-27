package br.com.fiap.techchallenge.fiapfood.core.applications.services.cliente;


import br.com.fiap.techchallenge.fiapfood.adapter.driven.infra.repositories.DaoFactory;
import br.com.fiap.techchallenge.fiapfood.core.domain.dto.ClienteORM;
import br.com.fiap.techchallenge.fiapfood.core.domain.ports.output.ClienteRepositoryORM;
import br.com.fiap.techchallenge.fiapfood.core.domain.valueobject.Cpf;

import java.util.Optional;


public class ExcluirClienteUseCase {

    private ClienteRepositoryORM clienteRepository;


    public ExcluirClienteUseCase() {
        ClienteRepositoryORM clienteRepository = DaoFactory.getInstance().getClienteRepositoryORM();
        this.clienteRepository = clienteRepository;
    }

    public Boolean excluir(Cpf cpf) {        ;
        return this.clienteRepository.excluir(cpf);
    }


}
