package com.library.model;
import com.library.enums.Status;
import com.library.exception.BookNotFound;

import java.util.ArrayList;
import java.util.List;
public class Reader extends Person{

    private List<Book> books;
    private double total_amount;

    private static final int MAX_BOOK_LIMIT = 5;

    public Reader(Long id, String name, List<Book> books, double total_amount) {
        super(id, name);
        this.books = new ArrayList<>();
        this.total_amount = 0.0;
    }
    //Getter
    public List<Book> getBooks() {
        return books;
    }

    public double getTotal_amount() {
        return total_amount;
    }

    //Kitap ödünç alma
    public void borrowBook(Book book) {
        if (books.size() >= MAX_BOOK_LIMIT) {
            System.out.println("Kitap alma limiti aşıldı!");
            return;
        }
        if (book.getStatus() == Status.BORROWED) {
            System.out.println("Kitap şu anda başka bir kullanıcıda.");
            return;
        }
        books.add(book);
        book.update_status(Status.BORROWED);
        book.change_owner(this.getId());
        total_amount += book.getPrice();
        System.out.println(book.get_title() + " kitabı ödünç alındı.");
    }
    //Kitap satın alma
    public void purchaseBook(Book book) {
        System.out.println(book.getName() + " adlı kitap için fatura kesildi. Tutar: " + book.getPrice() + "₺");
    }

    //Kitap geri iade
    public void returnBook(Book book) {
        if (!books.contains(book)) {
            System.out.println("Bu kitap size ait değil!");
            return;
        }
        books.remove(book);
        book.update_status(Status.AVAILABLE);
        book.change_owner(null);
        total_amount -= book.getPrice();
        System.out.println(book.get_title() + " kitabı iade edildi.");
    }
    //Kitapları listeler
    public void showBooks() {
        if (books.isEmpty()) {
            throw new BookNotFound(getName() +  " adlı okuyucuya ait kayıtlı kitap bulunmamaktadır.");
        } else {
            System.out.println(getName() + " adlı okuyucunun ödünç aldığı kitaplar:");
            for (Book book : books) {
                System.out.println("- " + book.getName());
            }
        }
    }


    @Override
    public String whoYouAre() {
        return "Reader";
    }

    @Override
    public String toString() {
        return "Reader{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", books=" + books.size() +
                ", total=" + total_amount +
                '}';
    }
}
