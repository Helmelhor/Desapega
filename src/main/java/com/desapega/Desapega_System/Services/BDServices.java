package com.desapega.Desapega_System.Services;

import com.desapega.Desapega_System.Domain.Models.*;
import jakarta.persistence.*;

public class BDServices {

    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("desapega");

    public static void adicionarUsuario(Usuario usuario) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(usuario);
        em.getTransaction().commit();
        em.close();
    }

    public static void adicionarProduto(Produtos produto) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(produto);
        em.getTransaction().commit();
        em.close();
    }

    public static void criarPedido(Pedido pedido) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(pedido);
        em.getTransaction().commit();
        em.close();
    }

    public static void criarNotaFiscal(NotaFiscal notaFiscal) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(notaFiscal);
        em.getTransaction().commit();
        em.close();
    }

    public static void adicionarItemPedido(ItemPedido item) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(item);
        em.getTransaction().commit();
        em.close();
    }

    public static Usuario consultarUsuario(Long id) {
        EntityManager em = emf.createEntityManager();
        Usuario usuario = em.find(Usuario.class, id);
        em.close();
        return usuario;
    }

    public static Produtos consultarProduto(Long id) {
        EntityManager em = emf.createEntityManager();
        Produtos produto = em.find(Produtos.class, id);
        em.close();
        return produto;
    }

    public static Pedido consultarPedido(Long id) {
        EntityManager em = emf.createEntityManager();
        Pedido pedido = em.find(Pedido.class, id);
        em.close();
        return pedido;
    }

    public static NotaFiscal consultarNotaFiscal(Long id) {
        EntityManager em = emf.createEntityManager();
        NotaFiscal nf = em.find(NotaFiscal.class, id);
        em.close();
        return nf;
    }

    public static ItemPedido consultarItemPedido(Long id) {
        EntityManager em = emf.createEntityManager();
        ItemPedido item = em.find(ItemPedido.class, id);
        em.close();
        return item;
    }
}
