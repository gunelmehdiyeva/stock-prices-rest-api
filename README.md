## API TO SEARCH THE STOCK PRICES

- THIS API is used for to get information about stock price by providing stock index in single and batch mode using YAHOO as provider for stock prices
- API authentication user and password -- myYahoo


---

# FOLLOWING ARE API URL TO CALLBACK WITH

## Single index price ::
- With parameter valid stock index such as stockindex=googl
- http://localhost:8080/api/one?stockprovider=yahoo&stockindex=googl


## Multiple index prices 
- http://localhost:8080/api/many?stockprovider=yahoo

----
## Feature http-client documentation

- [Micronaut HTTP Client documentation](https://docs.micronaut.io/latest/guide/index.html#httpClient)

