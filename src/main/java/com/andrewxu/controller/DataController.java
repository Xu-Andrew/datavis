package com.andrewxu.controller;

import com.andrewxu.entity.Stock;
import com.andrewxu.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

/**
 * Created by chrysaora on 6/18/17.
 */
@Controller
public class DataController {

    @Autowired
    private StockService stockService;

    @RequestMapping(value = "/data", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Collection<Stock> getData(HttpServletRequest request, Model model) {

        String n = request.getParameter("n") == null ? "10" : request.getParameter("n");
        Integer numberOfStock = Integer.parseInt(n);

        String ticker = request.getParameter("ticker");

        return stockService.getStock(ticker);
        //return stockService.getAllStocks();
    }
}
