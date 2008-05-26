/*
 * PanelGenSolFab.java
 *
 * Created on 22 de mayo de 2008, 22:38
 */

package Paneles;

import controladores.ControladorPanelEnvios;
import GUI.MenuPrincipal;

/**
 *
 * @author  Administrador
 */
public class PanelGenSolFab extends javax.swing.JPanel {

    private ControladorPanelEnvios controlador;
    private MenuPrincipal ref;
    /** Creates new form PanelGenSolFab */
    public PanelGenSolFab(MenuPrincipal r) {
        initComponents();
        this.ref = r;
        this.controlador = new ControladorPanelEnvios(ref.getVistaPadre().getModelo(),ref.getVistaPadre());
    }
    
    public void update()
    {

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        buttonCargarPendientes = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        buttonEnviar = new javax.swing.JButton();
        buttonVistaPrevia = new javax.swing.JButton();

        jLabel1.setText("Solicitud de articulos a fabricar");

        buttonCargarPendientes.setText("Cargar Pendientes");
        buttonCargarPendientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCargarPendientesActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Descripcion", "Stock Actual", "Stock Solicitado", "Stock Minimo a Pedir", "Cantidad a Fabricar"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTable1);

        buttonEnviar.setText("Enviar");
        buttonEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEnviarActionPerformed(evt);
            }
        });

        buttonVistaPrevia.setText("Vista Previa");
        buttonVistaPrevia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonVistaPreviaActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(47, 47, 47)
                        .add(buttonEnviar)
                        .add(88, 88, 88)
                        .add(buttonVistaPrevia))
                    .add(layout.createSequentialGroup()
                        .add(31, 31, 31)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 657, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(layout.createSequentialGroup()
                                .add(jLabel1)
                                .add(108, 108, 108)
                                .add(buttonCargarPendientes)))))
                .addContainerGap(351, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(35, 35, 35)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel1)
                    .add(buttonCargarPendientes))
                .add(36, 36, 36)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 225, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(26, 26, 26)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(buttonEnviar)
                    .add(buttonVistaPrevia))
                .addContainerGap(82, Short.MAX_VALUE))
        );
    }// </editor-fold>                        

private void buttonEnviarActionPerformed(java.awt.event.ActionEvent evt) {                                             
// TODO add your handling code here:
}                                            

private void buttonCargarPendientesActionPerformed(java.awt.event.ActionEvent evt) {                                                       
// TODO add your handling code here:
}                                                      

private void buttonVistaPreviaActionPerformed(java.awt.event.ActionEvent evt) {                                                  
// TODO add your handling code here:
}                                                 


    // Variables declaration - do not modify                     
    private javax.swing.JButton buttonCargarPendientes;
    private javax.swing.JButton buttonEnviar;
    private javax.swing.JButton buttonVistaPrevia;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration                   

}
