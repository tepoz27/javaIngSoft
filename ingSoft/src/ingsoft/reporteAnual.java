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
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mx06200a
 */
public class reporteAnual extends javax.swing.JFrame {

    /**
     * Creates new form reporteAnual
     */
    public reporteAnual() {
        initComponents();
        btnLimpiar.setEnabled(false);
    }
    
    General gen = new General();
    double totalHoy = 0.0, totalAyer = 0.0, posibleManana;
    
    public ArrayList<ReporteTiempo> reporteList(){
        ArrayList<ReporteTiempo> reporteList = new ArrayList<>();
        String busquedaI = cmbAno.getSelectedItem().toString() + "-01-01";
        String busquedaF = cmbAno.getSelectedItem().toString() + "-12-31";
        
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = gen.conection;
            Connection con = DriverManager.getConnection(url);
            String sql = "select ref.IDFactura, cli.Nombre + ' ' + cli.Apellido as Nombre, p.Nombre as Producto, f.Fecha, c.Cantidad, (c.Cantidad * p.Precio) as Precio "
                    + "from toyshido_detallecompras as ref "
                    + "Inner Join toyshido_compras as c "
                    + "On c.IDCompra = ref.IDCompra "
                    + "Inner Join toyshido_facturas as f "
                    + "On f.IDFactura = ref.IDFactura "
                    + "Inner Join toyshido_clientes as cli "
                    + "On cli.IDCliente = f.IDCliente "
                    + "Inner Join toyshido_producto as p "
                    + "On c.IDProducto = c.IDProducto "
                    + "Where f.Fecha Between ? and ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, busquedaI);
            pst.setString(2, busquedaF);
            ResultSet rs = pst.executeQuery();
            ReporteTiempo reportetiempo;
            while (rs.next()){
                reportetiempo = new ReporteTiempo(rs.getInt("IDFactura"), rs.getString("Nombre"), rs.getString("Producto"), rs.getString("Fecha"), rs.getInt("Cantidad"), rs.getFloat("Precio"));
                reporteList.add(reportetiempo);
            }
            con.close();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        
        return reporteList;
    }
    
    public ArrayList<ReporteAntes> antesList(){
        ArrayList<ReporteAntes> antesList = new ArrayList<>();
        //int mesAnt = Integer.parseInt(cmbMes.getSelectedItem().toString()), Ant;
        int anoAnt = Integer.parseInt(cmbAno.getSelectedItem().toString()) - 1;
        
        
        String busquedaI = Integer.toString(anoAnt) + "-01-01";
        String busquedaF = Integer.toString(anoAnt) + "-12-31";
        
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = gen.conection;
            Connection con = DriverManager.getConnection(url);
            String sql = "select (c.Cantidad * p.Precio) as Precio "
                    + "from toyshido_detallecompras as ref "
                    + "Inner Join toyshido_compras as c "
                    + "On c.IDCompra = ref.IDCompra "
                    + "Inner Join toyshido_facturas as f "
                    + "On f.IDFactura = ref.IDFactura "
                    + "Inner Join toyshido_clientes as cli "
                    + "On cli.IDCliente = f.IDCliente "
                    + "Inner Join toyshido_producto as p "
                    + "On c.IDProducto = c.IDProducto "
                    + "Where f.Fecha Between ? and ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, busquedaI);
            pst.setString(2, busquedaF);
            ResultSet rs = pst.executeQuery();
            ReporteAntes reporteantes;
            while (rs.next()){
                reporteantes = new ReporteAntes(rs.getDouble("Precio"));
                antesList.add(reporteantes);
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        
        return antesList;
    }
    
    public void showReport(){
        ArrayList<ReporteTiempo> list = reporteList();
        ArrayList<ReporteAntes> listAntes = antesList();
        DefaultTableModel model = (DefaultTableModel)tblanual.getModel();
        Object[] row = new Object[6];
        
        for (int i = 0; i < list.size(); i++){
            row[0] = list.get(i).getID();
            row[1] = list.get(i).getNombre();
            row[2] = list.get(i).getProd();
            row[3] = list.get(i).getFecha();
            row[4] = list.get(i).getCant();
            row[5] = list.get(i).getPrecio();
            model.addRow(row);
            totalHoy = totalHoy + list.get(i).getPrecio();
        }
        
        row[0] = "";
        row[1] = "";
        row[2] = "";
        row[3] = "";
        row[4] = "Total de compras del mes";
        row[5] = totalHoy;
        model.addRow(row);
        
        for (int x = 0; x < listAntes.size(); x++){
            totalAyer = totalAyer + listAntes.get(x).getPrecio();
        }
        
        lblcAyer.setText(Double.toString(totalAyer));
        posibleManana = (totalAyer + totalHoy) / 2;
        lblcMan.setText(Double.toString(posibleManana));
        
        list.clear();
        listAntes.clear();
        totalHoy = 0.0;
        totalAyer = 0.0;
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
        tblanual = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        cmbAno = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        lblcAyer = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblcMan = new javax.swing.JLabel();
        btnAnalizar = new javax.swing.JButton();
        btnback = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblanual.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "IDFactura", "Nombre", "Producto", "Fecha", "Cantidad", "Precio"
            }
        ));
        jScrollPane1.setViewportView(tblanual);

        jLabel1.setText("Seleccione el año a evaluar: ");

        cmbAno.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2017", "2018", "2019", "2020", "2021", "2022" }));

        jLabel2.setText("Ventas del año anterior: ");

        jLabel3.setText("Posibles ventas para el próximo año: ");

        btnAnalizar.setText("Analizar");
        btnAnalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnalizarActionPerformed(evt);
            }
        });

        btnback.setText("Regresar al menu");
        btnback.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbackActionPerformed(evt);
            }
        });

        btnLimpiar.setText("Limpiar búsqueda");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(47, 47, 47)
                        .addComponent(lblcAyer)
                        .addGap(481, 481, 481)
                        .addComponent(jLabel3)
                        .addGap(39, 39, 39)
                        .addComponent(lblcMan)
                        .addGap(0, 263, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(58, 58, 58)
                        .addComponent(cmbAno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(414, 414, 414)
                        .addComponent(btnAnalizar)
                        .addGap(65, 65, 65)
                        .addComponent(btnLimpiar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnback)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cmbAno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAnalizar)
                    .addComponent(btnback)
                    .addComponent(btnLimpiar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblcAyer)
                    .addComponent(jLabel3)
                    .addComponent(lblcMan))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAnalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnalizarActionPerformed
        // TODO add your handling code here:
        showReport();
        btnAnalizar.setEnabled(false);
        btnLimpiar.setEnabled(true);
    }//GEN-LAST:event_btnAnalizarActionPerformed

    private void btnbackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbackActionPerformed
        // TODO add your handling code here:
        new menuReport().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnbackActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel)tblanual.getModel();
        model.setRowCount(0);
        lblcAyer.setText("");
        lblcMan.setText("");
        btnAnalizar.setEnabled(true);
        btnLimpiar.setEnabled(false);
    }//GEN-LAST:event_btnLimpiarActionPerformed

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
            java.util.logging.Logger.getLogger(reporteAnual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(reporteAnual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(reporteAnual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(reporteAnual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new reporteAnual().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnalizar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnback;
    private javax.swing.JComboBox<String> cmbAno;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblcAyer;
    private javax.swing.JLabel lblcMan;
    private javax.swing.JTable tblanual;
    // End of variables declaration//GEN-END:variables
}
