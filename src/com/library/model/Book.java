package com.library.model;

import com.library.enums.Status;

import java.time.LocalDateTime;

public class Book {
    private Long id;
    private String name;
    private Author author;
    private double price;
    private String edition;
    private Status status;
    private LocalDateTime dateOfPurchase;
    private Long ownerId; // readerId


    public Book(Long id, String name, Author author, double price, String edition) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.price = price;
        this.edition = edition;
        this.status = Status.AVAILABLE;
        this.dateOfPurchase = LocalDateTime.now();
        this.ownerId = null;
    }

    // Getter & Setter


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Author getAuthor() {
        return author;
    }

    public double getPrice() {
        return price;
    }

    public String getEdition() {
        return edition;
    }

    public Status getStatus() {
        return status;
    }

    public LocalDateTime getDateOfPurchase() {
        return dateOfPurchase;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    @Override
    public String toString() {
        return "Book{" +
                "ID=" + id +
                ", Ad='" + name + '\'' +
                ", Yazar=" + author.getName() +
                ", Fiyat=" + price +
                ", Kategori='" + edition + '\'' +
                ", Durum='" + status + '\'' +
                ", Sahip=" + (ownerId != null ? ownerId : "Kütüphane") +
                ", Alım Tarihi=" + dateOfPurchase +
                '}';
    }

    public String get_title() {
        return this.name;
    }

    public String get_author() {
        return this.author != null ? this.author.getName() : "Bilinmeyen Yazar";
    }

    public void change_owner(Long newOwnerId) {
        this.ownerId = newOwnerId;
    }

    public void display() {
        System.out.println(this.toString());
    }

    public void update_status(Status newStatus) {
        this.status = newStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id != null && id.equals(book.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

}
