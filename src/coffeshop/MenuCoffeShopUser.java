/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package coffeshop;

import database.DatabaseConnection;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

/**
 *
 * @author akmal
 */
public class MenuCoffeShopUser extends javax.swing.JFrame {
    /**
     * Creates new form MenuCoffeShop
     */
    private void setNumberOnly(JTextField textField) {
        textField.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                char c = evt.getKeyChar();
                if (!Character.isDigit(c)) { 
                    evt.consume(); 
                }
            }
        });
    }

    public MenuCoffeShopUser() {
        initComponents();
        setNumberOnly(txtExpresso);
        setNumberOnly(txtLatte);
        setNumberOnly(txtCappucino);
        setNumberOnly(txtAmericano);
        setHargaProduk();
        loadDataKeTabel();
    }
    
    private void setHargaProduk() {
        String query = "SELECT namaMenu, harga FROM detailMenu WHERE namaMenu IN ('Expresso', 'Latte', 'Cappuccino', 'Americano')";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String namaMenu = rs.getString("namaMenu");
                int harga = rs.getInt("harga");

                // Set harga produk ke JLabel berdasarkan nama produk
                switch (namaMenu) {
                    case "Expresso":
                        harga1.setText("Rp " + harga);
                        break;
                    case "Latte":
                        harga2.setText("Rp " + harga);
                        break;
                    case "Cappuccino":
                        harga3.setText("Rp " + harga);
                        break;
                    case "Americano":
                        harga4.setText("Rp " + harga);
                        break;
                }
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal mengambil data harga produk: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void resetForm() {
        JCheckBox[] checkBoxes = {checkBoxExpresso, checkBoxLatte, checkBoxCappuccino, checkBoxAmericano};
        JTextField[] jumlahFields = {txtExpresso, txtLatte, txtCappucino, txtAmericano};

        for (JCheckBox checkBox : checkBoxes) {
            checkBox.setSelected(false);
        }

        for (JTextField field : jumlahFields) {
            field.setText("");
        }
    }
    
    private void simpanKeDatabase(DefaultTableModel model, Connection conn) throws SQLException {
        String query = "INSERT INTO menu (idMenu, namaMenu, harga, jumlah, totalHarga, idDetMenu) VALUES (DEFAULT, ?, ?, ?, ?, ?)";
        PreparedStatement pst = null;

        try {
            pst = conn.prepareStatement(query);

            // Dapatkan ID untuk detMenu
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT MAX(idDetMenu) FROM menu");
            int newDetMenuId = rs.next() ? rs.getInt(1) + 1 : 1;

            for (int i = 0; i < model.getRowCount(); i++) {
                pst.setString(1, model.getValueAt(i, 0).toString()); // namaMenu
                pst.setInt(2, Integer.parseInt(model.getValueAt(i, 1).toString())); // harga
                pst.setInt(3, Integer.parseInt(model.getValueAt(i, 2).toString())); // jumlah
                pst.setInt(4, Integer.parseInt(model.getValueAt(i, 3).toString())); // totalHarga
                pst.setInt(5, newDetMenuId); // idDetMenu
                pst.addBatch();

                newDetMenuId++; // Increment untuk baris berikutnya
            }

            pst.executeBatch();

            if (stmt != null) {
                stmt.close();
            }
            if (rs != null) {
                rs.close();
            }

        } finally {
            if (pst != null) {
                pst.close();
            }
        }
    }    
    private int ambilStokProduk(String produk, Connection conn) throws SQLException {
        String query = "SELECT jumlah FROM detailMenu WHERE namaMenu = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, produk);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("jumlah");
                }
            }
        }
        return 0; 
    }

    private void kurangiStokProduk(String produk, int jumlah, Connection conn) throws SQLException {
        String query = "UPDATE detailMenu SET jumlah = jumlah - ? WHERE namaMenu = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, jumlah);
            ps.setString(2, produk);
            ps.executeUpdate();
        }
    }
    
    private void loadDataKeTabel() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); 

        String query = "SELECT * FROM menu"; 
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                String namaMenu = rs.getString("namaMenu");
                int harga = rs.getInt("harga");
                int jumlah = rs.getInt("jumlah");
                int totalHarga = rs.getInt("totalHarga");

                model.addRow(new Object[]{namaMenu, harga, jumlah, totalHarga});
            }

        } catch (SQLException e) {
            javax.swing.JOptionPane.showMessageDialog(null, 
                "Error memuat data dari database: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }



    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        harga4 = new javax.swing.JLabel();
        harga1 = new javax.swing.JLabel();
        harga2 = new javax.swing.JLabel();
        harga3 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        btnTambah = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        btnOut = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        checkBoxExpresso = new javax.swing.JCheckBox();
        checkBoxLatte = new javax.swing.JCheckBox();
        checkBoxCappuccino = new javax.swing.JCheckBox();
        checkBoxAmericano = new javax.swing.JCheckBox();
        txtExpresso = new javax.swing.JTextField();
        txtLatte = new javax.swing.JTextField();
        txtCappucino = new javax.swing.JTextField();
        txtAmericano = new javax.swing.JTextField();
        btnPesan = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(163, 122, 81));

        jLabel1.setFont(new java.awt.Font("High Tower Text", 1, 32)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 102));
        jLabel1.setText("Coffe Shop");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Menu:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Harga:");

        harga4.setText("Rp. 22,000 ");

        harga1.setText("Rp. 20,000");

        harga2.setText("Rp. 25,000");

        harga3.setText("Rp. 25,000");

        jLabel8.setText("Jumlah:");

        jLabel9.setText("Menu Yang Dipesan:");

        btnTambah.setBackground(new java.awt.Color(0, 0, 0));
        btnTambah.setForeground(new java.awt.Color(255, 255, 255));
        btnTambah.setText("Tambah");
        btnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahActionPerformed(evt);
            }
        });

        btnReset.setBackground(new java.awt.Color(255, 0, 0));
        btnReset.setForeground(new java.awt.Color(255, 255, 255));
        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        btnOut.setBackground(new java.awt.Color(0, 0, 0));
        btnOut.setForeground(new java.awt.Color(255, 255, 255));
        btnOut.setText("Logout");
        btnOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOutActionPerformed(evt);
            }
        });

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Menu", "Harga", "Jumlah", "Total Harga"
            }
        ));
        jScrollPane1.setViewportView(table);

        checkBoxExpresso.setText("Expresso");
        checkBoxExpresso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxExpressoActionPerformed(evt);
            }
        });

        checkBoxLatte.setText("Latte");
        checkBoxLatte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxLatteActionPerformed(evt);
            }
        });

        checkBoxCappuccino.setText("Cappuccino");
        checkBoxCappuccino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxCappuccinoActionPerformed(evt);
            }
        });

        checkBoxAmericano.setText("Americano");
        checkBoxAmericano.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxAmericanoActionPerformed(evt);
            }
        });

        txtExpresso.setBackground(new java.awt.Color(255, 255, 255));
        txtExpresso.setForeground(new java.awt.Color(51, 51, 51));

        txtLatte.setBackground(new java.awt.Color(255, 255, 255));
        txtLatte.setForeground(new java.awt.Color(51, 51, 51));

        txtCappucino.setBackground(new java.awt.Color(255, 255, 255));
        txtCappucino.setForeground(new java.awt.Color(51, 51, 51));

        txtAmericano.setBackground(new java.awt.Color(255, 255, 255));
        txtAmericano.setForeground(new java.awt.Color(51, 51, 51));

        btnPesan.setBackground(new java.awt.Color(255, 255, 255));
        btnPesan.setForeground(new java.awt.Color(0, 0, 0));
        btnPesan.setText("Pesan");
        btnPesan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnOut))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(checkBoxExpresso)
                            .addComponent(checkBoxLatte)
                            .addComponent(checkBoxCappuccino)
                            .addComponent(checkBoxAmericano))
                        .addGap(47, 47, 47)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(harga1)
                            .addComponent(harga2)
                            .addComponent(harga3)
                            .addComponent(harga4))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(82, 82, 82)
                                .addComponent(txtAmericano, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(84, 84, 84)
                                    .addComponent(jLabel8))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtExpresso, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtLatte, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtCappucino, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(143, 143, 143))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 44, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btnTambah, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnPesan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(41, 41, 41))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(harga3)
                        .addComponent(checkBoxCappuccino)
                        .addComponent(txtCappucino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(harga1)
                                    .addComponent(checkBoxExpresso)
                                    .addComponent(txtExpresso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(45, 45, 45))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(harga2)
                                .addComponent(checkBoxLatte)
                                .addComponent(txtLatte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(47, 47, 47)))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(harga4)
                    .addComponent(checkBoxAmericano)
                    .addComponent(txtAmericano, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTambah)
                    .addComponent(btnReset)
                    .addComponent(btnPesan))
                .addGap(14, 14, 14)
                .addComponent(btnOut)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        boolean adaPesanan = false;
        JCheckBox[] checkBoxes = {checkBoxExpresso, checkBoxLatte, checkBoxCappuccino, checkBoxAmericano};
        JTextField[] jumlahFields = {txtExpresso, txtLatte, txtCappucino, txtAmericano};
        int totalSemuaPesanan = 0;
        Connection conn = null;
        PreparedStatement selectStmt = null;

        try {
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false);

            String selectQuery = "SELECT harga FROM detailMenu WHERE namaMenu = ?";
            selectStmt = conn.prepareStatement(selectQuery);

            for (int i = 0; i < checkBoxes.length; i++) {
                JCheckBox checkBox = checkBoxes[i];
                JTextField jumlahField = jumlahFields[i];

                if (checkBox.isSelected()) {
                    adaPesanan = true;
                    String produk = checkBox.getText();
                    int jumlah;

                    try {
                        jumlah = jumlahField.getText().isEmpty() ? 0 : Integer.parseInt(jumlahField.getText());
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(this, "Masukkan jumlah yang valid untuk " + produk);
                        return;
                    }

                    if (jumlah <= 0) {
                        JOptionPane.showMessageDialog(this, "Jumlah untuk " + produk + " harus lebih dari 0.");
                        return;
                    }

                    selectStmt.setString(1, produk);
                    ResultSet rs = selectStmt.executeQuery();

                    if (rs.next()) {
                        int harga = rs.getInt("harga");
                        int stokTersedia = ambilStokProduk(produk, conn);

                        if (stokTersedia < jumlah) {
                            JOptionPane.showMessageDialog(this, "Stok untuk " + produk + " tidak mencukupi. Stok tersedia: " + stokTersedia);
                            if (!conn.isClosed()) {
                                conn.rollback();
                            }
                            return;
                        }

                        kurangiStokProduk(produk, jumlah, conn);
                        int totalHarga = harga * jumlah;
                        model.addRow(new Object[]{produk, harga, jumlah, totalHarga});
                        totalSemuaPesanan += totalHarga;
                        rs.close();
                    } else {
                        JOptionPane.showMessageDialog(this, "Produk " + produk + " tidak ditemukan.");
                        return;
                    }
                }
            }

            if (!adaPesanan) {
                JOptionPane.showMessageDialog(this, "Pilih minimal satu menu.");
                return;
            }

            simpanKeDatabase(model, conn);

            conn.commit();
            JOptionPane.showMessageDialog(this, "Pesanan berhasil dibuat! Total: Rp " + totalSemuaPesanan);

        } catch (SQLException e) {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan koneksi database: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (selectStmt != null && !selectStmt.isClosed()) {
                    selectStmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnTambahActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        int confirm = javax.swing.JOptionPane.showConfirmDialog(
            this,
            "Apakah Anda yakin ingin menghapus semua pesanan?",
            "Konfirmasi Reset",
            javax.swing.JOptionPane.YES_NO_OPTION
        );

        if (confirm == javax.swing.JOptionPane.YES_OPTION) {
            Connection conn = null;
            Statement stmt = null;

            try {
                conn = DatabaseConnection.getConnection();
                if (conn == null) {
                    throw new SQLException("Tidak dapat terhubung ke database");
                }

                conn.setAutoCommit(false);
                stmt = conn.createStatement();

                String sqlReset1 = "SET FOREIGN_KEY_CHECKS = 0";
                String sqlReset2 = "TRUNCATE menu";
                String sqlReset3 = "ALTER TABLE menu AUTO_INCREMENT = 1";
                String sqlReset4 = "SET FOREIGN_KEY_CHECKS = 1";
                stmt.executeUpdate(sqlReset1);
                stmt.executeUpdate(sqlReset2);
                stmt.executeUpdate(sqlReset3);
                stmt.executeUpdate(sqlReset4);

                DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.setRowCount(0);

                resetForm();
                conn.commit();
                javax.swing.JOptionPane.showMessageDialog(this, 
                    "Semua pesanan berhasil dihapus!", 
                    "Sukses", 
                    javax.swing.JOptionPane.INFORMATION_MESSAGE);

            } catch (SQLException e) {
                if (conn != null) {
                    try {
                        conn.rollback();
                    } catch (SQLException ex) {
                        System.err.println("Error during rollback: " + ex.getMessage());
                    }
                }
                javax.swing.JOptionPane.showMessageDialog(this,
                    "Error menghapus data: " + e.getMessage(),
                    "Error",
                    javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOutActionPerformed
        LoginPengguna log = new LoginPengguna();
        log.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnOutActionPerformed

    private void checkBoxExpressoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBoxExpressoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkBoxExpressoActionPerformed

    private void checkBoxLatteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBoxLatteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkBoxLatteActionPerformed

    private void checkBoxCappuccinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBoxCappuccinoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkBoxCappuccinoActionPerformed

    private void checkBoxAmericanoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBoxAmericanoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkBoxAmericanoActionPerformed

    private void btnPesanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesanActionPerformed
        PembayaranUser bayar = new PembayaranUser();
        bayar.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnPesanActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MenuCoffeShopUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuCoffeShopUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuCoffeShopUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuCoffeShopUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuCoffeShopUser().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnOut;
    private javax.swing.JButton btnPesan;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnTambah;
    private javax.swing.JCheckBox checkBoxAmericano;
    private javax.swing.JCheckBox checkBoxCappuccino;
    private javax.swing.JCheckBox checkBoxExpresso;
    private javax.swing.JCheckBox checkBoxLatte;
    private javax.swing.JLabel harga1;
    private javax.swing.JLabel harga2;
    private javax.swing.JLabel harga3;
    private javax.swing.JLabel harga4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table;
    private javax.swing.JTextField txtAmericano;
    private javax.swing.JTextField txtCappucino;
    private javax.swing.JTextField txtExpresso;
    private javax.swing.JTextField txtLatte;
    // End of variables declaration//GEN-END:variables
}
