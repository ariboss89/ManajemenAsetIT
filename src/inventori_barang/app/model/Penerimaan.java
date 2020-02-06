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
public class Penerimaan{
    
    private Koneksi con;
    private Statement st;
    private ResultSet res;
    private String query;
    
    public void UpdateStatus(String status, String Id) {
        con = new Koneksi();
        con.connect();
        try {
            st = con.conn.createStatement();
            query = "update reqpurchasingdetail set status='" + status + "' where iddetail = '" + Id + "'";
            st.executeUpdate(query);
            st.close();
            con.conn.close();
            JOptionPane.showMessageDialog(null, "Data di Update");
        } catch (SQLException e) {

        }
    }
    
}
