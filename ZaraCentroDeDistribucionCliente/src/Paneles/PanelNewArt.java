/*
 * PanelNewArt.java
 *
 * Created on 22 de mayo de 2008, 22:44
 */

package Paneles;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import controladores.ControladorPanelNewArt;
import VO.ArticuloHogarVO;
import VO.ArticuloRopaVO;
import Varios.Constantes;
import Varios.XMLArticulo;
import Varios.XMLWrapper;
import Vistas.VistaNewArt;
import BusinessLogic.BusinessDelegate;
import Exceptions.ExistingProductException;
import GUI.Dialogo3Opciones;
import GUI.FileChooser;
import GUI.MenuPrincipal;

/**
 * 
 * @author Administrador
 */
public class PanelNewArt extends javax.swing.JPanel 
{
	/** Creates new form PanelNewArt */
	private static final long serialVersionUID = 1L;
	private MenuPrincipal ref;
	private VistaNewArt vistaNewArt;
	private boolean cargarTable;
	private String urlXML = null;
	private String chooserButton = null;

	public PanelNewArt(MenuPrincipal m, VistaNewArt vista) {
		initComponents();
		this.ref = m;
		this.vistaNewArt = vista;
		this.buttonGuardar.setEnabled(false);
	}


	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings({ "unchecked", "serial" })
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        tableArticulo = new javax.swing.JTable();
        buttonCargarXML = new javax.swing.JButton();
        buttonGuardar = new javax.swing.JButton();

        tableArticulo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Atributo", "Valor"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableArticulo.getTableHeader().setResizingAllowed(false);
        tableArticulo.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tableArticulo);

        buttonCargarXML.setText("Cargar Articulo");
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
                .add(35, 35, 35)
                .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 332, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(73, 73, 73)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(buttonGuardar, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(buttonCargarXML, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .add(494, 494, 494))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(90, 90, 90)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 307, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(layout.createSequentialGroup()
                        .add(31, 31, 31)
                        .add(buttonCargarXML)
                        .add(31, 31, 31)
                        .add(buttonGuardar)))
                .add(52, 52, 52))
        );
	}// </editor-fold>

	private void buttonCargarXMLActionPerformed(java.awt.event.ActionEvent evt) {
		// Cargar XML
		chooser = new FileChooser(ref, true, ref.getDefaltXmlPath());
		urlXML = chooser.getPath();
		chooserButton = chooser.getButton();
		if (chooserButton.equals("Cancel")) {
			
		} else {
			// Cargar los table
			((ControladorPanelNewArt) vistaNewArt.getControlador()).doCargarXML(true);
		}
	}

	private void buttonGuardarActionPerformed(java.awt.event.ActionEvent evt) {
		// Guardar Articulo
		((ControladorPanelNewArt) vistaNewArt.getControlador()).doCargarXML(false);
	}

	public void update() {
		XMLArticulo articuloXML;
		if(cargarTable){
			//cargar articulo
			XMLWrapper xml = new XMLWrapper();
			articuloXML = (XMLArticulo) xml.parseXMLArticulo(urlXML);
			if(articuloXML == null){
				JOptionPane.showMessageDialog(this,"Archivo incorrecto",Constantes.APPLICATION_NAME,JOptionPane.ERROR_MESSAGE);
			}else{
				if(((BusinessDelegate)vistaNewArt.getModelo()).existeArticulo(articuloXML.getCodigo())){
					vaciarTabla();
					ref.getJTextArea1().append(ref.getDate()+": Articulo 'existente' en el Centro de Distribucion \n");
					this.buttonCargarXML.setEnabled(true);
					this.buttonGuardar.setEnabled(false);
					JOptionPane.showMessageDialog(this,"El articulo ya existe",Constantes.APPLICATION_NAME,JOptionPane.ERROR_MESSAGE);
				}else{
					vaciarTabla();
					cargarTable(articuloXML);
					ref.getJTextArea1().append(ref.getDate()+": Articulo cargado.");
					this.buttonGuardar.setEnabled(true);
					this.buttonCargarXML.setEnabled(false);
				}
			}
		}else{
			//persiste articulo
			String msj = null;
			if(((DefaultTableModel)tableArticulo.getModel()).getValueAt(0, 1).equals("Ropa")){
				//es un articulo ropa
				ArticuloRopaVO articulo = crearArticuloRopaVO();
				try
				{
					((BusinessDelegate)vistaNewArt.getModelo()).guardarArticuloRopa(articulo);
					msj = codificarDetalle(articulo);
				}
				catch(ExistingProductException e)
				{
					JOptionPane.showMessageDialog(this, e.getMessage(),Constantes.APPLICATION_NAME,JOptionPane.ERROR_MESSAGE);
				}
			}else{
				//es un articulo hogar
				ArticuloHogarVO articulo = crearArticuloHogarVO();
				try
				{
					((BusinessDelegate)vistaNewArt.getModelo()).guardarArticuloHogar(articulo);
					msj = codificarDetalle(articulo);
				}
				catch(ExistingProductException e)
				{
					JOptionPane.showMessageDialog(this, e.getMessage(),Constantes.APPLICATION_NAME,JOptionPane.ERROR_MESSAGE);
				}
			}
			vaciarTabla();
			ref.getJTextArea1().append(ref.getDate()+": Articulo guardado en el Centro de Distribucion");
			this.buttonCargarXML.setEnabled(true);
			this.buttonGuardar.setEnabled(false);
			new Dialogo3Opciones("Operacion concretada", this,msj).setVisible(true);
		}
		
	}
	
	private String codificarDetalle(ArticuloHogarVO articulo) {
		String msj = new String();
		msj = "Articulo Nuevo: \n\n";
		msj = msj + "Cantidad: "+articulo.getCantidad()+"\n";
		msj = msj + "Categoria: "+articulo.getCategoria()+"\n";
		msj = msj + "Codigo: "+articulo.getCodigo()+"\n";
		msj = msj + "Color: "+articulo.getColor()+"\n";
		msj = msj + "Composicion: "+articulo.getComposicion()+"\n";
		msj = msj + "Descipcion: "+articulo.getDescripcion()+"\n";
		msj = msj + "Detalles: "+articulo.getDetalles()+"\n";
		msj = msj + "Linea: "+articulo.getLinea()+"\n";
		msj = msj + "Medidas: "+articulo.getMedidas()+"\n";
		msj = msj + "Precio: "+articulo.getPrecio()+"\n";
		msj = msj + "Seccion: "+articulo.getSeccion()+"\n";
		return msj;
	}


	private String codificarDetalle(ArticuloRopaVO articulo) {
		String msj = new String();
		msj = "Articulo Nuevo: \n\n";
		msj = msj + "Cantidad: "+articulo.getCantidad()+"\n";
		msj = msj + "Codigo: "+articulo.getCodigo()+"\n";
		msj = msj + "Color: "+articulo.getColor()+"\n";
		msj = msj + "Descripcion: "+articulo.getDescripcion()+"\n";
		msj = msj + "Linea: "+articulo.getLinea()+"\n";
		msj = msj + "Origen: "+articulo.getOrigen()+"\n";
		msj = msj + "Precio: "+articulo.getPrecio()+"\n";
		msj = msj + "Seccion: "+articulo.getSeccion()+"\n";
		msj = msj + "Talle: "+articulo.getTalle()+"\n";
		return msj;
	}
	
	
	public void vaciarTabla(){
		((DefaultTableModel)tableArticulo.getModel()).getDataVector().removeAllElements();
	}

	public ArticuloRopaVO crearArticuloRopaVO(){
		ArticuloRopaVO art = new ArticuloRopaVO();
		art.setCodigo(Integer.parseInt(((DefaultTableModel)tableArticulo.getModel()).getValueAt(1, 1).toString()));
		art.setDescripcion(((DefaultTableModel)tableArticulo.getModel()).getValueAt(2, 1).toString());
		art.setPrecio(Float.parseFloat(((DefaultTableModel)tableArticulo.getModel()).getValueAt(3, 1).toString()));
		art.setSeccion(((DefaultTableModel)tableArticulo.getModel()).getValueAt(4, 1).toString());
		art.setColor(((DefaultTableModel)tableArticulo.getModel()).getValueAt(5, 1).toString());
		art.setLinea(((DefaultTableModel)tableArticulo.getModel()).getValueAt(6, 1).toString());
		art.setCantidad(Integer.parseInt(((DefaultTableModel)tableArticulo.getModel()).getValueAt(7, 1).toString()));
		art.setTalle(((DefaultTableModel)tableArticulo.getModel()).getValueAt(8, 1).toString());
		art.setOrigen(((DefaultTableModel)tableArticulo.getModel()).getValueAt(9, 1).toString());
		return art;
	}
	
	public ArticuloHogarVO crearArticuloHogarVO(){
		ArticuloHogarVO art = new ArticuloHogarVO();
		art.setCodigo(Integer.parseInt(((DefaultTableModel)tableArticulo.getModel()).getValueAt(1, 1).toString()));
		art.setDescripcion(((DefaultTableModel)tableArticulo.getModel()).getValueAt(2, 1).toString());
		art.setPrecio(Float.parseFloat(((DefaultTableModel)tableArticulo.getModel()).getValueAt(3, 1).toString()));
		art.setSeccion(((DefaultTableModel)tableArticulo.getModel()).getValueAt(4, 1).toString());
		art.setColor(((DefaultTableModel)tableArticulo.getModel()).getValueAt(5, 1).toString());
		art.setLinea(((DefaultTableModel)tableArticulo.getModel()).getValueAt(6, 1).toString());
		art.setCantidad(Integer.parseInt(((DefaultTableModel)tableArticulo.getModel()).getValueAt(7, 1).toString()));
		art.setDetalles(((DefaultTableModel)tableArticulo.getModel()).getValueAt(8, 1).toString());
		art.setComposicion(((DefaultTableModel)tableArticulo.getModel()).getValueAt(9, 1).toString());
		art.setCategoria(((DefaultTableModel)tableArticulo.getModel()).getValueAt(10, 1).toString());
		art.setMedidas(((DefaultTableModel)tableArticulo.getModel()).getValueAt(11, 1).toString());
		return art;
	}
	
	public void cargarTable(XMLArticulo art) {
		if(art.getCategoria().equals("")){
			//Es una articulo ropa
			((DefaultTableModel)tableArticulo.getModel()).addRow(new Object[]{"Articlo","Ropa"});
			((DefaultTableModel)tableArticulo.getModel()).addRow(new Object[]{"Codigo",art.getCodigo()});
			((DefaultTableModel)tableArticulo.getModel()).addRow(new Object[]{"Descripcion",art.getDescripcion()});
			((DefaultTableModel)tableArticulo.getModel()).addRow(new Object[]{"Precio",art.getPrecio()});
			((DefaultTableModel)tableArticulo.getModel()).addRow(new Object[]{"Seccion",art.getSeccion()});
			((DefaultTableModel)tableArticulo.getModel()).addRow(new Object[]{"Color",art.getColor()});
			((DefaultTableModel)tableArticulo.getModel()).addRow(new Object[]{"Linea",art.getLinea()});
			((DefaultTableModel)tableArticulo.getModel()).addRow(new Object[]{"Cantidad",art.getCantidad()});
			((DefaultTableModel)tableArticulo.getModel()).addRow(new Object[]{"Talle",art.getTalle()});
			((DefaultTableModel)tableArticulo.getModel()).addRow(new Object[]{"Origen",art.getOrigen()});
		}else {
			//Es un articulo hogar
			((DefaultTableModel)tableArticulo.getModel()).addRow(new Object[]{"Articulo","Hogar"});
			((DefaultTableModel)tableArticulo.getModel()).addRow(new Object[]{"Codigo",art.getCodigo()});
			((DefaultTableModel)tableArticulo.getModel()).addRow(new Object[]{"Descripcion",art.getDescripcion()});
			((DefaultTableModel)tableArticulo.getModel()).addRow(new Object[]{"Precio",art.getPrecio()});
			((DefaultTableModel)tableArticulo.getModel()).addRow(new Object[]{"Seccion",art.getSeccion()});
			((DefaultTableModel)tableArticulo.getModel()).addRow(new Object[]{"Color",art.getColor()});
			((DefaultTableModel)tableArticulo.getModel()).addRow(new Object[]{"Linea",art.getLinea()});
			((DefaultTableModel)tableArticulo.getModel()).addRow(new Object[]{"Cantidad",art.getCantidad()});
			((DefaultTableModel)tableArticulo.getModel()).addRow(new Object[]{"Detalles",art.getDetalles()});
			((DefaultTableModel)tableArticulo.getModel()).addRow(new Object[]{"Composicion",art.getComposicion()});
			((DefaultTableModel)tableArticulo.getModel()).addRow(new Object[]{"Categoria",art.getCategoria()});
			((DefaultTableModel)tableArticulo.getModel()).addRow(new Object[]{"Medidas",art.getMedidas()});
		}
	}


	// Variables declaration - do not modify
	private javax.swing.JButton buttonCargarXML;

	private javax.swing.JButton buttonGuardar;

	private javax.swing.JScrollPane jScrollPane2;

	private javax.swing.JTable tableArticulo;

	private FileChooser chooser;

	// End of variables declaration

	public VistaNewArt getVistaNewArt() {
		return vistaNewArt;
	}

	public void setVistaNewArt(VistaNewArt vistaNewArt) {
		this.vistaNewArt = vistaNewArt;
	}

	public boolean isCargarTable() {
		return cargarTable;
	}

	public void setCargarTable(boolean cargarTable) {
		this.cargarTable = cargarTable;
	}


	public MenuPrincipal getRef() {
		return ref;
	}


	public void setRef(MenuPrincipal ref) {
		this.ref = ref;
	}

}
