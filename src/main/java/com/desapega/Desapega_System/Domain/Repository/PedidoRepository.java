package com.desapega.Desapega_System.Domain.Repository;

import com.desapega.Desapega_System.Domain.Models.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PedidoRepository  extends JpaRepository<Pedido, Long> {
    Optional<Pedido> findById(Long idPedido);
}

