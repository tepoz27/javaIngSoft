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
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author mx06200a
 */
public class Facturas extends javax.swing.JFrame {

    /**
     * Creates new form Facturas
     */
    public Facturas() {
        initComponents();
    }
    
    General gen = new General();
    String idC;
    String idCompra;
    
    public ArrayList<Factura> facturaList() {
         ArrayList<Factura> facturaList  = new ArrayList<>();
         
         String fechaIni = cmbAIni.getSelectedItem().toString() + "-" + cmbMIni.getSelectedItem().toString() + "-" + cmbDIni.getSelectedItem().toString();
         String fechaFini = cmbAFini.getSelectedItem().toString() + "-" + cmbMFini.getSelectedItem().toString() + "-" + cmbDFini.getSelectedItem().toString();
         
         try{
              Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = gen.conection;
            Connection con = DriverManager.getConnection(url);
            String sql = "select IDFactura, c.Nombre + ' ' + c.Apellido as Nombre, ModoPago, Fecha from toyshido_facturas "
                    + "INNER JOIN toyshido_clientes as c ON c.IDCliente = toyshido_facturas.IDCliente "
                    + "Where Fecha Between ? and ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, fechaIni);
            pst.setString(2, fechaFini);
            ResultSet rs = pst.executeQuery();
            Factura factura;
            while (rs.next()){
                factura = new Factura(rs.getInt("IDFactura"), rs.getString("Nombre"), rs.getString("ModoPago"), rs.getString("Fecha"));
                facturaList.add(factura);
            }
            con.close();
         }catch (Exception e){
             JOptionPane.showMessageDialog(null, e);
         }
        return facturaList;
    }
    
    
    public ArrayList<Compra> compraList(){
        ArrayList<Compra> compraList = new ArrayList<>();
        
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = gen.conection;
            Connection con = DriverManager.getConnection(url);
            String sql = "Select IDCompra from toyshido_detallecompras Where IDFactura = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, idC);
            ResultSet rs = pst.executeQuery();
            //Compra compra;
            while (rs.next()){
                //compra = new Compra(rs.getString("Nombre"),rs.getString("Cant"));
                idCompra = rs.getString("IDCompra");
            }
            con.close();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = gen.conection;
            Connection con = DriverManager.getConnection(url);
            String sql = "select IDCompra, p.Nombre as Nombre, Cantidad, (Cantidad * p.Precio) as Precio from toyshido_compras "
                    + "Inner Join toyshido_producto as p "
                    + "ON p.IDproducto = toyshido_compras.IDProducto "
                    + "Where IDCompra = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, idCompra);
            ResultSet rs = pst.executeQuery();
            Compra compra;
            while (rs.next()){
                compra = new Compra(rs.getString("Nombre"),rs.getInt("Cantidad"), rs.getInt("IDCompra"), rs.getFloat("Precio"));
                //idCompra = rs.getString("IDCompra");
                compraList.add(compra);
            }
            con.close();
        }catch (Exception e){
            
        }
        return compraList;
    }
    
    public void showFacturas(){
        ArrayList<Factura> list = facturaList();
        DefaultTableModel model = (DefaultTableModel)tableFact.getModel();
        Object[] row = new Object[4];
        
        for(int i = 0; i < list.size(); i++ ){
            row[0] = list.get(i).getID();
            row[1] = list.get(i).getNombre();
            row[2] = list.get(i).getModoPago();
            row[3] = list.get(i).getFecha();
            model.addRow(row);
        }
    }
    
    public void showCompra(){
        ArrayList<Compra> list = compraList();
        DefaultTableModel model = (DefaultTableModel)tableFact.getModel();
        Object[] row = new Object[4];
        
        for(int i = 0; i < list.size(); i++){
            
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tableFact = new javax.swing.JTable();
        cmbDIni = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cmbMIni = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        cmbAIni = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cmbDFini = new javax.swing.JComboBox<>();
        cmbMFini = new javax.swing.JComboBox<>();
        cmbAFini = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        btnSearch = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCompra = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tableFact.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "IDFactura", "Cliente", "Fecha", "Pago"
            }
        ));
        tableFact.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableFactMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableFact);

        cmbDIni.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30" }));

        jLabel1.setText("Fecha inicial");

        jLabel2.setText("Dia");

        cmbMIni.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "07", "08", "09", "10", "11", "12" }));
        cmbMIni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbMIniActionPerformed(evt);
            }
        });

        jLabel3.setText("Mes");

        cmbAIni.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2017", "2018", "2019", "2020", "2021", "2022" }));

        jLabel4.setText("Año");

        jLabel5.setText("Fecha final");

        cmbDFini.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30" }));

        cmbMFini.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));

        cmbAFini.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2017", "2018", "2019", "2020", "2021", "2022" }));

        jLabel6.setText("Dia");

        jLabel7.setText("Mes");

        jLabel8.setText("Año");

        btnSearch.setText("Iniciar búsquedea");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        tblCompra.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "IDCompra", "Producto", "Cantidad", "Precio"
            }
        ));
        jScrollPane2.setViewportView(tblCompra);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(53, 53, 53)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbDIni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(52, 52, 52)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbMIni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(49, 49, 49)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cmbAIni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addComponent(jLabel5))
                            .addComponent(jLabel4))
                        .addGap(62, 62, 62)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbDFini, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbMFini, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cmbAFini, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnSearch))))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1154, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(jLabel3)
                        .addComponent(jLabel6)
                        .addComponent(jLabel7)
                        .addComponent(jLabel8)))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cmbDIni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbMIni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbAIni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(cmbDFini, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbMFini, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbAFini, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbMIniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbMIniActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbMIniActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        showFacturas();
    }//GEN-LAST:event_btnSearchActionPerformed

    private void tableFactMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableFactMouseClicked
        // TODO add your handling code here:
        int i = tableFact.getSelectedRow();
        TableModel model = tableFact.getModel();
        
        idC = model.getValueAt(i, 0).toString();
        
    }//GEN-LAST:event_tableFactMouseClicked

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
            java.util.logging.Logger.getLogger(Facturas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Facturas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Facturas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Facturas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Facturas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSearch;
    private javax.swing.JComboBox<String> cmbAFini;
    private javax.swing.JComboBox<String> cmbAIni;
    private javax.swing.JComboBox<String> cmbDFini;
    private javax.swing.JComboBox<String> cmbDIni;
    private javax.swing.JComboBox<String> cmbMFini;
    private javax.swing.JComboBox<String> cmbMIni;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tableFact;
    private javax.swing.JTable tblCompra;
    // End of variables declaration//GEN-END:variables
}
