/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package inventori_barang.app.model;

import inventori_barang.app.controller.Koneksi;
import inventori_barang.app.gui.FormMain;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class Login {
    private static String nama;
    private String alamat;
    private static String username;
    private String password;
    private static String status;
    private Koneksi con;
    private Statement st;
    private ResultSet res;
    private String query;
    private static String kategori;
    private static String id;

    public static String getNama() {
        return nama;
    }

    public static void setNama(String nama) {
        Login.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String aUsername) {
        username = aUsername;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static String getStatus() {
        return status;
    }

    public static void setStatus(String aStatus) {
        status = aStatus;
    }

    public static String getKategori() {
        return kategori;
    }

    public static void setKategori(String kategori) {
        Login.kategori = kategori;
    }

    public static String getId() {
        return id;
    }

    public static void setId(String id) {
        Login.id = id;
    }
    
    public void Login(String username, String password) {
        con = new Koneksi();
        con.connect();
        try {
            Login login = new Login();
            st = con.connect().createStatement();
            res = st.executeQuery("select *from tmpetugas where username ='" + username + "' And password = '" + password + "'");
            if (res.next()) {
                JOptionPane.showMessageDialog(null, "Welcome " + username);
              
                new FormMain().setVisible(true);
      
            } 
            else{
                JOptionPane.showMessageDialog(null, "Username or Password is Wrong");
            }
        } catch (SQLException e) {

        }
    }
    
    public void GetData(String username) {
        con = new Koneksi();
        con.connect();
        try {
            Login login = new Login();
            st = con.connect().createStatement();
            res = st.executeQuery("select *from tmpetugas where username ='" + username + "'");
            while (res.next()) {
                setNama(res.getString("nama"));
                setId(res.getString("id"));
           } 
        } catch (SQLException e) {

        }
    }
}
