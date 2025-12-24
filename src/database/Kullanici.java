package database;

public class Kullanici {
    private int id;
    private String kullaniciAdi;
    private String sifre;
    private String adSoyad;
    private String email;
    
    public Kullanici() {}
    
    public Kullanici(String kullaniciAdi, String sifre, String adSoyad, String email) {
        this.kullaniciAdi = kullaniciAdi;
        this.sifre = sifre;
        this.adSoyad = adSoyad;
        this.email = email;
    }
    
    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getKullaniciAdi() { return kullaniciAdi; }
    public void setKullaniciAdi(String kullaniciAdi) { this.kullaniciAdi = kullaniciAdi; }
    
    public String getSifre() { return sifre; }
    public void setSifre(String sifre) { this.sifre = sifre; }
    
    public String getAdSoyad() { return adSoyad; }
    public void setAdSoyad(String adSoyad) { this.adSoyad = adSoyad; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}