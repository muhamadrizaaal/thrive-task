import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

abstract class Transaction {
    protected double nominal;
    protected Date tanggal;
    protected Customer customer;

    public Transaction(double nominal, Customer customer) {
        this.nominal = nominal;
        this.tanggal = new Date();
        this.customer = customer;
    }

    public double getNominal() {
        return nominal;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public Customer getCustomer() {
        return customer;
    }

    public abstract void cetakInformasiTransaksi();
}

class Topup extends Transaction {
    private String nomorReferensi;
    private String metodePembayaran;

    public Topup(double nominal, Customer customer, String nomorReferensi, String metodePembayaran) {
        super(nominal, customer);
        this.nomorReferensi = nomorReferensi;
        this.metodePembayaran = metodePembayaran;
    }

    public String getNomorReferensi() {
        return nomorReferensi;
    }

    public void setNomorReferensi(String nomorReferensi) {
        this.nomorReferensi = nomorReferensi;
    }

    public String getMetodePembayaran() {
        return metodePembayaran;
    }

    public void setMetodePembayaran(String metodePembayaran) {
        this.metodePembayaran = metodePembayaran;
    }

    @Override
    public void cetakInformasiTransaksi() {
        System.out.println("Topup:");
        System.out.println("Nominal: " + nominal);
        System.out.println("Tanggal: " + tanggal);
        System.out.println("Customer: " + customer.getNama());
        System.out.println("Nomor Referensi: " + nomorReferensi);
        System.out.println("Metode Pembayaran: " + metodePembayaran);
    }
}

class Transfer extends Transaction {
    private Customer penerima;

    public Transfer(double nominal, Customer customerPengirim, Customer penerima) {
        super(nominal, customerPengirim);
        this.penerima = penerima;
    }

    public Customer getPenerima() {
        return penerima;
    }

    public void setPenerima(Customer penerima) {
        this.penerima = penerima;
    }

    @Override
    public void cetakInformasiTransaksi() {
        System.out.println("Transfer:");
        System.out.println("Nominal: " + nominal);
        System.out.println("Tanggal: " + tanggal);
        System.out.println("Pengirim: " + customer.getNama());
        System.out.println("Penerima: " + penerima.getNama());
    }
}

class Ewallet {
    private double saldo;
    private List<Transaction> transactions;

    public Ewallet() {
        this.saldo = 0;
        this.transactions = new ArrayList<>();
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public void cekSaldo() {
        System.out.println("Saldo saat ini: " + saldo);
    }

    public void topup(double nominal, Customer customer, String nomorReferensi, String metodePembayaran) {
        this.saldo += nominal;
        Topup topup = new Topup(nominal, customer, nomorReferensi, metodePembayaran);
        transactions.add(topup);
        System.out.println("Topup berhasil");
    }

    public void transfer(double nominal, Customer pengirim, Customer penerima) {
        if (saldo >= nominal) {
            this.saldo -= nominal;
            Transfer transfer = new Transfer(nominal, pengirim, penerima);
            transactions.add(transfer);
            System.out.println("Transfer berhasil");
        } else {
            System.out.println("Saldo tidak mencukupi");
        }
    }
}

class Customer {
    private String nama;
    private String alamat;
    private Ewallet ewallet;

    public Customer(String nama, String alamat) {
        this.nama = nama;
        this.alamat = alamat;
        this.ewallet = new Ewallet();
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public Ewallet getEwallet() {
        return ewallet;
    }

    public void setEwallet(Ewallet ewallet) {
        this.ewallet = ewallet;
    }
}

public class EwalletApp {
    private static Scanner scanner = new Scanner(System.in);
    private static List<Customer> customers = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Tambah akun");
            System.out.println("2. Topup");
            System.out.println("3. Transfer");
            System.out.println("4. Cek saldo");
            System.out.println("5. Keluar");
            System.out.print("Pilih menu: ");
            int pilihan = scanner.nextInt();
            scanner.nextLine();

            switch (pilihan) {
                case 1:
                    tambahAkun();
                    break;
                case 2:
                    topup();
                    break;
                case 3:
                    transfer();
                    break;
                case 4:
                    cekSaldo();
                    break;
                case 5:
                    System.out.println("Keluar...");
                    return;
                default:
                    System.out.println("Pilihan tidak valid");
            }
        }
    }

    private static void tambahAkun() {
        System.out.print("Masukkan nama: ");
        String nama = scanner.nextLine();
        System.out.print("Masukkan alamat: ");
        String alamat = scanner.nextLine();
        customers.add(new Customer(nama, alamat));
        System.out.println("Akun berhasil ditambahkan");
    }

    private static void topup() {
        Customer customer = pilihCustomer();
        if (customer == null) return;

        System.out.print("Masukkan nominal topup: ");
        double nominal = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Masukkan nomor referensi: ");
        String nomorReferensi = scanner.nextLine();
        System.out.print("Masukkan metode pembayaran: ");
        String metodePembayaran = scanner.nextLine();

        customer.getEwallet().topup(nominal, customer, nomorReferensi, metodePembayaran);
    }

    private static void transfer() {
        Customer pengirim = pilihCustomer();
        if (pengirim == null) return;

        System.out.print("Masukkan nominal transfer: ");
        double nominal = scanner.nextDouble();
        scanner.nextLine();
        Customer penerima = pilihCustomer();
        if (penerima == null) return;

        pengirim.getEwallet().transfer(nominal, pengirim, penerima);
    }

    private static void cekSaldo() {
        Customer customer = pilihCustomer();
        if (customer == null) return;

        customer.getEwallet().cekSaldo();
    }

    private static Customer pilihCustomer() {
        System.out.println("Pilih customer:");
        for (int i = 0; i < customers.size(); i++) {
            System.out.println((i + 1) + ". " + customers.get(i).getNama());
        }
        System.out.print("Pilih nomor: ");
        int nomor = scanner.nextInt();
        scanner.nextLine();
        if (nomor < 1 || nomor > customers.size()) {
            System.out.println("Pilihan tidak valid");
            return null;
        }
        return customers.get(nomor - 1);
    }
}
