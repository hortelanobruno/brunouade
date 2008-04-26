package Paneles;

import BusinessLogic.ArticuloHogar;
import BusinessLogic.ArticuloRopa;
import BusinessLogic.ControladorMain;
import BusinessLogic.BusinessDelegate;
import BusinessLogic.SolicitudDistribucion;
import GUI.Dialogo3Opciones;
import GUI.FileChooser;
import GUI.MenuPrincipal;

import java.awt.Color;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import Varios.Constantes;

public class PanelSolDist extends javax.swing.JPanel  
{
    private static final long serialVersionUID = -7822083574555343999L;
    DefaultTableModel dtm;
    private MenuPrincipal ref;
    
    public PanelSolDist(MenuPrincipal mn) 
    {
        initComponents();
        this.ref = mn;
        dtm = new DefaultTableModel();
        
        jTable1.getModel().addTableModelListener(new TableModelListener() 
        {
            public void tableChanged(TableModelEvent e)
            {
               if(e.getColumn() > -1) validateTable();
            }
        }); 
    }

    public void fillSDTable(SolicitudDistribucion soldis)
    {
        dtm = (DefaultTableModel) jTable1.getModel();
        
        Vector<ArticuloRopa> articulosropa = soldis.getArticulosropa();
        Vector<ArticuloHogar> articuloshogar = soldis.getArticuloshogar();

        int codigo;
        int cantidad;

        if (articulosropa != null)
        {
            for (int i = 0; i < articulosropa.size(); i++)
            {
                codigo = articulosropa.elementAt(i).getCodigo();
                cantidad = articulosropa.elementAt(i).getStock();
                int stock = Integer.valueOf(((BusinessDelegate) (ref.getVistaPadre().getModelo())).getStock(codigo));
                String descripcion = String.valueOf(((BusinessDelegate) (ref.getVistaPadre().getModelo())).getDescripcion(codigo));
                int numero = soldis.getNumero();

                dtm.addRow(new Object[]{numero, codigo, descripcion, cantidad, stock, 0});
                //System.out.println("cargo" + i);
            }
        }
        
        if (articuloshogar != null)
        {
            for (int i = 0; i < articuloshogar.size(); i++)
            {
                codigo = articuloshogar.elementAt(i).getCodigo();
                cantidad = articuloshogar.elementAt(i).getStock();
                int stock = Integer.valueOf(((BusinessDelegate) (ref.getVistaPadre().getModelo())).getStock(codigo));
                String descripcion = String.valueOf(((BusinessDelegate) (ref.getVistaPadre().getModelo())).getDescripcion(codigo));
                int numero = soldis.getNumero();

                dtm.addRow(new Object[]{numero, codigo, descripcion, cantidad, stock, 0});
            }
        }

        this.validateTable();
    }
    
    private void validateTable()
    {
        boolean grabar = true;
        int cantCeros = 0;
        
        for (int i = 0; i < jTable1.getModel().getRowCount(); i++)
        {
            int valor  = Integer.parseInt(jTable1.getModel().getValueAt(i, 5).toString());
            int pedido = Integer.parseInt(jTable1.getModel().getValueAt(i, 3).toString());
            
            if (valor == 0) cantCeros++;
            if ( (valor < 0) || (valor > pedido) )
            {
                grabar = false;
                if (valor > pedido) JOptionPane.showMessageDialog(this,"El valor ingresado es mayor al pedido.",Constantes.APPLICATION_NAME,JOptionPane.ERROR_MESSAGE);
                break;
            }
        }

        if ((grabar) && (cantCeros < jTable1.getModel().getRowCount()))
        {
            jButton1.setEnabled(true);
            jLabel2.setText("OK");
            jLabel2.setForeground(Color.GREEN);
        }
        else
        {
            jButton1.setEnabled(false);
            jLabel2.setForeground(Color.RED);
            if(!grabar) jLabel2.setText("Hay errores en los valores ingresados");
            else jLabel2.setText("Debe ingresar valores.");
        }
    }

    public void update()
    {
    	jTable1.setModel(dtm);
        //System.out.println("llego");
        //algo asi que lea de la base
        //String.valueOf(((ProxyModeloAdmProductos)(this.getVistaPadre().getModelo())).getStock((String)(comboProductos.getSelectedItem()) ))
        ref.invalidate();
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        jButton1.setText("Guardar Pedido");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                    "Numero Solicitud", "Tienda","Cod. Art", "Descripcion", "Cantidad Pedida", "Stock Actual", "Stock seleccionado","Cantidad a fabricar"
                }
            ) {
                Class[] types = new Class [] {
                    java.lang.Integer.class, java.lang.String.class,java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class,java.lang.Integer.class
                };

                public Class getColumnClass(int columnIndex) {
                    return types [columnIndex];
                }
            });

        jTable1.setColumnSelectionAllowed(true);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTable1);
        jTable1.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        jButton2.setText("Cargar XML");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 15));
        jLabel1.setText("Detalles de Envio");

        jLabel3.setText("Solicitud de Distribucion");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 15));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(43, 43, 43)
                                .addComponent(jButton2))
                            .addComponent(jButton1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 646, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(126, 126, 126)
                        .addComponent(jLabel1)))
                .addContainerGap(48, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jButton2))
                .addGap(29, 29, 29)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)
                        .addGap(37, 37, 37))))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
     ///Guardar pedido 
        dtm = (DefaultTableModel) jTable1.getModel();
        SolicitudDistribucion soldis = new SolicitudDistribucion();
        ((ControladorMain)ref.getVistaPadre().getControlador()).doGuardarSolicitud(soldis);
        //JOptionPane.showMessageDialog(this, "Operacion concretada.");
        new Dialogo3Opciones("Operacion concretada",this.ref).setVisible(true);
        //ref.ponerPanelPrincipal();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // Carga archivo XML
        chooser = new FileChooser(ref,true,ref.getDefaltXmlPath());
        String a = chooser.getPath();
       // String b = chooser.getFile();
        
        if(a.equals(""))JOptionPane.showMessageDialog(this,"Debe ingresar la ubicacion del archivo XML.\n", Constantes.APPLICATION_NAME, JOptionPane.ERROR_MESSAGE);
        else
        {
            //Cargar los table
            ref.getJTextArea1().append("Archivo Cargado\n");
            String url = chooser.getPath();
            ((ControladorMain)ref.getVistaPadre().getControlador()).doMostrarSolicitud(url);
        }
    }//GEN-LAST:event_jButton2ActionPerformed
    
   /* public void fillSDTable(String url)
    {
        dtm = (DefaultTableModel) jTable1.getModel();
        FileReaderWrapper fileReader = new FileReaderWrapper(url);
        String XML = fileReader.obtenerContenido();
        XStream xstream = new XStream();
        
        xstream.alias("CentroDistribucion", CentroDistribucion.class);
        xstream.alias("soldis", SolicitudDistribucion.class);
        xstream.alias("articuloropa", ArticuloRopa.class);
        xstream.alias("articulohogar", ArticuloHogar.class);
        
        CentroDistribucion cd = (CentroDistribucion) xstream.fromXML(XML);
        
        ref.getJTextArea1().append(""+cd.getSolicituddistribucion().elementAt(0).getArticulosropa().elementAt(0).getCantidad());
        
        Vector<SolicitudDistribucion> soldis = cd.getSolicituddistribucion();
        
        fillSDTable(soldis); 
    }*/
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
    private FileChooser chooser;
}
