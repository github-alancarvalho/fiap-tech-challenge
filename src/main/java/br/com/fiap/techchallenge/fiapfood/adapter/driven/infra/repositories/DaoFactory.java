package br.com.fiap.techchallenge.fiapfood.adapter.driven.infra.repositories;

import br.com.fiap.techchallenge.fiapfood.adapter.driven.infra.repositories.mariadb.ClienteRepositoryImpl;
import br.com.fiap.techchallenge.fiapfood.adapter.driven.infra.repositories.mariadb.PagamentoRepositoryImpl;
import br.com.fiap.techchallenge.fiapfood.adapter.driven.infra.repositories.mariadb.PedidoRepositoryImpl;
import br.com.fiap.techchallenge.fiapfood.adapter.driven.infra.repositories.mariadb.ProdutoRepositoryImpl;
import br.com.fiap.techchallenge.fiapfood.core.applications.ports.ClientePort;
import br.com.fiap.techchallenge.fiapfood.core.applications.ports.PagamentoPort;
import br.com.fiap.techchallenge.fiapfood.core.applications.ports.PedidoPort;
import br.com.fiap.techchallenge.fiapfood.core.applications.ports.ProdutoPort;

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

    public ClientePort getClienteRepository() {
        return new ClienteRepositoryImpl();
    }

    public ProdutoPort getProdutoRepository() {
        return new ProdutoRepositoryImpl();
    }

    public PedidoPort getPedidoRepository() {
        return new PedidoRepositoryImpl();
    }

    public PagamentoPort getPagamentoRepository() {
        return new PagamentoRepositoryImpl();
    }
}
