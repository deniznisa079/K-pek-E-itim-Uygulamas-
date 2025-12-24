package database;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import database.KullaniciDAO;
import database.Kullanici;

public class GirisEkrani extends JFrame {
    private JTextField txtKullaniciAdi;
    private JPasswordField txtSifre;
    private JButton btnGiris, btnKayitOl;
    private KullaniciDAO kullaniciDAO;
    
    public GirisEkrani() {
        kullaniciDAO = new KullaniciDAO();
        initComponents();
    }
    
    private void initComponents() {
        setTitle("Köpek Komut Eğitim Sistemi - Giriş");
        setSize(450, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        
        // Ana panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(new Color(240, 248, 255));
        
        // Logo/Başlık
        JLabel lblBaslik = new JLabel("🐕 Köpek Komut Eğitimi", SwingConstants.CENTER);
        lblBaslik.setBounds(50, 30, 350, 40);
        lblBaslik.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblBaslik.setForeground(new Color(41, 128, 185));
        mainPanel.add(lblBaslik);
        
        JLabel lblAltBaslik = new JLabel("Sevimli dostlarınızı eğitin!", SwingConstants.CENTER);
        lblAltBaslik.setBounds(50, 75, 350, 25);
        lblAltBaslik.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblAltBaslik.setForeground(new Color(127, 140, 141));
        mainPanel.add(lblAltBaslik);
        
        // Kullanıcı adı
        JLabel lblKullaniciAdi = new JLabel("Kullanıcı Adı:");
        lblKullaniciAdi.setBounds(50, 140, 120, 25);
        lblKullaniciAdi.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        mainPanel.add(lblKullaniciAdi);
        
        txtKullaniciAdi = new JTextField();
        txtKullaniciAdi.setBounds(50, 170, 350, 40);
        txtKullaniciAdi.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtKullaniciAdi.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(189, 195, 199), 2),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        mainPanel.add(txtKullaniciAdi);
        
        // Şifre
        JLabel lblSifre = new JLabel("Şifre:");
        lblSifre.setBounds(50, 230, 120, 25);
        lblSifre.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        mainPanel.add(lblSifre);
        
        txtSifre = new JPasswordField();
        txtSifre.setBounds(50, 260, 350, 40);
        txtSifre.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtSifre.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(189, 195, 199), 2),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        mainPanel.add(txtSifre);
        
        // Giriş butonu
        btnGiris = new JButton("Giriş Yap");
        btnGiris.setBounds(50, 330, 350, 45);
        btnGiris.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnGiris.setBackground(new Color(46, 204, 113));
        btnGiris.setForeground(Color.WHITE);
        btnGiris.setFocusPainted(false);
        btnGiris.setBorderPainted(false);
        btnGiris.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnGiris.addActionListener(e -> girisYap());
        mainPanel.add(btnGiris);
        
        // Kayıt ol butonu
        btnKayitOl = new JButton("Kayıt Ol");
        btnKayitOl.setBounds(50, 390, 350, 45);
        btnKayitOl.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnKayitOl.setBackground(new Color(52, 152, 219));
        btnKayitOl.setForeground(Color.WHITE);
        btnKayitOl.setFocusPainted(false);
        btnKayitOl.setBorderPainted(false);
        btnKayitOl.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnKayitOl.addActionListener(e -> kayitEkraniAc());
        mainPanel.add(btnKayitOl);
        
        // Enter tuşu ile giriş
        txtSifre.addActionListener(e -> girisYap());
        
        add(mainPanel);
    }
    
    private void girisYap() {
        String kullaniciAdi = txtKullaniciAdi.getText().trim();
        String sifre = new String(txtSifre.getPassword());
        
        if (kullaniciAdi.isEmpty() || sifre.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Lütfen tüm alanları doldurun!", 
                "Uyarı", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        Kullanici kullanici = kullaniciDAO.girisYap(kullaniciAdi, sifre);
        
        if (kullanici != null) {
            JOptionPane.showMessageDialog(this, 
                "Hoş geldiniz, " + kullanici.getAdSoyad() + "!", 
                "Başarılı", 
                JOptionPane.INFORMATION_MESSAGE);
            
            // Ana ekranı aç
            new AnaEkran(kullanici).setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, 
                "Kullanıcı adı veya şifre hatalı!", 
                "Hata", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void kayitEkraniAc() {
        new KayitEkrani().setVisible(true);
        dispose();
    }
    
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        SwingUtilities.invokeLater(() -> {
            new GirisEkrani().setVisible(true);
        });
    }
}