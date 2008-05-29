/*
 * PanelEnvios.java
 *
 * Created on 22 de mayo de 2008, 22:26
 */

package Paneles;

import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import controladores.ControladorPanelSolDis;
import GUI.MenuPrincipal;

/**
 *
 * @author  Administrador
 */
public class PanelEnvios extends javax.swing.JPanel {

    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MenuPrincipal ref;
    
    /** Creates new form PanelEnvios */
    public PanelEnvios(MenuPrincipal menu) {
        initComponents();
        this.ref = menu;
        
        //Esto se reemplasa segun lo que elige en el combo
        DefaultMutableTreeNode abuelo = new DefaultMutableTreeNode("Solicitudes");
        DefaultTreeModel modelo = new DefaultTreeModel(abuelo);
        treeArticulos = new JTree(modelo);
        DefaultMutableTreeNode padre = new DefaultMutableTreeNode("Solicitud 1");
        DefaultMutableTreeNode padre2 = new DefaultMutableTreeNode("Solicitud 2");
        modelo.insertNodeInto(padre,abuelo,0);
        modelo.insertNodeInto(padre2,abuelo,1);
        jScrollPane1.setViewportView(treeArticulos);
        
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
        comboBoxTiendas = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        treeArticulos = new javax.swing.JTree();
        buttonCargar = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablePendientes = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        buttonEnviarTienda = new javax.swing.JButton();

        jLabel1.setText("Tienda");

        comboBoxTiendas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tienda 1", "Tienda 2", "Tienda 3" }));
        comboBoxTiendas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxTiendasActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(treeArticulos);

        buttonCargar.setText("Cargar");
        buttonCargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCargarActionPerformed(evt);
            }
        });

        tablePendientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo Art.", "Descripcion", "Cantidad", "Stock"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tablePendientes);

        jLabel2.setText("Articulos Pendientes");

        buttonEnviarTienda.setText("Enviar Tienda");
        buttonEnviarTienda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEnviarTiendaActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .addContainerGap()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 149, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(layout.createSequentialGroup()
                                .add(jLabel1)
                                .add(30, 30, 30)
                                .add(comboBoxTiendas, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(layout.createSequentialGroup()
                                .add(170, 170, 170)
                                .add(jScrollPane3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 335, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(layout.createSequentialGroup()
                                .add(280, 280, 280)
                                .add(jLabel2))))
                    .add(layout.createSequentialGroup()
                        .add(49, 49, 49)
                        .add(buttonCargar)
                        .add(323, 323, 323)
                        .add(buttonEnviarTienda)))
                .addContainerGap(376, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(67, 67, 67)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel1)
                            .add(comboBoxTiendas, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel2))
                        .add(44, 44, 44)
                        .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 147, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(51, 51, 51))
                    .add(layout.createSequentialGroup()
                        .add(jScrollPane3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 181, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(18, 18, 18)))
                .add(15, 15, 15)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(buttonCargar)
                    .add(buttonEnviarTienda))
                .addContainerGap(83, Short.MAX_VALUE))
        );
    }// </editor-fold>                        

private void buttonCargarActionPerformed(java.awt.event.ActionEvent evt) {                                             
// Cargar solicitud en tablas
    
 
}                                            

private void comboBoxTiendasActionPerformed(java.awt.event.ActionEvent evt) {                                                
  //Combo de seleccion de tienda
}                                               

private void buttonEnviarFabricaActionPerformed(java.awt.event.ActionEvent evt) {                                                    
// TODO add your handling code here:
}                                                   

private void buttonEnviarTiendaActionPerformed(java.awt.event.ActionEvent evt) {                                                   
// TODO add your handling code here:
}                                                  


    // Variables declaration - do not modify                     
    private javax.swing.JButton buttonCargar;
    private javax.swing.JButton buttonEnviarFabrica;
    private javax.swing.JButton buttonEnviarTienda;
    private javax.swing.JComboBox comboBoxTiendas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tableEnviar;
    private javax.swing.JTable tablePendientes;
    private javax.swing.JTree treeArticulos;
    // End of variables declaration                   

}
