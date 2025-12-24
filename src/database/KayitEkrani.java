package database;

import javax.swing.*;
import java.awt.*;
import database.KullaniciDAO;
import database.Kullanici;

public class KayitEkrani extends JFrame {
    private JTextField txtKullaniciAdi, txtAdSoyad, txtEmail;
    private JPasswordField txtSifre, txtSifreTekrar;
    private JButton btnKayitOl, btnGeriDon;
    private KullaniciDAO kullaniciDAO;
    
    public KayitEkrani() {
        kullaniciDAO = new KullaniciDAO();
        initComponents();
    }
    
    private void initComponents() {
        setTitle("Köpek Komut Eğitim Sistemi - Kayıt");
        setSize(450, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(new Color(240, 248, 255));
        
        // Başlık
        JLabel lblBaslik = new JLabel("Yeni Hesap Oluştur", SwingConstants.CENTER);
        lblBaslik.setBounds(50, 30, 350, 40);
        lblBaslik.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblBaslik.setForeground(new Color(41, 128, 185));
        mainPanel.add(lblBaslik);
        
        // Ad Soyad
        JLabel lblAdSoyad = new JLabel("Ad Soyad:");
        lblAdSoyad.setBounds(50, 100, 120, 25);
        lblAdSoyad.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        mainPanel.add(lblAdSoyad);
        
        txtAdSoyad = new JTextField();
        txtAdSoyad.setBounds(50, 130, 350, 40);
        txtAdSoyad.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtAdSoyad.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(189, 195, 199), 2),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        mainPanel.add(txtAdSoyad);
        
        // Kullanıcı Adı
        JLabel lblKullaniciAdi = new JLabel("Kullanıcı Adı:");
        lblKullaniciAdi.setBounds(50, 185, 120, 25);
        lblKullaniciAdi.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        mainPanel.add(lblKullaniciAdi);
        
        txtKullaniciAdi = new JTextField();
        txtKullaniciAdi.setBounds(50, 215, 350, 40);
        txtKullaniciAdi.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtKullaniciAdi.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(189, 195, 199), 2),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        mainPanel.add(txtKullaniciAdi);
        
        // Email
        JLabel lblEmail = new JLabel("E-posta:");
        lblEmail.setBounds(50, 270, 120, 25);
        lblEmail.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        mainPanel.add(lblEmail);
        
        txtEmail = new JTextField();
        txtEmail.setBounds(50, 300, 350, 40);
        txtEmail.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtEmail.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(189, 195, 199), 2),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        mainPanel.add(txtEmail);
        
        // Şifre
        JLabel lblSifre = new JLabel("Şifre:");
        lblSifre.setBounds(50, 355, 120, 25);
        lblSifre.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        mainPanel.add(lblSifre);
        
        txtSifre = new JPasswordField();
        txtSifre.setBounds(50, 385, 350, 40);
        txtSifre.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtSifre.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(189, 195, 199), 2),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        mainPanel.add(txtSifre);
        
        // Şifre Tekrar
        JLabel lblSifreTekrar = new JLabel("Şifre Tekrar:");
        lblSifreTekrar.setBounds(50, 440, 120, 25);
        lblSifreTekrar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        mainPanel.add(lblSifreTekrar);
        
        txtSifreTekrar = new JPasswordField();
        txtSifreTekrar.setBounds(50, 470, 350, 40);
        txtSifreTekrar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtSifreTekrar.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(189, 195, 199), 2),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        mainPanel.add(txtSifreTekrar);
        
        // Kayıt Ol butonu
        btnKayitOl = new JButton("Kayıt Ol");
        btnKayitOl.setBounds(50, 530, 170, 45);
        btnKayitOl.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnKayitOl.setBackground(new Color(46, 204, 113));
        btnKayitOl.setForeground(Color.WHITE);
        btnKayitOl.setFocusPainted(false);
        btnKayitOl.setBorderPainted(false);
        btnKayitOl.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnKayitOl.addActionListener(e -> kayitOl());
        mainPanel.add(btnKayitOl);
        
        // Geri Dön butonu
        btnGeriDon = new JButton("Geri Dön");
        btnGeriDon.setBounds(230, 530, 170, 45);
        btnGeriDon.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnGeriDon.setBackground(new Color(149, 165, 166));
        btnGeriDon.setForeground(Color.WHITE);
        btnGeriDon.setFocusPainted(false);
        btnGeriDon.setBorderPainted(false);
        btnGeriDon.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnGeriDon.addActionListener(e -> geriDon());
        mainPanel.add(btnGeriDon);
        
        add(mainPanel);
    }
    
    private void kayitOl() {
        String adSoyad = txtAdSoyad.getText().trim();
        String kullaniciAdi = txtKullaniciAdi.getText().trim();
        String email = txtEmail.getText().trim();
        String sifre = new String(txtSifre.getPassword());
        String sifreTekrar = new String(txtSifreTekrar.getPassword());
        
        // Validasyon
        if (adSoyad.isEmpty() || kullaniciAdi.isEmpty() || email.isEmpty() || 
            sifre.isEmpty() || sifreTekrar.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Lütfen tüm alanları doldurun!", 
                "Uyarı", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if (!sifre.equals(sifreTekrar)) {
            JOptionPane.showMessageDialog(this, 
                "Şifreler eşleşmiyor!", 
                "Hata", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (sifre.length() < 4) {
            JOptionPane.showMessageDialog(this, 
                "Şifre en az 4 karakter olmalıdır!", 
                "Uyarı", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if (kullaniciDAO.kullaniciAdiVarMi(kullaniciAdi)) {
            JOptionPane.showMessageDialog(this, 
                "Bu kullanıcı adı zaten kullanılıyor!", 
                "Hata", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Kayıt işlemi
        Kullanici yeniKullanici = new Kullanici(kullaniciAdi, sifre, adSoyad, email);
        
        if (kullaniciDAO.kullaniciKaydet(yeniKullanici)) {
            JOptionPane.showMessageDialog(this, 
                "Kayıt başarılı! Şimdi giriş yapabilirsiniz.", 
                "Başarılı", 
                JOptionPane.INFORMATION_MESSAGE);
            geriDon();
        } else {
            JOptionPane.showMessageDialog(this, 
                "Kayıt sırasında bir hata oluştu!", 
                "Hata", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void geriDon() {
        new GirisEkrani().setVisible(true);
        dispose();
    }
}