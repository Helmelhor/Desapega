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
    public GraficoPizzaProdutos() {
        setLayout(new BorderLayout());
        add(criarPainelGrafico(), BorderLayout.CENTER);
    }

    private ChartPanel criarPainelGrafico() {
        DefaultPieDataset<String> dataset = new DefaultPieDataset<>();
        try {
            List<Object[]> dados = BDServices.consultarNomeEEstoqueProdutos();
            for (Object[] linha : dados) {
                String nome = (String) linha[0];
                Integer estoque = (Integer) linha[1];
                if (estoque != null && estoque > 0) {
                    // Mostra nome na legenda e nome + quantidade na fatia
                    dataset.setValue(nome + " (" + estoque + ")", estoque);
                }
            }
        } catch (Exception e) {
            dataset.setValue("Sem dados", 1);
        }
        JFreeChart chart = ChartFactory.createPieChart(
                "Distribuição dos Produtos em Estoque",
                dataset,
                true, true, false);
        return new ChartPanel(chart);
    }
}
