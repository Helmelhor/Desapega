package com.desapega.Desapega_System.Domain.Repository;

import com.desapega.Desapega_System.Domain.Models.Produtos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProdutosRepository extends JpaRepository<Produtos, Long> {

    Optional<Produtos> findById(Long idProdutos);
}
