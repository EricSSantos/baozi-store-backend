package com.bozistore.service;

import com.bozistore.model.Cliente;
import com.bozistore.model.dtos.ClienteRequest;
import com.bozistore.model.dtos.ClienteResponse;
import com.bozistore.model.exceptions.ConflictException;
import com.bozistore.model.exceptions.ResourceNotFoundException;
import com.bozistore.repository.ClienteRepository;
import com.bozistore.repository.PedidoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository repo;
    private final PedidoRepository pedidos;

    public ClienteService(ClienteRepository repo, PedidoRepository pedidos) {
        this.repo = repo;
        this.pedidos = pedidos;
    }

    @Transactional
    public ClienteResponse criar(ClienteRequest request) {
        Cliente cliente = new Cliente(request.nome(), request.clienteDesde());
        return toResponse(repo.save(cliente));
    }

    @Transactional(readOnly = true)
    public List<ClienteResponse> listar() {
        return repo.findAll().stream().map(this::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public ClienteResponse buscar(Long id) {
        return toResponse(entity(id));
    }

    @Transactional
    public ClienteResponse atualizar(Long id, ClienteRequest request) {
        Cliente cliente = entity(id);
        cliente.setNome(request.nome());
        cliente.setClienteDesde(request.clienteDesde());
        return toResponse(cliente);
    }

    @Transactional
    public void excluir(Long id) {
        Cliente cliente = entity(id);
        if (pedidos.existsByCliente_Id(id)) {
            throw new ConflictException("Cliente possui pedidos e não pode ser excluído");
        }
        repo.delete(cliente);
    }

    private Cliente entity(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado: " + id));
    }

    private ClienteResponse toResponse(Cliente cliente) {
        return new ClienteResponse(cliente.getId(), cliente.getNome(), cliente.getClienteDesde());
    }
}
