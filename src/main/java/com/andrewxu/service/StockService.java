package com.andrewxu.service;

import com.andrewxu.dao.StockDao;
import com.andrewxu.dao.YahooFinanceDao;
import com.andrewxu.entity.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Created by chrysaora on 6/18/17.
 */
@Service
public class StockService {

    @Autowired
    private StockDao stockDao;

    @Autowired
    private YahooFinanceDao yahooFinanceDao;

    public Collection<Stock> getStock(String ticker) {
        return stockDao.getStock(ticker);
    }
}
