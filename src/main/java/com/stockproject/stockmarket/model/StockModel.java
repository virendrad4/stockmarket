package com.stockproject.stockmarket.model;

import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "stock")
public class StockModel {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @Column(name = "name")
    String stockName;

    @Column(name = "current_price")
    Integer currentPrice;

    @UpdateTimestamp
    @Column(name = "last_update")
    Date lastUpdate;
    public int getId() {
        return id;
    }

    public StockModel setId(int id) {
        this.id = id;
        return this;
    }

    public String getStockName() {
        return stockName;
    }

    public StockModel setStockName(String stockName) {
        this.stockName = stockName;
        return this;
    }

    public Integer getCurrentPrice() {
        return currentPrice;
    }

    public StockModel setCurrentPrice(Integer currentPrice) {
        this.currentPrice = currentPrice;
        return this;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public StockModel setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
        return this;
    }

}
