package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    // CMD'den bulduğun IPv4 adresini buraya tırnak içine yaz
    // Örnek: "192.168.1.45"
    private static final String SERVER_IP = "192.168.1.116"; 
    private static final String DATABASE_NAME = "kopek_komut_db";
    
    // MSSQL Bağlantı Dizini
    private static final String URL = "jdbc:sqlserver://" + SERVER_IP + ":1433;"
            + "databaseName=" + DATABASE_NAME + ";"
            + "encrypt=true;"
            + "trustServerCertificate=true;";
    
    private static final String USER = "sa"; 
    private static final String PASSWORD = "sifre"; // Kendi MSSQL şifren
    
    private static Connection connection = null;
    
    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Bağlantı Başarılı! Hedef IP: " + SERVER_IP);
            }
        } catch (Exception e) {
            System.err.println("Bağlantı Hatası! IP veya Port yanlış olabilir.");
            e.printStackTrace();
        }
        return connection;
    }
}