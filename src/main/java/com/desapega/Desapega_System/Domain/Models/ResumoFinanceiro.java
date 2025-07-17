package com.desapega.Desapega_System.Domain.Models;

import com.desapega.Desapega_System.Domain.Enum.TipoResumoFinanceiro;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "resumo_financeiro")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResumoFinanceiro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "data", nullable = false)
    private LocalDateTime data;

    @Column(name = "valor_total", nullable = false)
    private BigDecimal valorTotal;

    @Column(name = "quantidade", nullable = false)
    private int quantidade;

    @Column(name = "tipo", nullable = false)
    private TipoResumoFinanceiro tipo;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produtos", nullable = false)
    private Produtos produto;

}
