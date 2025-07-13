package view;

import com.desapega.Desapega_System.Domain.Controllers.CriacaoPagamento;
import com.desapega.Desapega_System.Domain.Models.PedidoPagamento;
import com.desapega.Desapega_System.Domain.Models.Produtos;
import com.desapega.Desapega_System.Services.BDServices;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class JTelaPagamentoPDV extends JFrame {

    private static final long serialVersionUID = 1L;
    private final JComboBox<Produtos> comboProdutos;
    private final JButton botaoAdicionar;
    private final JTable tabelaItens;
    private final DefaultTableModel tableModel;
    private final JLabel labelTotal;
    private final JButton botaoCancelar;
    private final JComboBox<String> comboFormaPagamento;
    private JButton btnNewButton;

    private void atualizarTotal() {
        double totalVenda = 0.0;
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            String valor = tableModel.getValueAt(i, 3).toString().replace("R$", "").trim().replace(",", ".");
            totalVenda += Double.parseDouble(valor);
        }
        labelTotal.setText(String.format("Total da venda: R$ %.2f", totalVenda));
    }

    private List<PedidoPagamento.Item> montarItensParaPagamento() {
        List<PedidoPagamento.Item> itens = new ArrayList<>();

        for (int i = 0; i < tableModel.getRowCount(); i++) {
            String nome = tableModel.getValueAt(i, 0).toString();
            double preco = Double.parseDouble(tableModel.getValueAt(i, 1).toString().replace("R$", "").replace(",", ".").trim());
            int quantidade = Integer.parseInt(tableModel.getValueAt(i, 2).toString());

            PedidoPagamento.Item item = new PedidoPagamento.Item();
            item.setId(i + 1); // ou ID real do banco
            item.setTitle(nome);
            item.setCurrency_id("BRL");
            item.setQuantity(quantidade);
            item.setUnit_price(preco);

            itens.add(item);
        }

        return itens;
    }

    public JTelaPagamentoPDV() {

        setTitle("PDV - Caixa Aberto");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(650, 700);
        setLocationRelativeTo(null);

        JPanel contentPane = new JPanel(new BorderLayout(10, 10));
        contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));
        setContentPane(contentPane);

        JPanel painelNorte = new JPanel(new BorderLayout(5, 5));
        painelNorte.add(new JLabel("Selecionar produto:"), BorderLayout.NORTH);

        // Criar e configurar o JComboBox
        comboProdutos = new JComboBox<>();
        comboProdutos.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                if (value instanceof Produtos) {
                    Produtos produto = (Produtos) value;
                    value = produto.getNomeProduto() + " - R$ " + produto.getPrecoProduto();
                }
                return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            }
        });

        // Carregar produtos do banco
        List<Produtos> produtos = BDServices.consultarTodosProdutos();
        for (Produtos produto : produtos) {
            comboProdutos.addItem(produto);
        }

        painelNorte.add(comboProdutos, BorderLayout.CENTER);

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

        botaoAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Produtos produtoSelecionado = (Produtos) comboProdutos.getSelectedItem();
                if (produtoSelecionado != null) {
                    String nome = produtoSelecionado.getNomeProduto();
                    double precoUnitario = produtoSelecionado.getPrecoProduto().doubleValue();
                    int quantidade = 1;
                    double total = precoUnitario * quantidade;

                    tableModel.addRow(new Object[]{
                            nome,
                            String.format("R$ %.2f", precoUnitario),
                            quantidade,
                            String.format("R$ %.2f", total)
                    });

                    atualizarTotal();
                    btnNewButton.setEnabled(true);
                }
            }
        });

        tabelaItens.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int linhaSelecionada = tabelaItens.getSelectedRow();

                if (linhaSelecionada != -1 && SwingUtilities.isLeftMouseButton(evt)) {
                    int opcao = JOptionPane.showConfirmDialog(
                            JTelaPagamentoPDV.this,
                            "Deseja remover este item da venda?",
                            "Confirmar remoção",
                            JOptionPane.YES_NO_OPTION
                    );

                    if (opcao == JOptionPane.YES_OPTION) {
                        tableModel.removeRow(linhaSelecionada);
                        atualizarTotal();

                        if (tableModel.getRowCount() == 0) {
                            btnNewButton.setEnabled(false);
                        }
                    }
                }
            }
        });

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

        botaoCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int linhas = tableModel.getRowCount();
                for (int i = linhas - 1; i >= 0; i--) {
                    tableModel.removeRow(i);
                }
                labelTotal.setText("Total da venda: R$ 0.00");
                btnNewButton.setEnabled(false);
            }
        });

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

        painelSul.add(painelBotoesFinais, BorderLayout.SOUTH);

        contentPane.add(painelSul, BorderLayout.SOUTH);

        btnNewButton = new JButton("Finalizar Compra");
        btnNewButton.setEnabled(false);
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        painelSul.add(btnNewButton, BorderLayout.CENTER);

        btnNewButton.addActionListener(e -> {
            List<PedidoPagamento.Item> itens = montarItensParaPagamento();

            CriacaoPagamento pagamentoController = new CriacaoPagamento();
            String urlPagamento = pagamentoController.criarPagamentoComItens(itens);

            if (urlPagamento != null) {
                try {
                    Desktop.getDesktop().browse(new URI(urlPagamento));
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Erro ao abrir o navegador para pagamento.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao criar link de pagamento.");
            }
        });

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
