package com.bozistore.model.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record PedidoRequest(
        @NotNull Long clienteId,
        @NotNull Long produtoId,
        @NotNull @Min(1) Integer quantidade
) {
}
