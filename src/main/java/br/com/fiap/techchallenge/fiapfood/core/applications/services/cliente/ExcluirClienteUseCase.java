package br.com.fiap.techchallenge.fiapfood.core.applications.services.cliente;


import br.com.fiap.techchallenge.fiapfood.adapter.driven.infra.repositories.DaoFactory;
import br.com.fiap.techchallenge.fiapfood.core.domain.ports.output.ClienteRepository;
import br.com.fiap.techchallenge.fiapfood.core.domain.valueobject.Cpf;


public class ExcluirClienteUseCase {

    private ClienteRepository clienteRepository;


    public ExcluirClienteUseCase() {
        ClienteRepository clienteRepository = DaoFactory.getInstance().getClienteRepositoryORM();
        this.clienteRepository = clienteRepository;
    }

    public Boolean excluir(Cpf cpf) {        ;
        return this.clienteRepository.excluir(cpf);
    }


}
