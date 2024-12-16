/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package coffeshop;

import java.util.Scanner;
/**
 *
 * @author akmal
 */


public class CoffeShop {
    public static void main(String[] args) {
        // Membuat objek pengguna yang sudah ada
        Pengguna pengguna = new Pengguna("pelanggan", "12345");

        // Menjalankan GUI LoginPengguna
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                // Menampilkan LoginPengguna dengan mengirimkan objek Pengguna
                new LoginPengguna(pengguna).setVisible(true);
            }
        });
    }
}

