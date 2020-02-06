/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventori_barang.app.gui;

import inventori_barang.app.controller.Koneksi;
import inventori_barang.app.model.Login;
import inventori_barang.app.model.Main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.io.File;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author User
 */
public class FormMain extends javax.swing.JFrame {

    Main main = new Main();

    /**
     * Creates new form FormMain
     */
    public FormMain() {
        initComponents();
        getContentPane().setBackground(new Color(23, 35, 51));
        Dimension windowSize = getSize();
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Point centerPoint = ge.getCenterPoint();
        int dx = centerPoint.x - windowSize.width / 2;
        int dy = centerPoint.y - windowSize.height / 2;
        setLocation(dx, dy);
        jTextField1.setText(Login.getNama());
        jTextField2.setText(Login.getUsername());
        Auth();
        Refresh();
        Run();
    }

    private void Refresh(){
        CountReqAsset();
        CountReqPurchasing();
        ShowPengumuman();
    }
    
    private void ShowPengumuman() {
        java.sql.Connection conn = new Koneksi().connect();
        try {
            java.sql.Statement stmt = conn.createStatement();
            java.sql.ResultSet res = stmt.executeQuery("select *from pengumuman");
            while (res.next()) {
                jTextArea1.setText(res.getString("pengumuman"));
            }
        } catch (SQLException ex) {

        }
    }

    private void Auth() {
        String level = Login.getStatus();
        if (level.equals("Manager")) {
            menuBarang.setEnabled(false);
            menuKategori.setEnabled(false);
            menuKaryawan.setEnabled(false);
            menuReqPurchasing.setEnabled(false);
            menuApprovalAsset.setEnabled(false);
            menuRiwayatPurchasing.setEnabled(false);
            menuLaporan.setEnabled(false);
            menuPenerimaan.setEnabled(false);
            jTextArea1.setEditable(true);
            jButton1.setVisible(true);
        }
        if (level.equals("Supervisor")) {
            menuInput.setEnabled(false);
            menuReqPurchasing.setEnabled(false);
            menuMonitoring.setEnabled(false);
            menuRiwayatPurchasing.setEnabled(false);
            menuLaporan.setEnabled(false);
            menuPenerimaan.setEnabled(false);
            jTextArea1.setEditable(true);
            jButton1.setVisible(true);
        }
        if (level.equals("IT Staff")) {
            menuSupplier.setEnabled(false);
            menuReqAsset.setEnabled(false);
            menuApprovalPurchasing.setEnabled(false);
            menuRiwayatAsset.setEnabled(false);
            jTextArea1.setEditable(true);
            jButton1.setVisible(true);
        }
        if (level.equals("Employee Staff")) {
            menuInput.setEnabled(false);
            menuReqPurchasing.setEnabled(false);
            menuMonitoring.setEnabled(false);
            menuRiwayatPurchasing.setEnabled(false);
            menuLaporan.setEnabled(false);
            menuPenerimaan.setEnabled(false);
            jButton1.setVisible(false);
        }
    }

    private void CountReqAsset() {
        java.sql.Connection conn = new Koneksi().connect();
        try {
            java.sql.Statement stmt = conn.createStatement();
            java.sql.ResultSet res = stmt.executeQuery("select count(iddetailreqasset) as hitung from reqassetdetail where status = 'On Waiting'");
            while (res.next()) {
                lblJumlahReqAsset.setText(res.getString("hitung"));
            }
        } catch (SQLException ex) {

        }
    }

    private void CountReqPurchasing() {
        java.sql.Connection conn = new Koneksi().connect();
        try {
            java.sql.Statement stmt = conn.createStatement();
            java.sql.ResultSet res = stmt.executeQuery("select count(iddetail) as hitung from reqpurchasingdetail where status = 'On Waiting'");
            while (res.next()) {
                lblJumlahReqPurchasing.setText(res.getString("hitung"));
            }
        } catch (SQLException ex) {

        }
    }

    public void Run() {
        new Thread() {
            public void run() {
                int waktu = 0;
                while (waktu == 0) {
                    Calendar kalender = new GregorianCalendar();

                    //set variabel  
                    int jam = kalender.get(Calendar.HOUR);
                    int menit = kalender.get(Calendar.MINUTE);
                    int detik = kalender.get(Calendar.SECOND);
                    int am_pm = kalender.get(Calendar.AM_PM);

                    int tahun = kalender.get(Calendar.YEAR);
                    int bulan = kalender.get(Calendar.MONTH) + 1;
                    int hari = kalender.get(Calendar.DATE);

                    //mengatur menggunakan AM atau PM  
                    String siang_malam = "";
                    if (am_pm == 1) {
                        siang_malam = "PM";
                    } else {
                        siang_malam = "AM";
                    }

                    //mengatur format penulisan waktu  
                    String format_waktu = jam + ":" + menit + ":" + detik + " " + siang_malam;
                    String formatTanggal = tahun + "/" + bulan + "/" + hari;
                    //menampilkan pada label yang digunakan sebagai penunjuk waktu  
                    lblJamdanTanggal.setText(format_waktu + " " + formatTanggal);
                }
            }
        }.start();//ini wajib  
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        lblJumlahReqAsset = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        lblJumlahReqPurchasing = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        lblJamdanTanggal = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuInput = new javax.swing.JMenu();
        menuKategori = new javax.swing.JMenuItem();
        menuSupplier = new javax.swing.JMenuItem();
        menuBarang = new javax.swing.JMenuItem();
        menuKaryawan = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        menuReqAsset = new javax.swing.JMenuItem();
        menuReqPurchasing = new javax.swing.JMenuItem();
        menuMonitoring = new javax.swing.JMenu();
        menuApprovalAsset = new javax.swing.JMenuItem();
        menuApprovalPurchasing = new javax.swing.JMenuItem();
        menuRiwayat = new javax.swing.JMenu();
        menuRiwayatAsset = new javax.swing.JMenuItem();
        menuRiwayatPurchasing = new javax.swing.JMenuItem();
        menuLaporan = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        menuPenerimaan = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jTextField1.setEditable(false);
        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jTextField2.setEditable(false);
        jTextField2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextField2.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("NAMA");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("USERNAME");

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventori_barang/img/dashboard.png"))); // NOI18N

        jPanel1.setBackground(new java.awt.Color(23, 35, 51));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jPanel2.setBackground(new java.awt.Color(255, 51, 51));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblJumlahReqAsset.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        lblJumlahReqAsset.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblJumlahReqAsset.setText("-");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblJumlahReqAsset, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblJumlahReqAsset, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(153, 255, 153));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblJumlahReqPurchasing.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        lblJumlahReqPurchasing.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblJumlahReqPurchasing.setText("-");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblJumlahReqPurchasing, javax.swing.GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblJumlahReqPurchasing, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(23, 35, 51));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("PENGUMUMAN !!!");

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        lblJamdanTanggal.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblJamdanTanggal.setForeground(new java.awt.Color(255, 255, 255));
        lblJamdanTanggal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblJamdanTanggal.setText("-");

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventori_barang/img/edit.png"))); // NOI18N
        jButton1.setText("UPDATE");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 573, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblJamdanTanggal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(lblJamdanTanggal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap())
        );

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("JUMLAH REQ ASSET YANG BUTUH APPROVAL");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("JUMLAH REQ PURCHASING YANG BUTUH APPROVAL");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel8))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jMenuBar1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        menuInput.setText("INPUT");

        menuKategori.setText("DATA KATEGORI");
        menuKategori.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuKategoriActionPerformed(evt);
            }
        });
        menuInput.add(menuKategori);

        menuSupplier.setText("DATA SUPPLIER");
        menuSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuSupplierActionPerformed(evt);
            }
        });
        menuInput.add(menuSupplier);

        menuBarang.setText("DATA BARANG");
        menuBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuBarangActionPerformed(evt);
            }
        });
        menuInput.add(menuBarang);

        menuKaryawan.setText("DATA KARYAWAN");
        menuKaryawan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuKaryawanActionPerformed(evt);
            }
        });
        menuInput.add(menuKaryawan);

        jMenuBar1.add(menuInput);

        jMenu2.setText("REQUEST");

        menuReqAsset.setText("REQUEST ASSET");
        menuReqAsset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuReqAssetActionPerformed(evt);
            }
        });
        jMenu2.add(menuReqAsset);

        menuReqPurchasing.setText("REQUEST PURCHASING");
        menuReqPurchasing.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuReqPurchasingActionPerformed(evt);
            }
        });
        jMenu2.add(menuReqPurchasing);

        jMenuBar1.add(jMenu2);

        menuMonitoring.setText("MONITORING");

        menuApprovalAsset.setText("APPROVAL ASSET");
        menuApprovalAsset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuApprovalAssetActionPerformed(evt);
            }
        });
        menuMonitoring.add(menuApprovalAsset);

        menuApprovalPurchasing.setText("APPROVAL PURCHASING");
        menuApprovalPurchasing.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuApprovalPurchasingActionPerformed(evt);
            }
        });
        menuMonitoring.add(menuApprovalPurchasing);

        jMenuBar1.add(menuMonitoring);

        menuRiwayat.setText("RIWAYAT");

        menuRiwayatAsset.setText("RIWAYAT ASSET");
        menuRiwayatAsset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuRiwayatAssetActionPerformed(evt);
            }
        });
        menuRiwayat.add(menuRiwayatAsset);

        menuRiwayatPurchasing.setText("RIWAYAT PURCHASING");
        menuRiwayatPurchasing.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuRiwayatPurchasingActionPerformed(evt);
            }
        });
        menuRiwayat.add(menuRiwayatPurchasing);

        jMenuBar1.add(menuRiwayat);

        menuLaporan.setText("LAPORAN");

        jMenuItem1.setText("LAPORAN REQUEST ASSET");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        menuLaporan.add(jMenuItem1);

        jMenuItem2.setText("LAPORAN REQUEST PURCHASING");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        menuLaporan.add(jMenuItem2);

        jMenuBar1.add(menuLaporan);

        menuPenerimaan.setText("PENERIMAAN ITEM");
        menuPenerimaan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuPenerimaanMouseClicked(evt);
            }
        });
        jMenuBar1.add(menuPenerimaan);

        jMenu5.setText("LOG OUT");
        jMenu5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu5MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu5);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
                            .addComponent(jTextField1)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenu5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu5MouseClicked
        // TODO add your handling code here:
        String nama = Login.getNama();
        int ok = JOptionPane.showConfirmDialog(null, "Anda Yakin ?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (ok == 0) {
            JOptionPane.showMessageDialog(null, "Goodbye " + nama);
            System.exit(ok);
        } else {

        }
    }//GEN-LAST:event_jMenu5MouseClicked

    private void menuReqAssetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuReqAssetActionPerformed
        // TODO add your handling code here:
        new FormRequestAsset(this, rootPaneCheckingEnabled).show();
    }//GEN-LAST:event_menuReqAssetActionPerformed

    private void menuKaryawanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuKaryawanActionPerformed
        // TODO add your handling code here:
        new FormKaryawan().show();
    }//GEN-LAST:event_menuKaryawanActionPerformed

    private void menuSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuSupplierActionPerformed
        // TODO add your handling code here:
        new FormSupplier().show();
    }//GEN-LAST:event_menuSupplierActionPerformed

    private void menuKategoriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuKategoriActionPerformed
        // TODO add your handling code here:
        new FormKategori().show();
    }//GEN-LAST:event_menuKategoriActionPerformed

    private void menuBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuBarangActionPerformed
        // TODO add your handling code here:
        new FormBarang().show();
    }//GEN-LAST:event_menuBarangActionPerformed

    private void menuReqPurchasingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuReqPurchasingActionPerformed
        // TODO add your handling code here:
        new FormRequestPurchasing(this, rootPaneCheckingEnabled).show();
    }//GEN-LAST:event_menuReqPurchasingActionPerformed

    private void menuApprovalAssetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuApprovalAssetActionPerformed
        // TODO add your handling code here:
        new FormApprovalRequestAsset().show();
    }//GEN-LAST:event_menuApprovalAssetActionPerformed

    private void menuApprovalPurchasingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuApprovalPurchasingActionPerformed
        // TODO add your handling code here:
        new FormApprovalRequestPurchasing().show();
    }//GEN-LAST:event_menuApprovalPurchasingActionPerformed

    private void menuRiwayatAssetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuRiwayatAssetActionPerformed
        // TODO add your handling code here:
        new FormRiwayatAsset().show();
    }//GEN-LAST:event_menuRiwayatAssetActionPerformed

    private void menuRiwayatPurchasingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuRiwayatPurchasingActionPerformed
        // TODO add your handling code here:
        new FormRiwayatPurchasing().show();
    }//GEN-LAST:event_menuRiwayatPurchasingActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String isi = jTextArea1.getText();
        main.Update(isi);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
        Refresh();
    }//GEN-LAST:event_formWindowActivated

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        new FormLaporanRequestAsset().show();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        new FormLaporanRequestPurchasing().show();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void menuPenerimaanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuPenerimaanMouseClicked
        // TODO add your handling code here:
        new FormPenerimaan().show();
    }//GEN-LAST:event_menuPenerimaanMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormMain().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JLabel lblJamdanTanggal;
    private javax.swing.JLabel lblJumlahReqAsset;
    private javax.swing.JLabel lblJumlahReqPurchasing;
    private javax.swing.JMenuItem menuApprovalAsset;
    private javax.swing.JMenuItem menuApprovalPurchasing;
    private javax.swing.JMenuItem menuBarang;
    private javax.swing.JMenu menuInput;
    private javax.swing.JMenuItem menuKaryawan;
    private javax.swing.JMenuItem menuKategori;
    private javax.swing.JMenu menuLaporan;
    private javax.swing.JMenu menuMonitoring;
    private javax.swing.JMenu menuPenerimaan;
    private javax.swing.JMenuItem menuReqAsset;
    private javax.swing.JMenuItem menuReqPurchasing;
    private javax.swing.JMenu menuRiwayat;
    private javax.swing.JMenuItem menuRiwayatAsset;
    private javax.swing.JMenuItem menuRiwayatPurchasing;
    private javax.swing.JMenuItem menuSupplier;
    // End of variables declaration//GEN-END:variables
}
