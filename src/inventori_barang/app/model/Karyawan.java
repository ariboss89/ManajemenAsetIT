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
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class Karyawan {
    private Koneksi con;
    private Statement st;
    private String query;
    private ResultSet res;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public void Save(String nama, String username, String password, String level, String departemen, String status) {
        con = new Koneksi();
        con.connect();
        try {
            st = con.conn.createStatement();
            query = "insert into tmpetugas (nama, username, password, level, departemen, status)values('" + nama + "','" + username + "','" + password + "','" + level + "','"+departemen+"','"+status+"')";
            st.executeUpdate(query);
            st.close();
            con.conn.close();
            JOptionPane.showMessageDialog(null, "Data di Proses");
        } catch (SQLException e) {
        }
    }
    
    public void Update(String nama, String username, String level, String departemen, String status, int id) {
        con = new Koneksi();
        con.connect();
        try {
            st = con.conn.createStatement();
            query = "update tmpetugas set nama='" + nama + "', username = '"+username+"', level = '"+level+"', departemen = '"+departemen+"', status = '"+status+"' where id = '" + id + "'";
            st.executeUpdate(query);
            st.close();
            con.conn.close();
            JOptionPane.showMessageDialog(null, "Data di Update");
        } catch (SQLException e) {

        }
    }
    
    public void Delete(int Id) {
        con = new Koneksi();
        con.connect();
        try {
            st = con.conn.createStatement();
            query = "delete from tmpetugas where id = '" + Id + "'";
            st.executeUpdate(query);
            st.close();
            con.conn.close();
            JOptionPane.showMessageDialog(null, "Data di Hapus");
        } catch (SQLException e) {

        }
    }

    public String[][] ShowData() {

        res = null;
        String[][] data = null;
        con = new Koneksi();
        con.connect();
        int jumlahBaris = 0;
        try {
            st = con.conn.createStatement();
            query = "Select count(id) AS Jumlah FROM tmpetugas";
            res = st.executeQuery(query);
            if (res.next()) {
                jumlahBaris = res.getInt("Jumlah");
            }
            query = "select *from tmpetugas";
            res = st.executeQuery(query);
            data = new String[jumlahBaris][5];
            int r = 0;
            while (res.next()) {
                data[r][0] = res.getString("nama");
                data[r][1] = res.getString("username");
                data[r][2] = res.getString("level");
                data[r][3] = res.getString("departemen");
                data[r][4] = res.getString("status");
                r++;
            }
            int jmlBaris = r;
            String[][] tmpArray = data;
            data = new String[jmlBaris][5];
            for (r = 0; r < jmlBaris; r++) {
                for (int c = 0; c < 5; c++) {
                    data[r][c] = tmpArray[r][c];
                }
            }
            st.close();
            con.conn.close();
        } catch (SQLException e) {
            System.err.println("SQLException : " + e.getMessage());
        }
        return data;
    }
}
