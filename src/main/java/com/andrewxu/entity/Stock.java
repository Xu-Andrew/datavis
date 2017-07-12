package com.andrewxu.entity;

import java.util.Date;

/**
 * Created by chrysaora on 6/18/17.
 */
public class Stock {

    private String ticker;
    private String name;
    private double price;
    private Date date;

    public Stock(String ticker, String name, double price, Date date) {
        this.ticker = ticker;
        this.name = name;
        this.price = price;
        this.date = date;
    }

    public Stock() {

    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "ticker: " + ticker + ", name: " + name + ", price: " + price + ", date: " + date;
    }
}
