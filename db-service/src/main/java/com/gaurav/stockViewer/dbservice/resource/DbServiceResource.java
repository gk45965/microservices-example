package com.gaurav.stockViewer.dbservice.resource;

import com.gaurav.stockViewer.dbservice.model.Quote;
import com.gaurav.stockViewer.dbservice.model.Quotes;
import com.gaurav.stockViewer.dbservice.repo.QuotesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/rest/db")
public class DbServiceResource {

    @Autowired
    private QuotesRepo quotesRepo;

//    public DbServiceResource(QuotesRepo quotesRepo) {
//       this.quotesRepo = quotesRepo ;
//    }

    @GetMapping("/{userName}")
    public List<String> getQuotes(@PathVariable("userName") final String userName) {
        return getQuotesByUserName(userName);
    }

    @PostMapping("/add")
    public List<String> add(@RequestBody final Quotes quotes) {
        quotes.getQuotes().stream()
                .map(quote -> new Quote(quotes.getUserName(), quote))
                .forEach(quote -> quotesRepo.save(quote));

        return getQuotesByUserName(quotes.getUserName());
    }

    @DeleteMapping("/{userName}")
    public List<String> delete(@PathVariable String userName) {
        List<Quote> quotes = quotesRepo.findByUserName(userName);
        if (Objects.nonNull(quotes) && !quotes.isEmpty()) {
            quotes.stream()
                    .forEach(quote -> quotesRepo.delete(quote));
        }

        return getQuotesByUserName(userName);
    }


    private List<String> getQuotesByUserName(@PathVariable("userName") String userName) {
        return quotesRepo.findByUserName(userName).stream()
                .map(Quote::getQuote)
                .collect(Collectors.toList());
    }

}
