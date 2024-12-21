/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package classs;

import java.util.Scanner;
/**
 *
 * @author cofeshop
 */


public class CoffeShop {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Inisialisasi objek untuk Admin dan Pelanggan
        Pengguna admin = new Pengguna("admin", "adminpass", "admin");
        Pengguna pelanggan = new Pengguna("pelanggan", "12345", "pelanggan");
        
        // Menggabungkan ke dalam sebuah array untuk memudahkan pengecekan
        Pengguna[] penggunaList = {admin, pelanggan};

        Menu menu = new Menu();
        Pesanan pesanan = new Pesanan();
        Transaksi transaksi = new Transaksi();
        Pembayaran pembayaran = new Pembayaran();

        // Login
        System.out.println("===== Selamat Datang di Coffee Shop =====");
        System.out.print("Masukkan Nama Pengguna: ");
        String namaPengguna = scanner.nextLine();
        System.out.print("Masukkan Kata Sandi: ");
        String kataSandi = scanner.nextLine();

        Pengguna pengguna = null;
        
        // Memeriksa login
        for (Pengguna p : penggunaList) {
            if (p.getNamaPengguna().equals(namaPengguna) && p.cekKataSandi(kataSandi)) {
                pengguna = p;
                break;
            }
        }

        if (pengguna != null) {
            System.out.println("Login berhasil! Anda adalah " + pengguna.getRole());

            boolean selesai = false;
            while (!selesai) {
                if (pengguna.getRole().equals("admin")) {
                    // Admin Menu
                    System.out.println("\n===== Menu Admin =====");
                    System.out.println("1. Kelola Menu");
                    System.out.println("2. Lihat Orderan");
                    System.out.println("3. Update Status Orderan");
                    System.out.println("4. Lihat Laporan Transaksi");
                    System.out.println("5. Keluar");
                    System.out.print("Pilih opsi: ");
                } else {
                    // Pelanggan Menu
                    System.out.println("\n===== Menu Pelanggan =====");
                    System.out.println("1. Lihat Menu");
                    System.out.println("2. Buat Pesanan");
                    System.out.println("3. Lihat Pesanan");
                    System.out.println("4. Lakukan Pembayaran");
                    System.out.println("5. Lihat Riwayat Transaksi");
                    System.out.println("6. Keluar");
                    System.out.print("Pilih opsi: ");
                }

                int pilihan = scanner.nextInt();
                scanner.nextLine(); // Membersihkan buffer

                if (pengguna.getRole().equals("admin")) {
                    switch (pilihan) {
                        case 1:
                            // Admin mengelola menu (CRUD)
                            break;
                        case 2:
                            // Admin melihat orderan
                            break;
                        case 3:
                            // Admin mengupdate status orderan
                            break;
                        case 4:
                            // Admin melihat laporan transaksi
                            break;
                        case 5:
                            selesai = true;
                            System.out.println("Terima kasih telah berkunjung!");
                            break;
                        default:
                            System.out.println("Opsi tidak valid.");
                    }
                } else {
                    switch (pilihan) {
                        case 1:
                            menu.tampilkanMenu();
                            break;
                        case 2:
                            menu.tampilkanMenu();
                            System.out.print("Pilih nomor menu: ");
                            int nomorMenu = scanner.nextInt();
                            scanner.nextLine(); // Membersihkan buffer
                            String item = menu.getMenuItem(nomorMenu - 1);
                            if (item != null) {
                                pesanan.tambahPesanan(item);
                                System.out.println(item + " telah ditambahkan ke pesanan Anda.");
                            } else {
                                System.out.println("Menu tidak valid.");
                            }
                            break;
                        case 3:
                            pesanan.tampilkanPesanan();
                            break;
                        case 4:
                            System.out.print("Masukkan jumlah pembayaran: ");
                            double jumlah = scanner.nextDouble();
                            scanner.nextLine(); // Membersihkan buffer
                            pembayaran.prosesPembayaran(jumlah);
                            transaksi.tambahTransaksi("Pembayaran sebesar Rp " + jumlah);
                            break;
                        case 5:
                            transaksi.tampilkanRiwayat();
                            break;
                        case 6:
                            selesai = true;
                            System.out.println("Terima kasih telah berkunjung!");
                            break;
                        default:
                            System.out.println("Opsi tidak valid.");
                    }
                }
            }
        } else {
            System.out.println("Login gagal. Nama pengguna atau kata sandi salah.");
        }

        scanner.close();
    }
}


