package com.bozistore.service;

import com.bozistore.model.Produto;
import com.bozistore.model.dtos.ProdutoRequest;
import com.bozistore.model.dtos.ProdutoResponse;
import com.bozistore.model.exceptions.ConflictException;
import com.bozistore.model.exceptions.ResourceNotFoundException;
import com.bozistore.repository.PedidoRepository;
import com.bozistore.repository.ProdutoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository repo;
    private final PedidoRepository pedidos;

    public ProdutoService(ProdutoRepository repo, PedidoRepository pedidos) {
        this.repo = repo;
        this.pedidos = pedidos;
    }

    @Transactional
    public ProdutoResponse criar(ProdutoRequest request) {
        Produto produto = new Produto(request.nome(), request.preco(), request.estoque());
        return toResponse(repo.save(produto));
    }

    @Transactional(readOnly = true)
    public List<ProdutoResponse> listar() {
        return repo.findAll().stream().map(this::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public ProdutoResponse buscar(Long id) {
        return toResponse(entity(id));
    }

    @Transactional
    public ProdutoResponse atualizar(Long id, ProdutoRequest request) {
        Produto produto = entity(id);
        produto.setNome(request.nome());
        produto.setPreco(request.preco());
        produto.setEstoque(request.estoque());
        return toResponse(produto);
    }

    @Transactional
    public void excluir(Long id) {
        Produto produto = entity(id);
        if (pedidos.existsByProduto_Id(id)) {
            throw new ConflictException("Produto possui pedidos e não pode ser excluído");
        }
        repo.delete(produto);
    }

    private Produto entity(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado: " + id));
    }

    private ProdutoResponse toResponse(Produto produto) {
        return new ProdutoResponse(produto.getId(), produto.getNome(), produto.getPreco(), produto.getEstoque());
    }
}
