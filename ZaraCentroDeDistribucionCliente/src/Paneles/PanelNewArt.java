/*
 * PanelNewArt.java
 *
 * Created on 22 de mayo de 2008, 22:44
 */

package Paneles;

import javax.swing.JOptionPane;

import Varios.Constantes;

import GUI.FileChooser;
import GUI.MenuPrincipal;

/**
 *
 * @author  Administrador
 */
public class PanelNewArt extends javax.swing.JPanel {

    /** Creates new form PanelNewArt */
    MenuPrincipal ref;
    
    
    public PanelNewArt(MenuPrincipal m) {
        initComponents();
        this.ref = m;
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

        jScrollPane1 = new javax.swing.JScrollPane();
        tableRopa = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableHogar = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        buttonCargarXML = new javax.swing.JButton();
        buttonGuardar = new javax.swing.JButton();

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        tableRopa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Referencia", "Descripcion", "Precio", "Cantidad", "Seccion", "Linea", "Color", "Talle", "Origen"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Double.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tableRopa.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane1.setViewportView(tableRopa);

        tableHogar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Referencia", "Descripcion", "Precio", "Cantidad", "Seccion", "Linea", "Color", "Detalles", "Composicion", "Categoria", "Medidas"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Double.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tableHogar.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane2.setViewportView(tableHogar);

        jLabel1.setText("Articulos Ropa");

        jLabel2.setText("Articulos Hogar");

        buttonCargarXML.setText("Cargar XML");
        buttonCargarXML.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCargarXMLActionPerformed(evt);
            }
        });

        buttonGuardar.setText("Guardar");
        buttonGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGuardarActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(26, 26, 26)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(layout.createSequentialGroup()
                                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 286, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(57, 57, 57))
                            .add(layout.createSequentialGroup()
                                .add(jLabel1)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)))
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel2)
                            .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 306, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                    .add(layout.createSequentialGroup()
                        .add(167, 167, 167)
                        .add(buttonCargarXML)
                        .add(136, 136, 136)
                        .add(buttonGuardar)))
                .addContainerGap(54, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(56, 56, 56)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel2)
                    .add(jLabel1))
                .add(50, 50, 50)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(jScrollPane2, 0, 0, Short.MAX_VALUE)
                    .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE))
                .add(49, 49, 49)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(buttonCargarXML)
                    .add(buttonGuardar))
                .add(93, 93, 93))
        );
    }// </editor-fold>                        

private void buttonCargarXMLActionPerformed(java.awt.event.ActionEvent evt) {                                                
// TODO add your handling code here:
        chooser = new FileChooser(ref,true,ref.getDefaltXmlPath());
        String a = chooser.getPath();
        String b = chooser.getFile();
        
        if(a.equals(""))JOptionPane.showMessageDialog(this,"Debe ingresar la ubicacion del archivo XML.\n", Constantes.APPLICATION_NAME, JOptionPane.ERROR_MESSAGE);
        else
        {
            //Cargar los table
            ref.getJTextArea1().append("Archivo Cargado\n");
            String url = chooser.getPath();
           // ((ControladorMain)ref.getVistaPadre().getControlador()).doMostrarSolicitud(url);
        }
}                                               

private void buttonGuardarActionPerformed(java.awt.event.ActionEvent evt) {                                              
// TODO add your handling code here:
}                                             


    // Variables declaration - do not modify                     
    private javax.swing.JButton buttonCargarXML;
    private javax.swing.JButton buttonGuardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tableHogar;
    private javax.swing.JTable tableRopa;
    private FileChooser chooser;
    // End of variables declaration                   

}
