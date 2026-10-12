package com.bozistore.model.dtos;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record ProdutoRequest(
        @NotBlank @Size(max = 160) String nome,
        @NotNull @DecimalMin("0.00") @Digits(integer = 10, fraction = 2) BigDecimal preco,
        @NotNull Boolean estoque
) {
}
