package database;

import java.sql.*;
import database.Kullanici;

public class KullaniciDAO {
    
    // Kullanıcı kaydı
    public boolean kullaniciKaydet(Kullanici kullanici) {
        String sql = "INSERT INTO kullanicilar (kullanici_adi, sifre, ad_soyad, email) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, kullanici.getKullaniciAdi());
            pstmt.setString(2, kullanici.getSifre());
            pstmt.setString(3, kullanici.getAdSoyad());
            pstmt.setString(4, kullanici.getEmail());
            
            int result = pstmt.executeUpdate();
            return result > 0;
            
        } catch (SQLException e) {
            System.err.println("Kayıt hatası: " + e.getMessage());
            return false;
        }
    }
    
    // Kullanıcı girişi
    public Kullanici girisYap(String kullaniciAdi, String sifre) {
        String sql = "SELECT * FROM kullanicilar WHERE kullanici_adi = ? AND sifre = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, kullaniciAdi);
            pstmt.setString(2, sifre);
            
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                Kullanici kullanici = new Kullanici();
                kullanici.setId(rs.getInt("id"));
                kullanici.setKullaniciAdi(rs.getString("kullanici_adi"));
                kullanici.setSifre(rs.getString("sifre"));
                kullanici.setAdSoyad(rs.getString("ad_soyad"));
                kullanici.setEmail(rs.getString("email"));
                return kullanici;
            }
            
        } catch (SQLException e) {
            System.err.println("Giriş hatası: " + e.getMessage());
        }
        
        return null;
    }
    
    // Kullanıcı adı kontrolü
    public boolean kullaniciAdiVarMi(String kullaniciAdi) {
        String sql = "SELECT COUNT(*) FROM kullanicilar WHERE kullanici_adi = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, kullaniciAdi);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
            
        } catch (SQLException e) {
            System.err.println("Kontrol hatası: " + e.getMessage());
        }
        
        return false;
    }
}