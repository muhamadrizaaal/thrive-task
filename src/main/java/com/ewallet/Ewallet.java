package main.java.com.ewallet;

import java.util.ArrayList;
import java.util.List;

public class Ewallet {
    private int saldo;
    private List<Transaction> transactions;

    public Ewallet() {
        this.saldo = 0;
        this.transactions = new ArrayList<>();
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public void cekSaldo() {
        System.out.println("Saldo saat ini: " + saldo);
    }

    public void topup(int nominal, String nomorReferensi, String metodePembayaran, Customer customer) {
        Topup topup = new Topup(nominal, nomorReferensi, metodePembayaran, customer);
        saldo += nominal;
        transactions.add(topup);
        topup.cetakInformasiTransaksi();
    }

    public void transfer(Customer pengirim, Customer penerima, int nominal) {
        if (saldo >= nominal) {
            Transfer transfer = new Transfer(nominal, pengirim, penerima);
            saldo -= nominal;
            transactions.add(transfer);
            transfer.cetakInformasiTransaksi();
        } else {
            System.out.println("Saldo tidak cukup untuk transfer.");
        }
    }
}
