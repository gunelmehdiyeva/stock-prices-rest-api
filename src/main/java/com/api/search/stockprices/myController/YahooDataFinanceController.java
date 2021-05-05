package com.api.search.stockprices.myController;


import com.api.search.stockprices.myServices.YahooDataFinanceServices;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.annotation.QueryValue;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;

import javax.inject.Inject;
import java.io.IOException;
import java.util.logging.Logger;

@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller("/api")
public class YahooDataFinanceController {

    private static final Logger LOG = Logger.getLogger(YahooDataFinanceController.class.getName());
    //Injecting the service
    @Inject
    private YahooDataFinanceServices financeServices;

    //GET request for single stock index price e.g stock-index=BNB
    @Get("/one")
    @Produces(MediaType.APPLICATION_JSON)
    public Object getOneStockPrice(@QueryValue("stockprovider") String stockIndexPriceProvider,
                                   @QueryValue("stockindex") String stockIndexSymbol) throws IOException {

        LOG.info("STOCK INDEX PRICE PROVIDER -->" + stockIndexPriceProvider);
        LOG.info("STOCK INDEX SYMBOL -->" + stockIndexSymbol);
        //Checking the Yahoo Data Finance Provider
        if (stockIndexPriceProvider.equals("yahoo")) {
            LOG.info("STOCK INDEX PRICE PROVIDER CHECKED -->" + stockIndexPriceProvider);
            return financeServices.getSingleStockIndexPrice(stockIndexSymbol);
        } else {
            LOG.info("STOCK INDEX PRICE PROVIDER IS NOT YAHOO");
            return HttpResponse.badRequest("INVALID STOCK PROVIDER");
        }
    }

    @Get("/many")
    @Produces(MediaType.APPLICATION_JSON)
    public String getManyStockPrices(@QueryValue("stockprovider") String stockIndexPriceProvider) throws IOException {
        LOG.info("STOCK INDEX PRICE PROVIDER -->" + stockIndexPriceProvider);
        //Checking the Yahoo Data Finance Provider
        if (stockIndexPriceProvider.equals("yahoo")) {
            LOG.info("STOCK INDEX PRICE PROVIDER CHECKED -->" + stockIndexPriceProvider);
            return financeServices.getMultipleStockIndexPrices();
        } else {
            LOG.info("STOCK INDEX PRICE PROVIDER IS NOT YAHOO");
            return HttpResponse.badRequest("INVALID STOCK PROVIDER").toString();
        }
    }
}
