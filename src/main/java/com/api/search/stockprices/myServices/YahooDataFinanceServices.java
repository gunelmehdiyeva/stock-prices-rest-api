package com.api.search.stockprices.myServices;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import javax.inject.Singleton;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;
import java.util.logging.Logger;

@Singleton
public class YahooDataFinanceServices {

    private static final Logger LOG = Logger.getLogger(YahooDataFinanceServices.class.getName());


    public BigDecimal getSingleStockIndexPrice(String sIndex) throws IOException {
        Stock stockObj = null;
        BigDecimal stockPriceObj = BigDecimal.ONE;
        if (sIndex != null) {
            LOG.info("STOCK INDEX IS NOT NULL");
            stockObj = YahooFinance.get(sIndex);

            stockPriceObj = stockObj.getQuote(true).getPrice();
            LOG.info("STOCK PRICE OBJECT CONTAIN THE SINGLE STOCK PRICE --> " + stockPriceObj);

        }

        return stockPriceObj;
    }

    public String getMultipleStockIndexPrices() throws IOException {

        //Reference Yahoo Finance API Variaus LISTs
        Map<String, Stock> multipleStocks = null;
        String[] stockIndexSymbols = new String[]{"DJI", "GOOGL", "IXIC", "ETH", "BTC", "BSEN", "GSPC", "TSLA"};
        multipleStocks = YahooFinance.get(stockIndexSymbols);
        LOG.info("MULTIPLE STOCK PRICES IN BATCH MODE -->" + multipleStocks);
        return multipleStocks.toString();
    }
}
