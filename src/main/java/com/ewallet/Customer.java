package main.java.com.ewallet;

public class Customer {
    private String nama;
    private String alamat;
    private Ewallet ewallet;

    // Constructor
    public Customer(String nama, String alamat) {
        this.nama = nama;
        this.alamat = alamat;
    }

    public String getAlamat() {
        return alamat;
    }

    // Getter dan Setter untuk nama
    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    // Getter dan Setter untuk ewallet
    public Ewallet getEwallet() {
        return ewallet;
    }

    public void setEwallet(Ewallet ewallet) {
        this.ewallet = ewallet;
    }
}
