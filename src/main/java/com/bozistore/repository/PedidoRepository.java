package com.bozistore.repository;

import com.bozistore.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    List<Pedido> findByCliente_Id(Long clienteId);

    List<Pedido> findByProduto_Id(Long produtoId);

    boolean existsByCliente_Id(Long clienteId);

    boolean existsByProduto_Id(Long produtoId);
}
