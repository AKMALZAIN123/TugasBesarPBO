/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classs;

import java.util.ArrayList;

/**
 *
 * @author cofeshop
 */
public class Menu {
    private ArrayList<String> daftarMenu;

    public Menu() {
        daftarMenu = new ArrayList<>();
        daftarMenu.add("Espresso - Rp 20,000");
        daftarMenu.add("Latte - Rp 25,000");
        daftarMenu.add("Cappuccino - Rp 25,000");
        daftarMenu.add("Americano - Rp 22,000");
    }

    public void tampilkanMenu() {
        System.out.println("Menu:");
        for (int i = 0; i < daftarMenu.size(); i++) {
            System.out.println((i + 1) + ". " + daftarMenu.get(i));
        }
    }

    public String getMenuItem(int indeks) {
        if (indeks >= 0 && indeks < daftarMenu.size()) {
            return daftarMenu.get(indeks);
        }
        return null;
    }
}

