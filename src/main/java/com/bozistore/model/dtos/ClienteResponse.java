package com.bozistore.model.dtos;

import java.time.LocalDate;

public record ClienteResponse(Long id, String nome, LocalDate clienteDesde) {
}
