package com.library.model;

import com.library.exception.BookNotFound;

import java.util.ArrayList;
import java.util.List;

public class Author extends Person{
    private List<Book> books;

    public Author(Long id, String name, List<Book> books) {
        super(id, name);
        this.books = new ArrayList<>();
    }

    public List<Book> getBooks() {
        return books;
    }

    // Yeni kitap ekler
    public void newBook(Book book) {
        if (book != null) {
            books.add(book);
            System.out.println("Yeni kitap eklendi: " + book.get_title());
        }
    }

    // Yazarın kitaplarını listeler
    public void showBooks() {
        if (books.isEmpty()) {
            throw new BookNotFound(getName() +  " adlı yazara ait kayıtlı kitap bulunmamaktadır.");
        } else {
            System.out.println(getName() + " adlı yazarın kitapları:");
            for (Book book : books) {
                System.out.println("- " + book.get_title());
            }
        }
    }


    @Override
    public String whoYouAre() {
        return "Author";
    }
}
