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
    
    public boolean acceso(String usuario, String contra){
        boolean flag = false;
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://ingsoft.database.windows.net:1433;database=Toyshido;user=aatr27@ingsoft;password=Borregos28);encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
            Connection con = DriverManager.getConnection(url);
            String sql = "Select * from toyshido_usuarios where Usuario=? and Contra=?";
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
            String url = "jdbc:sqlserver://ingsoft.database.windows.net:1433;database=Toyshido;user=aatr27@ingsoft;password=Borregos28);encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
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
    
    
}
