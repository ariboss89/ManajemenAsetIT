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
public class ReqAsset {
    
    private Koneksi con;
    private Statement st;
    private String query;
    private ResultSet res;
    private String IdSupplier;
    private String IdBarang;
    private String IdDetail;
    private String IdAsset;
    private String status;
    private String sum;

    public String getIdSupplier() {
        return IdSupplier;
    }

    public void setIdSupplier(String IdSupplier) {
        this.IdSupplier = IdSupplier;
    }

    public String getIdBarang() {
        return IdBarang;
    }

    public void setIdBarang(String IdBarang) {
        this.IdBarang = IdBarang;
    }

    public String getIdDetail() {
        return IdDetail;
    }

    public void setIdDetail(String IdDetail) {
        this.IdDetail = IdDetail;
    }

    public String getIdAsset() {
        return IdAsset;
    }

    public void setIdAsset(String IdAsset) {
        this.IdAsset = IdAsset;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void SaveDetail(String iddetail, String id, String idbarang, int jumlah, String status) {
        con = new Koneksi();
        con.connect();
        try {
            st = con.conn.createStatement();
            query = "insert into reqassetdetail(iddetailreqasset, idreqasset, idbarang, subjumlah, status)values('" + iddetail + "','" + id + "','" + idbarang + "','" + jumlah + "','"+status+"')";
            st.executeUpdate(query);
            st.close();
            con.conn.close();
            JOptionPane.showMessageDialog(null, "Data di Tambahkan");
        } catch (SQLException e) {
        }
    }
    
    public void Save(String idreqasset, Date tanggal, int jumlah, String idsupplier, String idpetugas) {
        con = new Koneksi();
        con.connect();
        try {
            st = con.conn.createStatement();
            query = "insert into reqasset(idreqasset, tanggal, jumlah, idsupplier, idpetugas)values('" + idreqasset + "','" + tanggal + "','" + jumlah + "','" + idsupplier + "','"+idpetugas+"')";
            st.executeUpdate(query);
            st.close();
            con.conn.close();
            JOptionPane.showMessageDialog(null, "Data di Proses");
        } catch (SQLException e) {
        }
    }
    
    public void Delete(String Id) {
        con = new Koneksi();
        con.connect();
        try {
            st = con.conn.createStatement();
            query = "delete from reqassetdetail where iddetailreqasset = '" + Id + "'";
            st.executeUpdate(query);
            st.close();
            con.conn.close();
            JOptionPane.showMessageDialog(null, "Data di Hapus");
        } catch (SQLException e) {

        }
    }

    public String[][] ShowData(String Id) {

        res = null;
        String[][] data = null;
        con = new Koneksi();
        con.connect();
        int jumlahBaris = 0;
        try {
            st = con.conn.createStatement();
            query = "Select count(iddetailreqasset) AS Jumlah FROM reqassetdetail";
            res = st.executeQuery(query);
            if (res.next()) {
                jumlahBaris = res.getInt("Jumlah");
            }
            query = "select *from reqassetdetail where idreqasset = '"+Id+"'";
            res = st.executeQuery(query);
            data = new String[jumlahBaris][5];
            int r = 0;
            while (res.next()) {
                data[r][0] = res.getString("iddetailreqasset");
                data[r][1] = res.getString("idreqasset");
                data[r][2] = res.getString("idbarang");
                data[r][3] = res.getString("subjumlah");
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

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }
}
