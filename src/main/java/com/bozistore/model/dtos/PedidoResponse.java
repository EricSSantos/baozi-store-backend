package com.bozistore.model.dtos;

public record PedidoResponse(Long id, Long clienteId, Long produtoId, Integer quantidade) {
}
