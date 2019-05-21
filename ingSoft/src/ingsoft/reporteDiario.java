/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ingsoft;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mx06200a
 */
public class reporteDiario extends javax.swing.JFrame {

    /**
     * Creates new form reporteDiario
     */
    public reporteDiario() {
        initComponents();
        showReport();
    }
    
    General gen = new General();
    double totalHoy = 0.0, totalAyer = 0.0, posibleManana;
    
    public ArrayList<ReporteTiempo> reporteList(){
        ArrayList<ReporteTiempo> reporteList = new ArrayList<>();
        
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
                    + "Where f.Fecha = CONVERT(date, GETDATE()) ";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
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
                    + "Where f.Fecha = DATEADD(DAY, -1, CONVERT(date, GETDATE())) ";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
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
        DefaultTableModel model = (DefaultTableModel)tbldiario.getModel();
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
        row[4] = "Total de compras de hoy";
        row[5] = totalHoy;
        model.addRow(row);
        
        for (int x = 0; x < listAntes.size(); x++){
            totalAyer = totalAyer + listAntes.get(x).getPrecio();
        }
        
        lblcAyer.setText(Double.toString(totalAyer));
        posibleManana = (totalAyer + totalHoy) / 2;
        lblcMan.setText(Double.toString(posibleManana));
        
       
    }
    /*2
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tbldiario = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        lblcAyer = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblcMan = new javax.swing.JLabel();
        btnback = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tbldiario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "IDFactura", "Cliente", "Producto", "Fecha", "Cantidad", "Precio"
            }
        ));
        jScrollPane1.setViewportView(tbldiario);

        jLabel1.setText("Compras de ayer:");

        jLabel2.setText("Compras proyectadas para mañana:");

        btnback.setText("Regresar a menu");
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
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(55, 55, 55)
                        .addComponent(lblcAyer)
                        .addGap(457, 457, 457)
                        .addComponent(jLabel2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(79, 79, 79)
                                .addComponent(lblcMan)
                                .addGap(0, 222, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnback)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(lblcAyer)
                            .addComponent(jLabel2)
                            .addComponent(lblcMan)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(btnback)))
                .addContainerGap(55, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(reporteDiario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(reporteDiario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(reporteDiario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(reporteDiario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new reporteDiario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnback;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblcAyer;
    private javax.swing.JLabel lblcMan;
    private javax.swing.JTable tbldiario;
    // End of variables declaration//GEN-END:variables
}
