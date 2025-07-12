package com.desapega.Desapega_System.Domain.Models;

import com.desapega.Desapega_System.Domain.Enum.TipoUsuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuario_table")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_usuario;

    @Column(name = "nome_usuario", nullable = false)
    private String nomeUsuario;

    @Column(name = "telefone_usuario", nullable = false)
    private String telefoneUsuario;

    @Column(name = "email_usuario", nullable = false)
    private String emailUsuario;

    @Column(name = "senha_usuario", nullable = false)
    private String senhaUsuario;

    //tipo enum
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_usuario", nullable = false)
    private TipoUsuario tipo_usuario;

}
