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
    private String role; // Menambahkan role (admin/pelanggan)

    public Pengguna(String namaPengguna, String kataSandi, String role) {
        this.namaPengguna = namaPengguna;
        this.kataSandi = kataSandi;
        this.role = role;
    }

    public String getNamaPengguna() {
        return namaPengguna;
    }

    public String getKataSandi() {
        return kataSandi;
    }

    public String getRole() {
        return role;
    }

    public boolean cekKataSandi(String inputKataSandi) {
        return kataSandi.equals(inputKataSandi);
    }
}





