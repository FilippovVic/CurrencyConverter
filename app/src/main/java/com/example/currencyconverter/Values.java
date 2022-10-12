package com.example.currencyconverter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

class Values extends Thread {

    private Document doc;
    private Element main_table;
    private Elements currency_line;
    private Elements currency_elements;

    private String dollarInRoubles;
    private String euroInRoubles;
    private String manatInRoubles;

    private ArrayList<String> list;

    public Values() {
    }

    @Override
    public void run() {

        try {
            doc = Jsoup.connect("https://www.cbr.ru/currency_base/daily/").get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        main_table = doc.getElementsByTag("tbody").get(0);
        currency_line = main_table.children();

        currency_elements = currency_line.get(11).children();
        dollarInRoubles = currency_elements.get(4).text();

        currency_elements = currency_line.get(12).children();
        euroInRoubles = currency_elements.get(4).text();

        currency_elements = currency_line.get(2).children();
        manatInRoubles = currency_elements.get(4).text();

        list = new ArrayList<>();

        list.add(dollarInRoubles.replace(",", "."));
        list.add(euroInRoubles.replace(",", "."));
        list.add(manatInRoubles.replace(",", "."));

    }

    public ArrayList<String> getValues() {
        return list;
    }

}