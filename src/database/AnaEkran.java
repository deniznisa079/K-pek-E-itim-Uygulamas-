package database;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.sql.*;
import database.Kullanici;
import database.DatabaseConnection;

public class AnaEkran extends JFrame {
    private Kullanici kullanici;
    private JTable tblKopekler, tblKomutlar, tblEgitim;
    private DefaultTableModel modelKopekler, modelKomutlar, modelEgitim;
    private JButton btnKopekEkle, btnKomutEkle, btnEgitimEkle, btnCikis;
    private JLabel lblHosgeldin;
    
    public AnaEkran(Kullanici kullanici) {
        this.kullanici = kullanici;
        initComponents();
        loadData();
    }
    
    private void initComponents() {
        setTitle("Köpek Komut Eğitim Sistemi - Ana Sayfa");
        setSize(1100, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Ana panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(236, 240, 241));
        
        // Üst panel (Hoşgeldin mesajı ve çıkış)
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(52, 152, 219));
        topPanel.setPreferredSize(new Dimension(0, 70));
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        
        lblHosgeldin = new JLabel("🐕 Hoş Geldiniz, " + kullanici.getAdSoyad() + "!");
        lblHosgeldin.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblHosgeldin.setForeground(Color.WHITE);
        topPanel.add(lblHosgeldin, BorderLayout.WEST);
        
        btnCikis = new JButton("Çıkış");
        btnCikis.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnCikis.setBackground(new Color(231, 76, 60));
        btnCikis.setForeground(Color.WHITE);
        btnCikis.setFocusPainted(false);
        btnCikis.setBorderPainted(false);
        btnCikis.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCikis.setPreferredSize(new Dimension(100, 40));
        btnCikis.addActionListener(e -> cikisYap());
        topPanel.add(btnCikis, BorderLayout.EAST);
        
        mainPanel.add(topPanel, BorderLayout.NORTH);
        
        // Merkez panel (Sekmeli yapı)
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Segoe UI", Font.BOLD, 14));
        
        // Köpeklerim sekmesi
        tabbedPane.addTab("🐕 Köpeklerim", createKopekPanel());
        
        // Komutlar sekmesi
        tabbedPane.addTab("📋 Komutlar", createKomutPanel());
        
        // Eğitim Kayıtları sekmesi
        tabbedPane.addTab("📊 Eğitim Kayıtları", createEgitimPanel());
        
        mainPanel.add(tabbedPane, BorderLayout.CENTER);
        
        add(mainPanel);
    }
    
    private JPanel createKopekPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(Color.WHITE);
        
        // Üst buton paneli
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btnPanel.setBackground(Color.WHITE);
        
        btnKopekEkle = new JButton("➕ Yeni Köpek Ekle");
        btnKopekEkle.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnKopekEkle.setBackground(new Color(46, 204, 113));
        btnKopekEkle.setForeground(Color.WHITE);
        btnKopekEkle.setFocusPainted(false);
        btnKopekEkle.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnKopekEkle.addActionListener(e -> kopekEkle());
        btnPanel.add(btnKopekEkle);
        
        panel.add(btnPanel, BorderLayout.NORTH);
        
        // Tablo
        String[] columns = {"ID", "Köpek Adı", "Tür", "Yaş", "Kayıt Tarihi"};
        modelKopekler = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tblKopekler = new JTable(modelKopekler);
        tblKopekler.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tblKopekler.setRowHeight(30);
        tblKopekler.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        tblKopekler.getTableHeader().setBackground(new Color(52, 152, 219));
        tblKopekler.getTableHeader().setForeground(Color.WHITE);
        
        JScrollPane scrollPane = new JScrollPane(tblKopekler);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel createKomutPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(Color.WHITE);
        
        // Tablo
        String[] columns = {"ID", "Komut Adı", "Açıklama", "Zorluk Seviyesi"};
        modelKomutlar = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tblKomutlar = new JTable(modelKomutlar);
        tblKomutlar.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tblKomutlar.setRowHeight(30);
        tblKomutlar.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        tblKomutlar.getTableHeader().setBackground(new Color(52, 152, 219));
        tblKomutlar.getTableHeader().setForeground(Color.WHITE);
        
        JScrollPane scrollPane = new JScrollPane(tblKomutlar);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel createEgitimPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(Color.WHITE);
        
        // Üst buton paneli
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btnPanel.setBackground(Color.WHITE);
        
        btnEgitimEkle = new JButton("➕ Eğitim Kaydı Ekle");
        btnEgitimEkle.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnEgitimEkle.setBackground(new Color(52, 152, 219));
        btnEgitimEkle.setForeground(Color.WHITE);
        btnEgitimEkle.setFocusPainted(false);
        btnEgitimEkle.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnEgitimEkle.addActionListener(e -> egitimEkle());
        btnPanel.add(btnEgitimEkle);
        
        panel.add(btnPanel, BorderLayout.NORTH);
        
        // Tablo
        String[] columns = {"ID", "Köpek Adı", "Komut", "Başarı Durumu", "Not", "Tarih"};
        modelEgitim = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tblEgitim = new JTable(modelEgitim);
        tblEgitim.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tblEgitim.setRowHeight(30);
        tblEgitim.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        tblEgitim.getTableHeader().setBackground(new Color(52, 152, 219));
        tblEgitim.getTableHeader().setForeground(Color.WHITE);
        
        JScrollPane scrollPane = new JScrollPane(tblEgitim);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    private void loadData() {
        loadKopekler();
        loadKomutlar();
        loadEgitimKayitlari();
    }
    
    private void loadKopekler() {
        modelKopekler.setRowCount(0);
        String sql = "SELECT * FROM kopekler WHERE kullanici_id = ? ORDER BY id DESC";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, kullanici.getId());
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                modelKopekler.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("kopek_adi"),
                    rs.getString("tur"),
                    rs.getInt("yas"),
                    rs.getString("kayit_tarihi")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void loadKomutlar() {
        modelKomutlar.setRowCount(0);
        String sql = "SELECT * FROM komutlar ORDER BY zorluk_seviyesi";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                modelKomutlar.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("komut_adi"),
                    rs.getString("aciklama"),
                    rs.getString("zorluk_seviyesi")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void loadEgitimKayitlari() {
        modelEgitim.setRowCount(0);
        // MSSQL'de 'not' rezerve bir kelime olduğu için e.[not] şeklinde kullanıldı.
        String sql = "SELECT e.id, k.kopek_adi, kom.komut_adi, e.basari_durumu, e.[not], e.tarih " +
                     "FROM egitim_kayitlari e " +
                     "JOIN kopekler k ON e.kopek_id = k.id " +
                     "JOIN komutlar kom ON e.komut_id = kom.id " +
                     "WHERE k.kullanici_id = ? ORDER BY e.tarih DESC";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, kullanici.getId());
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                modelEgitim.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("kopek_adi"),
                    rs.getString("komut_adi"),
                    rs.getString("basari_durumu"),
                    rs.getString("not"),
                    rs.getString("tarih")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void kopekEkle() {
        JTextField txtAd = new JTextField();
        JTextField txtTur = new JTextField();
        JSpinner spnYas = new JSpinner(new SpinnerNumberModel(1, 0, 30, 1));
        
        Object[] message = {
            "Köpek Adı:", txtAd,
            "Tür:", txtTur,
            "Yaş:", spnYas
        };
        
        int option = JOptionPane.showConfirmDialog(this, message, "Yeni Köpek Ekle", 
            JOptionPane.OK_CANCEL_OPTION);
        
        if (option == JOptionPane.OK_OPTION) {
            String ad = txtAd.getText().trim();
            String tur = txtTur.getText().trim();
            int yas = (Integer) spnYas.getValue();
            
            if (ad.isEmpty() || tur.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Lütfen tüm alanları doldurun!");
                return;
            }
            
            String sql = "INSERT INTO kopekler (kullanici_id, kopek_adi, tur, yas) VALUES (?, ?, ?, ?)";
            
            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                
                pstmt.setInt(1, kullanici.getId());
                pstmt.setString(2, ad);
                pstmt.setString(3, tur);
                pstmt.setInt(4, yas);
                
                pstmt.executeUpdate();
                JOptionPane.showMessageDialog(this, "Köpek başarıyla eklendi!");
                loadKopekler();
                
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Hata: " + e.getMessage());
            }
        }
    }
    
    private void egitimEkle() {
        // Köpek seçimi
        String sql = "SELECT id, kopek_adi FROM kopekler WHERE kullanici_id = ?";
        DefaultComboBoxModel<String> kopekModel = new DefaultComboBoxModel<>();
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, kullanici.getId());
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                kopekModel.addElement(rs.getInt("id") + " - " + rs.getString("kopek_adi"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        if (kopekModel.getSize() == 0) {
            JOptionPane.showMessageDialog(this, "Önce bir köpek eklemelisiniz!");
            return;
        }
        
        // Komut seçimi
        sql = "SELECT id, komut_adi FROM komutlar";
        DefaultComboBoxModel<String> komutModel = new DefaultComboBoxModel<>();
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                komutModel.addElement(rs.getInt("id") + " - " + rs.getString("komut_adi"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        JComboBox<String> cmbKopek = new JComboBox<>(kopekModel);
        JComboBox<String> cmbKomut = new JComboBox<>(komutModel);
        JComboBox<String> cmbDurum = new JComboBox<>(new String[]{"Öğreniyor", "Kısmen Öğrendi", "Tam Öğrendi"});
        JTextArea txtNot = new JTextArea(3, 20);
        
        Object[] message = {
            "Köpek:", cmbKopek,
            "Komut:", cmbKomut,
            "Başarı Durumu:", cmbDurum,
            "Not:", new JScrollPane(txtNot)
        };
        
        int option = JOptionPane.showConfirmDialog(this, message, "Eğitim Kaydı Ekle", 
            JOptionPane.OK_CANCEL_OPTION);
        
        if (option == JOptionPane.OK_OPTION) {
            String kopekStr = (String) cmbKopek.getSelectedItem();
            String komutStr = (String) cmbKomut.getSelectedItem();
            int kopekId = Integer.parseInt(kopekStr.split(" - ")[0]);
            int komutId = Integer.parseInt(komutStr.split(" - ")[0]);
            String durum = (String) cmbDurum.getSelectedItem();
            String not = txtNot.getText().trim();
            
            // MSSQL'de 'not' rezerve bir kelime olduğu için [not] şeklinde kullanıldı.
            sql = "INSERT INTO egitim_kayitlari (kopek_id, komut_id, basari_durumu, [not]) VALUES (?, ?, ?, ?)";
            
            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                
                pstmt.setInt(1, kopekId);
                pstmt.setInt(2, komutId);
                pstmt.setString(3, durum);
                pstmt.setString(4, not);
                
                pstmt.executeUpdate();
                JOptionPane.showMessageDialog(this, "Eğitim kaydı başarıyla eklendi!");
                loadEgitimKayitlari();
                
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Hata: " + e.getMessage());
            }
        }
    }
    
    private void cikisYap() {
        int option = JOptionPane.showConfirmDialog(this, 
            "Çıkmak istediğinize emin misiniz?", 
            "Çıkış", 
            JOptionPane.YES_NO_OPTION);
        
        if (option == JOptionPane.YES_OPTION) {
            new GirisEkrani().setVisible(true);
            dispose();
        }
    }
}