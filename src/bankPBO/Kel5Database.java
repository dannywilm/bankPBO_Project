/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankPBO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author mxD
 */
public class Kel5Database {
    
    Connection cn;
    PreparedStatement ps;
    ResultSet rs;
    
    Connection cn2;
    PreparedStatement ps2;
    ResultSet rs2;
    
    String driverClass = "com.mysql.jdbc.Driver";
    String dbUrl = "jdbc:mysql://localhost:3306/kelompok5?zeroDateTimeBehavior=convertToNull";
    String dbUser = "root";
    String dbPass = "";
    
    private int noRekening, pin, uang;
    private String nama, alamat, email, qKeamanan;
    protected static int uangUser, uangKirim, uangTerima, uangPenerima, noRekPenerima, jumlahUangTransfer;
    protected static String namaPenerima = null, tanggalTransaksi, waktuTransaksi;
    
    private int noRekDaft;
    public int u = 0;
        
    public Kel5Database()
    {
        try
        {
            Class.forName(driverClass).newInstance();
            cn = DriverManager.getConnection(dbUrl, dbUser, dbPass);
            
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Can't Connected\nPlease Check your DB Connectivity");
            System.exit(1);
        }
        catch(ClassNotFoundException | InstantiationException | IllegalAccessException ex){}
    }
    
    protected void preIn(int noRek, int pin)
    {
        try
        {
            u = 0;
            ps = cn.prepareStatement("SELECT * FROM bank_pbo WHERE no_rekening = ? AND pin = ?");
            
            ps.setInt(1, noRek);
            ps.setInt(2, pin);
            
            rs = ps.executeQuery();
            
            if (rs.next())
            {
                new UserActivity().setVisible(true);
                u++;
            }
            else
            {
                JOptionPane.showMessageDialog(null, "no rekening / pin anda salah\natau anda belum terdaftar");
            }
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error : " + ex.getMessage());
        }
    }
    
    protected void preIn2 (int noRek, int pin)
    {
        try
        {
            ps = cn.prepareStatement("SELECT * FROM bank_pbo WHERE no_rekening = ? AND pin = ?");
            
            ps.setInt(1, noRek);
            ps.setInt(2, pin);
            
            rs = ps.executeQuery();
            
            if (rs.next())
            {
                this.noRekening = rs.getInt("no_rekening");
                this.nama = rs.getString("nama");
                this.pin = rs.getInt("pin");
                this.email = rs.getString("email");
                this.alamat = rs.getString("alamat");
                this.uang = rs.getInt("uang");
                
                uangUser = uang;
            }
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    
    protected void prePreIn(int pin, String nama, String email, String alamat, String qKeamanan)
    {
        u = 0;
        
        this.uang = 500000;
        noRekDaft = this.getNoRekDaft();
        
        try
        {
            ps = cn.prepareStatement("INSERT INTO bank_pbo VALUES" + "(?, ?, ?, ?, ?, ?, ?)");
            
            ps.setString(1, String.valueOf(noRekDaft));
            ps.setString(2, String.valueOf(pin));
            ps.setString(3, nama);
            ps.setString(4, email);
            ps.setString(5, alamat);
            ps.setInt(6, uang);
            ps.setString(7, qKeamanan);
            
            ps.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Registered Success");
            
            u++;
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Register Failed\nError : " + ex.getMessage());
        }
    }
    
    protected void forgotPin(int noRek)
    {
        try
        {
            ps = cn.prepareStatement("SELECT * FROM bank_pbo WHERE no_rekening = ?");
            this.noRekening = noRek;
            
            ps.setInt(1, noRek);
            
            rs = ps.executeQuery();
            
            if(rs.next())
            {
                JOptionPane.showMessageDialog(null, "Nomor Rekening anda ditemukan");
                u++;
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Maaf nomor rekening anda tidak terdaftar");
            }
        }
        catch(SQLException ex)
        {
            
        }
    }
    
    protected void qAnswer(String q)
    {
        u = 0;
        
        try
        {
            ps = cn.prepareStatement("SELECT q_keamanan FROM bank_pbo WHERE no_rekening = ? AND q_keamanan = ?");
            
            ps.setInt(1, getNoRekening());
            ps.setString(2, q);
            
            rs = ps.executeQuery();
            
            if(rs.next())
            {
                u++;
            }
        }
        catch (SQLException ex)
        {
            
        }
    }
    
    protected void forgotPinChangePass(int newPin)
    {
        u = 0;
        try
        {
            String noRek = String.valueOf(getNoRekening());
            ps = cn.prepareStatement("UPDATE bank_pbo SET pin = ? WHERE no_rekening = ?");
            
            ps.setString(1, String.valueOf(newPin));
            ps.setString(2, noRek);
            
            ps.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Password anda berhasil diubah");
            new ForgotPin().setVisible(false);
            new LoginForm().setVisible(true); 
            
            u++;
        }
        catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error : " + ex.getMessage());
        }
    }
    
    protected void forgotNoRek(String email)
    { 
        u = 0;
        
        try
        {
            ps = cn.prepareStatement("SELECT no_rekening FROM bank_pbo WHERE email = ?");
            ps.setString(1, email);
            
            rs = ps.executeQuery();
            
            if(rs.next())
            {
                u++;
                JOptionPane.showMessageDialog(null, "Email ditemukan");
                this.noRekening = Integer.parseInt(rs.getString("no_rekening"));
                System.out.println(getNoRekening());
            }
            else
            {
                JOptionPane.showMessageDialog(null, "E-Mail tidak ditemukan");
            }
        }
        catch(SQLException ex)
        {
            
        }
    }
    
    protected void changeProfile(String nama, int pin, String email, String alamat)
    {
        try
        {
            ps = cn.prepareStatement("UPDATE bank_pbo SET nama = ?, pin = ?, email = ?, alamat = ? WHERE no_rekening = " + noRekening);
            
            ps.setString(1, nama);
            ps.setString(2, String.valueOf(pin));
            ps.setString(3, email);
            ps.setString(4, alamat);
            
            ps.executeUpdate();
            
            System.out.println(nama + "\n" + pin);
            
            this.nama = nama;
            this.pin = pin;
            this.email = email;
            this.alamat = alamat;
            
            JOptionPane.showMessageDialog(null, "Data berhasil diubah :)");
        }
        catch(SQLException ex)
        {
             JOptionPane.showMessageDialog(null, "Error : " + ex.getMessage());
        }
    }
    
    protected void transfer(int noRek, int uangTransfer)
    {
        u = 0;
        
        try
        {
            ps = cn.prepareStatement("SELECT uang, nama FROM bank_pbo WHERE no_rekening = ?");
            ps.setInt(1, noRek);
            
            rs = ps.executeQuery();
            
            if(rs.next())
            {
                noRekPenerima = noRek;
                uangPenerima = rs.getInt("uang");
                namaPenerima = rs.getString("nama");
                u++;
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Maaf, rekening yang anda tuju salah");
                System.out.println("Gagal kirim uang penerima");
            }
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error : " + ex.getMessage());
        }
        
        try
        {
            ps = cn.prepareStatement("SELECT uang FROM bank_pbo WHERE no_rekening = ?");
            ps.setString(1, String.valueOf(LoginForm.noRek));
            
            rs = ps.executeQuery();
            
            if(rs.next())
            {
                uangUser = rs.getInt("uang");
                u++;
            }
            else
            {
                System.out.println("Gagal mendapatkan data anda");
            }
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error : " + ex.getMessage());
        }
        
        if (uangTransfer > uangUser)
        {
            JOptionPane.showMessageDialog(null, "Maaf, saldo anda tidak cukup T_T");
        }
        else
        {
            u++;
        }
        
        if (u == 3)
        {
            uangKirim = uangTransfer;
            uangPenerima += uangTransfer;
            uangUser -= uangTransfer;
        }
        
        System.out.println(uangPenerima + "\n" + uangUser + "\n" + namaPenerima);
    }
    
    protected void kirim()
    {
        u = 0;
        try
        {
            ps = cn.prepareStatement("UPDATE bank_pbo SET uang = ? WHERE no_rekening = ?");
            
            ps.setInt(1, uangPenerima);
            ps.setString(2, String.valueOf(noRekPenerima));
            
            ps.executeUpdate();
            
            u++;
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error : " + ex.getMessage());
        }
        
        try
        {
            ps = cn.prepareStatement("UPDATE bank_pbo SET uang = ? WHERE no_rekening = ?");
            
            ps.setInt(1, uangUser);
            ps.setString(2, String.valueOf(LoginForm.noRek));
            
            ps.executeUpdate();
            
            u++;
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error : " + ex.getMessage());
        }
    }
    
    protected void inputTransaksi()
    {
        try
        {
            ps = cn.prepareStatement("INSERT INTO transaksi VALUES " + "(?, ?, ?, ?, ?, ?)");
            
            ps.setString(1, String.valueOf(LoginForm.noRek));
            ps.setInt(2, uangKirim);
            ps.setString(3, String.valueOf(noRekPenerima));
            ps.setString(4, namaPenerima);
            ps.setString(5, TransferForm1.tanggalTransfer);
            ps.setString(6, TransferForm1.waktu);
            
            ps.executeUpdate();
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error : " + ex.getMessage());
        }
    }
    
    protected void inputPembayaran(String noRek, String nom, int uang, String perihal)
    {   
        u = 0;
        
        try
        {
            ps = cn.prepareStatement("SELECT uang FROM bank_pbo WHERE no_rekening = ?");
            ps.setString(1, String.valueOf(LoginForm.noRek));
            
            rs = ps.executeQuery();
            
            if(rs.next())
            {
                uangUser = rs.getInt("uang");
            }
            else
            {
                System.out.println("OH NO ADA ERROR");
                
            }
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error : " + ex.getMessage());
        }
        
        try
        {
            ps = cn.prepareStatement("INSERT INTO pembayaran VALUES " + "(?, ?, ?, ?, ?, ?)");
            ps.setString(1, noRek);
            ps.setString(2, nom);
            ps.setInt(3, uang);
            ps.setString(4, perihal);
            ps.setString(5, TransferForm1.tanggalTransfer);
            ps.setString(6, TransferForm1.waktu);
            
            ps.executeUpdate();
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error : " + ex.getMessage());
        }
        
        if(uangUser < uang)
        {
            JOptionPane.showMessageDialog(null, "Maaf uang saldo anda kurang untuk melakukan pembelian");
        }
        else
        {
            uangUser -= uang;
            u++;
        }
        
        if (u > 0 && nom.length() >= 11 && nom.length() <= 12)
        {
            try
            {
                ps = cn.prepareStatement("UPDATE bank_pbo SET uang = ? WHERE no_rekening = ?");
                ps.setInt(1, uangUser);
                ps.setString(2, String.valueOf(LoginForm.noRek));

                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "Pembelian berhasil");
            }
            catch(SQLException ex)
            {
                JOptionPane.showMessageDialog(null, "Error : " + ex.getMessage());
            }
        }
    }
    
    public int getNoRekening() {
        return noRekening;
    }

    public int getPin() {
        return pin;
    }
    
    public int getUang() {
        return uang;
    }

    public String getNama() {
        return nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getqKeamanan() {
        return qKeamanan;
    }
    
    public int getNoRekDaft() {
        try
        {
            ps = cn.prepareStatement("SELECT no_rekening FROM bank_pbo");
            
            rs = ps.executeQuery();
            
            while (rs.next())
            {
                this.noRekDaft = Integer.parseInt(rs.getString("no_rekening"));
            }
            
            this.noRekDaft++;
        }
        catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error : " + ex.getMessage());
        }
        
        return noRekDaft;
    }

    public int getU() {
        return u;
    }

    /*void transfer(String tanggalTransfer, String waktu) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    } */
}
