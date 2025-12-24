-- Önce veritabanýný oluþtur
CREATE DATABASE kopek_komut_db;
GO

USE kopek_komut_db;
GO

-- 1. Kullanýcýlar Tablosu
CREATE TABLE kullanicilar (
    id INT PRIMARY KEY IDENTITY(1,1),
    kullanici_adi NVARCHAR(50) NOT NULL UNIQUE,
    sifre NVARCHAR(50) NOT NULL,
    ad_soyad NVARCHAR(100),
    email NVARCHAR(100)
);

-- 2. Köpekler Tablosu
CREATE TABLE kopekler (
    id INT PRIMARY KEY IDENTITY(1,1),
    kullanici_id INT FOREIGN KEY REFERENCES kullanicilar(id),
    kopek_adi NVARCHAR(50),
    tur NVARCHAR(50),
    yas INT,
    kayit_tarihi DATETIME DEFAULT GETDATE()
);

-- 3. Komutlar Tablosu
CREATE TABLE komutlar (
    id INT PRIMARY KEY IDENTITY(1,1),
    komut_adi NVARCHAR(50),
    aciklama NVARCHAR(MAX),
    zorluk_seviyesi NVARCHAR(20)
);

-- 4. Eðitim Kayýtlarý Tablosu
-- 'not' kelimesi MSSQL'de özel olduðu için [] içine alýnmalýdýr.
CREATE TABLE egitim_kayitlari (
    id INT PRIMARY KEY IDENTITY(1,1),
    kopek_id INT FOREIGN KEY REFERENCES kopekler(id),
    komut_id INT FOREIGN KEY REFERENCES komutlar(id),
    basari_durumu NVARCHAR(50),
    [not] NVARCHAR(MAX), 
    tarih DATETIME DEFAULT GETDATE()
);

-- Örnek komutlarý ekleyelim (Tablolar boþ kalmasýn)
INSERT INTO komutlar (komut_adi, aciklama, zorluk_seviyesi) VALUES 
('Otur', 'Köpeðin arka üzerine oturmasýný saðlar', 'Kolay'),
('Gel', 'Çaðrýldýðýnda yanýnýza gelmesini saðlar', 'Kolay'),
('Pati Ver', 'Sað veya sol patisini uzatmasýný saðlar', 'Orta');