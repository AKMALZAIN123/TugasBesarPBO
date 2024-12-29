/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class;

/**
 *
 * @author akmal
 */
public class Pengguna {

    private String namaPengguna;
    private String kataSandi;
    private String role;

    public Pengguna(String namaPengguna, String kataSandi, String role) throws ValidasiInputException {
        if (namaPengguna == null || namaPengguna.isEmpty()) {
            throw new ValidasiInputException("namaPengguna tidak boleh kosong!");
        }
        if (kataSandi == null || kataSandi.isEmpty()) {
            throw new ValidasiInputException("kataSandi tidak boleh kosong!");
        }
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
