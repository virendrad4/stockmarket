package com.stockproject.stockmarket.service;

import com.stockproject.stockmarket.model.Stock;
import com.stockproject.stockmarket.model.StockModel;
import java.util.List;

public interface StockService {
    List<StockModel> getListOfStocks();
    StockModel getStockById(int id);
    StockModel save(Stock stock);
    StockModel update(int id, Stock stock);
    //deleting a specific record
    void delete(int id);
    void delete(String stockName);
}
