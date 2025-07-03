package com.desapega.Desapega_System.Domain.Repository;

import com.desapega.Desapega_System.Domain.Models.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {
    Optional<ItemPedido> findById(Long idPedido);
}
