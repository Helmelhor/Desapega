package com.desapega.Desapega_System.Domain.Models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
public class PedidoPagamento {
    private List<Item> items;

    public List<Item> getItens() {
        return items;
    }

    @Setter
    @Getter
    public static class Item {
        private int id;
        private String title;
        private String description;
        private String currency_id;
        private int quantity;
        private double unit_price;

    }

}
