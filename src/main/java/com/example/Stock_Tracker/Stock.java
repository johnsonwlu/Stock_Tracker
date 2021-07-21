package com.example.Stock_Tracker;

public class Stock {
    private String stockName;
    private int stockCount;

    public Stock(String stockName, int stockCount) {
        this.stockName  = stockName;
        this.stockCount = stockCount;
    }

    public String getStockName() {
        return stockName;
    }

    public int getCount() {
        return stockCount;
    }
}
