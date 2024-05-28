package br.com.fiap.techchallenge.fiapfood.adapter.driven.infra.repositories;

import br.com.fiap.techchallenge.fiapfood.adapter.driven.infra.repositories.mariadb.*;
import br.com.fiap.techchallenge.fiapfood.core.domain.ports.output.ClienteRepositoryORM;
import br.com.fiap.techchallenge.fiapfood.core.domain.ports.output.PagamentoRepositoryORM;
import br.com.fiap.techchallenge.fiapfood.core.domain.ports.output.PedidoRepositoryORM;
import br.com.fiap.techchallenge.fiapfood.core.domain.ports.output.ProdutoRepositoryORM;

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


    public ClienteRepositoryORM getClienteRepositoryORM() {
        return new ClienteDaoORM();
    }

    public ProdutoRepositoryORM getProdutoRepositoryORM() {
        return new ProdutoDaoORM();
    }

    public PedidoRepositoryORM getPedidoRepositoryORM() {
        return new PedidoDaoORM();
    }

    public PagamentoRepositoryORM getPagamentoRepositoryORM() {
        return new PagamentoDaoORM();
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
