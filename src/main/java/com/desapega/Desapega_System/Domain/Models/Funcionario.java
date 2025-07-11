package com.desapega.Desapega_System.Domain.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "funcionarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_funcionario", nullable = false, length = 100)
    private String nome;

    @Column(name = "cpf", nullable = false, unique = true, length = 14)
    private String cpf;

    @Column(name = "data_nascimento", nullable = false, length = 10)
    private String dataNascimento; // dd/MM/yyyy

    @Column(name = "sexo", nullable = false, length = 10)
    private String sexo;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Column(name = "telefone", nullable = true, length = 20)
    private String telefone;
}

