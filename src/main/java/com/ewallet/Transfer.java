package main.java.com.ewallet;

public class Transfer extends Transaction {
    private Customer pengirim;
    private Customer penerima;

    public Transfer(int nominal, Customer pengirim, Customer penerima) {
        super(nominal, pengirim);
        this.pengirim = pengirim;
        this.penerima = penerima;
    }

    public Customer getPengirim() {
        return pengirim;
    }

    public void setPengirim(Customer pengirim) {
        this.pengirim = pengirim;
    }

    public Customer getPenerima() {
        return penerima;
    }

    public void setPenerima(Customer penerima) {
        this.penerima = penerima;
    }

    @Override
    public void cetakInformasiTransaksi() {
        System.out.println("Transfer: " + getNominal() + " dari " + pengirim.getNama() + " ke " + penerima.getNama() + " | Tanggal: " + getTanggal());
    }
    

}
