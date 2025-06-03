package com.library.model;

public class Magazines extends  Book{

    public Magazines(Long id, String name, Author author, double price, String edition) {
        super(id, name, author, price, "Magazines");
    }

}
