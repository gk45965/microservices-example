package com.gaurav.stockViewer.dbservice.model;

import javax.persistence.*;

@Entity
@Table(name = "quotes")
public class Quote {

    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private Integer id;
    private String userName;
    private String quote;

    public Quote() {
    }

    public Quote(String userName, String quote) {
        this.userName = userName ;
        this.quote = quote ;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }
}
