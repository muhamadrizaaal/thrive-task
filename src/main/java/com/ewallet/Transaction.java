package main.java.com.ewallet;

import java.util.Date;

public abstract class Transaction {
    private double nominal;
    private Date tanggal;
    private Customer customer;

    public Transaction(double nominal, Customer customer) {
        this.nominal = nominal;
        this.tanggal = new Date();
        this.customer = customer;
    }

    public double getNominal() {
        return nominal;
    }
    

    public void setNominal(double nominal) {
        this.nominal = nominal;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public abstract void cetakInformasiTransaksi();
}

