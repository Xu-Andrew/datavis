package com.andrewxu.dao;

import com.andrewxu.entity.Stock;
import com.opencsv.CSVReader;
import org.springframework.stereotype.Repository;

import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * Created by chrysaora on 6/18/17.
 */
//https://stackoverflow.com/questions/44030983/yahoo-finance-url-not-working
@Repository
public class YahooFinanceDao {

    private static final String CSV_FILE = "/home/chrysaora/Documents/Projects/GitHub/DataVis/CAKE.csv";

    private ArrayList<Stock> stocks = new ArrayList<>();
    private int lineNumber = 0;

//    private static CSVReader reader;
//
//    static {
//        try {
//            reader = new CSVReader(new FileReader(CSV_FILE));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }


    public Collection<Stock> getStock(int n) {
        try {
            CSVReader reader = new CSVReader(new FileReader(CSV_FILE));
            String[] line;

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            // get rid of header row
            reader.readNext();
            while ((line = reader.readNext()) != null && lineNumber < n) {
                Date date = new Date();
                try {
                    date = dateFormat.parse(line[0]);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Stock stock = new Stock("CAKE", "Cheesecake Factory", Double.parseDouble(line[5]), date);
                this.lineNumber++;
                this.stocks.add(stock);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return this.stocks;
    }
}
