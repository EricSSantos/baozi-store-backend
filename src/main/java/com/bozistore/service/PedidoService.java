package com.bozistore.service;

import com.bozistore.model.Cliente;
import com.bozistore.model.Pedido;
import com.bozistore.model.Produto;
import com.bozistore.model.dtos.PedidoRequest;
import com.bozistore.model.dtos.PedidoResponse;
import com.bozistore.model.exceptions.ResourceNotFoundException;
import com.bozistore.repository.ClienteRepository;
import com.bozistore.repository.PedidoRepository;
import com.bozistore.repository.ProdutoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository pedidos;
    private final ClienteRepository clientes;
    private final ProdutoRepository produtos;

    public PedidoService(PedidoRepository pedidos, ClienteRepository clientes, ProdutoRepository produtos) {
        this.pedidos = pedidos;
        this.clientes = clientes;
        this.produtos = produtos;
    }

    @Transactional
    public PedidoResponse criar(PedidoRequest request) {
        Pedido pedido = new Pedido(cliente(request.clienteId()), produto(request.produtoId()), request.quantidade());
        return toResponse(pedidos.save(pedido));
    }

    @Transactional(readOnly = true)
    public List<PedidoResponse> listar() {
        return pedidos.findAll().stream().map(this::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public PedidoResponse buscar(Long id) {
        return toResponse(pedido(id));
    }

    @Transactional
    public PedidoResponse atualizar(Long id, PedidoRequest request) {
        Pedido pedido = pedido(id);
        pedido.setCliente(cliente(request.clienteId()));
        pedido.setProduto(produto(request.produtoId()));
        pedido.setQuantidade(request.quantidade());
        return toResponse(pedido);
    }

    @Transactional
    public void excluir(Long id) {
        pedidos.delete(pedido(id));
    }

    private Pedido pedido(Long id) {
        return pedidos.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido não encontrado: " + id));
    }

    private Cliente cliente(Long id) {
        return clientes.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado: " + id));
    }

    private Produto produto(Long id) {
        return produtos.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado: " + id));
    }

    private PedidoResponse toResponse(Pedido pedido) {
        return new PedidoResponse(
                pedido.getId(),
                pedido.getCliente().getId(),
                pedido.getProduto().getId(),
                pedido.getQuantidade()
        );
    }
}
