package com.library.model;

import java.time.LocalDate;

public class MemberRecord {
    private Long memberId;
    private String type;
    private LocalDate dateOfMembership;
    private int noBooksIssued;
    private final int maxBookLimit;
    private String name;
    private String address;
    private String phoneNo;

    public MemberRecord(Long memberId, String type, LocalDate dateOfMembership, int noBooksIssued, int maxBookLimit, String name, String address, String phoneNo) {
        this.memberId = memberId;
        this.type = type;
        this.dateOfMembership = LocalDate.now();
        this.noBooksIssued = 0;
        this.maxBookLimit = 5;
        this.name = name;
        this.address = address;
        this.phoneNo = phoneNo;
    }

    // Üye bilgilerini döner
    public String getMember() {
        return "ID: " + memberId + ", Ad: " + name + ", Tür: " + type + ", Üyelik Tarihi: " + dateOfMembership +
                ", Alınan Kitap: " + noBooksIssued + "/" + maxBookLimit;
    }

    // Kitap sayısını artırır
    public void incBookIssued() {
        if (noBooksIssued < maxBookLimit) {
            noBooksIssued++;
        } else {
            System.out.println("Maksimum kitap alma sınırına ulaşıldı.");
        }
    }

    // Kitap sayısını azaltır
    public void decBookIssued() {
        if (noBooksIssued > 0) {
            noBooksIssued--;
        } else {
            System.out.println("Üzerinizde kayıtlı kitap bulunmamaktadır.");
        }
    }
    // Fatura ödemesi
    public void payBill(double amount) {
        System.out.println(name + " adlı üye " + amount + "₺ tutarında ödeme yaptı.");
    }

    // Getter

    public Long getMemberId() {
        return memberId;
    }

    public String getType() {
        return type;
    }

    public LocalDate getDateOfMembership() {
        return dateOfMembership;
    }

    public int getNoBooksIssued() {
        return noBooksIssued;
    }

    public int getMaxBookLimit() {
        return maxBookLimit;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

}
