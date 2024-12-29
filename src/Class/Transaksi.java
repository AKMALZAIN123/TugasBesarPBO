/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class;

import java.util.ArrayList;

/**
 *
 * @author akmal
 */
public class Transaksi {
    private ArrayList<String> riwayatTransaksi;

    public Transaksi() {
        riwayatTransaksi = new ArrayList<>();
    }

    public void tambahTransaksi(String transaksi) {
        riwayatTransaksi.add(transaksi);
    }

    public void tampilkanRiwayat() {
        System.out.println("Riwayat Transaksi:");
        System.out.println("Zain suka salsa");
        for (String transaksi : riwayatTransaksi) {
            System.out.println(transaksi);
        }
    }
}

