package dal.admins;

import db.Database;
import java.sql.*;

public class AdminDAO {
    public AdminDAO() {
        createTable();
    }

    private void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS admins (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "username TEXT NOT NULL," +
                "password TEXT NOT NULL)";
        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean checkIfAdminExists(String username, String password) {
        String sql = "SELECT * FROM admins WHERE username = ? AND password = ?";
        try (
                Connection conn = Database.getConnection();
                PreparedStatement ptstmt = conn.prepareStatement(sql)
        ) {
            ptstmt.setString(1, username);
            ptstmt.setString(2, password);

            try (ResultSet rs = ptstmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public void newUser(String username, String password)
    {
        String sql = "INSERT INTO admins (username, password) VALUES (?, ?)";


        try (
                Connection conn = Database.getConnection();
                PreparedStatement ptstmt = conn.prepareStatement(sql)
            ) 
        {
            ptstmt.setString(1, username);
            ptstmt.setString(2, password);
            ptstmt.executeUpdate();

             
            
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }

    public boolean checkUsername(String username)
    {
        String sql = "SELECT * FROM admins WHERE username = ?";
        try (
                Connection conn = Database.getConnection();
                PreparedStatement ptstmt = conn.prepareStatement(sql)
            ) 
        {
            ptstmt.setString(1, username);

            try (ResultSet rs = ptstmt.executeQuery()) 
             {
                return rs.next();
            }
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        return false;
    }

    public void forgorPass(String username, String newPassword)
    {
        String sql = "UPDATE admins SET password = ? WHERE username = ?";
        try (
                Connection conn = Database.getConnection();
                PreparedStatement ptstmt = conn.prepareStatement(sql)
            ) 
        {
            ptstmt.setString(1, username);
            ptstmt.setString(2, newPassword);
            ptstmt.executeUpdate();
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }
}
