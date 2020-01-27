/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package inventori_barang.app.model;

import inventori_barang.app.controller.Koneksi;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class Main {
     private Koneksi con;
    private Statement st;
    private String query;
    private ResultSet res;

    public void Update(String pengumuman) {
        con = new Koneksi();
        con.connect();
        try {
            st = con.conn.createStatement();
            query = "update pengumuman set pengumuman='" + pengumuman + "'";
            st.executeUpdate(query);
            st.close();
            con.conn.close();
            JOptionPane.showMessageDialog(null, "Data di Update");
        } catch (SQLException e) {

        }
    }
}
