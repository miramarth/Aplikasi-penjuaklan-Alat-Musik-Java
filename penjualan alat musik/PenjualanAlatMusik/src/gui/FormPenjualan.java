/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;
import database.Koneksi;
import gui.FormBarang;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.awt.event.KeyEvent;
import java.sql.*;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
/**
 *
 * @author user
 */
public class FormPenjualan extends javax.swing.JFrame {
    public FormPenjualan() {
        initComponents();
        Awal();
        
    }
    
    FormPenjualan(String kd, String nm, String harga){
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        initComponents();
        Set();
        
        ed_KdBrgTrans.setText(kd);
        ed_NmBrgTrans.setText(nm);
        ed_HargaBrg.setText(harga);
        
        loginPanel.setVisible(false);
        
        transaksiPanel.setVisible(true);
    }
    /**
     * Creates new form FormPenjualan
     */
    public void Set(){
    String kdTrans = kd.getText();
    kd.setText(kdTrans);
    String tglT = ed_TglTrans.getText();
    ed_TglTrans.setText(tglT);
    }
    
    public void Awal(){
        loginPanel.setVisible(true);
        homePanel.setVisible(false);
        barangPanel.setVisible(false);
        jenisBarangPanel.setVisible(false);
        karyawanPanel.setVisible(false);
        supplierPanel.setVisible(false);
        pelangganPanel.setVisible(false);
        daftarPanel.setVisible(false);
        transaksiPanel.setVisible(false);
        
        btnLoginPanel.setEnabled(true);
        btnBarang.setEnabled(false);
        btnJenisBarang.setEnabled(false);
        btnKaryawan.setEnabled(false);
        btnPelanggan.setEnabled(false);
        btnSupplier.setEnabled(false);
        btnTransaksi.setEnabled(false);
        btnHome.setEnabled(false);
        btnLaporan.setEnabled(false);
        
        BersihkanLogin();
    }
    public void BersihkanLogin(){
    ed_Username.setText("");
    ed_Password.setText("");
    }
    
    public void hakKasir(){
        homePanel.setVisible(true);
        barangPanel.setVisible(false);
        jenisBarangPanel.setVisible(false);
        karyawanPanel.setVisible(false);
        supplierPanel.setVisible(false);
        pelangganPanel.setVisible(false);
        daftarPanel.setVisible(false);
        transaksiPanel.setVisible(false);
        loginPanel.setVisible(false);
        
        btnJenisBarang.setEnabled(false);
        btnBarang.setEnabled(false);
        btnKaryawan.setEnabled(false);
        btnPelanggan.setEnabled(true);
        btnSupplier.setEnabled(false);
        btnTransaksi.setEnabled(true);
        btnHome.setEnabled(true);
        btnLaporan.setEnabled(false);
    }
    public void hakPemilik(){
        homePanel.setVisible(true);
        barangPanel.setVisible(false);
        jenisBarangPanel.setVisible(false);
        karyawanPanel.setVisible(false);
        supplierPanel.setVisible(false);
        pelangganPanel.setVisible(false);
        daftarPanel.setVisible(false);
        transaksiPanel.setVisible(false);
        loginPanel.setVisible(false);
        
        btnJenisBarang.setEnabled(true);
        btnBarang.setEnabled(true);
        btnKaryawan.setEnabled(true);
        btnPelanggan.setEnabled(true);
        btnSupplier.setEnabled(true);
        btnTransaksi.setEnabled(true);
        btnHome.setEnabled(true);
        btnLaporan.setEnabled(true);
    }
    
     private void clearBrg(){ 
        ed_KdBrg.setText("");
        ed_NamaBrg.setText("");
        ed_Merk.setText("");
        ed_Harga.setText("");
        ed_Stok.setText("");
        ed_KdKategori.setText("");
        ed_IdSupplier.setText("");
    }
     
    private void clearKategori(){ 
        ed_KdKat.setText("");
        ed_Kat.setText("");    
    }
       
    private void clearSupplier(){ 
        ed_IdSup.setText("");
        ed_NamaSup.setText("");
        ed_AlamatSup.setText("");
        ed_NoHpSup.setText("");
    }
    
    private void clearKaryawan(){ 
        ed_IdKar.setText("");
        ed_NamaKar.setText("");
        ed_AlamatKar.setText("");
        ed_NoHoKar.setText("");
    }
    
    private void clearPelanggan(){ 
        ed_IdPelanggan.setText("");
        ed_Nama_Pelanggan.setText("");
        ed_AlamatPelanggan.setText("");
        ed_NoHpPelanggan.setText("");

    }
       private void clearTransaksi(){ 
        kd.setText("");
        ed_IdKarTrans.setText("");
        ed_IdPelTrans.setText("");
        ed_KdBrgTrans.setText("");
        ed_NmBrgTrans.setText("");
        ed_HargaBrg.setText("");
        ed_JumlahBrgTrans.setText("");
        lblTotal.setText("Total");
        ed_TglTrans.setText("");
    }
       private void clearDaftarAdmin(){ 
        ed_user.setText("");
        edf_pass.setText("");
        edf_verif.setText("");
        ed_hak.setText("");
        jLabel48.setVisible(false);
        jLabel47.setVisible(false);
    }
     
    private void tampilDataBrg(){
         DefaultTableModel model = new DefaultTableModel();
         model.addColumn("kd barang");
         model.addColumn("nama barang");
         model.addColumn("Merk");
         model.addColumn("Harga");
         model.addColumn("Stok");
         model.addColumn("Kd Kategori");
         model.addColumn("Id Supplier");
    
      try{
        Connection objConnection = new Koneksi().getConnection();
        try (Statement s = objConnection.createStatement()) {
            String query = "Select* from barang";
            ResultSet r= s.executeQuery(query);
            while (r.next()){
                Object[] obj = new Object[7];
                obj[0]=r.getString("kd_barang");
                obj[1]=r.getString("nama_barang");
                obj[2]=r.getString("merk");
                obj[3]=r.getString("harga_barang");
                obj[4]=r.getString("stok");
                obj[5]=r.getString("kd_kategori");
                obj[6]=r.getString("id_supplier");
               
                model.addRow(obj);  
            }
            tblBarang.setModel(model);
             }
         }
        catch (SQLException ex){
        System.out.println("Tampil data Barang: Terjadi Kesalahan");
         }
     }
    private void tampilDataKategori(){
         DefaultTableModel model = new DefaultTableModel();
         model.addColumn("kd Kategori");
         model.addColumn("nama Kategori");
    
        try{
            Connection objConnection = new Koneksi().getConnection();
            try (Statement s = objConnection.createStatement()) {
                String query = "Select* from kategori";
                ResultSet r= s.executeQuery(query);
                
                while (r.next()){
                Object[] obj = new Object[2];
                obj[0]=r.getString("kd_kategori");
                obj[1]=r.getString("nama_kategori");
                            
                model.addRow(obj);
                }
                tabelKategori.setModel(model);}
                } catch (SQLException ex){
                System.out.println("Tampil data Kategori: Terjadi Kesalahan");}
    }
       
    private void tampilataSupplier(){
         DefaultTableModel model = new DefaultTableModel();
         model.addColumn("Id Supplier");
         model.addColumn("nama Supplier");
         model.addColumn("Alamat");
         model.addColumn("No Hp");
    
      try{
        Connection objConnection = new Koneksi().getConnection();
        try (Statement s = objConnection.createStatement()) {
            String query = "Select* from supplier";
            ResultSet r= s.executeQuery(query);
            while (r.next()){
                Object[] obj = new Object[4];
                obj[0]=r.getString("id_supplier");
                obj[1]=r.getString("nama_supplier");
                obj[2]=r.getString("Alamat");
                obj[3]=r.getString("no_hp");
               
                model.addRow(obj);  
                }
                tblSupplier.setModel(model);}
                } catch (SQLException ex){
                  System.out.println("Tampil data Supplier: Terjadi Kesalahan");}
     }
    
     private void tampilataKaryawan(){
         DefaultTableModel model = new DefaultTableModel();
         model.addColumn("Id Karyawan");
         model.addColumn("nama Karyawan");
         model.addColumn("Alamat");
         model.addColumn("No Hp");
    
      try{
        Connection objConnection = new Koneksi().getConnection();
        try (Statement s = objConnection.createStatement()) {
            String query = "Select* from karyawan";
            ResultSet r= s.executeQuery(query);
            while (r.next()){
                Object[] obj = new Object[4];
                obj[0]=r.getString("id_karyawan");
                obj[1]=r.getString("nama_karyawan");
                obj[2]=r.getString("Alamat");
                obj[3]=r.getString("no_hp");
               
                model.addRow(obj);
                }
                tblKaryawan.setModel(model);}
                } catch (SQLException ex){
                  System.out.println("Tampil data karyawan: Terjadi Kesalahan");}
     }
     
      private void tampilataPelanggan(){
         DefaultTableModel model = new DefaultTableModel();
         model.addColumn("Id Pelanggan");
         model.addColumn("nama Pelanggan");
         model.addColumn("Alamat");
         model.addColumn("No Hp");
    
      try{
        Connection objConnection = new Koneksi().getConnection();
        try (Statement s = objConnection.createStatement()) {
            String query = "Select* from pelanggan";
            ResultSet r= s.executeQuery(query);
            while (r.next()){
                Object[] obj = new Object[4];
                obj[0]=r.getString("id_Pelanggan");
                obj[1]=r.getString("nama_Pelanggan");
                obj[2]=r.getString("Alamat");
                obj[3]=r.getString("no_hp");
               
                model.addRow(obj);  
            }
            tblPelanggan.setModel(model);}
            } catch (SQLException ex){
              System.out.println("Tampil data pelanggan: Terjadi Kesalahan");}
     }
      private void tampilataTransaksi(){
         DefaultTableModel model = new DefaultTableModel();
         model.addColumn("Kd Transaksi");
         model.addColumn("id karyawan");
         model.addColumn("id pelanggan");
         model.addColumn("kd barang");
         model.addColumn("nama barang");
         model.addColumn("harga barang");
         model.addColumn("jumlah barang");
         model.addColumn("total bayar");
         model.addColumn("tgl transaksi");
    
      try{
        Connection objConnection = new Koneksi().getConnection();
        try (Statement s = objConnection.createStatement()) {
            String query = "Select* from transaksi";
            ResultSet r= s.executeQuery(query);
            while (r.next()){
                Object[] obj = new Object[9];
                obj[0]=r.getString("kd_transaksi");
                obj[1]=r.getString("id_karyawan");
                obj[2]=r.getString("id_pelanggan");
                obj[3]=r.getString("kd_barang");
                obj[4]=r.getString("nama_barang");
                obj[5]=r.getString("harga_barang");
                obj[6]=r.getString("jumlah_barang");
                obj[7]=r.getString("total_bayar");
                obj[8]=r.getString("tgl_transaksi");
               
                model.addRow(obj);  
            }
            tblTransaksi.setModel(model);}
         } catch (SQLException ex){
           System.out.println("Tampil data transaksi: Terjadi Kesalahan");}
     }
      private String kode,tgl,pegawai,pelanggan,brg,merk,harga,jml,ttl;
      private void tampilDaftarAdmin(){
         DefaultTableModel model = new DefaultTableModel();
         model.addColumn("Username");
         model.addColumn("Password");
         model.addColumn("Hak Akses");
    
        try{
            Connection objConnection = new Koneksi().getConnection();
            try (Statement s = objConnection.createStatement()) {
                String query = "Select * from admin";
                ResultSet r= s.executeQuery(query);
                
                while (r.next()){
                Object[] obj = new Object[3];
                obj[0]=r.getString("username");
                obj[1]=r.getString("password");
                obj[2]=r.getString("hak_akses");
                            
                model.addRow(obj);  
            }
            tbl_DaftarAdmin.setModel(model);}
            } catch (SQLException ex){
              System.out.println("Tampil Data Admin: Terjadi Kesalahan");}
         }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelDasar = new javax.swing.JPanel();
        panelButton = new javax.swing.JPanel();
        btnHome = new javax.swing.JButton();
        btnBarang = new javax.swing.JButton();
        btnSupplier = new javax.swing.JButton();
        btnJenisBarang = new javax.swing.JButton();
        btnKaryawan = new javax.swing.JButton();
        btnLaporan = new javax.swing.JButton();
        btnTransaksi = new javax.swing.JButton();
        btnPelanggan = new javax.swing.JButton();
        btnLoginPanel = new javax.swing.JButton();
        mainPanel = new javax.swing.JPanel();
        loginPanel = new javax.swing.JPanel();
        btnLogin = new javax.swing.JButton();
        ed_Password = new javax.swing.JPasswordField();
        ed_Username = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        homePanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel36 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        barangPanel = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        ed_KdBrg = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        ed_NamaBrg = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        ed_Merk = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        ed_Harga = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        ed_Stok = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        ed_KdKategori = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBarang = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        ed_IdSupplier = new javax.swing.JTextField();
        btnSimpan = new javax.swing.JButton();
        btnUbah = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        jenisBarangPanel = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        ed_KdKat = new javax.swing.JTextField();
        ed_Kat = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabelKategori = new javax.swing.JTable();
        btnSimpanKat = new javax.swing.JButton();
        btnUbahKat = new javax.swing.JButton();
        btnHapusKat = new javax.swing.JButton();
        supplierPanel = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        ed_IdSup = new javax.swing.JTextField();
        ed_NamaSup = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        ed_AlamatSup = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        ed_NoHpSup = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblSupplier = new javax.swing.JTable();
        btnSimpanSup = new javax.swing.JButton();
        btnUbahSup = new javax.swing.JButton();
        btnHapusSup = new javax.swing.JButton();
        karyawanPanel = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        ed_IdKar = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        ed_NamaKar = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        ed_AlamatKar = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        ed_NoHoKar = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblKaryawan = new javax.swing.JTable();
        btnSimpanKaryawan = new javax.swing.JButton();
        btnUbahKaryawan = new javax.swing.JButton();
        btnHapusKaryawan = new javax.swing.JButton();
        jLabel40 = new javax.swing.JLabel();
        daftarPanel = new javax.swing.JPanel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tbl_DaftarAdmin = new javax.swing.JTable();
        ed_user = new javax.swing.JTextField();
        ed_hak = new javax.swing.JTextField();
        edf_pass = new javax.swing.JPasswordField();
        edf_verif = new javax.swing.JPasswordField();
        btn_simpanAdmin = new javax.swing.JButton();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        pelangganPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        ed_IdPelanggan = new javax.swing.JTextField();
        Jlabel = new javax.swing.JLabel();
        ed_Nama_Pelanggan = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        ed_AlamatPelanggan = new javax.swing.JTextField();
        ed_NoHpPelanggan = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblPelanggan = new javax.swing.JTable();
        btnSimpanPelanggan = new javax.swing.JButton();
        btnUbahPelanggan = new javax.swing.JButton();
        btnHapusPelanggan = new javax.swing.JButton();
        transaksiPanel = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        ed_TglTrans = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        ed_IdKarTrans = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        ed_IdPelTrans = new javax.swing.JTextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblTransaksi = new javax.swing.JTable();
        ed_KdBrgTrans = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        ed_NmBrgTrans = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        ed_HargaBrg = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        ed_JumlahBrgTrans = new javax.swing.JTextField();
        lblTotal = new javax.swing.JLabel();
        btnSimpanTrans = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        btnCetak = new javax.swing.JButton();
        kd = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelDasar.setBackground(new java.awt.Color(0, 0, 51));

        panelButton.setBackground(new java.awt.Color(255, 255, 255));

        btnHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/home.png"))); // NOI18N
        btnHome.setText("Home");
        btnHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHomeActionPerformed(evt);
            }
        });

        btnBarang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/barang2.png"))); // NOI18N
        btnBarang.setText("Barang");
        btnBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBarangActionPerformed(evt);
            }
        });

        btnSupplier.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/supplier.png"))); // NOI18N
        btnSupplier.setText("Supplier");
        btnSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSupplierActionPerformed(evt);
            }
        });

        btnJenisBarang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/jenis.png"))); // NOI18N
        btnJenisBarang.setText("Jenis Barang");
        btnJenisBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnJenisBarangActionPerformed(evt);
            }
        });

        btnKaryawan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/pegawai.png"))); // NOI18N
        btnKaryawan.setText("Karyawan");
        btnKaryawan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKaryawanActionPerformed(evt);
            }
        });

        btnLaporan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/laporan.png"))); // NOI18N
        btnLaporan.setText("Laporan");

        btnTransaksi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/kasir.png"))); // NOI18N
        btnTransaksi.setText("Transaksi");
        btnTransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTransaksiActionPerformed(evt);
            }
        });

        btnPelanggan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/pegawai.png"))); // NOI18N
        btnPelanggan.setText("Pelanggan");
        btnPelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPelangganActionPerformed(evt);
            }
        });

        btnLoginPanel.setText("Login");

        javax.swing.GroupLayout panelButtonLayout = new javax.swing.GroupLayout(panelButton);
        panelButton.setLayout(panelButtonLayout);
        panelButtonLayout.setHorizontalGroup(
            panelButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelButtonLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelButtonLayout.createSequentialGroup()
                        .addGroup(panelButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnPelanggan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnLaporan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnKaryawan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnSupplier, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnJenisBarang, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnBarang, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnHome, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnTransaksi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(btnLoginPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelButtonLayout.setVerticalGroup(
            panelButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelButtonLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnLoginPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnHome)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnBarang)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnJenisBarang)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnKaryawan, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnTransaksi)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addComponent(btnLaporan)
                .addContainerGap())
        );

        mainPanel.setLayout(new java.awt.CardLayout());

        loginPanel.setBackground(new java.awt.Color(255, 255, 204));

        btnLogin.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnLogin.setText("Masuk");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        ed_Password.setText("jPasswordField1");

        ed_Username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ed_UsernameActionPerformed(evt);
            }
        });

        jLabel46.setFont(new java.awt.Font("Kristen ITC", 3, 28)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(0, 0, 102));
        jLabel46.setText("LOGIN");

        jLabel49.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel49.setText("Username");

        jLabel50.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel50.setText("Password");

        jLabel51.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/main.png"))); // NOI18N

        javax.swing.GroupLayout loginPanelLayout = new javax.swing.GroupLayout(loginPanel);
        loginPanel.setLayout(loginPanelLayout);
        loginPanelLayout.setHorizontalGroup(
            loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loginPanelLayout.createSequentialGroup()
                .addContainerGap(198, Short.MAX_VALUE)
                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loginPanelLayout.createSequentialGroup()
                        .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(279, 279, 279))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loginPanelLayout.createSequentialGroup()
                        .addComponent(jLabel51)
                        .addGap(185, 185, 185))))
            .addGroup(loginPanelLayout.createSequentialGroup()
                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(loginPanelLayout.createSequentialGroup()
                        .addGap(105, 105, 105)
                        .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel49)
                            .addComponent(jLabel50))
                        .addGap(64, 64, 64)
                        .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(ed_Username)
                            .addComponent(ed_Password, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(loginPanelLayout.createSequentialGroup()
                        .addGap(276, 276, 276)
                        .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        loginPanelLayout.setVerticalGroup(
            loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loginPanelLayout.createSequentialGroup()
                .addContainerGap(36, Short.MAX_VALUE)
                .addComponent(jLabel46)
                .addGap(18, 18, 18)
                .addComponent(jLabel51)
                .addGap(18, 18, 18)
                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ed_Username, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel49))
                .addGap(34, 34, 34)
                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ed_Password, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel50))
                .addGap(18, 18, 18)
                .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );

        mainPanel.add(loginPanel, "card10");

        homePanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/main.png"))); // NOI18N

        jLabel3.setFont(new java.awt.Font("Script MT Bold", 0, 12)); // NOI18N
        jLabel3.setText("by : Salma_Mira");

        jLabel25.setFont(new java.awt.Font("Kristen ITC", 3, 24)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(0, 0, 102));
        jLabel25.setText("Selamat Datang di");

        jButton2.setText("Log Out");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel36.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel36.setText("Toko Alat Musik ");

        jLabel39.setFont(new java.awt.Font("Segoe Print", 1, 24)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(244, 34, 34));
        jLabel39.setText("SM MELODY");

        javax.swing.GroupLayout homePanelLayout = new javax.swing.GroupLayout(homePanel);
        homePanel.setLayout(homePanelLayout);
        homePanelLayout.setHorizontalGroup(
            homePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(homePanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(homePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, homePanelLayout.createSequentialGroup()
                        .addGroup(homePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, homePanelLayout.createSequentialGroup()
                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(199, 199, 199))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, homePanelLayout.createSequentialGroup()
                .addContainerGap(187, Short.MAX_VALUE)
                .addGroup(homePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, homePanelLayout.createSequentialGroup()
                        .addComponent(jLabel36)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(121, 121, 121))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, homePanelLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(164, 164, 164))))
        );
        homePanelLayout.setVerticalGroup(
            homePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(homePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2)
                .addGap(31, 31, 31)
                .addComponent(jLabel25)
                .addGap(18, 18, 18)
                .addGroup(homePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel39))
                .addGap(43, 43, 43)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 73, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addContainerGap())
        );

        mainPanel.add(homePanel, "card2");

        barangPanel.setBackground(new java.awt.Color(69, 166, 154));

        jLabel4.setFont(new java.awt.Font("Arial Narrow", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 51));
        jLabel4.setText("Barang");

        jLabel5.setText("Kode Barang");

        ed_KdBrg.setText("jTextField1");

        jLabel6.setText("Nama Barang");

        ed_NamaBrg.setText("jTextField1");

        jLabel7.setText("Merk");

        ed_Merk.setText("jTextField1");

        jLabel8.setText("Harga");

        ed_Harga.setText("jTextField1");

        jLabel9.setText("Stok");

        ed_Stok.setText("jTextField1");

        jLabel10.setText("Kode Kategori");

        ed_KdKategori.setText("jTextField1");

        tblBarang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblBarang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBarangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblBarang);

        jLabel11.setText("Id Supplier");

        ed_IdSupplier.setText("jTextField1");

        btnSimpan.setText("Simpan");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });

        btnUbah.setText("Ubah");
        btnUbah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUbahActionPerformed(evt);
            }
        });

        btnHapus.setText("Hapus");
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout barangPanelLayout = new javax.swing.GroupLayout(barangPanel);
        barangPanel.setLayout(barangPanelLayout);
        barangPanelLayout.setHorizontalGroup(
            barangPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, barangPanelLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(barangPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(barangPanelLayout.createSequentialGroup()
                        .addGroup(barangPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(barangPanelLayout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(ed_Harga, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(barangPanelLayout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(ed_Merk, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, barangPanelLayout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(ed_NamaBrg, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, barangPanelLayout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(ed_KdBrg, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(barangPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(barangPanelLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(barangPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, barangPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(ed_Stok, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(ed_KdKategori, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(ed_IdSupplier, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(barangPanelLayout.createSequentialGroup()
                                .addComponent(btnSimpan)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 77, Short.MAX_VALUE)
                                .addComponent(btnUbah)
                                .addGap(66, 66, 66)
                                .addComponent(btnHapus)))))
                .addGap(22, 22, 22))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, barangPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(barangPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(barangPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel9)
                        .addComponent(jLabel4))
                    .addGroup(barangPanelLayout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addGroup(barangPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel10))))
                .addGap(255, 255, 255))
        );
        barangPanelLayout.setVerticalGroup(
            barangPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(barangPanelLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addGroup(barangPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(barangPanelLayout.createSequentialGroup()
                        .addGroup(barangPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(ed_KdBrg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addGap(18, 18, 18)
                        .addGroup(barangPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(ed_NamaBrg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(barangPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(ed_Merk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(barangPanelLayout.createSequentialGroup()
                        .addComponent(ed_Stok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(barangPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ed_KdKategori, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addGap(18, 18, 18)
                        .addGroup(barangPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ed_IdSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))))
                .addGap(18, 18, 18)
                .addGroup(barangPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ed_Harga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(btnSimpan)
                    .addComponent(btnUbah)
                    .addComponent(btnHapus))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        mainPanel.add(barangPanel, "card3");

        jenisBarangPanel.setBackground(new java.awt.Color(149, 203, 184));

        jLabel17.setFont(new java.awt.Font("Arial Narrow", 1, 24)); // NOI18N
        jLabel17.setText("Kategori Barang");

        jLabel18.setText("Kode Kategori");

        ed_KdKat.setText("jTextField1");
        ed_KdKat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ed_KdKatActionPerformed(evt);
            }
        });

        ed_Kat.setText("jTextField1");

        jLabel19.setText("Kategori Barang");

        tabelKategori.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabelKategori.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelKategoriMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tabelKategori);

        btnSimpanKat.setText("Simpan");
        btnSimpanKat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanKatActionPerformed(evt);
            }
        });

        btnUbahKat.setText("Ubah");
        btnUbahKat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUbahKatActionPerformed(evt);
            }
        });

        btnHapusKat.setText("Hapus");
        btnHapusKat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusKatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jenisBarangPanelLayout = new javax.swing.GroupLayout(jenisBarangPanel);
        jenisBarangPanel.setLayout(jenisBarangPanelLayout);
        jenisBarangPanelLayout.setHorizontalGroup(
            jenisBarangPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jenisBarangPanelLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jenisBarangPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3)
                    .addGroup(jenisBarangPanelLayout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addGap(18, 18, 18)
                        .addComponent(ed_KdKat, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                        .addComponent(jLabel19)
                        .addGap(18, 18, 18)
                        .addComponent(ed_Kat, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(32, 32, 32))
            .addGroup(jenisBarangPanelLayout.createSequentialGroup()
                .addGroup(jenisBarangPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jenisBarangPanelLayout.createSequentialGroup()
                        .addGap(152, 152, 152)
                        .addComponent(btnSimpanKat, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(62, 62, 62)
                        .addComponent(btnUbahKat, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55)
                        .addComponent(btnHapusKat, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jenisBarangPanelLayout.createSequentialGroup()
                        .addGap(262, 262, 262)
                        .addComponent(jLabel17)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jenisBarangPanelLayout.setVerticalGroup(
            jenisBarangPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jenisBarangPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17)
                .addGap(18, 18, 18)
                .addGroup(jenisBarangPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(ed_KdKat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19)
                    .addComponent(ed_Kat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jenisBarangPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSimpanKat)
                    .addComponent(btnUbahKat)
                    .addComponent(btnHapusKat))
                .addGap(31, 31, 31)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(213, Short.MAX_VALUE))
        );

        mainPanel.add(jenisBarangPanel, "card5");

        supplierPanel.setBackground(new java.awt.Color(230, 163, 163));

        jLabel12.setFont(new java.awt.Font("Arial Narrow", 1, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 102));
        jLabel12.setText("Supplier");

        jLabel13.setText("Id Supplier");

        ed_IdSup.setText("jTextField1");

        ed_NamaSup.setText("jTextField1");

        jLabel14.setText("Nama Supplier");

        ed_AlamatSup.setText("jTextField1");

        jLabel15.setText("Alamat");

        ed_NoHpSup.setText("jTextField1");

        jLabel16.setText("No Hp");

        tblSupplier.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblSupplier.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSupplierMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblSupplier);

        btnSimpanSup.setText("Simpan");
        btnSimpanSup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanSupActionPerformed(evt);
            }
        });

        btnUbahSup.setText("Ubah");
        btnUbahSup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUbahSupActionPerformed(evt);
            }
        });

        btnHapusSup.setText("Hapus");
        btnHapusSup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusSupActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout supplierPanelLayout = new javax.swing.GroupLayout(supplierPanel);
        supplierPanel.setLayout(supplierPanelLayout);
        supplierPanelLayout.setHorizontalGroup(
            supplierPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(supplierPanelLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(supplierPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(supplierPanelLayout.createSequentialGroup()
                        .addGroup(supplierPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(jLabel13))
                        .addGap(50, 50, 50)
                        .addGroup(supplierPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(supplierPanelLayout.createSequentialGroup()
                                .addComponent(btnSimpanSup)
                                .addGap(79, 79, 79)
                                .addComponent(btnUbahSup, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(83, 83, 83)
                                .addComponent(btnHapusSup))
                            .addGroup(supplierPanelLayout.createSequentialGroup()
                                .addGroup(supplierPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(supplierPanelLayout.createSequentialGroup()
                                        .addGroup(supplierPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(ed_IdSup, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                                            .addComponent(ed_NamaSup))
                                        .addGap(32, 32, 32)
                                        .addGroup(supplierPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel15)
                                            .addComponent(jLabel16)))
                                    .addGroup(supplierPanelLayout.createSequentialGroup()
                                        .addGap(161, 161, 161)
                                        .addComponent(jLabel12)))
                                .addGap(27, 27, 27)
                                .addGroup(supplierPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(ed_AlamatSup, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                                    .addComponent(ed_NoHpSup)))))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 636, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        supplierPanelLayout.setVerticalGroup(
            supplierPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(supplierPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addGap(17, 17, 17)
                .addGroup(supplierPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(ed_IdSup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(ed_AlamatSup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(supplierPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(ed_NamaSup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(ed_NoHpSup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(supplierPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSimpanSup)
                    .addComponent(btnUbahSup)
                    .addComponent(btnHapusSup))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(127, Short.MAX_VALUE))
        );

        mainPanel.add(supplierPanel, "card4");

        karyawanPanel.setBackground(new java.awt.Color(186, 186, 137));

        jLabel20.setFont(new java.awt.Font("Arial Narrow", 1, 24)); // NOI18N
        jLabel20.setText("Karyawan");

        jLabel21.setText("Id Karyawan");

        ed_IdKar.setText("jTextField3");

        jLabel22.setText("Nama Karyawan");

        ed_NamaKar.setText("jTextField3");

        jLabel23.setText("Alamat");

        ed_AlamatKar.setText("jTextField3");

        jLabel24.setText("No Hp");

        ed_NoHoKar.setText("jTextField3");

        tblKaryawan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblKaryawan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKaryawanMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblKaryawan);

        btnSimpanKaryawan.setText("Simpan");
        btnSimpanKaryawan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanKaryawanActionPerformed(evt);
            }
        });

        btnUbahKaryawan.setText("Ubah");
        btnUbahKaryawan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUbahKaryawanActionPerformed(evt);
            }
        });

        btnHapusKaryawan.setText("Hapus");
        btnHapusKaryawan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusKaryawanActionPerformed(evt);
            }
        });

        jLabel40.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(0, 0, 255));
        jLabel40.setText("Daftarkan Akun ADMIN");
        jLabel40.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel40MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout karyawanPanelLayout = new javax.swing.GroupLayout(karyawanPanel);
        karyawanPanel.setLayout(karyawanPanelLayout);
        karyawanPanelLayout.setHorizontalGroup(
            karyawanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(karyawanPanelLayout.createSequentialGroup()
                .addGroup(karyawanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(karyawanPanelLayout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(karyawanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(karyawanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jScrollPane4)
                                .addGroup(karyawanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(karyawanPanelLayout.createSequentialGroup()
                                        .addComponent(jLabel22)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(karyawanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(karyawanPanelLayout.createSequentialGroup()
                                                .addComponent(btnSimpanKaryawan, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(78, 78, 78)
                                                .addComponent(btnUbahKaryawan, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(74, 74, 74)
                                                .addComponent(btnHapusKaryawan, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(karyawanPanelLayout.createSequentialGroup()
                                                .addGap(25, 25, 25)
                                                .addGroup(karyawanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(ed_IdKar, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
                                                    .addComponent(ed_NamaKar))
                                                .addGap(18, 18, 18)
                                                .addGroup(karyawanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addGroup(karyawanPanelLayout.createSequentialGroup()
                                                        .addComponent(jLabel23)
                                                        .addGap(31, 31, 31)
                                                        .addComponent(ed_AlamatKar, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(karyawanPanelLayout.createSequentialGroup()
                                                        .addComponent(jLabel24)
                                                        .addGap(31, 31, 31)
                                                        .addComponent(ed_NoHoKar))))))
                                    .addComponent(jLabel21)))
                            .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(karyawanPanelLayout.createSequentialGroup()
                        .addGap(283, 283, 283)
                        .addComponent(jLabel20)))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        karyawanPanelLayout.setVerticalGroup(
            karyawanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(karyawanPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel20)
                .addGap(18, 18, 18)
                .addGroup(karyawanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(jLabel23)
                    .addComponent(ed_AlamatKar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ed_IdKar, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(karyawanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(jLabel24)
                    .addComponent(ed_NoHoKar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ed_NamaKar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(karyawanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSimpanKaryawan)
                    .addComponent(btnUbahKaryawan)
                    .addComponent(btnHapusKaryawan))
                .addGap(26, 26, 26)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 91, Short.MAX_VALUE)
                .addComponent(jLabel40)
                .addGap(34, 34, 34))
        );

        mainPanel.add(karyawanPanel, "card6");

        daftarPanel.setBackground(new java.awt.Color(184, 220, 184));

        jLabel41.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel41.setText("Daftar Akun Admin");

        jLabel42.setText("Username");

        jLabel43.setText("Password");

        jLabel44.setText("Verifikasi Password");

        jLabel45.setText("Hak Akses");

        tbl_DaftarAdmin.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane7.setViewportView(tbl_DaftarAdmin);

        ed_user.setText("jTextField1");

        ed_hak.setText("jTextField2");

        edf_pass.setText("jPasswordField1");
        edf_pass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                edf_passKeyReleased(evt);
            }
        });

        edf_verif.setText("jPasswordField2");
        edf_verif.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                edf_verifKeyReleased(evt);
            }
        });

        btn_simpanAdmin.setText("Simpan");
        btn_simpanAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpanAdminActionPerformed(evt);
            }
        });

        jLabel47.setForeground(new java.awt.Color(255, 0, 0));
        jLabel47.setText("jLabel47");

        jLabel48.setForeground(new java.awt.Color(255, 0, 0));
        jLabel48.setText("jLabel48");

        javax.swing.GroupLayout daftarPanelLayout = new javax.swing.GroupLayout(daftarPanel);
        daftarPanel.setLayout(daftarPanelLayout);
        daftarPanelLayout.setHorizontalGroup(
            daftarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(daftarPanelLayout.createSequentialGroup()
                .addGroup(daftarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(daftarPanelLayout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addGroup(daftarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel42)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 586, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(daftarPanelLayout.createSequentialGroup()
                                .addGroup(daftarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel44)
                                    .addComponent(jLabel45)
                                    .addComponent(jLabel43))
                                .addGap(92, 92, 92)
                                .addGroup(daftarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(ed_hak)
                                    .addComponent(jLabel47)
                                    .addComponent(jLabel48)
                                    .addComponent(ed_user)
                                    .addComponent(edf_verif, javax.swing.GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)
                                    .addComponent(edf_pass)))))
                    .addGroup(daftarPanelLayout.createSequentialGroup()
                        .addGap(237, 237, 237)
                        .addComponent(jLabel41))
                    .addGroup(daftarPanelLayout.createSequentialGroup()
                        .addGap(302, 302, 302)
                        .addComponent(btn_simpanAdmin)))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        daftarPanelLayout.setVerticalGroup(
            daftarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(daftarPanelLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel41)
                .addGap(26, 26, 26)
                .addGroup(daftarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel42)
                    .addComponent(ed_user, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(daftarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel43)
                    .addComponent(edf_pass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel48)
                .addGap(23, 23, 23)
                .addGroup(daftarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel44)
                    .addComponent(edf_verif, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel47)
                .addGap(26, 26, 26)
                .addGroup(daftarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel45)
                    .addComponent(ed_hak, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(btn_simpanAdmin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        mainPanel.add(daftarPanel, "card9");

        pelangganPanel.setBackground(new java.awt.Color(201, 226, 176));

        jLabel2.setFont(new java.awt.Font("Arial Narrow", 1, 24)); // NOI18N
        jLabel2.setText("Pelanggan");

        jLabel26.setText("Id Pelanggan");

        ed_IdPelanggan.setText("jTextField7");

        Jlabel.setText("Nama Pelanggan");

        ed_Nama_Pelanggan.setText("jTextField7");

        jLabel28.setText("Alamat");

        ed_AlamatPelanggan.setText("jTextField7");

        ed_NoHpPelanggan.setText("jTextField7");
        ed_NoHpPelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ed_NoHpPelangganActionPerformed(evt);
            }
        });

        jLabel29.setText("No Hp");

        tblPelanggan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblPelanggan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPelangganMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblPelanggan);

        btnSimpanPelanggan.setText("Simpan");
        btnSimpanPelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanPelangganActionPerformed(evt);
            }
        });

        btnUbahPelanggan.setText("Ubah");
        btnUbahPelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUbahPelangganActionPerformed(evt);
            }
        });

        btnHapusPelanggan.setText("Hapus");
        btnHapusPelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusPelangganActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pelangganPanelLayout = new javax.swing.GroupLayout(pelangganPanel);
        pelangganPanel.setLayout(pelangganPanelLayout);
        pelangganPanelLayout.setHorizontalGroup(
            pelangganPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pelangganPanelLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(pelangganPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane5)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pelangganPanelLayout.createSequentialGroup()
                        .addGroup(pelangganPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Jlabel)
                            .addComponent(jLabel26))
                        .addGap(27, 27, 27)
                        .addGroup(pelangganPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pelangganPanelLayout.createSequentialGroup()
                                .addComponent(btnSimpanPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(91, 91, 91)
                                .addComponent(btnUbahPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 111, Short.MAX_VALUE)
                                .addComponent(btnHapusPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(78, 78, 78))
                            .addGroup(pelangganPanelLayout.createSequentialGroup()
                                .addGroup(pelangganPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(ed_IdPelanggan, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                                    .addComponent(ed_Nama_Pelanggan))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(pelangganPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel29)
                                    .addComponent(jLabel28))
                                .addGap(47, 47, 47)
                                .addGroup(pelangganPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(ed_AlamatPelanggan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(ed_NoHpPelanggan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(23, 23, 23))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pelangganPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(276, 276, 276))
        );
        pelangganPanelLayout.setVerticalGroup(
            pelangganPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pelangganPanelLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel2)
                .addGap(21, 21, 21)
                .addGroup(pelangganPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pelangganPanelLayout.createSequentialGroup()
                        .addGroup(pelangganPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel26)
                            .addComponent(ed_IdPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pelangganPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Jlabel)
                            .addComponent(ed_Nama_Pelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pelangganPanelLayout.createSequentialGroup()
                        .addGroup(pelangganPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(ed_AlamatPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel28))
                        .addGap(18, 18, 18)
                        .addGroup(pelangganPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ed_NoHpPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel29))))
                .addGap(18, 18, 18)
                .addGroup(pelangganPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSimpanPelanggan)
                    .addComponent(btnUbahPelanggan)
                    .addComponent(btnHapusPelanggan))
                .addGap(70, 70, 70)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(117, Short.MAX_VALUE))
        );

        mainPanel.add(pelangganPanel, "card8");

        transaksiPanel.setBackground(new java.awt.Color(204, 204, 204));

        jLabel27.setFont(new java.awt.Font("Arial Narrow", 1, 24)); // NOI18N
        jLabel27.setText("Transaksi");

        jLabel30.setText("Kode Barang");

        jLabel31.setText("Tanggal Transaksi");

        ed_TglTrans.setText("jTextField7");

        jLabel32.setText("Id Karyawan");

        ed_IdKarTrans.setText("jTextField7");

        jLabel33.setText("Id Pelanggan");

        ed_IdPelTrans.setText("jTextField7");

        tblTransaksi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblTransaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTransaksiMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tblTransaksi);

        ed_KdBrgTrans.setText("jTextField7");
        ed_KdBrgTrans.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ed_KdBrgTransKeyReleased(evt);
            }
        });

        jLabel34.setText("Kode Transaksi");

        jLabel35.setText("Nama Barang");

        ed_NmBrgTrans.setText("jTextField7");

        jLabel37.setText("Harga Barang");

        ed_HargaBrg.setText("jTextField7");

        jLabel38.setText("Jumlah Barang");

        ed_JumlahBrgTrans.setText("jTextField7");
        ed_JumlahBrgTrans.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ed_JumlahBrgTransActionPerformed(evt);
            }
        });
        ed_JumlahBrgTrans.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ed_JumlahBrgTransKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ed_JumlahBrgTransKeyTyped(evt);
            }
        });

        lblTotal.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTotal.setText("Total");
        lblTotal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                lblTotalKeyTyped(evt);
            }
        });

        btnSimpanTrans.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnSimpanTrans.setText("Simpan");
        btnSimpanTrans.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanTransActionPerformed(evt);
            }
        });

        jButton1.setText("Pilih Barang");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnCetak.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnCetak.setText("Cetak");
        btnCetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCetakActionPerformed(evt);
            }
        });

        kd.setText("jTextField1");

        jButton3.setText("Lihat");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout transaksiPanelLayout = new javax.swing.GroupLayout(transaksiPanel);
        transaksiPanel.setLayout(transaksiPanelLayout);
        transaksiPanelLayout.setHorizontalGroup(
            transaksiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(transaksiPanelLayout.createSequentialGroup()
                .addGroup(transaksiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(transaksiPanelLayout.createSequentialGroup()
                        .addGap(286, 286, 286)
                        .addComponent(jLabel27))
                    .addGroup(transaksiPanelLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 634, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(23, Short.MAX_VALUE))
            .addGroup(transaksiPanelLayout.createSequentialGroup()
                .addGroup(transaksiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(transaksiPanelLayout.createSequentialGroup()
                        .addGroup(transaksiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(transaksiPanelLayout.createSequentialGroup()
                                .addGap(52, 52, 52)
                                .addGroup(transaksiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel34)
                                    .addComponent(jLabel30)
                                    .addGroup(transaksiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel32)
                                        .addComponent(jLabel33)))
                                .addGap(32, 32, 32))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, transaksiPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel31)
                                .addGap(18, 18, 18)))
                        .addGroup(transaksiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(ed_IdPelTrans, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ed_IdKarTrans, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ed_KdBrgTrans)
                            .addComponent(ed_TglTrans, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                            .addComponent(kd, javax.swing.GroupLayout.Alignment.LEADING)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, transaksiPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnSimpanTrans, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)))
                .addGroup(transaksiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, transaksiPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnCetak)
                        .addGap(112, 112, 112))
                    .addGroup(transaksiPanelLayout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(transaksiPanelLayout.createSequentialGroup()
                        .addGroup(transaksiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(transaksiPanelLayout.createSequentialGroup()
                                .addGap(108, 108, 108)
                                .addComponent(jLabel37)
                                .addGap(6, 6, 6))
                            .addGroup(transaksiPanelLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jButton3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(transaksiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel38)
                                    .addComponent(jLabel35))))
                        .addGap(18, 18, 18)
                        .addGroup(transaksiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ed_HargaBrg, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ed_JumlahBrgTrans, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ed_NmBrgTrans, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, transaksiPanelLayout.createSequentialGroup()
                        .addComponent(lblTotal)
                        .addGap(96, 96, 96))))
        );
        transaksiPanelLayout.setVerticalGroup(
            transaksiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(transaksiPanelLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel27)
                .addGap(32, 32, 32)
                .addGroup(transaksiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(transaksiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel34)
                        .addComponent(kd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton3))
                    .addGroup(transaksiPanelLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(transaksiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ed_TglTrans, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel31))
                        .addGap(18, 18, 18)
                        .addGroup(transaksiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ed_IdKarTrans, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel32))
                        .addGap(18, 18, 18)
                        .addGroup(transaksiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ed_IdPelTrans, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel33))
                        .addGap(18, 18, 18)
                        .addGroup(transaksiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ed_KdBrgTrans, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel30)
                            .addComponent(jButton1)))
                    .addGroup(transaksiPanelLayout.createSequentialGroup()
                        .addGroup(transaksiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ed_NmBrgTrans, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel35))
                        .addGap(18, 18, 18)
                        .addGroup(transaksiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ed_HargaBrg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel37))
                        .addGap(20, 20, 20)
                        .addGroup(transaksiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel38)
                            .addComponent(ed_JumlahBrgTrans, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(lblTotal)))
                .addGap(30, 30, 30)
                .addGroup(transaksiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSimpanTrans)
                    .addComponent(btnCetak))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        mainPanel.add(transaksiPanel, "card7");

        javax.swing.GroupLayout panelDasarLayout = new javax.swing.GroupLayout(panelDasar);
        panelDasar.setLayout(panelDasarLayout);
        panelDasarLayout.setHorizontalGroup(
            panelDasarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDasarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 678, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelDasarLayout.setVerticalGroup(
            panelDasarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDasarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelDasarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(panelButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelDasar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelDasar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSupplierActionPerformed
        // TODO add your handling code here:
        clearSupplier();
        tampilataSupplier();
        barangPanel.setVisible(false);
        homePanel.setVisible(false);
        jenisBarangPanel.setVisible(false);
        karyawanPanel.setVisible(false);
        supplierPanel.setVisible(true);
        pelangganPanel.setVisible(false);
        transaksiPanel.setVisible(false);
        daftarPanel.setVisible(false);
       
    }//GEN-LAST:event_btnSupplierActionPerformed

    private void btnBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBarangActionPerformed
        // TODO add your handling code here:
        clearBrg();
        tampilDataBrg();
        barangPanel.setVisible(true);
        homePanel.setVisible(false);
        jenisBarangPanel.setVisible(false);
        karyawanPanel.setVisible(false);
        supplierPanel.setVisible(false);
        pelangganPanel.setVisible(false);
        transaksiPanel.setVisible(false);
        daftarPanel.setVisible(false);
        
      
    }//GEN-LAST:event_btnBarangActionPerformed

    private void btnHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomeActionPerformed
        // TODO add your handling code here:
        barangPanel.setVisible(false);
        homePanel.setVisible(true);
        jenisBarangPanel.setVisible(false);
        daftarPanel.setVisible(false);
        karyawanPanel.setVisible(false);
        supplierPanel.setVisible(false);
        pelangganPanel.setVisible(false);
        transaksiPanel.setVisible(false);
        
    }//GEN-LAST:event_btnHomeActionPerformed

    private void ed_KdKatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ed_KdKatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ed_KdKatActionPerformed

    private void btnSimpanKatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanKatActionPerformed
        // TODO add your handling code here:
        try {
            String kd = ed_KdKat.getText();
            String kat = ed_Kat.getText();
            
            Connection objConnection = new Koneksi().getConnection();
            Statement s = objConnection.createStatement(); 
            String query = "INSERT INTO `kategori`(`kd_kategori`,`nama_kategori`)"
                    + " VALUES ('"+kd+"','"+kat+"')";
            
            s.executeUpdate(query);
            s.close();
            JOptionPane.showMessageDialog(null, "Data berhasil ditambah");
            tampilDataKategori();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data gagal ditambah");
        }
    }//GEN-LAST:event_btnSimpanKatActionPerformed

    private void btnSimpanKaryawanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanKaryawanActionPerformed
        // TODO add your handling code here:
        try {
            String id = ed_IdKar.getText();
            String nama = ed_NamaKar.getText();
            String alamat = ed_AlamatKar.getText();
            String nohp = ed_NoHoKar.getText();
            
            Connection objConnection = new Koneksi().getConnection();
            Statement s = objConnection.createStatement(); 
            String query = "INSERT INTO `karyawan`(`id_karyawan`, `nama_karyawan`, `alamat`, `no_hp`)"
                    + " VALUES ('"+id+"','"+nama+"','"+alamat+"','"+nohp+"')";
            
            s.executeUpdate(query);
            s.close();
            JOptionPane.showMessageDialog(null, "Data berhasil ditambah");
            tampilataKaryawan();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data gagal ditambah");
        }
    }//GEN-LAST:event_btnSimpanKaryawanActionPerformed

    private void btnPelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPelangganActionPerformed
      
        clearPelanggan();
        tampilataPelanggan();
        barangPanel.setVisible(false);
        homePanel.setVisible(false);
        jenisBarangPanel.setVisible(false);
        karyawanPanel.setVisible(false);
        supplierPanel.setVisible(false);
        pelangganPanel.setVisible(true);
        transaksiPanel.setVisible(false);
        daftarPanel.setVisible(false);
        
    }//GEN-LAST:event_btnPelangganActionPerformed

    private void ed_NoHpPelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ed_NoHpPelangganActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ed_NoHpPelangganActionPerformed

    private void btnUbahPelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUbahPelangganActionPerformed
        // TODO add your handling code here:
        String id = ed_IdPelanggan.getText();
            String nm = ed_Nama_Pelanggan.getText();
            String alamat = ed_AlamatPelanggan.getText();
            String nohp = ed_NoHpPelanggan.getText();
            
          
        try {
            Connection objcConnection = new Koneksi().getConnection();
            
            String queryUpdate= "update pelanggan set nama_pelanggan=?, alamat=?, no_hp=? where id_pelanggan=?";
            try (PreparedStatement ps = objcConnection.prepareStatement(queryUpdate)) {
                ps.setString(1, nm);
                ps.setString(2, alamat);
                ps.setString(3, nohp);
                ps.setString(4, id);
                
                ps.executeUpdate();
                tampilataPelanggan();
              JOptionPane.showMessageDialog(null, "Data Berhasil diubah"); 
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data gagal diubah");
        }
        
    }//GEN-LAST:event_btnUbahPelangganActionPerformed

    private void btnSimpanTransActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanTransActionPerformed
        // TODO add your handling code here:
       try {
         String idKar = ed_IdKarTrans.getText();
            String idPel = ed_IdPelTrans.getText();
            String kdBrg = ed_KdBrgTrans.getText();
            String nmBrg = ed_NmBrgTrans.getText();
            String harga = ed_HargaBrg.getText();
            String jmlh = ed_JumlahBrgTrans.getText();
            String total = lblTotal.getText();
            String tgl = ed_TglTrans.getText();
      
            Connection objConnection = new Koneksi().getConnection();
            try (Statement s = objConnection.createStatement()) {
                String query = "INSERT INTO `transaksi`(`id_karyawan`, `id_pelanggan`, `kd_barang`,`nama_barang`,`harga_barang`,`jumlah_barang`,`total_bayar`,`tgl_transaksi`)"
                        + " VALUES ('"+idKar+"','"+idPel+"','"+kdBrg+"','"+nmBrg+"','"+harga+"','"+jmlh+"','"+total+"','"+tgl+"')";
                
                s.executeUpdate(query);
                s.close();
            }
            JOptionPane.showMessageDialog(null, "Data berhasil ditambah");
            tampilataTransaksi();
            clearTransaksi();
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Data gagal ditambah");
        }
    }//GEN-LAST:event_btnSimpanTransActionPerformed

    private void ed_JumlahBrgTransActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ed_JumlahBrgTransActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ed_JumlahBrgTransActionPerformed

    private void btnJenisBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnJenisBarangActionPerformed
        // TODO add your handling code here:
        clearKategori();
        tampilDataKategori();
       
        barangPanel.setVisible(false);
        homePanel.setVisible(false);
        jenisBarangPanel.setVisible(true);
        karyawanPanel.setVisible(false);
        supplierPanel.setVisible(false);
        pelangganPanel.setVisible(false);
        transaksiPanel.setVisible(false);
        daftarPanel.setVisible(false);

    }//GEN-LAST:event_btnJenisBarangActionPerformed

    private void btnKaryawanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKaryawanActionPerformed
        // TODO add your handling code here:
         clearKaryawan();
         tampilataKaryawan();
        barangPanel.setVisible(false);
        homePanel.setVisible(false);
        jenisBarangPanel.setVisible(false);
        karyawanPanel.setVisible(true);
        supplierPanel.setVisible(false);
        pelangganPanel.setVisible(false);
        transaksiPanel.setVisible(false);
        daftarPanel.setVisible(false);
        
    }//GEN-LAST:event_btnKaryawanActionPerformed

    private void btnTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTransaksiActionPerformed
        // TODO add your handling code here:
        clearTransaksi();
        tampilataTransaksi();
        barangPanel.setVisible(false);
        homePanel.setVisible(false);
        jenisBarangPanel.setVisible(false);
        karyawanPanel.setVisible(false);
        supplierPanel.setVisible(false);
        pelangganPanel.setVisible(false);
        transaksiPanel.setVisible(true);
        daftarPanel.setVisible(false);
    }//GEN-LAST:event_btnTransaksiActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        Awal();
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        // TODO add your handling code here:
           try {
            String kd = ed_KdBrg.getText();
            String nmBrg = ed_NamaBrg.getText();
            String merk = ed_Merk.getText();
            String harga = ed_Harga.getText();
            String stok = ed_Stok.getText();
            String kdKat = ed_KdKategori.getText();
            String idSup = ed_IdSupplier.getText();
            
            
            Connection objConnection = new Koneksi().getConnection();
               try (Statement s = objConnection.createStatement()) {
                   String query = "INSERT INTO `barang`(`kd_barang`, `nama_barang`, `merk`, `harga_barang`,`stok`,`kd_kategori`,`id_supplier`)"
                           + " VALUES ('"+kd+"','"+nmBrg+"','"+merk+"','"+harga+"','"+stok+"','"+kdKat+"','"+idSup+"')";
                   
                   s.executeUpdate(query);
               }
            JOptionPane.showMessageDialog(null, "Data berhasil ditambah");
            tampilDataBrg();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data gagal ditambah");
        }

    }//GEN-LAST:event_btnSimpanActionPerformed

    private void tblBarangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBarangMouseClicked
        // TODO add your handling code here:
        int idxRow = tblBarang.getSelectedRow();
        String kd_brg = tblBarang.getValueAt(idxRow, 0).toString();
        String nm_brg = tblBarang.getValueAt(idxRow, 1).toString();
        String merk = tblBarang.getValueAt(idxRow, 2).toString();
        String harga = tblBarang.getValueAt(idxRow, 3).toString();
        String stok = tblBarang.getValueAt(idxRow, 4).toString();
        String kd_kat = tblBarang.getValueAt(idxRow, 5).toString();
        String id_sup = tblBarang.getValueAt(idxRow, 6).toString();
        
        ed_KdBrg.setText(kd_brg);
        ed_NamaBrg.setText(nm_brg);
        ed_Merk.setText(merk);
        ed_Harga.setText(harga);
        ed_Stok.setText(stok);
        ed_KdKategori.setText(kd_kat);
        ed_IdSupplier.setText(id_sup);
    }//GEN-LAST:event_tblBarangMouseClicked

    private void tabelKategoriMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelKategoriMouseClicked
        // TODO add your handling code here:
           int idxRow = tabelKategori.getSelectedRow();
        String kd_kategori = tabelKategori.getValueAt(idxRow, 0).toString();
        String kategori = tabelKategori.getValueAt(idxRow, 1).toString();
      
        ed_KdKat.setText(kd_kategori);
        ed_Kat.setText(kategori);
     
    }//GEN-LAST:event_tabelKategoriMouseClicked

    private void tblSupplierMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSupplierMouseClicked
        // TODO add your handling code here:
        int idxRow =tblSupplier.getSelectedRow();
        String id_sup = tblSupplier.getValueAt(idxRow, 0).toString();
        String nm_sup = tblSupplier.getValueAt(idxRow, 1).toString();
        String alamat = tblSupplier.getValueAt(idxRow, 2).toString();
        String nohp = tblSupplier.getValueAt(idxRow, 3).toString();
        
        ed_IdSup.setText(id_sup);
        ed_NamaSup.setText(nm_sup);
        ed_AlamatSup.setText(alamat);
        ed_NoHpSup.setText(nohp);
    }//GEN-LAST:event_tblSupplierMouseClicked

    private void tblKaryawanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKaryawanMouseClicked
        // TODO add your handling code here:
        int idxRow =tblKaryawan.getSelectedRow();
        String id_kar = tblKaryawan.getValueAt(idxRow, 0).toString();
        String nm_kar = tblKaryawan.getValueAt(idxRow, 1).toString();
        String alamat = tblKaryawan.getValueAt(idxRow, 2).toString();
        String nohp = tblKaryawan.getValueAt(idxRow, 3).toString();
        
        ed_IdKar.setText(id_kar);
        ed_NamaKar.setText(nm_kar);
        ed_AlamatKar.setText(alamat);
        ed_NoHoKar.setText(nohp);
    }//GEN-LAST:event_tblKaryawanMouseClicked

    private void tblPelangganMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPelangganMouseClicked
        // TODO add your handling code here:
        int idxRow =tblPelanggan.getSelectedRow();
        String id_pel = tblPelanggan.getValueAt(idxRow, 0).toString();
        String nm_pel = tblPelanggan.getValueAt(idxRow, 1).toString();
        String alamat = tblPelanggan.getValueAt(idxRow, 2).toString();
        String nohp = tblPelanggan.getValueAt(idxRow, 3).toString();
        
        ed_IdPelanggan.setText(id_pel);
        ed_Nama_Pelanggan.setText(nm_pel);
        ed_AlamatPelanggan.setText(alamat);
        ed_NoHpPelanggan.setText(nohp);
    }//GEN-LAST:event_tblPelangganMouseClicked
    
    private void tblTransaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTransaksiMouseClicked
        // TODO add your handling code here:
        
        int idxRow = tblTransaksi.getSelectedRow();
        kode = tblTransaksi.getValueAt(idxRow, 0).toString();
        tgl = tblTransaksi.getValueAt(idxRow, 1).toString();
        pegawai = tblTransaksi.getValueAt(idxRow, 2).toString();
        pelanggan = tblTransaksi.getValueAt(idxRow, 3).toString();
        brg = tblTransaksi.getValueAt(idxRow, 4).toString();        
        merk = tblTransaksi.getValueAt(idxRow, 5).toString();
        harga = tblTransaksi.getValueAt(idxRow, 6).toString();
        jml = tblTransaksi.getValueAt(idxRow, 7).toString();
        ttl = tblTransaksi.getValueAt(idxRow, 8).toString();
        
    }//GEN-LAST:event_tblTransaksiMouseClicked

    private void btnSimpanSupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanSupActionPerformed
        // TODO add your handling code here:
           try {
            String id = ed_IdSup.getText();
            String nama = ed_NamaSup.getText();
            String alamat = ed_AlamatSup.getText();
            String nohp = ed_NoHpSup.getText();
            
            Connection objConnection = new Koneksi().getConnection();
            Statement s = objConnection.createStatement(); 
            String query = "INSERT INTO `supplier`(`id_supplier`, `nama_supplier`, `alamat`, `no_hp`)"
                    + " VALUES ('"+id+"','"+nama+"','"+alamat+"','"+nohp+"')";
            
            s.executeUpdate(query);
            s.close();
            JOptionPane.showMessageDialog(null, "Data berhasil ditambah");
            tampilataSupplier();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data gagal ditambah");
        }
    }//GEN-LAST:event_btnSimpanSupActionPerformed

    private void btnSimpanPelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanPelangganActionPerformed
        // TODO add your handling code here:
           try {
            String id = ed_IdPelanggan.getText();
            String nama = ed_Nama_Pelanggan.getText();
            String alamat = ed_AlamatPelanggan.getText();
            String nohp = ed_NoHpPelanggan.getText();
            
            Connection objConnection = new Koneksi().getConnection();
            Statement s = objConnection.createStatement(); 
            String query = "INSERT INTO `pelanggan`(`id_pelanggan`, `nama_pelanggan`, `alamat`, `no_hp`)"
                    + " VALUES ('"+id+"','"+nama+"','"+alamat+"','"+nohp+"')";
            
            s.executeUpdate(query);
            s.close();
            JOptionPane.showMessageDialog(null, "Data berhasil ditambah");
            tampilataPelanggan();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data gagal ditambah");
        }
    }//GEN-LAST:event_btnSimpanPelangganActionPerformed

    private void btnHapusKatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusKatActionPerformed
        // TODO add your handling code here:
              try {
            String kd = ed_KdKat.getText();
            
            
            Connection objConnection = new Koneksi().getConnection();
            Statement s = objConnection.createStatement(); 
            String query = "DELETE FROM `kategori` WHERE `kd_kategori`='"+kd+"'";
            
            s.executeUpdate(query);
            s.close();
            JOptionPane.showMessageDialog(null, "Data berhasil dihapus");
            tampilDataKategori();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data gagal dihapus");
        }
    }//GEN-LAST:event_btnHapusKatActionPerformed

    private void btnHapusSupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusSupActionPerformed
        // TODO add your handling code here:
          try {
            String id = ed_IdSup.getText();
            
            
            Connection objConnection = new Koneksi().getConnection();
            Statement s = objConnection.createStatement(); 
            String query = "DELETE FROM `supplier` where `id_supplier`='"+id+"'";
            
            s.executeUpdate(query);
            s.close();
            JOptionPane.showMessageDialog(null, "Data berhasil dihapus");
            tampilataSupplier();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data gagal dihapus");
        }
    }//GEN-LAST:event_btnHapusSupActionPerformed

    private void btnUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUbahActionPerformed
        // TODO add your handling code here:
            String kd = ed_KdBrg.getText();
            String nmBrg = ed_NamaBrg.getText();
            String merk = ed_Merk.getText();
            String harga = ed_Harga.getText();
            String stok = ed_Stok.getText();
            String kdKat = ed_KdKategori.getText();
            String idSup = ed_IdSupplier.getText();
        try {
            Connection objcConnection = new Koneksi().getConnection();
            
            String queryUpdate= "update barang set nama_barang=?,merk=?,"
                    + "harga_barang=?, stok=?, kd_kategori=?, id_supplier=? where kd_barang=?";
            try (PreparedStatement ps = objcConnection.prepareStatement(queryUpdate)) {
                ps.setString(1, nmBrg);
                ps.setString(2, merk);
                 ps.setString(3, harga);
                ps.setString(4, stok);
                 ps.setString(5, kdKat);
                ps.setString(6, idSup); 
                ps.setString(7, kd);
                
                ps.executeUpdate();
                tampilDataBrg();
              JOptionPane.showMessageDialog(null, "Data Berhasil diubah"); 
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data gagal diubah");
        }
    }//GEN-LAST:event_btnUbahActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        // TODO add your handling code here:
        try {
            String kd = ed_KdBrg.getText();
            
            
            Connection objConnection = new Koneksi().getConnection();
            Statement s = objConnection.createStatement(); 
            String query = "DELETE FROM `barang` where `kd_barang`='"+kd+"'";
            
            s.executeUpdate(query);
            s.close();
            JOptionPane.showMessageDialog(null, "Data berhasil dihapus");
            tampilDataBrg();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data gagal dihapus");
        }
    }//GEN-LAST:event_btnHapusActionPerformed

    private void btnUbahKatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUbahKatActionPerformed
        // TODO add your handling code here:
            String kd = ed_KdKat.getText();
            String nmKat = ed_Kat.getText();
          
        try {
            Connection objcConnection = new Koneksi().getConnection();
            
            String queryUpdate= "update kategori set nama_kategori=? where kd_kategori=?";
            try (PreparedStatement ps = objcConnection.prepareStatement(queryUpdate)) {
                ps.setString(1, nmKat);
                ps.setString(2, kd);
               
                
                ps.executeUpdate();
                tampilDataKategori();
              JOptionPane.showMessageDialog(null, "Data Berhasil diubah"); 
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data gagal diubah");
        }
    }//GEN-LAST:event_btnUbahKatActionPerformed

    private void btnUbahSupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUbahSupActionPerformed
        // TODO add your handling code here:
            String id = ed_IdSup.getText();
            String nm = ed_NamaSup.getText();
            String alamat = ed_AlamatSup.getText();
            String nohp = ed_NoHpSup.getText();
            
          
        try {
            Connection objcConnection = new Koneksi().getConnection();
            
            String queryUpdate= "update supplier set nama_supplier=?, alamat=?, no_hp=? where id_supplier=?";
            try (PreparedStatement ps = objcConnection.prepareStatement(queryUpdate)) {
                ps.setString(1, nm);
                ps.setString(2, alamat);
                ps.setString(3, nohp);
                ps.setString(4, id);
               
                
                ps.executeUpdate();
                tampilataSupplier();
              JOptionPane.showMessageDialog(null, "Data Berhasil diubah"); 
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data gagal diubah");
        }
                                     
    }//GEN-LAST:event_btnUbahSupActionPerformed

    private void btnHapusKaryawanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusKaryawanActionPerformed
        // TODO add your handling code here:
         try {
            String id = ed_IdKar.getText();
            
            
            Connection objConnection = new Koneksi().getConnection();
            Statement s = objConnection.createStatement(); 
            String query = "DELETE FROM `karyawan` where `id_karyawan`='"+id+"'";
            
            s.executeUpdate(query);
            s.close();
            JOptionPane.showMessageDialog(null, "Data berhasil dihapus");
            tampilataKaryawan();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data gagal dihapus");
        }
    }//GEN-LAST:event_btnHapusKaryawanActionPerformed

    private void btnUbahKaryawanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUbahKaryawanActionPerformed
        // TODO add your handling code here:
            String id = ed_IdKar.getText();
            String nm = ed_NamaKar.getText();
            String alamat = ed_AlamatKar.getText();
            String nohp = ed_NoHoKar.getText();
            
          
        try {
            Connection objcConnection = new Koneksi().getConnection();
            
            String queryUpdate= "update karyawan set nama_karyawan=?, alamat=?, no_hp=? where id_karyawan=?";
            try (PreparedStatement ps = objcConnection.prepareStatement(queryUpdate)) {
                ps.setString(1, nm);
                ps.setString(2, alamat);
                ps.setString(3, nohp);
                ps.setString(4, id);
               
                
                ps.executeUpdate();
                tampilataKaryawan();
              JOptionPane.showMessageDialog(null, "Data Berhasil diubah"); 
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data gagal diubah");
        }
    }//GEN-LAST:event_btnUbahKaryawanActionPerformed

    private void btnHapusPelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusPelangganActionPerformed
        // TODO add your handling code here:
         try {
            String id = ed_IdPelanggan.getText();
            
            
            Connection objConnection = new Koneksi().getConnection();
            Statement s = objConnection.createStatement(); 
            String query = "DELETE FROM `pelanggan` where `id_pelanggan`='"+id+"'";
            
            s.executeUpdate(query);
            s.close();
            JOptionPane.showMessageDialog(null, "Data berhasil dihapus");
            tampilataPelanggan();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data gagal dihapus");
        }
    }//GEN-LAST:event_btnHapusPelangganActionPerformed

    private void jLabel40MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel40MouseClicked
        // TODO add your handling code here:
        clearDaftarAdmin();
        tampilDaftarAdmin();
        barangPanel.setVisible(false);
        homePanel.setVisible(false);
        jenisBarangPanel.setVisible(false);
        karyawanPanel.setVisible(false);
        supplierPanel.setVisible(false);
        pelangganPanel.setVisible(false);
        transaksiPanel.setVisible(false);
        daftarPanel.setVisible(true);
    }//GEN-LAST:event_jLabel40MouseClicked

    private void btn_simpanAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpanAdminActionPerformed
        String usern = ed_user.getText();
        String pass = new String (edf_pass.getPassword());
        String verif = new String (edf_verif.getPassword());
        String hakses = ed_hak.getText();
        
        String cekUser = null;
        try {
            Connection con = new Koneksi().getConnection();
            Statement s= con.createStatement();
            String sql = "Select * from admin where username='"+usern+"'";
            ResultSet rs= s.executeQuery(sql);
            
            while (rs.next()) {
            cekUser= rs.getString("username");}
            rs.close();
            s.close();
        } catch (SQLException e) {
            System.out.println("Terjadi Error!!");
        }
        if (!pass.equals(verif)) {
            String pesan = "Password yang anda ketikkan tidak cocok! Ulangi";
            jLabel47.setText(pesan);
            jLabel47.setVisible(true);
        }else if (new String (edf_pass.getPassword()).length()<6) {
            String pesan2 = "Password kurang dari 6 karakter! Ulangi";
            jLabel48.setText(pesan2);
            jLabel48.setVisible(true);
        }else{
            try {
                Connection con = new Koneksi().getConnection();
                
                String sql = "INSERT INTO admin (username, password, hak_akses)"
                        + "VALUES (?,md5(?),?)";
                PreparedStatement p = con.prepareStatement(sql);
                    p.setString(1, usern);
                    p.setString(2, pass);
                    p.setString(3, hakses);
                    p.executeUpdate();
                
                String pesan = "Selamat akun anda telah behasil didaftarkan!";
                JOptionPane.showMessageDialog(null, pesan,"Berhasil",
                        JOptionPane.INFORMATION_MESSAGE);
                clearDaftarAdmin();
                tampilDaftarAdmin();
            } catch (HeadlessException | SQLException e) {
            Logger.getLogger(FormPenjualan.class.getName()).log(Level.SEVERE, null, e);
            }}
    }//GEN-LAST:event_btn_simpanAdminActionPerformed

    private void edf_passKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edf_passKeyReleased
        if (new String(edf_pass.getPassword()).length()<6) {
            String pesan2 = "Password kurang dari 6 karakter! Ulangi";
            jLabel48.setText(pesan2);
            jLabel48.setVisible(true);}
        else {
            jLabel48.setVisible(false);}
    }//GEN-LAST:event_edf_passKeyReleased

    private void edf_verifKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edf_verifKeyReleased
        if (new String (edf_verif.getPassword()).length()<6) {
            String pesan2 = "Password yang anda ketikkan tidak cocok! Ulangi";
            jLabel47.setText(pesan2);
            jLabel47.setVisible(true);}
        else {
            jLabel47.setVisible(false);}
    }//GEN-LAST:event_edf_verifKeyReleased

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        // TODO add your handling code her
        Connection con = new Koneksi().getConnection();
        try {String username = ed_Username.getText();
            String pass = new String(ed_Password.getPassword());
            PreparedStatement ps = con.prepareStatement("Select * from admin "
                + "where username=? and password=md5(?)");
            ps.setString(1, username);
            ps.setString(2, pass);
            ResultSet r = ps.executeQuery();
            while (r.next()){
                String hakakses = r.getString("hak_akses");
                if (hakakses.equals("kasir")) {
                    JOptionPane.showMessageDialog(null, "Selamat Datang Kasir Toko");
                    hakKasir();
                    btnLoginPanel.setEnabled(false);
                }
                else if (hakakses.equals("pemilik")) {
                    JOptionPane.showMessageDialog(null, "Selamat Datang Pemilik Toko");
                    hakPemilik();
                    btnLoginPanel.setEnabled(false);
                }
                else {
                    JOptionPane.showMessageDialog(null, "Username Tidak Terdaftar");}}
        } catch (SQLException ex) {
            Logger.getLogger(FormPenjualan.class.getName()).log(Level.SEVERE, null, ex);}
    }//GEN-LAST:event_btnLoginActionPerformed

    private void ed_UsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ed_UsernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ed_UsernameActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
//        String kd = ed_KdBrgTrans.getText();
        String kd = ed_KdBrgTrans.getText();
        
        try{
            Connection objcConnection = new Koneksi().getConnection();
            Statement s = objcConnection.createStatement();
            
            String query = "SELECT * FROM pilih "
                    + "WHERE kd_barang='"+kd+"'";
            
            ResultSet res = s.executeQuery(query);
            if(res.next()){
                String kdB = res.getString("kd_barang");
                String namaB = res.getString("nama_barang");
                String harga= res.getString("Harga_barang");
                
                ed_KdBrgTrans.setText(kdB);
                ed_NmBrgTrans.setText(namaB);
                ed_HargaBrg.setText(harga);
                        

            }
            else{
                    JOptionPane.showMessageDialog(null, "Data Tidak Ditemukan!");
                    }
            s.close();
            
            } catch (SQLException ex) {
            Logger.getLogger(FormBarang.class.getName()).log(Level.SEVERE, null, ex);
          
        }
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void ed_KdBrgTransKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ed_KdBrgTransKeyReleased
        // TODO add your handling code here:
        
    }//GEN-LAST:event_ed_KdBrgTransKeyReleased

    private void ed_JumlahBrgTransKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ed_JumlahBrgTransKeyReleased
        // TODO add your handling code here:
//        Double hitung=0.0;
//        Double jumlah;
//        String jmlh = ed_JumlahBrgTrans.getText();
//        String harga = ed_HargaBrg.getText();
//        String total = ed_Total.getText();
//        
//        Double harga1 = Double.parseDouble(harga);
//        Double jmlh1 = Double.parseDouble(jmlh);
//        Double total1 = Double.parseDouble(String.valueOf(ed_Total.getText()));
//        jmlh1=0.0;
//        harga1=0.0;
//        
//        if(ed_JumlahBrgTrans.equals("")){
//        JOptionPane.showMessageDialog(null, "Masukkan Jumlah Brang","Peringatan",JOptionPane.INFORMATION_MESSAGE);
//        }else{
//           jumlah = jmlh1*harga1;
//           total = Double.toString(jumlah);
//        }
    }//GEN-LAST:event_ed_JumlahBrgTransKeyReleased

    private void ed_JumlahBrgTransKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ed_JumlahBrgTransKeyTyped
        // TODO add your handling code here:
        int a = Integer.parseInt(ed_JumlahBrgTrans.getText()+evt.getKeyChar());
        int b = Integer.parseInt(ed_HargaBrg.getText());
        
        lblTotal.setText(Integer.toString(a*b));
       
    }//GEN-LAST:event_ed_JumlahBrgTransKeyTyped

    private void lblTotalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lblTotalKeyTyped
        // TODO add your handling code here:

    }//GEN-LAST:event_lblTotalKeyTyped

    private void btnCetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCetakActionPerformed
        // TODO add your handling code here:
        CetakTiketPdf(kode,tgl,pegawai,pelanggan,brg,merk,harga,jml,ttl);
    }//GEN-LAST:event_btnCetakActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = new DefaultTableModel();
         model.addColumn("Kd Transaksi");
         model.addColumn("tgl transaksi");
         model.addColumn("karyawan");
         model.addColumn("pelanggan");
         model.addColumn("nama barang");
         model.addColumn("Merk");
         model.addColumn("harga");
         model.addColumn("jumlah");
         model.addColumn("total");
        
        int Kd_tran = Integer.parseInt(kd.getText());
        
        try {
            Connection con = new Koneksi().getConnection();
            Statement s = con.createStatement();
            
            String sql="SELECT kd_transaksi,tgl_transaksi,karyawan.nama_karyawan,pelanggan.nama_pelanggan,barang.nama_barang,merk,barang.harga_barang,jumlah_barang,total_bayar\n" +
                            "FROM transaksi INNER JOIN pelanggan ON pelanggan.id_pelanggan=transaksi.id_pelanggan\n" +
                            "INNER JOIN karyawan ON karyawan.id_karyawan=transaksi.id_karyawan\n" +
                            "INNER JOIN barang ON barang.kd_barang=transaksi.kd_barang\n" +
                            "WHERE kd_transaksi="+Kd_tran+"";
            
            ResultSet r = s.executeQuery(sql);
            
            while (r.next()){
                Object[] obj = new Object[9];
                obj[0]=r.getString("kd_transaksi");
                obj[1]=r.getString("tgl_transaksi");
                obj[2]=r.getString("karyawan.nama_karyawan");
                obj[3]=r.getString("pelanggan.nama_pelanggan");
                obj[4]=r.getString("barang.nama_barang");
                obj[5]=r.getString("barang.merk");
                obj[6]=r.getString("barang.harga_barang");
                obj[7]=r.getString("jumlah_barang");
                obj[8]=r.getString("total_bayar");
               
                
                model.addRow(obj);  
                
            }
            tblTransaksi.setModel(model);
            s.close();
                
            
        } catch (SQLException ex) {
            Logger.getLogger(FormPenjualan.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(FormPenjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormPenjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormPenjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormPenjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormPenjualan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Jlabel;
    private javax.swing.JPanel barangPanel;
    private javax.swing.JButton btnBarang;
    private javax.swing.JButton btnCetak;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnHapusKaryawan;
    private javax.swing.JButton btnHapusKat;
    private javax.swing.JButton btnHapusPelanggan;
    private javax.swing.JButton btnHapusSup;
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnJenisBarang;
    private javax.swing.JButton btnKaryawan;
    private javax.swing.JButton btnLaporan;
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btnLoginPanel;
    private javax.swing.JButton btnPelanggan;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnSimpanKaryawan;
    private javax.swing.JButton btnSimpanKat;
    private javax.swing.JButton btnSimpanPelanggan;
    private javax.swing.JButton btnSimpanSup;
    private javax.swing.JButton btnSimpanTrans;
    private javax.swing.JButton btnSupplier;
    private javax.swing.JButton btnTransaksi;
    private javax.swing.JButton btnUbah;
    private javax.swing.JButton btnUbahKaryawan;
    private javax.swing.JButton btnUbahKat;
    private javax.swing.JButton btnUbahPelanggan;
    private javax.swing.JButton btnUbahSup;
    private javax.swing.JButton btn_simpanAdmin;
    private javax.swing.JPanel daftarPanel;
    private javax.swing.JTextField ed_AlamatKar;
    private javax.swing.JTextField ed_AlamatPelanggan;
    private javax.swing.JTextField ed_AlamatSup;
    private javax.swing.JTextField ed_Harga;
    private javax.swing.JTextField ed_HargaBrg;
    private javax.swing.JTextField ed_IdKar;
    private javax.swing.JTextField ed_IdKarTrans;
    private javax.swing.JTextField ed_IdPelTrans;
    private javax.swing.JTextField ed_IdPelanggan;
    private javax.swing.JTextField ed_IdSup;
    private javax.swing.JTextField ed_IdSupplier;
    private javax.swing.JTextField ed_JumlahBrgTrans;
    private javax.swing.JTextField ed_Kat;
    private javax.swing.JTextField ed_KdBrg;
    private javax.swing.JTextField ed_KdBrgTrans;
    private javax.swing.JTextField ed_KdKat;
    private javax.swing.JTextField ed_KdKategori;
    private javax.swing.JTextField ed_Merk;
    private javax.swing.JTextField ed_NamaBrg;
    private javax.swing.JTextField ed_NamaKar;
    private javax.swing.JTextField ed_NamaSup;
    private javax.swing.JTextField ed_Nama_Pelanggan;
    private javax.swing.JTextField ed_NmBrgTrans;
    private javax.swing.JTextField ed_NoHoKar;
    private javax.swing.JTextField ed_NoHpPelanggan;
    private javax.swing.JTextField ed_NoHpSup;
    private javax.swing.JPasswordField ed_Password;
    private javax.swing.JTextField ed_Stok;
    private javax.swing.JTextField ed_TglTrans;
    private javax.swing.JTextField ed_Username;
    private javax.swing.JTextField ed_hak;
    private javax.swing.JTextField ed_user;
    private javax.swing.JPasswordField edf_pass;
    private javax.swing.JPasswordField edf_verif;
    private javax.swing.JPanel homePanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JPanel jenisBarangPanel;
    private javax.swing.JPanel karyawanPanel;
    private javax.swing.JTextField kd;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JPanel loginPanel;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JPanel panelButton;
    private javax.swing.JPanel panelDasar;
    private javax.swing.JPanel pelangganPanel;
    private javax.swing.JPanel supplierPanel;
    private javax.swing.JTable tabelKategori;
    private javax.swing.JTable tblBarang;
    private javax.swing.JTable tblKaryawan;
    private javax.swing.JTable tblPelanggan;
    private javax.swing.JTable tblSupplier;
    private javax.swing.JTable tblTransaksi;
    private javax.swing.JTable tbl_DaftarAdmin;
    private javax.swing.JPanel transaksiPanel;
    // End of variables declaration//GEN-END:variables

    private void CetakTiketPdf(String kd, String tgl, String pegawai,String harga, String pelanggan, String brg, String merk, String jml, String ttl) {
        PDPage tiketKA = new PDPage();
        PDFont jenisFont = PDType1Font.COURIER;
        int fontsixe = 12;
        
        try (PDDocument doc = new PDDocument()){
        doc.addPage(tiketKA);
        PDPageContentStream content = new PDPageContentStream(doc, tiketKA);
        
        content.beginText();
        content.setFont(jenisFont, fontsixe);
        
        content.setLeading(20f);
        content.newLineAtOffset(50, 750);
        
        content.showText("Nota Penjualan");
        content.newLine();
        content.newLine();
        content.showText("Kode Transaksi :"+kode);
        content.newLine();
        content.showText("Tanggal: "+tgl);
        content.newLine();
        content.showText("Pegawai: "+pegawai);
        content.newLine();
        content.showText("Pelanggan: "+harga);
        content.newLine();
        content.newLine();
        content.showText("Nama Barang: "+pelanggan);
        content.newLine();
        content.showText("Merk: "+brg);
        content.newLine();
        content.showText("Harga: "+merk);
        content.newLine();
        content.showText("Jumlah Barang: "+jml);
        content.newLine();
        content.showText("Total: "+ttl);
        content.newLine();
        
        content.endText();
        content.close();
        
        doc.save("E:/cetaknota.pdf");
        
        
        } catch (IOException e) {
            System.out.println("Gagal Membuat PDF");
        }
        try {
            File file = new File("E:/cetaknota.pdf");
            if (file.exists()){
                Desktop.getDesktop().open(file);
                
            }
            
            else{
                System.out.println("File Tidak Ditemukan");
            }
        } catch (IOException e) {
            System.out.println("Gagal Membuka File");
        }
        }
}
