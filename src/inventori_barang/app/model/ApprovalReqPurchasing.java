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
public class ApprovalReqPurchasing extends ReqAsset {
    private Koneksi con;
    private Statement st;
    private ResultSet res;
    private String query;
    private int stok;
    private int total;
    
    public String[][] ShowByStatus(String Status) {

        res = null;
        String[][] data = null;
        con = new Koneksi();
        con.connect();
        int jumlahBaris = 0;
        try {
            st = con.conn.createStatement();
            query = "Select count(iddetail) AS Jumlah FROM reqpurchasingdetail";
            res = st.executeQuery(query);
            if (res.next()) {
                jumlahBaris = res.getInt("Jumlah");
            }
            query = "select *from reqpurchasingdetail where status = '"+Status+"'";
            res = st.executeQuery(query);
            data = new String[jumlahBaris][5];
            int r = 0;
            while (res.next()) {
                data[r][0] = res.getString("iddetail");
                data[r][1] = res.getString("idreqpurchasing");
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
    
    public void Update(String status, String catatan, String Id) {
        con = new Koneksi();
        con.connect();
        try {
            st = con.conn.createStatement();
            query = "update reqpurchasingdetail set status='" + status + "', catatan = '"+catatan+"' where iddetail = '" + Id + "'";
            st.executeUpdate(query);
            st.close();
            con.conn.close();
            JOptionPane.showMessageDialog(null, "Data di Update");
        } catch (SQLException e) {

        }
    }
    
    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
