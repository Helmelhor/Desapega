package com.desapega.Desapega_System.Domain.Repository;

import com.desapega.Desapega_System.Domain.Models.NotaFiscal;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NotaFiscalRepository extends JpaRepository<NotaFiscal, Long> {
    Optional<NotaFiscal> findById(Long idNotaFiscal);
}
