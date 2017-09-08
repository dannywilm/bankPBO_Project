/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankPBO;

import java.util.Calendar;
import javax.swing.JOptionPane;
import java.awt.event.*;
import java.sql.SQLException;
import javax.swing.Action;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mxD
 */

public class UserActivity extends javax.swing.JFrame {

    /**
     * Creates new form UserActivity
     */
    static String a,b,c;
    private int xMouse, yMouse;
    
    protected static int date, month, year;
    protected static int hour, minute, second;
    
    DefaultTableModel tableTransaksiModel, tablePembayaranModel;
    
    Kel5Database db = new Kel5Database();
    
    public UserActivity() {
        initComponents();
        ActionListener acl=new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                a=Lbjalan.getText();
                b=a.substring(0,1);
                c=a.substring(1,a.length());
                Lbjalan.setText(c+b);
            }
        };new javax.swing.Timer(300,acl).start();
                
        this.setSize(700, 454);
        setLocationRelativeTo(null);
        
        db.preIn2(LoginForm.noRek, LoginForm.pin);
        
        namaField.setEditable(false);
        emailField.setEditable(false);
        pinField.setEditable(false);
        alamatField.setEditable(false);
        finishB.setVisible(false);
        cancelB.setVisible(false);
        
        realTime();
        userProfile();
        
        showTransaksiTable();
        showPembayaranTable();
        
        tableTransaksi.disable();
        tablePembayaran.disable();;
    }
    
    protected void realTime()
    {
        Thread clock = new Thread(){
            public void run()
                {
                    while (true){
                        Calendar cal = Calendar.getInstance();
                        date = cal.get(Calendar.DAY_OF_MONTH);
                        month = cal.get(Calendar.MONTH);
                        year = cal.get(Calendar.YEAR);

                        hour = cal.get(Calendar.HOUR_OF_DAY);
                        minute = cal.get(Calendar.MINUTE);
                        second = cal.get(Calendar.SECOND);
                        
                        dateText.setText(date + "/" + (month+1) + "/" + year);
                        dateText1.setText(date + "/" + (month+1) + "/" + year);
                        clockText.setText(hour + ":" + minute + ":" + second);
                        clockText1.setText(hour + ":" + minute + ":" + second);
                    }
                }
        };
        clock.start();
    }
    
    public void showTransaksiTable()
    {
        String sql = "SELECT * FROM transaksi WHERE no_rekeningPengirim = ?";
        
        tableTransaksiModel = new DefaultTableModel();
        
        tableTransaksi.setModel(tableTransaksiModel);
        tableTransaksiModel.addColumn("Nomor Rekening Penerima");
        tableTransaksiModel.addColumn("Nama Penerima");
        tableTransaksiModel.addColumn("Jumlah Uang Transfer");
        tableTransaksiModel.addColumn("Tanggal Transaksi");
        tableTransaksiModel.addColumn("Waktu Transaksi");
        
        // hapus semua elemen awal table
        tableTransaksiModel.getDataVector().removeAllElements();
        tableTransaksiModel.fireTableDataChanged();
        
        // get data from database
        try
        {
            db.ps = db.cn.prepareStatement(sql);
            db.ps.setString(1, String.valueOf(LoginForm.noRek));
            
            db.rs = db.ps.executeQuery();
            
            while(db.rs.next())
            {
                Object[] obj = new Object[5];
                obj[0] = db.rs.getString("no_rekeningPenerima");
                obj[1] = db.rs.getString("namaPenerima");
                obj[2] = db.rs.getInt("jumlahUangTransfer");
                obj[3] = db.rs.getString("tanggalTransaksi");
                obj[4] = db.rs.getString("jamTransaksi");
                
                tableTransaksiModel.addRow(obj);
            }
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error : " + ex.getMessage());
        }
    }
    
    public void showPembayaranTable()
    {
        String sql = "SELECT * FROM pembayaran WHERE no_rekening = ?";
        
        tablePembayaranModel = new DefaultTableModel();
        
        tablePembayaran.setModel(tablePembayaranModel);
        tablePembayaranModel.addColumn("Nomor");
        tablePembayaranModel.addColumn("Jumlah Bayar");
        tablePembayaranModel.addColumn("Keperluan");
        tablePembayaranModel.addColumn("Tanggal Pembelian");
        tablePembayaranModel.addColumn("Waktu Pembelian");
        
        // hapus semua elemen awal table
        tablePembayaranModel.getDataVector().removeAllElements();
        tablePembayaranModel.fireTableDataChanged();
        
        // get data from database
        try
        {
            db.ps = db.cn.prepareStatement(sql);
            db.ps.setString(1, String.valueOf(LoginForm.noRek));
            
            db.rs = db.ps.executeQuery();
            
            while(db.rs.next())
            {
                Object[] obj = new Object[5];
                obj[0] = db.rs.getString("nom");
                obj[1] = db.rs.getInt("hargaByr");
                obj[2] = db.rs.getString("perihal");
                obj[3] = db.rs.getString("tanggalTransaksi");
                obj[4] = db.rs.getString("jamTransaksi");
                
                tablePembayaranModel.addRow(obj);
            }
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error : " + ex.getMessage());
        }
    }
    
    protected void userProfile()
    {
        noRekText.setText(String.valueOf(db.getNoRekening()));
        namaField.setText(db.getNama());
        pinField.setText(String.valueOf(db.getPin()));
        emailField.setText(db.getEmail());
        alamatField.setText(db.getAlamat());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        minimizeWindows = new javax.swing.JButton();
        exitButton = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        frameDrag = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        tableHistori = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        transferB = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        clockText = new javax.swing.JLabel();
        LBJALAN = new javax.swing.JLabel();
        dateText = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        Lbjalan = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        dateText1 = new javax.swing.JLabel();
        clockText1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        namaField = new javax.swing.JTextField();
        pinField = new javax.swing.JPasswordField();
        editProfileB = new javax.swing.JButton();
        finishB = new javax.swing.JButton();
        noRekText = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        alamatField = new javax.swing.JTextArea();
        emailField = new javax.swing.JTextField();
        cancelB = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableTransaksi = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablePembayaran = new javax.swing.JTable();
        jLabel14 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel5.setBackground(new java.awt.Color(0, 153, 153));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        minimizeWindows.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgSrc/minimize2.png"))); // NOI18N
        minimizeWindows.setContentAreaFilled(false);
        minimizeWindows.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minimizeWindowsActionPerformed(evt);
            }
        });
        jPanel5.add(minimizeWindows, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 0, 30, -1));

        exitButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgSrc/closeApp2.png"))); // NOI18N
        exitButton.setContentAreaFilled(false);
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });
        jPanel5.add(exitButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(667, 0, 30, -1));

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgSrc/kel5title.png"))); // NOI18N
        jPanel5.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 0, 110, 20));

        frameDrag.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                frameDragMouseDragged(evt);
            }
        });
        frameDrag.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                frameDragMousePressed(evt);
            }
        });
        jPanel5.add(frameDrag, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 700, 20));

        getContentPane().add(jPanel5, java.awt.BorderLayout.PAGE_START);

        jPanel1.setLayout(null);

        jPanel3.setLayout(null);

        transferB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgSrc/bankTransfer.png"))); // NOI18N
        transferB.setText("Transfer");
        transferB.setToolTipText("Transfer Uang");
        transferB.setContentAreaFilled(false);
        transferB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                transferBActionPerformed(evt);
            }
        });
        jPanel3.add(transferB);
        transferB.setBounds(-10, 20, 180, 80);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgSrc/balanceCheck2.png"))); // NOI18N
        jButton3.setText("Cek Saldo");
        jButton3.setToolTipText("Check Balance");
        jButton3.setContentAreaFilled(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton3);
        jButton3.setBounds(-10, 120, 170, 80);

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Jam :");
        jPanel3.add(jLabel2);
        jLabel2.setBounds(410, 270, 60, 40);

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Tanggal :");
        jPanel3.add(jLabel3);
        jLabel3.setBounds(410, 300, 100, 40);

        clockText.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        clockText.setForeground(new java.awt.Color(255, 255, 255));
        clockText.setText("00 : 00");
        jPanel3.add(clockText);
        clockText.setBounds(490, 270, 90, 40);

        LBJALAN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgSrc/Kel5BankLogo_11.png"))); // NOI18N
        jPanel3.add(LBJALAN);
        LBJALAN.setBounds(490, 0, 200, 170);

        dateText.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        dateText.setForeground(new java.awt.Color(255, 255, 255));
        dateText.setText("00/00/0000");
        jPanel3.add(dateText);
        dateText.setBounds(490, 300, 100, 40);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgSrc/logoutLogo.png"))); // NOI18N
        jButton1.setToolTipText("Logout");
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton1);
        jButton1.setBounds(583, 263, 100, 90);

        Lbjalan.setFont(new java.awt.Font("Jokerman", 0, 18)); // NOI18N
        Lbjalan.setForeground(new java.awt.Color(255, 0, 0));
        Lbjalan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Lbjalan.setText("============= Selamat Datang  User You'll feel that JOIN US :) =============");
        jPanel3.add(Lbjalan);
        Lbjalan.setBounds(0, 370, 700, 20);

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgSrc/payment.png"))); // NOI18N
        jButton5.setText("Pembayaran Lain Lain");
        jButton5.setContentAreaFilled(false);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton5);
        jButton5.setBounds(190, 20, 220, 70);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgSrc/bokeh1.jpg"))); // NOI18N
        jPanel3.add(jLabel1);
        jLabel1.setBounds(0, -20, 710, 460);

        tableHistori.addTab("Menu Utama", jPanel3);

        jPanel2.setLayout(null);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgSrc/Kel5BankLogo_11.png"))); // NOI18N
        jPanel2.add(jLabel4);
        jLabel4.setBounds(490, 0, 200, 170);

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel5.setText("Jam :");
        jPanel2.add(jLabel5);
        jLabel5.setBounds(500, 160, 60, 40);

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel6.setText("Tanggal :");
        jPanel2.add(jLabel6);
        jLabel6.setBounds(500, 190, 100, 40);

        dateText1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        dateText1.setText("00/00/0000");
        jPanel2.add(dateText1);
        dateText1.setBounds(580, 190, 100, 40);

        clockText1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        clockText1.setText("00 : 00");
        jPanel2.add(clockText1);
        clockText1.setBounds(580, 160, 90, 40);

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel7.setText("Nama");
        jPanel2.add(jLabel7);
        jLabel7.setBounds(20, 60, 60, 30);

        jLabel8.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel8.setText("Nomor Rekening");
        jPanel2.add(jLabel8);
        jLabel8.setBounds(20, 10, 130, 30);

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel9.setText("PIN");
        jPanel2.add(jLabel9);
        jLabel9.setBounds(20, 110, 120, 30);

        namaField.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        namaField.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.add(namaField);
        namaField.setBounds(170, 60, 190, 30);

        pinField.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        pinField.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.add(pinField);
        pinField.setBounds(170, 110, 190, 30);

        editProfileB.setText("EDIT PROFILE");
        editProfileB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editProfileBActionPerformed(evt);
            }
        });
        jPanel2.add(editProfileB);
        editProfileB.setBounds(170, 330, 190, 23);

        finishB.setText("Finish");
        finishB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                finishBActionPerformed(evt);
            }
        });
        jPanel2.add(finishB);
        finishB.setBounds(170, 330, 190, 23);

        noRekText.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        noRekText.setText("00000");
        jPanel2.add(noRekText);
        noRekText.setBounds(170, 10, 140, 30);

        jLabel11.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel11.setText("E-Mail");
        jPanel2.add(jLabel11);
        jLabel11.setBounds(20, 160, 120, 30);

        jLabel12.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel12.setText("Alamat");
        jPanel2.add(jLabel12);
        jLabel12.setBounds(20, 210, 120, 30);

        alamatField.setColumns(20);
        alamatField.setRows(5);
        alamatField.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jScrollPane1.setViewportView(alamatField);

        jPanel2.add(jScrollPane1);
        jScrollPane1.setBounds(170, 210, 190, 98);

        emailField.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.add(emailField);
        emailField.setBounds(170, 160, 190, 30);

        cancelB.setText("Cancel");
        cancelB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelBActionPerformed(evt);
            }
        });
        jPanel2.add(cancelB);
        cancelB.setBounds(170, 360, 190, 23);

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgSrc/user-profile-bg.jpg"))); // NOI18N
        jPanel2.add(jLabel13);
        jLabel13.setBounds(0, 0, 700, 400);

        tableHistori.addTab("Profil", jPanel2);

        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tableTransaksi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nomor Rekening Penerima", "Nama Penerima", "Jumlah Uang Transfer", "Tanggal Transaksi", "Jam Transaksi"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableTransaksi.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_LAST_COLUMN);
        tableTransaksi.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tableTransaksi);
        if (tableTransaksi.getColumnModel().getColumnCount() > 0) {
            tableTransaksi.getColumnModel().getColumn(0).setResizable(false);
            tableTransaksi.getColumnModel().getColumn(1).setResizable(false);
            tableTransaksi.getColumnModel().getColumn(2).setResizable(false);
            tableTransaksi.getColumnModel().getColumn(3).setResizable(false);
            tableTransaksi.getColumnModel().getColumn(4).setResizable(false);
        }

        jPanel4.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, 675, 140));

        jButton4.setText("Refresh");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 350, -1, -1));

        tablePembayaran.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nomor", "Harga  Bayar", "Keperluan"
            }
        ));
        tablePembayaran.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(tablePembayaran);

        jPanel4.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 680, 140));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgSrc/underwaterBG.jpg"))); // NOI18N
        jPanel4.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        tableHistori.addTab("Histori Transaksi", jPanel4);

        jPanel1.add(tableHistori);
        tableHistori.setBounds(0, 10, 705, 420);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void frameDragMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_frameDragMouseDragged
        // TODO add your handling code here:

        int x, y;

        x = evt.getXOnScreen();
        y = evt.getYOnScreen();

        this.setLocation(x - xMouse, y - yMouse);
    }//GEN-LAST:event_frameDragMouseDragged

    private void frameDragMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_frameDragMousePressed
        // TODO add your handling code here:

        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_frameDragMousePressed

    private void minimizeWindowsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minimizeWindowsActionPerformed
        // TODO add your handling code here:

        this.setState(LoginForm.ICONIFIED);
    }//GEN-LAST:event_minimizeWindowsActionPerformed

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        // TODO add your handling code here:

        System.exit(1);
    }//GEN-LAST:event_exitButtonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        
        LoginForm.noRek = 0;
        
        dispose();
        new LoginForm().setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        new CekSaldo().setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void transferBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_transferBActionPerformed
        // TODO add your handling code here:

        new TransferForm().setVisible(true);
    }//GEN-LAST:event_transferBActionPerformed

    private void cancelBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelBActionPerformed
        // TODO add your handling code here:

        namaField.setEditable(false);
        emailField.setEditable(false);
        pinField.setEditable(false);
        alamatField.setEditable(false);
        finishB.setVisible(false);
        cancelB.setVisible(false);

        editProfileB.setVisible(true);
        
        userProfile();
    }//GEN-LAST:event_cancelBActionPerformed

    private void finishBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_finishBActionPerformed
        // TODO add your handling code here:

        String nama = namaField.getText();
        int pin = Integer.parseInt(String.valueOf(pinField.getPassword()));
        String email = emailField.getText();
        String alamat = alamatField.getText();

        if (String.valueOf(pin).length() > 5)
        {
            JOptionPane.showMessageDialog(null, "Maaf, pin tidak boleh lebih dari 5 angka");
            pinField.requestFocus();
        }
        else
        {
            db.changeProfile(nama, pin, email, alamat);
            
            finishB.setVisible(false);
            cancelB.setVisible(false);
            editProfileB.setVisible(true);
            
            namaField.setEditable(false);
            emailField.setEditable(false);
            pinField.setEditable(false);
            alamatField.setEditable(false);
            finishB.setVisible(false);
            cancelB.setVisible(false);
        }
    }//GEN-LAST:event_finishBActionPerformed
  
    private void editProfileBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editProfileBActionPerformed
        // TODO add your handling code here:

        editProfileB.setVisible(false);
        finishB.setVisible(true);
        cancelB.setVisible(true);

        namaField.setEditable(true);
        emailField.setEditable(true);
        pinField.setEditable(true);
        alamatField.setEditable(true);
    }//GEN-LAST:event_editProfileBActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        
        showTransaksiTable();
        showPembayaranTable();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        
        new Pembayaran().setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UserActivity.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UserActivity.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UserActivity.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UserActivity.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UserActivity().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LBJALAN;
    private javax.swing.JLabel Lbjalan;
    private javax.swing.JTextArea alamatField;
    private javax.swing.JButton cancelB;
    private javax.swing.JLabel clockText;
    private javax.swing.JLabel clockText1;
    private javax.swing.JLabel dateText;
    private javax.swing.JLabel dateText1;
    private javax.swing.JButton editProfileB;
    private javax.swing.JTextField emailField;
    private javax.swing.JButton exitButton;
    private javax.swing.JButton finishB;
    private javax.swing.JLabel frameDrag;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton minimizeWindows;
    private javax.swing.JTextField namaField;
    private javax.swing.JLabel noRekText;
    private javax.swing.JPasswordField pinField;
    private javax.swing.JTabbedPane tableHistori;
    private javax.swing.JTable tablePembayaran;
    private javax.swing.JTable tableTransaksi;
    private javax.swing.JButton transferB;
    // End of variables declaration//GEN-END:variables
}
