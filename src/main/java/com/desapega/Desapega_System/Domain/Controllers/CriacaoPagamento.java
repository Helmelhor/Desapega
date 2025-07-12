package com.desapega.Desapega_System.Domain.Controllers;

import com.desapega.Desapega_System.Domain.Models.PedidoPagamento;
import com.desapega.Desapega_System.Domain.Models.RespostaPagamento;
import com.desapega.Desapega_System.Domain.Utils.Constantes;
import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class CriacaoPagamento {

    public static void main(String[] args) {
        CriacaoPagamento criacaoPagamento = new CriacaoPagamento();

    }

    public String criarPagamentoComItens(List<PedidoPagamento.Item> itens) {
        try {
            PedidoPagamento pagamento = new PedidoPagamento();
            pagamento.setItems(itens);

            Gson gson = new Gson();
            String corpoRequisicao = gson.toJson(pagamento);

            HttpRequest requisicao = HttpRequest.newBuilder()
                    .uri(new URI("https://api.mercadopago.com/checkout/preferences"))
                    .header("Authorization", "Bearer " + Constantes.Chave_acesso)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(corpoRequisicao))
                    .build();

            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> resposta = client.send(requisicao, HttpResponse.BodyHandlers.ofString());

            RespostaPagamento respostaPagamento = gson.fromJson(resposta.body(), RespostaPagamento.class);

            return respostaPagamento.getSandbox_init_point();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
