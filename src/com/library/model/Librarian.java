package com.library.model;

import com.library.enums.Status;
import com.library.exception.BookNotFound;

import java.time.Duration;
import java.time.LocalDateTime;

public class Librarian {
    private String name;
    private String password;

    public Librarian(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    // Kitap arama (kitap ID ile)
    public Book search_book(Long bookId, Iterable<Book> books) {
        for (Book book : books) {
            if (book.getId().equals(bookId)) {
                return book;
            }
        }
        throw new BookNotFound("Kitap ID: " + bookId + " bulunamadı.");
    }

    // Üye doğrulama
    public boolean verify_member(Long memberId, Iterable<Reader> readers) {
        for (Reader reader : readers) {
            if (reader.getId().equals(memberId)) {
                return true;
            }
        }
        return false;
    }

    // Kitap verme işlemi
    public void issue_book(Book book, Reader reader) {
        if (book.getStatus() != Status.AVAILABLE) {
            System.out.println("Kitap zaten ödünç alınmış.");
            return;
        }
        reader.borrowBook(book);
    }

    // Gecikme cezası hesaplama
    public double calculate_fine(Book book, LocalDateTime returnDate) {
        LocalDateTime purchaseDate = book.getDateOfPurchase();
        long daysBetween = Duration.between(purchaseDate, returnDate).toDays();
        long delay = daysBetween - 15; // örnek: 15 gün içinde iade bekleniyor
        double finePerDay = 5.0; // örnek gecikme bedeli (₺)
        return delay > 0 ? delay * finePerDay : 0.0;
    }

    // Fatura oluşturma
    public void create_bill(Reader reader) {
        System.out.println(reader.getName() + " adlı üyeye ait toplam tutar: " + reader.getTotal_amount() + "₺");
    }

    // Kitap iade
    public void return_book(Book book, Reader reader) {
        reader.returnBook(book);
    }
}
