package com.stockproject.stockmarket.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stockproject.stockmarket.model.Stock;
import com.stockproject.stockmarket.model.StockModel;
import org.json.JSONArray;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.test.util.AssertionErrors.assertTrue;
import static org.springframework.test.util.AssertionErrors.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.properties")
public class StockServiceTest {

    @Autowired
    private MockMvc mockMvc;

    ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    /*
    Test: Get list of stocks
     */
    @Test
    public void GetStockListTest() {
        try {
            MvcResult results = mockMvc.perform(get("/api/stocks").contentType(MediaType.APPLICATION_JSON))
                                .andExpect(status().is2xxSuccessful())
                                .andDo(print())
                                .andReturn();
            JSONArray eventsArrayJson = new JSONArray(results.getResponse().getContentAsString());
            StockModel[] stocksList= mapper.readValue(eventsArrayJson.toString(),StockModel[].class);
            assertTrue("Initial count of stocks",stocksList.length == 5);
        } catch (Exception e) {
            fail("Error while running CreateStockTest" + e.getMessage());
        }

    }

    /*
    Test: Get stock based on Id
     */
    @Test
    public void GetStockTest() {
        try {
            MvcResult results = mockMvc.perform(get("/api/stock/3").contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().is2xxSuccessful())
                    .andDo(print())
                    .andReturn();

            StockModel stock= mapper.readValue(results.getResponse().getContentAsString(),StockModel.class);
            assertTrue("Stock Name unmatched", stock.getStockName().equals("HUL"));
        } catch (Exception e) {
            fail("Error while running GetStockTest" + e.getMessage());
        }

    }

    /*
    Test: Create Stock
     */
    @Test
    public void CreateStockTest() {
        try {
            Stock testStock = new Stock().setStockName("AFFLE").setCurrentPrice(270);
            String jsonStr = mapper.writeValueAsString(testStock);
            mockMvc.perform(post("/api/stock").contentType(MediaType.APPLICATION_JSON).content(jsonStr))
                    .andExpect(status().is2xxSuccessful())
                    .andDo(print())
                    .andReturn();
        } catch (Exception e) {
            fail("Error while running CreateStockWithoutNameRaiseError: " + e.getMessage());
        }

    }

    /*
    Test: Creating stock with same Name raise 4xx error
     */
    @Test
    public void CreateSameStockRaiseErrorTest() {
        try {
            Stock testStock = new Stock().setStockName("TCS").setCurrentPrice(270);
            mockMvc.perform(post("/api/stock").contentType(MediaType.APPLICATION_JSON).content(testStock.toString()))
                    .andExpect(status().is4xxClientError())
                    .andDo(print())
                    .andReturn();
        } catch (Exception e) {
            fail("Error while running CreateSameStockRaiseError:" + e.getMessage());
        }

    }

    /*
     Test: Create stock without Name raise 4xx error
    */
    @Test
    public void CreateStockWithoutNameRaiseErrorTest() {
        try {
            Stock testStock = new Stock().setStockName("").setCurrentPrice(270);
            mockMvc.perform(post("/api/stock").contentType(MediaType.APPLICATION_JSON).content(testStock.toString()))
                    .andExpect(status().is4xxClientError())
                    .andDo(print())
                    .andReturn();
        } catch (Exception e) {
            fail("Error while running CreateStockWithoutNameRaiseError: " + e.getMessage());
        }

    }

    /*
     Test: Update existing Stock
    */
    @Test
    public void UpdateStockTest() {
        try {
            Stock testStock = new Stock().setStockName("TCS").setCurrentPrice(600);
            String jsonStr = mapper.writeValueAsString(testStock);
            mockMvc.perform(put("/api/stock/2").contentType(MediaType.APPLICATION_JSON).content(jsonStr))
                    .andExpect(status().is2xxSuccessful())
                    .andDo(print())
                    .andReturn();
        } catch (Exception e) {
            fail("Error while running UpdateStockTest: " + e.getMessage());
        }

    }

    /*
     Test: Delete stock based on Id
    */
    @Test
    public void DeleteStockTest() {
        try {
            mockMvc.perform(delete("/api/stock/1").contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().is2xxSuccessful())
                    .andDo(print())
                    .andReturn();
        } catch (Exception e) {
            fail("Error while running DeleteStockTest: " + e.getMessage());
        }
    }

}
