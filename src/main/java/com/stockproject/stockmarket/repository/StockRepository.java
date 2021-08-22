package com.stockproject.stockmarket.repository;

import com.stockproject.stockmarket.model.StockModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/*
  Repository to perform operation on Stock table
*/
@RepositoryRestResource
public interface StockRepository extends CrudRepository<StockModel, Integer> {

    @Query(value = "delete from StockModel s " +
            "where s.stockName=:stockName")
    void deleteByName(@Param("stockName") String stockName);

    @Query(value = "select s from StockModel s " +
            "where s.stockName=:stockName")
    StockModel findByName(@Param("stockName") String stockName);
}
