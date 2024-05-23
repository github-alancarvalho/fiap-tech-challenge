package br.com.fiap.techchallenge.fiapfood.core.applications.services;


import br.com.fiap.techchallenge.fiapfood.adapter.driven.infra.repositories.DaoFactory;
import br.com.fiap.techchallenge.fiapfood.core.applications.ports.PagamentoPort;
import br.com.fiap.techchallenge.fiapfood.core.applications.ports.PedidoPort;
import br.com.fiap.techchallenge.fiapfood.core.domain.base.StatusPedido;
import br.com.fiap.techchallenge.fiapfood.core.domain.entities.Categoria;
import br.com.fiap.techchallenge.fiapfood.core.domain.entities.ItemPedido;
import br.com.fiap.techchallenge.fiapfood.core.domain.entities.Pagamento;
import br.com.fiap.techchallenge.fiapfood.core.domain.entities.Pedido;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PedidoService {

    private final PedidoPort pedidoRepository;

    public PedidoService() {
        PedidoPort PedidoRepository = DaoFactory.getInstance().getPedidoRepository();
        this.pedidoRepository = PedidoRepository;
    }

    public Optional<List<Pedido>> buscarTudo() {
        return this.pedidoRepository.listarTudo();
    }

    public Optional<Pedido> inserir(Pedido pedido) {
        return this.pedidoRepository.inserir(pedido);
    }

    public Optional<Pedido> atualizarProgresso(Pedido pedido, StatusPedido novoStatus) {
        return this.pedidoRepository.atualizarProgresso(pedido, novoStatus);
    }

    public Optional<Pedido> buscarPorId(Long id) {
        return this.pedidoRepository.buscarPorId(id);
    }

    public Optional<Pedido> buscarPedidoCompletoPorId(Long id) {
        return this.pedidoRepository.buscarPedidoCompletoPorId(id);
    }

    public Boolean excluir(Pedido pedido) {
        return this.pedidoRepository.excluir(pedido);
    }

    public Optional<List<Pedido>> buscarPedidosEmAberto() {
        return this.pedidoRepository.listarPedidosEmAberto();
    }

    public Optional<List<Pedido>> buscarPedidosPorStatus(StatusPedido status) {
        return this.pedidoRepository.listarPedidosPorStatus(status);
    }

    public Double calcularValorTotalDoPedido(Pedido pedido) {

        List<ItemPedido> list = new ArrayList<>();
        Double valorTotalPedido = 0D;
        Double valorProduto = 0D;

        for(ItemPedido ip : pedido.getListItens()){
            valorProduto = ip.getProduto().getPreco();
            valorTotalPedido+= valorProduto * ip.getQuantidade();
        }
        return valorTotalPedido;
    }
}
