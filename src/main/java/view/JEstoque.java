package view;

import com.desapega.Desapega_System.Domain.Models.Produtos;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.List;

public class JEstoque extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    // Paleta de cores
    private final Color COLOR_BG = Color.decode("#153448");
    private final Color COLOR_BTN = Color.decode("#3C5B6F");
    private final Color COLOR_BTN_TEXT = Color.decode("#153448");
    private final Color COLOR_LABEL = Color.decode("#DFD0B8");
    private final Color COLOR_PANEL = Color.decode("#948979");

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
        contentPane.setBackground(COLOR_BG);
        contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(10, 10));

        JLabel titulo = new JLabel("ESTOQUE DE PRODUTOS", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titulo.setForeground(COLOR_LABEL);
        titulo.setBackground(COLOR_PANEL);
        titulo.setOpaque(true);
        titulo.setBorder(BorderFactory.createLineBorder(COLOR_LABEL, 2, true));
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
        tabela.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tabela.setRowHeight(26);
        tabela.setBackground(COLOR_PANEL);
        tabela.setForeground(COLOR_BG); // texto escuro
        tabela.setSelectionBackground(COLOR_BTN);
        tabela.setSelectionForeground(COLOR_BG);

        JTableHeader header = tabela.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 15));
        header.setBackground(COLOR_PANEL);
        header.setForeground(COLOR_BG); // cabeçalho escuro

        JScrollPane scrollPane = new JScrollPane(tabela);
        scrollPane.setBackground(COLOR_PANEL);
        scrollPane.getViewport().setBackground(COLOR_PANEL);
        scrollPane.setBorder(BorderFactory.createLineBorder(COLOR_LABEL, 2, true));
        contentPane.add(scrollPane, BorderLayout.CENTER);

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBackground(COLOR_BTN);
        btnVoltar.setForeground(COLOR_BTN_TEXT);
        btnVoltar.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btnVoltar.setFocusPainted(false);
        btnVoltar.setBorder(BorderFactory.createLineBorder(COLOR_LABEL, 2, true));
        btnVoltar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVoltar.addActionListener(e -> {
            JTelaPrincipal telaHome = new JTelaPrincipal();
            telaHome.setVisible(true);
            dispose();
        });
        contentPane.add(btnVoltar, BorderLayout.SOUTH);
    }
}
