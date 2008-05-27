/*
 * PanelSolDist.java
 *
 * Created on 22 de mayo de 2008, 22:58
 */

package Paneles;

import java.awt.Color;

import BusinessLogic.SolicitudDistribucionVO;
import GUI.Dialogo3Opciones;
import GUI.FileChooser;
import GUI.MenuPrincipal;

import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import controladores.ControladorPanelConfig;
import controladores.ControladorPanelSolDis;

import Varios.Constantes;

/**
 * 
 * @author Administrador
 */
public class PanelSolDist extends javax.swing.JPanel {

	DefaultTableModel dtm;

	private MenuPrincipal ref;

	/** Creates new form PanelSolDist */
	public PanelSolDist(MenuPrincipal mn) {
		initComponents();
		this.ref = mn;
		dtm = new DefaultTableModel();

		tableArticulos.getModel().addTableModelListener(
				new TableModelListener() {
					public void tableChanged(TableModelEvent e) {
						if (e.getColumn() > -1)
							validateTable();
					}
				});
	}

	public void update() {
		tableArticulos.setModel(dtm);
		// System.out.println("llego");
		// algo asi que lea de la base
		// String.valueOf(((ProxyModeloAdmProductos)(this.getVistaPadre().getModelo())).getStock((String)(comboProductos.getSelectedItem())
		// ))
		ref.invalidate();
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		jLabel1 = new javax.swing.JLabel();
		buttonCargarXML = new javax.swing.JButton();
		jScrollPane1 = new javax.swing.JScrollPane();
		tableArticulos = new javax.swing.JTable();
		buttonGuardarPedido = new javax.swing.JButton();
		labelValidacion = new javax.swing.JLabel();

		jLabel1.setText("Solicitud de distribucion");

		buttonCargarXML.setText("Cargar XML");
		buttonCargarXML.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				buttonCargarXMLActionPerformed(evt);
			}
		});

		tableArticulos.setModel(new javax.swing.table.DefaultTableModel(
				new Object[][] {

				}, new String[] { "Nombre Solicitud", "Tienda", "Codigo Art.",
						"Descripcion", "Cantidad Pedida", "Stock Actual",
						"Cantidad a Enviar" }) {
			Class[] types = new Class[] { java.lang.String.class,
					java.lang.String.class, java.lang.Integer.class,
					java.lang.String.class, java.lang.Integer.class,
					java.lang.Integer.class, java.lang.Integer.class };

			boolean[] canEdit = new boolean[] { false, false, false, false,
					false, false, true };

			public Class getColumnClass(int columnIndex) {
				return types[columnIndex];
			}

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		});
		tableArticulos.setEnabled(false);
		tableArticulos.setRowSelectionAllowed(false);
		tableArticulos
				.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		tableArticulos.getTableHeader().setReorderingAllowed(false);
		jScrollPane1.setViewportView(tableArticulos);

		buttonGuardarPedido.setText("Guardar Pedido");
		buttonGuardarPedido
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						buttonGuardarPedidoActionPerformed(evt);
					}
				});

		org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(
				this);
		this.setLayout(layout);
		layout
				.setHorizontalGroup(layout
						.createParallelGroup(
								org.jdesktop.layout.GroupLayout.LEADING)
						.add(
								layout
										.createSequentialGroup()
										.add(
												layout
														.createParallelGroup(
																org.jdesktop.layout.GroupLayout.LEADING)
														.add(
																layout
																		.createSequentialGroup()
																		.add(
																				766,
																				766,
																				766)
																		.add(
																				labelValidacion,
																				org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																				23,
																				org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
														.add(
																layout
																		.createSequentialGroup()
																		.add(
																				32,
																				32,
																				32)
																		.add(
																				layout
																						.createParallelGroup(
																								org.jdesktop.layout.GroupLayout.LEADING)
																						.add(
																								jScrollPane1,
																								org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																								705,
																								org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
																						.add(
																								layout
																										.createSequentialGroup()
																										.add(
																												jLabel1)
																										.add(
																												96,
																												96,
																												96)
																										.add(
																												buttonCargarXML))
																						.add(
																								buttonGuardarPedido))))
										.addContainerGap(251, Short.MAX_VALUE)));
		layout
				.setVerticalGroup(layout
						.createParallelGroup(
								org.jdesktop.layout.GroupLayout.LEADING)
						.add(
								layout
										.createSequentialGroup()
										.add(
												layout
														.createParallelGroup(
																org.jdesktop.layout.GroupLayout.LEADING)
														.add(
																layout
																		.createSequentialGroup()
																		.add(
																				28,
																				28,
																				28)
																		.add(
																				layout
																						.createParallelGroup(
																								org.jdesktop.layout.GroupLayout.BASELINE)
																						.add(
																								jLabel1)
																						.add(
																								buttonCargarXML))
																		.add(
																				29,
																				29,
																				29)
																		.add(
																				jScrollPane1,
																				org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																				204,
																				org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
														.add(
																layout
																		.createSequentialGroup()
																		.add(
																				155,
																				155,
																				155)
																		.add(
																				labelValidacion,
																				org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																				21,
																				org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
										.addPreferredGap(
												org.jdesktop.layout.LayoutStyle.RELATED,
												72, Short.MAX_VALUE).add(
												buttonGuardarPedido).add(70,
												70, 70)));
	}// </editor-fold>

	private void buttonGuardarPedidoActionPerformed(
			java.awt.event.ActionEvent evt) {
		// /Guardar pedido
		dtm = (DefaultTableModel) tableArticulos.getModel();
		SolicitudDistribucionVO soldis = new SolicitudDistribucionVO();
		((ControladorPanelSolDis) ref.getVistaPadre().getControlador())
				.doGuardarSolicitud(soldis);
		// JOptionPane.showMessageDialog(this, "Operacion concretada.");
		new Dialogo3Opciones("Operacion concretada", this.ref).setVisible(true);
		// ref.ponerPanelPrincipal();
	}

	private void buttonCargarXMLActionPerformed(java.awt.event.ActionEvent evt) {
		// Carga archivo XML
		chooser = new FileChooser(ref, true, ref.getDefaltXmlPath());
		String url = chooser.getPath();
		//String b = chooser.getFile();

		if (url.equals(""))
			JOptionPane.showMessageDialog(this,
					"Debe ingresar la ubicacion del archivo XML.\n",
					Constantes.APPLICATION_NAME, JOptionPane.ERROR_MESSAGE);
		else {
			//Cargar los table
			ref.getJTextArea1().append("Archivo Cargado\n");
			
			
			SolicitudDistribucionVO solDisVO = (SolicitudDistribucionVO)((ControladorPanelSolDis) this.ref.getVistaSolDis()
					.getControlador()).doCargarXML(url);
			((ControladorPanelSolDis) ref.getVistaPadre().getControlador())
					.doMostrarSolicitud(url);
		}
	}

	/*
	 * public void fillSDTable(String url) { dtm = (DefaultTableModel)
	 * jTable1.getModel(); FileReaderWrapper fileReader = new
	 * FileReaderWrapper(url); String XML = fileReader.obtenerContenido();
	 * XStream xstream = new XStream();
	 * 
	 * xstream.alias("CentroDistribucion", CentroDistribucion.class);
	 * xstream.alias("soldis", SolicitudDistribucion.class);
	 * xstream.alias("articuloropa", ArticuloRopa.class);
	 * xstream.alias("articulohogar", ArticuloHogar.class);
	 * 
	 * CentroDistribucion cd = (CentroDistribucion) xstream.fromXML(XML);
	 * 
	 * ref.getJTextArea1().append(""+cd.getSolicituddistribucion().elementAt(0).getArticulosropa().elementAt(0).getCantidad());
	 * 
	 * Vector<SolicitudDistribucion> soldis = cd.getSolicituddistribucion();
	 * 
	 * fillSDTable(soldis); }
	 */

	public void fillSDTable(SolicitudDistribucionVO soldis) {
		/*
		 * dtm = (DefaultTableModel) jTable1.getModel();
		 * 
		 * Vector<ArticuloRopa> articulosropa = soldis.getArticulosropa();
		 * Vector<ArticuloHogar> articuloshogar = soldis.getArticuloshogar();
		 * 
		 * int codigo; int cantidad;
		 * 
		 * if (articulosropa != null) { for (int i = 0; i <
		 * articulosropa.size(); i++) { codigo =
		 * articulosropa.elementAt(i).getCodigo(); cantidad =
		 * articulosropa.elementAt(i).getStock(); int stock =
		 * Integer.valueOf(((BusinessDelegate)
		 * (ref.getVistaPadre().getModelo())).getStock(codigo)); String
		 * descripcion = String.valueOf(((BusinessDelegate)
		 * (ref.getVistaPadre().getModelo())).getDescripcion(codigo)); int
		 * numero = soldis.getNumero();
		 * 
		 * dtm.addRow(new Object[]{numero, codigo, descripcion, cantidad, stock,
		 * 0}); //System.out.println("cargo" + i); } }
		 * 
		 * if (articuloshogar != null) { for (int i = 0; i <
		 * articuloshogar.size(); i++) { codigo =
		 * articuloshogar.elementAt(i).getCodigo(); cantidad =
		 * articuloshogar.elementAt(i).getStock(); int stock =
		 * Integer.valueOf(((BusinessDelegate)
		 * (ref.getVistaPadre().getModelo())).getStock(codigo)); String
		 * descripcion = String.valueOf(((BusinessDelegate)
		 * (ref.getVistaPadre().getModelo())).getDescripcion(codigo)); int
		 * numero = soldis.getNumero();
		 * 
		 * dtm.addRow(new Object[]{numero, codigo, descripcion, cantidad, stock,
		 * 0}); } }
		 * 
		 * this.validateTable();
		 */
	}

	private void validateTable() {
		boolean grabar = true;
		int cantCeros = 0;

		for (int i = 0; i < tableArticulos.getModel().getRowCount(); i++) {
			int valor = Integer.parseInt(tableArticulos.getModel().getValueAt(
					i, 5).toString());
			int pedido = Integer.parseInt(tableArticulos.getModel().getValueAt(
					i, 3).toString());

			if (valor == 0)
				cantCeros++;
			if ((valor < 0) || (valor > pedido)) {
				grabar = false;
				if (valor > pedido)
					JOptionPane.showMessageDialog(this,
							"El valor ingresado es mayor al pedido.",
							Constantes.APPLICATION_NAME,
							JOptionPane.ERROR_MESSAGE);
				break;
			}
		}

		if ((grabar) && (cantCeros < tableArticulos.getModel().getRowCount())) {
			buttonGuardarPedido.setEnabled(true);
			labelValidacion.setText("OK");
			labelValidacion.setForeground(Color.GREEN);
		} else {
			buttonGuardarPedido.setEnabled(false);
			labelValidacion.setForeground(Color.RED);
			if (!grabar)
				labelValidacion
						.setText("Hay errores en los valores ingresados");
			else
				labelValidacion.setText("Debe ingresar valores.");
		}
	}

	// Variables declaration - do not modify
	private javax.swing.JButton buttonCargarXML;

	private javax.swing.JButton buttonGuardarPedido;

	private javax.swing.JLabel jLabel1;

	private javax.swing.JScrollPane jScrollPane1;

	private javax.swing.JLabel labelValidacion;

	private javax.swing.JTable tableArticulos;

	private FileChooser chooser;
	// End of variables declaration

}
