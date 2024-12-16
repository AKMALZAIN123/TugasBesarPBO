/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coffeshop;

/**
 *
 * @author akmal
 */
public class Pengguna {
    private String namaPengguna;
    private String kataSandi;

    public Pengguna(String namaPengguna, String kataSandi) {
        this.namaPengguna = namaPengguna;
        this.kataSandi = kataSandi;
    }

    public String getNamaPengguna() {
        return namaPengguna;
    }

    public boolean cekKataSandi(String inputKataSandi) {
        return kataSandi.equals(inputKataSandi);
    }
}

