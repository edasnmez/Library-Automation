package com.library.model;

public class StudyBooks extends Book{

    public StudyBooks(Long id, String name, Author author, double price, String edition) {
        super(id, name, author, price, "Study Books");
    }

}
