package com.stockproject.stockmarket.service;

import com.stockproject.stockmarket.model.Stock;
import com.stockproject.stockmarket.model.StockModel;
import com.stockproject.stockmarket.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class StockServiceImpl implements StockService{

    @Autowired
    StockRepository stockRepository;

    @Override
    public  List<StockModel> getListOfStocks() {
        try {
            List<StockModel> stockModels = new ArrayList<>();
            stockRepository.findAll().forEach(stockModel -> stockModels.add(stockModel));
            return stockModels;
        } catch(Exception e) {
            return Collections.emptyList();
        }
    }

    @Override
    public StockModel getStockById(int id)
    {
        return stockRepository.findById(id).get();
    }

    @Override
    public StockModel save(Stock stock)
    {
        StockModel stockModelExist = stockRepository.findByName(stock.getStockName().toUpperCase());
        if(stockModelExist != null) {
            return null;
        }
        StockModel stockModel = new StockModel();
        stockModel.setStockName(stock.getStockName().toUpperCase()).setCurrentPrice(stock.getCurrentPrice());
        stockRepository.save(stockModel);
        return stockModel;
    }

    @Override
    public StockModel update(int id, Stock stock)
    {
        Optional<StockModel> stockModel= stockRepository.findById(id);
        stockModel.get().setStockName(stock.getStockName()).setCurrentPrice(stock.getCurrentPrice());
        stockRepository.save(stockModel.get());
        return stockModel.get();
    }
    //deleting a specific record
    @Override
    public void delete(int id)
    {
        stockRepository.deleteById(id);
    }
    @Override
    public void delete(String stockName)
    {
        stockRepository.deleteByName(stockName);
    }
}
