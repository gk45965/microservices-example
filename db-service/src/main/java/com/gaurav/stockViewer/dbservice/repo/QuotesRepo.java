package com.gaurav.stockViewer.dbservice.repo;

import com.gaurav.stockViewer.dbservice.model.Quote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuotesRepo extends JpaRepository<Quote,Integer > {

    List<Quote> findByUserName(String userName);
}
