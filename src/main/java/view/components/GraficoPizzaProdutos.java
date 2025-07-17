package view.components;

import com.desapega.Desapega_System.Services.BDServices;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GraficoPizzaProdutos extends JPanel {
    private ChartPanel chartPanel;

    public GraficoPizzaProdutos() {
        setLayout(new BorderLayout());
        chartPanel = criarPainelGrafico();
        add(chartPanel, BorderLayout.CENTER);
    }

    private ChartPanel criarPainelGrafico() {
        JFreeChart chart = ChartFactory.createPieChart(
                "Distribuição dos Produtos em Estoque",
                criarDataset(),
                true, true, false);
        return new ChartPanel(chart);
    }

    private DefaultPieDataset<String> criarDataset() {
        DefaultPieDataset<String> dataset = new DefaultPieDataset<>();
        try {
            List<Object[]> dados = BDServices.consultarNomeEEstoqueProdutos();
            for (Object[] linha : dados) {
                String nome = (String) linha[0];
                Integer estoque = (Integer) linha[1];
                if (estoque != null && estoque > 0) {
                    dataset.setValue(nome + " (" + estoque + ")", estoque);
                }
            }
        } catch (Exception e) {
            dataset.setValue("Sem dados", 1);
        }
        return dataset;
    }

    public void atualizarDados() {
        JFreeChart chart = ChartFactory.createPieChart(
                "Distribuição dos Produtos em Estoque",
                criarDataset(),
                true, true, false);
        chartPanel.setChart(chart);
    }
}
