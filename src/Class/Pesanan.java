/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class;

/**
 *
 * @author akmal
 */
import java.util.ArrayList;

public class Pesanan {
    private ArrayList<String> daftarPesanan;

    public Pesanan() {
        daftarPesanan = new ArrayList<>();
    }

    public void tambahPesanan(String item) {
        daftarPesanan.add(item);
    }

    public void tampilkanPesanan() {
        System.out.println("Pesanan Anda:");
        for (String item : daftarPesanan) {
            System.out.println(item);
        }
    }

    public ArrayList<String> getDaftarPesanan() {
        return daftarPesanan;
    }
}

