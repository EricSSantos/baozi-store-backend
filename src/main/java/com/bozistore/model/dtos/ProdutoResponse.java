package com.bozistore.model.dtos;

import java.math.BigDecimal;

public record ProdutoResponse(Long id, String nome, BigDecimal preco, Boolean estoque) {
}
