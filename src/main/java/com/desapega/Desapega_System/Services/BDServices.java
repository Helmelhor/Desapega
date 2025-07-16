
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

    //consultando todos os pedidos
//    public static List<Pedido> consultarTodosPedidos() {
//        EntityManager em = emf.createEntityManager();
//        try {
//            List<Pedido> pedidos = em.createQuery("SELECT p FROM Pedido p LEFT JOIN FETCH p.itensPedido", Pedido.class).getResultList();
    // Retorna lista de nome e estoque dos produtos para o gráfico de pizza
    public static List<Object[]> consultarNomeEEstoqueProdutos() {
        EntityManager em = emf.createEntityManager();
        try {
            String jpql = "SELECT p.nomeProduto, p.estoqueProduto FROM Produtos p";
            TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
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

    public static Usuario autenticar(String usuarioDigitado, String senhaDigitada) {
        EntityManager em = emf.createEntityManager();

        try {
            TypedQuery<Usuario> query = em.createQuery(
                    "SELECT u FROM Usuario u WHERE u.emailUsuario = :email", Usuario.class);
            query.setParameter("email", usuarioDigitado);

            List<Usuario> resultado = query.getResultList();

            Usuario usuario = resultado.get(0);
            if (BCrypt.checkpw(senhaDigitada, usuario.getSenhaUsuario())) {
                return usuario;
            }
            return null;
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

    public static void atualizarEstoque(Produtos produtoAtualizado) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = null;

        try {
            tx = em.getTransaction();
            tx.begin();

            Produtos produtoExistente = em.find(Produtos.class, produtoAtualizado.getIdProduto());
            if (produtoExistente != null) {
                produtoExistente.setEstoqueProduto(produtoAtualizado.getEstoqueProduto());
            }

            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public static Produtos buscarProdutoPorNome(String nomeProduto) {
        EntityManager em = emf.createEntityManager();

        try {
            TypedQuery<Produtos> query = em.createQuery(
                    "SELECT p FROM Produtos p WHERE p.nomeProduto = :nome", Produtos.class);
            query.setParameter("nome", nomeProduto);

            List<Produtos> resultado = query.getResultList();
            return resultado.isEmpty() ? null : resultado.get(0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

}
