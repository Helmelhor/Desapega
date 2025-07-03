package com.desapega.Desapega_System.Domain.Repository;

import com.desapega.Desapega_System.Domain.Models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findById(Long idUsuario);
}
