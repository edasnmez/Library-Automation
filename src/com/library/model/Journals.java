package com.library.model;

public class Journals extends Book{

    public Journals(Long id, String name, Author author, double price, String edition) {
        super(id, name, author, price,
                "Journals");

    }
}
