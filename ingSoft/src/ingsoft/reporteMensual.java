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

/**
 *
 * @author mx06200a
 */
public class reporteMensual extends javax.swing.JFrame {

    /**
     * Creates new form reporteMensual
     */
    public reporteMensual() {
        initComponents();
    }
    
    General gen = new General();
    double totalHoy = 0.0, totalAyer = 0.0, posibleManana;
    
    public ArrayList<ReporteTiempo> reporteList(){
        ArrayList<ReporteTiempo> reporteList = new ArrayList<>();
        String busquedaI = cmbAno.getSelectedItem().toString() + "-" + cmbMes.getSelectedItem().toString() + "-01";
        String busquedaF = cmbAno.getSelectedItem().toString() + "-" + cmbMes.getSelectedItem().toString() + "-30";
        
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
        int mesAnt = Integer.parseInt(cmbMes.getSelectedItem().toString()), Ant;
        int anoAnt = Integer.parseInt(cmbAno.getSelectedItem().toString()), aAnt;
        
        if (mesAnt == 01){
            Ant = 12;
            aAnt = anoAnt -1;
        }else{
            Ant = mesAnt - 1;
            aAnt = anoAnt;
        }
        
        String busquedaI = Integer.toString(aAnt) + "-" + Integer.toString(Ant) + "-01";
        String busquedaF = Integer.toString(aAnt) + "-" + Integer.toString(Ant) + "-30";
        
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
        DefaultTableModel model = (DefaultTableModel)tblmensual.getModel();
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
        tblmensual = new javax.swing.JTable();
        lblini = new javax.swing.JLabel();
        cmbMes = new javax.swing.JComboBox<>();
        cmbAno = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        btnAnalizar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        lblcAyer = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblcMan = new javax.swing.JLabel();
        btnback = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblmensual.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "IDFactura", "Cliente", "Producto", "Fecha", "Cantidad", "Precio"
            }
        ));
        jScrollPane1.setViewportView(tblmensual);

        lblini.setText("Mes a evaluar: ");

        cmbMes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));

        cmbAno.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2017", "2018", "2019", "2020", "2021", "2022", "2023" }));

        jLabel1.setText("Año a evaluar: ");

        btnAnalizar.setText("Analizar");
        btnAnalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnalizarActionPerformed(evt);
            }
        });

        jLabel2.setText("Ventas realizadas el mes pasado: ");

        jLabel3.setText("Ventas previstas para el próximo mes: ");

        btnback.setText("Regresar al menu");
        btnback.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(32, 32, 32)
                        .addComponent(lblcAyer)
                        .addGap(425, 425, 425)
                        .addComponent(jLabel3)
                        .addGap(30, 30, 30)
                        .addComponent(lblcMan)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblini)
                                .addGap(57, 57, 57)
                                .addComponent(cmbMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(111, 111, 111)
                                .addComponent(jLabel1)
                                .addGap(105, 105, 105)
                                .addComponent(cmbAno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 215, Short.MAX_VALUE)
                                .addComponent(btnAnalizar)
                                .addGap(186, 186, 186)
                                .addComponent(btnback)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblini)
                    .addComponent(cmbMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbAno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(btnAnalizar)
                    .addComponent(btnback))
                .addGap(52, 52, 52)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 504, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblcAyer)
                    .addComponent(jLabel3)
                    .addComponent(lblcMan))
                .addGap(32, 32, 32))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAnalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnalizarActionPerformed
        // TODO add your handling code here:
        showReport();
    }//GEN-LAST:event_btnAnalizarActionPerformed

    private void btnbackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbackActionPerformed
        // TODO add your handling code here:
        new menuReport().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnbackActionPerformed

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
            java.util.logging.Logger.getLogger(reporteMensual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(reporteMensual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(reporteMensual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(reporteMensual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new reporteMensual().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnalizar;
    private javax.swing.JButton btnback;
    private javax.swing.JComboBox<String> cmbAno;
    private javax.swing.JComboBox<String> cmbMes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblcAyer;
    private javax.swing.JLabel lblcMan;
    private javax.swing.JLabel lblini;
    private javax.swing.JTable tblmensual;
    // End of variables declaration//GEN-END:variables
}
