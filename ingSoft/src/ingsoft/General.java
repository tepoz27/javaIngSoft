/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ingsoft;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
/**
 *
 * @author mx06200a
 */
public class General {
    
    public String conection = "jdbc:sqlserver://127.0.0.1:1433;database=toyshido;user=sa;password=Borregos28MAT32728#;encrypt=false;trustServerCertificate=false;loginTimeout=30;";
    
    public boolean acceso(String usuario, String contra){
        boolean flag = false;
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = conection;
            Connection con = DriverManager.getConnection(url);
            String sql = "Select * from toyshido_usuario where Usuario=? and Contrasena=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, usuario);
            pst.setString(2, contra);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                JOptionPane.showMessageDialog(null, "Usuario y contraseña correctos !");
                flag = true;
            }else {
                JOptionPane.showMessageDialog(null, "Usuario y contraseña incorrectos !");
                
            }
            
            con.close();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        return flag;
    }
    
    public boolean insert (String sqlInsertStatement, String ref){
        boolean flag = false;
        
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = conection;
            Connection con = DriverManager.getConnection(url);
            Statement stat = con.createStatement();
            stat.execute(sqlInsertStatement);
            con.close();
            JOptionPane.showMessageDialog(null, "La creación de su registro en: " + ref + " ha sido exitosa!");
            flag = true;
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        
        return flag;
    }
    
    public int generateCompraID(){
        int n = 0;
        
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = conection;
            Connection con = DriverManager.getConnection(url);
            String sql = "Select TOP 1 *  from toyshido_compras ORDER BY IDCompra DESC";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()){
                n = rs.getInt(1);
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        
        return n;
    }
    
    public int getIDLast(){
        int n = 0;
        
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = conection;
            Connection con = DriverManager.getConnection(url);
            String sql = "SELECT TOP 1 * FROM toyshido_facturas ORDER BY IDFactura DESC";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()){
                n = rs.getInt(1);
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        
        return n;
    }
}
