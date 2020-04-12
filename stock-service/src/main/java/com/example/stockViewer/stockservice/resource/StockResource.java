package com.example.stockViewer.stockservice.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/stock")
public class StockResource {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/{userName}")
    public List<Quote> getStock(@PathVariable final String userName) {

        ResponseEntity<List<String>> quoteResponse = restTemplate.exchange("http://db-service/rest/db/" + userName, HttpMethod.GET,
                null, new ParameterizedTypeReference<List<String>>() {
                });


        List<String> quotes = quoteResponse.getBody();

        return quotes.stream()
                .map(quote -> {
                    return new Quote(quote, userName);
                })
                .collect(Collectors.toList());

    }

    private class Quote {

        private String userName;
        private String quote;

        public Quote(String quote, String userName) {
            this.quote = quote;
            this.userName = userName;
        }

        public String getQuote() {
            return quote;
        }

        public void setQuote(String quote) {
            this.quote = quote;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }
    }


}

//public class StockResource {
//
//
//    @Autowired
//    private RestTemplate restTemplate ;
//
////    private YahooFinance yahooFinance ;
////
////    public StockResource() {
////        this.yahooFinance = new YahooFinance() ;
////    }
//
//    @GetMapping("/{userName}")
//    public List<Stock> getStock(@PathVariable String userName)
//    {
////        List<String> quotes  = restTemplate.getForObject("http://localhost:8300/rest/db/"+userName,List.class );
//
//       ResponseEntity<List<String>> quoteResponse = restTemplate.exchange("http://localhost:8300/rest/db/" + userName, HttpMethod.GET, null, new ParameterizedTypeReference<List<String>>() {}) ;
//
//       List<String> quotes = quoteResponse.getBody() ;
//
////       return quotes.stream()
////               .map( quote ->  {
////                  return getStockPrice(quote);
////               })
////               .collect(Collectors.toList());
//
//        return quotes.stream()
//                .map( this::getStockPrice   )
//                .collect(Collectors.toList());
//    }
//
//    private Stock getStockPrice(String quote) {
//        try {
//            return  YahooFinance.get(quote);
//        } catch (IOException e) {
//            e.printStackTrace();
//            return new Stock(quote );
//        }
//    }
//
//
//}
