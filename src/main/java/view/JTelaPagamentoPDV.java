package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class JTelaPagamentoPDV extends JFrame {

    private static final long serialVersionUID = 1L;
    private final JTextField campoBusca;
    private final JButton botaoAdicionar;
    private final JTable tabelaItens;
    private final DefaultTableModel tableModel;
    private final JLabel labelTotal;
    private final JButton botaoCancelar;
    private final JComboBox<String> comboFormaPagamento;
    private JButton btnNewButton;

    public JTelaPagamentoPDV() {

        setTitle("PDV - Caixa Aberto");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(650, 700);
        setLocationRelativeTo(null);

        JPanel contentPane = new JPanel(new BorderLayout(10, 10));
        contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));
        setContentPane(contentPane);

        JPanel painelNorte = new JPanel(new BorderLayout(5, 5));
        painelNorte.add(new JLabel("Buscar produto (código ou nome):"), BorderLayout.NORTH);
        campoBusca = new JTextField();
        painelNorte.add(campoBusca, BorderLayout.CENTER);
        botaoAdicionar = new JButton("Adicionar");
        painelNorte.add(botaoAdicionar, BorderLayout.EAST);
        contentPane.add(painelNorte, BorderLayout.NORTH);

        String[] colunas = {"Nome", "Preço Unitário", "Qtd.", "Total"};
        tableModel = new DefaultTableModel(null, colunas) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabelaItens = new JTable(tableModel);
        tabelaItens.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tabelaItens.setRowHeight(25);

        JScrollPane scrollPane = new JScrollPane(tabelaItens);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        JPanel painelSul = new JPanel(new BorderLayout(10, 10));
        labelTotal = new JLabel("Total da venda: R$ 0.00");
        labelTotal.setFont(new Font("Arial", Font.BOLD, 22));
        labelTotal.setHorizontalAlignment(SwingConstants.CENTER);
        painelSul.add(labelTotal, BorderLayout.NORTH);

        JPanel painelBotoesFinais = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        botaoCancelar = new JButton("Cancelar Venda");
        String[] formasPagamento = {"Dinheiro", "Cartão de Crédito", "Cartão de Débito", "Pix"};
        comboFormaPagamento = new JComboBox<>(formasPagamento);
        comboFormaPagamento.setPreferredSize(new Dimension(160, 30));

        // NOVO: Botão Voltar para a tela principal
        JButton botaoVoltar = new JButton("Voltar");
        botaoVoltar.addActionListener(e -> {
            JTelaPrincipal telaPrincipal = new JTelaPrincipal();
            telaPrincipal.setVisible(true);
            dispose();
        });

        painelBotoesFinais.add(botaoVoltar);
        painelBotoesFinais.add(botaoCancelar);
        painelBotoesFinais.add(new JLabel("Forma de Pagamento:"));
        painelBotoesFinais.add(comboFormaPagamento);

        painelSul.add(painelBotoesFinais, BorderLayout.CENTER);

        contentPane.add(painelSul, BorderLayout.SOUTH);
        
        btnNewButton = new JButton("Finalizar Compra");
        btnNewButton.setEnabled(false);
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        painelSul.add(btnNewButton, BorderLayout.EAST);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            try {
                // Melhora a aparência para o look and feel do sistema operacional
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            JTelaPagamentoPDV frame = new JTelaPagamentoPDV();
          //centralizando a tela
			frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
