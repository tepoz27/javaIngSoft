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
import javax.swing.JOptionPane;

/**
 *
 * @author mx06200a
 */
public class IngSoft {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
//        System.out.println("prueba de correr");
//        try{
//            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//            String url = "jdbc:sqlserver://ingsoft.database.windows.net:1433;database=Toyshido;user=aatr27@ingsoft;password=Borregos28);encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
//            Connection con = DriverManager.getConnection(url);
//            String sql = "Select * from toyshido_usuarios where Usuario=? and Contra=?";
//            PreparedStatement pst = con.prepareStatement(sql);
//            pst.setString(1, "angel.tepoz");
//            pst.setString(2, "borregos28");
//            ResultSet rs = pst.executeQuery();
//            if(rs.next()){
//                JOptionPane.showMessageDialog(null, "Usuario y contraseña correctos !");
//                System.out.println("Si accedió");
//            }else {
//                JOptionPane.showMessageDialog(null, "Usuario y contraseña incorrectos !");
//                System.out.println("No accedió");
//                //txtuser.setText("");
//                //txtpass.setText("");
//            }
//            
//            con.close();
//        }catch (Exception e){
//            System.out.println(e);
//        }
    }
    
}
