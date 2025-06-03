package com.library.service;

import com.library.enums.Status;
import com.library.model.Book;
import com.library.model.Reader;

import java.util.*;

public class Library {
    private static Library instance;

    private Map<Long, Book> books = new HashMap<>();
    private Map<Long, Reader> readers = new HashMap<>();

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

    // Reader ID'ye göre getir
    public Reader getReader(Long id) {
        return readers.get(id);
    }
    public void addReader(Reader reader) {
        this.readers.put(reader.getId(), reader);
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
    public boolean lendBook(Long bookId, Long readerId) {
        Book book = books.get(bookId);
        Reader reader = readers.get(readerId);

        if (book == null || reader == null) {
            System.out.println("Kitap veya kullanıcı bulunamadı.");
            return false;
        }

        if (book.getStatus()== Status.BORROWED) {
            System.out.println("Kitap zaten ödünç alınmış.");
            return false;
        }

        if (reader.getBooks().size() >= 5) {
            System.out.println("Kullanıcı kitap limitine ulaşmış.");
            return false;
        }

        reader.borrowBook(book);
        book.update_status(Status.BORROWED);
        book.change_owner(readerId);
        return true;
    }

    // Kitap iade etme
    public boolean takeBackBook(Long bookId, Long readerId) {
        Book book = books.get(bookId);
        Reader reader = readers.get(readerId);

        if (book == null || reader == null) {
            System.out.println("Kitap veya kullanıcı bulunamadı.");
            return false;
        }

        if (!reader.getBooks().contains(book)) {
            System.out.println("Bu kitap kullanıcıya ait değil.");
            return false;
        }

        reader.returnBook(book);
        book.update_status(Status.AVAILABLE);
        book.change_owner(null);
        System.out.println("Kitap başarıyla iade edildi.");
        return true;
    }



}
