package view;

import com.desapega.Desapega_System.Domain.Models.Produtos;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class JEstoque extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    List<Produtos> listaProdutos = List.of();
                    JEstoque frame = new JEstoque(listaProdutos);
                    //centralizando a tela
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public JEstoque(List<Produtos> listaProdutos) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(10, 10));

        JLabel titulo = new JLabel("ESTOQUE DE PRODUTOS", SwingConstants.CENTER);
        titulo.setFont(new Font("Tahoma", Font.BOLD, 22));
        contentPane.add(titulo, BorderLayout.NORTH);

        String[] colunas = {"Código", "Nome", "Quantidade", "Descrição"};
        Object[][] dados = new Object[listaProdutos.size()][4];

        for (int i = 0; i < listaProdutos.size(); i++) {
            Produtos p = listaProdutos.get(i);
            dados[i][0] = p.getIdProduto();
            dados[i][1] = p.getNomeProduto();
            dados[i][2] = p.getEstoqueProduto();
            dados[i][3] = p.getDescricaoProduto();
        }

        JTable tabela = new JTable(dados, colunas);
        JScrollPane scrollPane = new JScrollPane(tabela);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(e -> {
            JTelaPrincipal telaHome = new JTelaPrincipal();
            telaHome.setVisible(true);
            dispose();
        });
        contentPane.add(btnVoltar, BorderLayout.SOUTH);
    }
}
