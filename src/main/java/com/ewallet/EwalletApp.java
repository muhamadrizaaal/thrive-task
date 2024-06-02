package main.java.com.ewallet;

import java.util.Scanner;

public class EwalletApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Selamat datang di Ewallet App!");
        System.out.print("Masukkan nama Anda: ");
        String nama = scanner.nextLine();
        System.out.print("Masukkan alamat Anda: ");
        String alamat = scanner.nextLine();

        Customer customer = new Customer(nama, alamat);
        Ewallet ewallet = new Ewallet();
        customer.setEwallet(ewallet);

        boolean running = true;

        while (running) {
            System.out.println("Pilih opsi:");
            System.out.println("1. Cek Saldo");
            System.out.println("2. Topup");
            System.out.println("3. Transfer");
            System.out.println("4. Keluar");

            int pilihan = scanner.nextInt();
            scanner.nextLine();  // consume newline

            switch (pilihan) {
                case 1:
                    ewallet.cekSaldo();
                    break;
                case 2:
                    System.out.print("Masukkan nominal topup: ");
                    int nominalTopup = scanner.nextInt();
                    scanner.nextLine();  // consume newline
                    System.out.print("Masukkan nomor referensi: ");
                    String nomorReferensi = scanner.nextLine();
                    System.out.print("Masukkan metode pembayaran: ");
                    String metodePembayaran = scanner.nextLine();
                    ewallet.topup(nominalTopup, nomorReferensi, metodePembayaran, customer);
                    break;
                case 3:
                    System.out.print("Masukkan nama penerima: ");
                    String namaPenerima = scanner.nextLine();
                    System.out.print("Masukkan alamat penerima: ");
                    String alamatPenerima = scanner.nextLine();
                    Customer penerima = new Customer(namaPenerima, alamatPenerima);
                    System.out.print("Masukkan nominal transfer: ");
                    int nominalTransfer = scanner.nextInt();
                    scanner.nextLine();  // consume newline
                    ewallet.transfer(customer, penerima, nominalTransfer);
                    break;
                case 4:
                    running = false;
                    break;
                default:
                    System.out.println("Pilihan tidak valid, silakan coba lagi.");
            }
        }

        scanner.close();
        System.out.println("Terima kasih telah menggunakan Ewallet App!");
    }
}
