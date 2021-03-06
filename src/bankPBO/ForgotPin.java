/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankPBO;

import javax.swing.JOptionPane;

/**
 *
 * @author mxD
 */
public class ForgotPin extends javax.swing.JFrame {

    /**
     * Creates new form ForgotPin
     */
    
    Kel5Database db = new Kel5Database();
    
    int noRek = 0, pin = 0;
    private int xMouse, yMouse;
    
    public ForgotPin() {
        
        super("Forgot PIN");
        initComponents();
        this.setSize(535, 250);
        setLocationRelativeTo(null);
        
        newPinText.setVisible(false);
        newPinField.setVisible(false);
        next2.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        noRekField = new javax.swing.JTextField();
        nextB = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        newPinField = new javax.swing.JPasswordField();
        newPinText = new javax.swing.JLabel();
        next2 = new javax.swing.JButton();
        backB = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        frameDrag = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Masukkan nomor rekening anda untuk mengubah pin anda");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(70, 0, 380, 30);

        noRekField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        noRekField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        noRekField.setToolTipText("Nomor Rekening");
        jPanel1.add(noRekField);
        noRekField.setBounds(170, 30, 180, 30);

        nextB.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        nextB.setText("NEXT");
        nextB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextBActionPerformed(evt);
            }
        });
        jPanel1.add(nextB);
        nextB.setBounds(220, 160, 80, 23);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgSrc/Kel5BankLogo_Small.png"))); // NOI18N
        jPanel1.add(jLabel2);
        jLabel2.setBounds(0, 170, 60, 60);

        newPinField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel1.add(newPinField);
        newPinField.setBounds(170, 110, 180, 30);

        newPinText.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        newPinText.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        newPinText.setText("ENTER NEW PIN");
        jPanel1.add(newPinText);
        newPinText.setBounds(200, 90, 120, 13);

        next2.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        next2.setText("NEXT");
        next2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                next2ActionPerformed(evt);
            }
        });
        jPanel1.add(next2);
        next2.setBounds(220, 160, 73, 23);

        backB.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        backB.setText("BACK");
        backB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBActionPerformed(evt);
            }
        });
        jPanel1.add(backB);
        backB.setBounds(220, 190, 80, 20);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgSrc/sky-bg.jpg"))); // NOI18N
        jPanel1.add(jLabel3);
        jLabel3.setBounds(-100, -10, 700, 350);

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
        jPanel1.add(frameDrag);
        frameDrag.setBounds(0, 0, 540, 250);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 535, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nextBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextBActionPerformed
        // Action Button
        
        try
        {
            noRek = Integer.parseInt(noRekField.getText());
        }
        catch(NumberFormatException ex)
        {
            JOptionPane.showMessageDialog(null, "Tolong isi nomor rekeing anda");
        }
        
        db.forgotPin(noRek);
        
        if (db.getU() > 0)
        {
            String q = JOptionPane.showInputDialog(null, "Masukkan kata kunci keamanan anda : ");
            db.qAnswer(q);
        
            if (db.getU() > 0)
            {
                newPinText.setVisible(true);
                newPinField.setVisible(true);
                next2.setVisible(true);
                nextB.setVisible(false);
                newPinField.requestFocus();
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Maaf jawaban dari pertanyaan keamanan anda tidak benar\nSilahkan diulang kembali");
            }
        }
        
    }//GEN-LAST:event_nextBActionPerformed

    private void next2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_next2ActionPerformed
        // TODO add your handling code here:
        
        try
        {
            int newPin = Integer.valueOf(String.valueOf(newPinField.getPassword()));
            
            if(String.valueOf(newPin).length() > 5)
            {
                JOptionPane.showMessageDialog(null, "PIN tidak dapat lebih dari 5 angka");
                newPinField.requestFocus();
            }
            else
            {
                db.forgotPinChangePass(newPin);
                
                if (db.getU() > 0)
                {
                    this.dispose();
                }
            }
        }
        catch(NumberFormatException ex)
        {
            JOptionPane.showMessageDialog(null, "PIN harus berupa angka");
        }
        
    }//GEN-LAST:event_next2ActionPerformed

    private void backBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBActionPerformed
        // back to login form action button

        new LoginForm().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_backBActionPerformed

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
            java.util.logging.Logger.getLogger(ForgotPin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ForgotPin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ForgotPin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ForgotPin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ForgotPin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backB;
    private javax.swing.JLabel frameDrag;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField newPinField;
    private javax.swing.JLabel newPinText;
    private javax.swing.JButton next2;
    private javax.swing.JButton nextB;
    private javax.swing.JTextField noRekField;
    // End of variables declaration//GEN-END:variables
}
