package com.desapega.Desapega_System.Services;

import com.desapega.Desapega_System.Domain.Models.*;
import jakarta.persistence.*;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;

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

    //consultando todos os pedidos
//    public static List<Pedido> consultarTodosPedidos() {
//        EntityManager em = emf.createEntityManager();
//        try {
//            List<Pedido> pedidos = em.createQuery("SELECT p FROM Pedido p LEFT JOIN FETCH p.itensPedido", Pedido.class).getResultList();
//            return pedidos;
//        } finally {
//            em.close();
//        }
//    }
    public static List<Pedido> consultarTodosPedidos() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT p FROM Pedido p", Pedido.class).getResultList();
        } finally {
            em.close();
        }
    }


    public static void adicionarFuncionario(Funcionario funcionario) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(funcionario);
        em.getTransaction().commit();
        em.close();
        //emf.close();
    }

    public static List<Produtos> consultarTodosProdutos() {
        EntityManager em = emf.createEntityManager();
        List<Produtos> produtos = em.createQuery("SELECT p FROM Produtos p", Produtos.class).getResultList();
        em.close();
        return produtos;
    }

    public static boolean autenticar(String usuarioDigitado, String senhaDigitada) {
        EntityManager em = emf.createEntityManager();

        try {
            TypedQuery<Usuario> query = em.createQuery(
                    "SELECT u FROM Usuario u WHERE u.emailUsuario = :email", Usuario.class);
            query.setParameter("email", usuarioDigitado);

            List<Usuario> resultado = query.getResultList();

            if (!resultado.isEmpty()) {
                Usuario usuario = resultado.get(0);
                return BCrypt.checkpw(senhaDigitada, usuario.getSenhaUsuario());
            }
            return false;
        } finally {
            em.close();
        }
    }

    public static List<Produtos> buscarProdutos(String busca) {
        EntityManager em = emf.createEntityManager();
        try {
            String jpql = "SELECT p FROM Produtos p WHERE LOWER(p.nomeProduto) LIKE LOWER(:termo) OR CAST(p.id AS string) = :id";
            TypedQuery<Produtos> query = em.createQuery(jpql, Produtos.class);
            query.setParameter("termo", "%" + busca + "%");
            query.setParameter("id", busca);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

}
