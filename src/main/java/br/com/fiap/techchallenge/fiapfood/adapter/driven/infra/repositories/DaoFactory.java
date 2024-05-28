package br.com.fiap.techchallenge.fiapfood.adapter.driven.infra.repositories;

import br.com.fiap.techchallenge.fiapfood.adapter.driven.infra.repositories.mariadb.*;
import br.com.fiap.techchallenge.fiapfood.core.domain.ports.output.ClienteRepository;
import br.com.fiap.techchallenge.fiapfood.core.domain.ports.output.PagamentoRepository;
import br.com.fiap.techchallenge.fiapfood.core.domain.ports.output.PedidoRepository;
import br.com.fiap.techchallenge.fiapfood.core.domain.ports.output.ProdutoRepository;

public class DaoFactory {

    private static DaoFactory daoFactory;

    private DaoFactory() {

    }

    public static DaoFactory getInstance() {
        if (daoFactory == null) {
            daoFactory = new DaoFactory();
            return daoFactory;
        } else
            return daoFactory;
    }


    public ClienteRepository getClienteRepositoryORM() {
        return new ClienteDao();
    }

    public ProdutoRepository getProdutoRepositoryORM() {
        return new ProdutoDao();
    }

    public PedidoRepository getPedidoRepositoryORM() {
        return new PedidoDao();
    }

    public PagamentoRepository getPagamentoRepositoryORM() {
        return new PagamentoDao();
    }


//    public ClientePort getClienteRepository() {
//        return new ClienteRepositoryImpl();
//    }
//
//    public ProdutoPort getProdutoRepository() {
//        return new ProdutoRepositoryImpl();
//    }
//
//    public PedidoPort getPedidoRepository() {
//        return new PedidoRepositoryImpl();
//    }
//
//    public PagamentoPort getPagamentoRepository() {
//        return new PagamentoRepositoryImpl();
//    }
}
