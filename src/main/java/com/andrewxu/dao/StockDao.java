package com.andrewxu.dao;

/**
 * Created by chrysaora on 6/18/17.
 */
//@Repository
//public class StockDao {
//
//    private static Map<Integer, Stock> stocks;
//
//    static {
//        stocks = new HashMap<Integer, Stock>() {
//            {
//                put(0, new Stock("AAPL", "Apple", 101.92));
//                put(1, new Stock("AAPL", "Apple", 103.91));
//                put(2, new Stock("AAPL", "Apple", 102.56));
//            }
//        };
//    }
//
//    public Collection<Stock> getAllStocks() {
//        return this.stocks.values();
//    }
//}

//import com.andrewxu.entity.Stock;
//import org.apache.commons.io.IOUtils;
//import org.json.JSONObject;
//import org.springframework.stereotype.Repository;
//
//import java.io.IOException;
//import java.net.URL;
//import java.nio.charset.Charset;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.Iterator;
//
//@Repository
//public class StockDao {
//
//    private static final String API_KEY = "PZS9OKRPW2ODFCVA";
//
//
//    public Collection<Stock> getStock(String ticker) throws IOException {
//        String dataSourceUrl = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY_ADJUSTED&symbol=" + ticker + "&outputsize=full&apikey=" + API_KEY;
//        String data = IOUtils.toString(new URL(dataSourceUrl), Charset.forName("UTF-8"));
//        System.out.println(data);
//
//        JSONObject json = new JSONObject(data);
//        JSONObject json.get("Time Series (Daily)");
//        System.out.println(timeSeries);
//        ArrayList<Stock> stockList = new ArrayList<>();
//        JSONObject timeSeriesJson = new JSONObject(timeSeries);
//
//        Iterator<String> keys = timeSeriesJson.keys();
//        while (keys.hasNext()) {
//
//            String key = keys.next();
//            System.out.println(key);
//            JSONObject timeSeriesInstanceJson = new JSONObject(json.get(key));
//            Double adjustedClose = Double.parseDouble((String) timeSeriesInstanceJson.get("5. adjusted close"));
//            stockList.add(new Stock(ticker, "Name", adjustedClose));
//        }
//
//        System.out.println(stockList.toString());
//
//        return stockList;
//    }
//}

import com.andrewxu.entity.AlphavantageAPI;
import com.andrewxu.entity.Stock;
import com.google.gson.Gson;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

@Repository
public class StockDao {

    private static final String API_KEY = "PZS9OKRPW2ODFCVA";
    private static final String DATA_SOURCE_URL_NO_SYMBOL = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY_ADJUSTED&outputsize=full&apikey=" + API_KEY + "&symbol=";


    public Collection<Stock> getStock(String symbol) {
        String dataSourceUrlComplete = DATA_SOURCE_URL_NO_SYMBOL + symbol;
        String jsonDataAsString = getJsonAsString(dataSourceUrlComplete);
        System.out.println(jsonDataAsString);

        Gson gson = new Gson();
        AlphavantageAPI alphavantageAPI = gson.fromJson(jsonDataAsString, AlphavantageAPI.class);
        Map<String, Map<String, String>> timeSeries = alphavantageAPI.getTimeSeries();

        System.out.println(timeSeries);

        ArrayList<Stock> stocks = new ArrayList<>();
        for (Map.Entry<String, Map<String, String>> dailyData : timeSeries.entrySet()) {

            String dateAsString = dailyData.getKey();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date dateAsDate = new Date();
            try {
                dateAsDate = dateFormat.parse(dateAsString);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            String dailyAdjustedCloseAsString = dailyData.getValue().get("5. adjusted close");
            Double dailyAdjustedCloseAsDouble = Double.parseDouble(dailyAdjustedCloseAsString);
            Stock dailyDataAsStock = new Stock(symbol, "Company Name", dailyAdjustedCloseAsDouble, dateAsDate);
            stocks.add(0, dailyDataAsStock);
        }

        for (Stock stock : stocks) {
            System.out.println(stock);
        }
        return stocks;
    }

    private String getJsonAsString(String url) {
        String jsonDataAsString = "";
        try {
             jsonDataAsString = IOUtils.toString(new URL(url), Charset.forName("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonDataAsString;
    }
}