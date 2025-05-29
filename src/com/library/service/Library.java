package com.library.service;

import com.library.model.Book;
import com.library.model.Reader;

import java.util.*;

public class Library {
    private static Library instance;

    private Map<Long, Book> books = new HashMap<>(); // bookId -> Book
    private Map<String, Reader> readers = new HashMap<>(); // readerName -> Reader

    private Library() {
    }
    public static Library getInstance() {
        if (instance == null) {
            instance = new Library();
        }
        return instance;
    }

    // Kitap listesi döndürme
    public Collection<Book> getBooks() {
        return books.values();
    }

    // Reader listesi döndürme
    public Collection<Reader> getReaders() {
        return readers.values();
    }

    // Yeni kitap ekleme
    public boolean newBook(Book book){
        if(books.containsKey(book.getId())){
            System.out.println("Bu ID'ye sahip bir kitap zaten mevcut.");
            return false;
        }
        books.put(book.getId(), book);
        return true;
    }

    // Kitap gösterme (id ile)
    public Book showBook(Long bookId) {
        return books.get(bookId);
    }

    // Kitap ödünç verme
    /*public boolean lendBook(Long bookId, Long readerId) {
        Book book = books.get(bookId);
        Reader reader = readers.get(readerId);

        if (book == null || reader == null) {
            System.out.println("Kitap veya kullanıcı bulunamadı.");
            return false;
        }

        if (book.getStatus()=="") {
            System.out.println("Kitap zaten ödünç alınmış.");
            return false;
        }

        if (reader.().size() >= 5) {
            System.out.println("Kullanıcı kitap limitine ulaşmış.");
            return false;
        }

        reader.borrowBook(book);
        book.setBorrowed(true);
        book.change_owner(readerId);
        return true;
    }*/
}
