package com.stockproject.stockmarket.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Stock {

    @JsonProperty("stockName")
    String stockName;

    @JsonProperty("currentPrice")
    Integer currentPrice;

    public String getStockName() {
        return stockName;
    }

    public Stock setStockName(String stockName) {
        this.stockName = stockName;
        return this;
    }

    public Integer getCurrentPrice() {
        return currentPrice;
    }

    public Stock setCurrentPrice(Integer currentPrice) {
        this.currentPrice = currentPrice;
        return this;
    }
    public String toString(Stock stock) {
        return "{" +
                "\"stockName\":\"" + stock.getStockName()+"\","+
                "\"currentPrice\":\"" + stock.getCurrentPrice()+"\"" +
                "}";
    }

}
