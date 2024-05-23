package br.com.fiap.techchallenge.fiapfood.core.applications.services;


import br.com.fiap.techchallenge.fiapfood.adapter.driven.infra.repositories.DaoFactory;
import br.com.fiap.techchallenge.fiapfood.core.applications.ports.ClientePort;
import br.com.fiap.techchallenge.fiapfood.core.domain.entities.Categoria;
import br.com.fiap.techchallenge.fiapfood.core.domain.entities.Cliente;
import br.com.fiap.techchallenge.fiapfood.core.domain.entities.Produto;
import br.com.fiap.techchallenge.fiapfood.core.domain.valueobject.Cpf;

import java.util.List;
import java.util.Optional;

//Regras de negócio
public class ClienteService {

    private final ClientePort clienteRepository;

//    public ClienteService(ClientePort clienteRepository) {
//        this.clienteRepository = clienteRepository;
//    }

    public ClienteService() {
         ClientePort clienteRepository = DaoFactory.getInstance().getClienteRepository();
        this.clienteRepository = clienteRepository;
    }

    public Optional<Cliente> buscarPorCpf(Cpf cpf) {

        //return Optional.of(new Cliente());
        return this.clienteRepository.buscarPorCpf(cpf);
    }

    public Optional<Cliente> inserirCliente(Cliente cliente) {
        return this.clienteRepository.inserirCliente(cliente);
    }

    public Optional<Cliente> atualizar(Cliente cliente) {
        return this.clienteRepository.atualizar(cliente);
    }

    public Boolean excluir(Cliente cliente) {
        return this.clienteRepository.excluir(cliente);
    }

    public Optional<List<Cliente>> buscarTudo() {
        return this.clienteRepository.listarTudo();
    }
}
