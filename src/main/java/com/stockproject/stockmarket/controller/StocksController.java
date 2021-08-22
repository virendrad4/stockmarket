package com.stockproject.stockmarket.controller;

import com.stockproject.stockmarket.model.Stock;
import com.stockproject.stockmarket.model.StockModel;
import com.stockproject.stockmarket.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = {"/api"})
@CrossOrigin(origins = "http://localhost:3000")
public class StocksController {

    @Autowired
    StockService stockService;

    /*
    Api to Get list of stocks
     */
    @GetMapping(value = "/stocks")
    public ResponseEntity<?> getStocksList() {
        List<StockModel> stockModels = stockService.getListOfStocks();
        return new ResponseEntity<>(stockModels, HttpStatus.OK);
    }

    /*
    Api to Get stock based on Id
     */
    @GetMapping("/stock/{id}")
    private ResponseEntity<?> getStock(@PathVariable("id") int id)
    {
        StockModel stockModel = stockService.getStockById(id);
        return new ResponseEntity<>(stockModel, HttpStatus.OK);
    }

    //creating post mapping that post the stock detail in the database
    @PostMapping("/stock")
    private ResponseEntity<?> saveStock(@RequestBody Stock stock)
    {
        if(stock == null || stock.getCurrentPrice() == null || stock.getStockName()== null
                ||stock.getStockName().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        StockModel stockModel = stockService.save(stock);
        if(stockModel == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(stockModel, HttpStatus.OK);
    }


    /*
    Api to update existing stock
     */
    @PutMapping("/stock/{id}")
    private ResponseEntity<?> updateStock(@PathVariable("id") int id, @RequestBody Stock stock)
    {
        StockModel stockModel = stockService.update(id, stock);
        return new ResponseEntity<>(stockModel, HttpStatus.OK);
    }

    //creating a delete mapping that deletes a specific stock
    @DeleteMapping("/stock/{id}")
    private ResponseEntity<?> deleteStock(@PathVariable("id") int id)
    {
        stockService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/stock/name/{stockName}")
    private ResponseEntity<?> deleteStockByName(@PathVariable("stockName") String stockName)
    {
        stockService.delete(stockName);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
