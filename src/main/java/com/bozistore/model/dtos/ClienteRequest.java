package com.bozistore.model.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record ClienteRequest(
        @NotBlank @Size(max = 120) String nome,
        @NotNull LocalDate clienteDesde
) {
}
