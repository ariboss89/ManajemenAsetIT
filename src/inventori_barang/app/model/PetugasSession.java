/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventori_barang.app.model;

/**
 *
 * @author casperx
 */
public class PetugasSession {
    private static int u_id;
    private static String u_username;
    private static String u_password;
    private static String u_nama;
     
    public static int getU_id() {
        return u_id;
    }
 
    public static void setU_id(int u_id) {
        PetugasSession.u_id = u_id;
    }
 
    public static String getU_username() {
        return u_username;
    }
 
    public static void setU_username(String u_username) {
        PetugasSession.u_username = u_username;
    }
 
    public static String getU_password() {
        return u_password;
    }
 
    public static void setU_password(String u_password) {
        PetugasSession.u_password = u_password;
    }
 
    public static String getU_nama() {
        return u_nama;
    }
 
    public static void setU_nama(String u_nama) {
        PetugasSession.u_nama = u_nama;
    }
}
