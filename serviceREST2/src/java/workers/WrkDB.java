/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package workers;

import beans.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author FranzenL
 */
public class WrkDB {
    
    private String port;
    private String dbName;
    private Connection dbConnection;
    private String URL;
    
    public WrkDB() {
        this.dbConnection = null;
        this.port = "3309";
        this.dbName = "dbuser";
        this.URL = "jdbc:mysql://localhost:3309/dbuser?serverTimezone=Europe/Zurich";
        dbConnect();
    }

    //Faire la connexion DB
    public boolean dbConnect() {
        boolean ok = false;
        try {
            if (dbConnection == null) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                dbConnection = DriverManager.getConnection(URL, "root", "");
                ok = true;
            }
        } catch (SQLException b) {
            Logger.getLogger(WrkDB.class.getName()).log(Level.SEVERE, null, b);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(WrkDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return ok;
    }

    //Efface connexion DB
    public boolean dbDisconnect() {
        boolean ok = false;
        // On vérifie si une connexion est toujours présente (donc pas nulle)
        if (dbConnection != null) {
            try {
                // On essaie de fermer la connexion, puis "vide" la variable.
                dbConnection.close();
                dbConnection = null;
                ok = true;
            } catch (SQLException ex) {
                Logger.getLogger(WrkDB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ok;
    }

    //test if db is connected
    public boolean isConnected() {
        boolean result = false;
        try {
            if (!dbConnection.isClosed()) {
                result = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(WrkDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

//Get users
    public ArrayList<String> dbGetUsers() {
        ArrayList<String> lstUsers = null;
        boolean result = isConnected();
        if (result) {
            System.out.println("connection ok");
            
            lstUsers = new ArrayList<>();
            try ( PreparedStatement ps = dbConnection.prepareStatement("SELECT username FROM t_user");  ResultSet rs = ps.executeQuery();) {
                
                while (rs.next()) {
                    String user = rs.getString("username");
                    lstUsers.add(user);
                }
                rs.close();
                result = true;
                System.out.println("OK");
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        return lstUsers;
    }

    //Modify User
    public void dbModifyUser(int pk_user, String username, String password) throws DBException {
        String prep = "update t_user set username=?, password=? where PK_User=?";
        try ( PreparedStatement ps = dbConnection.prepareStatement(prep)) {
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setInt(3, pk_user);
            
            int nb = ps.executeUpdate();
            if (nb != 1) {
                throw new DBException("Erreur de mise à jour !!!");
            }
        } catch (SQLException ex) {
            throw new DBException(ex.getMessage());
        }
    }

    //Delete User
    public void dbDeleteUser(int pk) throws DBException {
        String prep = "delete from t_user where PK_User=?";
        try ( PreparedStatement ps = dbConnection.prepareStatement(prep)) {
            ps.setInt(1, pk);
            int nb = ps.executeUpdate();
            if (nb != 1) {
                throw new DBException("Erreur de mise à jour !!!");
            }
        } catch (SQLException ex) {
            throw new DBException(ex.getMessage());
        }
    }
    
    public boolean dbCreateUser(String username, String password) {
        boolean result = false;
        PreparedStatement prestmt = null;
        ResultSet rs = null;
        System.out.println("before if connect");
        if (isConnected()) {
            try {
                System.out.println("before if");
                String sql = "INSERT INTO dbuser.t_user (t_user.username, t_user.password) values (?, ?)";
                if ((prestmt = dbConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) != null) {
                    System.out.println("in if");
                    prestmt.setString(1, username);
                    prestmt.setString(2, password);
                    prestmt.executeUpdate();
                    if ((rs = prestmt.getGeneratedKeys()) != null) {
                        if (rs.next()) {
                            result = true;
                        }
                    }
                }
                prestmt.close();
                prestmt = null;
                rs.close();
                rs = null;
            } catch (SQLException ex) {
                Logger.getLogger(WrkDB.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                dbDisconnect();
                try {
                    if (rs != null) {
                        rs.close();
                        rs = null;
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(WrkDB.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    if (prestmt != null) {
                        prestmt.close();
                        prestmt = null;
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(WrkDB.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return result;
    }
    
    public boolean isValidUser(String username, String password) {
        boolean result = false;
        if (isConnected()) {
            try {
                User userToCompare = getUser(username);
                if (userToCompare.getUsername().equals(username) && userToCompare.getPassword().equals(password)) {
                    result = true;
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        return result;
    }
    
    public User getUser(String username) throws DBException {
        //    boolean result = dbConnect();
        boolean result = true;
        User user = new User();
        if (result) {
            System.out.println("connection ok");
            PreparedStatement ps = null;
            try {
                ps = dbConnection.prepareStatement("SELECT * FROM dbuser.t_user WHERE username=" + username);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    user.setPKUser(Integer.parseInt(rs.getString(1)));
                    user.setUsername(rs.getString(2));
                    user.setPassword(rs.getString(3));
                }
                rs.close();
                result = true;
                System.out.println("OK");
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            if (result) {
                result = dbDisconnect();
            }
        }
        return user;
    }
}
