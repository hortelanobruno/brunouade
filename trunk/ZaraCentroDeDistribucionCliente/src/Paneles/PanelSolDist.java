/*
 * PanelSolDist.java
 *
 * Created on 22 de mayo de 2008, 22:58
 */

package Paneles;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import GUI.Dialogo3Opciones;
import GUI.FileChooser;
import GUI.MenuPrincipal;
import VO.ArticuloAEnviarVO;
import VO.ArticuloAFabricarVO;
import VO.ArticuloHeaderVO;
import VO.ArticuloPedidoVO;
import VO.SolicitudDistribucionVO;
import Varios.Constantes;
import Varios.XMLWrapper;
import Vistas.VistaSolDis;
import controladores.ControladorPanelSolDis;

/**
 * 
 * @author Administrador
 */
public class PanelSolDist extends javax.swing.JPanel {

	private static final long serialVersionUID = 1L;

	private String urlXML;

	private boolean cargarTable;

	private MenuPrincipal ref;

	private SolicitudDistribucionVO solDisVO;

	private VistaSolDis vistaSolDis;

	/** Creates new form PanelSolDist */
	public PanelSolDist(MenuPrincipal mn, VistaSolDis vista) {
		initComponents();
		this.ref = mn;
		this.vistaSolDis = vista;
		tableArticulos.getModel().addTableModelListener(
				new TableModelListener() {
					public void tableChanged(TableModelEvent e) {
						if (e.getColumn() > -1)
							validateTable();
					}
				});
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
        jLabel2 = new javax.swing.JLabel();
        comboFabrica = new javax.swing.JComboBox();

        jLabel1.setText("Solicitud de distribucion");

        buttonCargarXML.setText("Cargar XML");
        buttonCargarXML.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCargarXMLActionPerformed(evt);
            }
        });

        tableArticulos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {
                "Numero Solicitud", "Tienda", "Codigo Art.", "Descripcion", "Cantidad Pedida", "Stock Actual", "Cantidad a Enviar"
            }
        ) {
			private static final long serialVersionUID = -7276347293843632059L;
			Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Long.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableArticulos.setRowSelectionAllowed(false);
        tableArticulos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tableArticulos.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tableArticulos);

        buttonGuardarPedido.setText("Guardar Pedido");
        buttonGuardarPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGuardarPedidoActionPerformed(evt);
            }
        });

        jLabel2.setText("Fabrica");

        comboFabrica.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(766, 766, 766)
                        .add(labelValidacion, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 23, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(layout.createSequentialGroup()
                        .add(32, 32, 32)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 705, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(layout.createSequentialGroup()
                                .add(jLabel1)
                                .add(96, 96, 96)
                                .add(buttonCargarXML))
                            .add(buttonGuardarPedido)
                            .add(layout.createSequentialGroup()
                                .add(jLabel2)
                                .add(71, 71, 71)
                                .add(comboFabrica, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 134, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(251, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(28, 28, 28)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel1)
                            .add(buttonCargarXML))
                        .add(29, 29, 29)
                        .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 204, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(layout.createSequentialGroup()
                        .add(155, 155, 155)
                        .add(labelValidacion, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 21, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .add(18, 18, 18)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel2)
                    .add(comboFabrica, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 34, Short.MAX_VALUE)
                .add(buttonGuardarPedido)
                .add(70, 70, 70))
        );
	}// </editor-fold>

	private void buttonGuardarPedidoActionPerformed(
			java.awt.event.ActionEvent evt) {
		((ControladorPanelSolDis) vistaSolDis.getControlador())
				.doCargarXML(false);
	}

	private void buttonCargarXMLActionPerformed(java.awt.event.ActionEvent evt) {
		// Carga archivo XML
		chooser = new FileChooser(ref, true, ref.getDefaltXmlPath());
		urlXML = chooser.getPath();
		if (urlXML.equals(""))
			JOptionPane.showMessageDialog(this,
					"Debe ingresar la ubicacion del archivo XML.\n",
					Constantes.APPLICATION_NAME, JOptionPane.ERROR_MESSAGE);
		else {
			// Cargar los table
			((ControladorPanelSolDis) vistaSolDis.getControlador())
					.doCargarXML(true);
		}
	}

	private void cargarTable(SolicitudDistribucionVO solDisVO,
			ArrayList<Long> codigos, ArrayList<String> descripciones,
			ArrayList<Integer> stocks) {
		Iterator iterador = (Iterator) solDisVO.getArticulosPedidos()
				.iterator();
		for (int i = 0; i < solDisVO.getArticulosPedidos().size(); i++) {
			((DefaultTableModel) tableArticulos.getModel())
					.addRow(new Object[] {
							solDisVO.getNumero(),
							solDisVO.getTienda().getNombreTienda(),
							codigos.get(i).toString(),
							descripciones.get(i).toString(),
							((ArticuloPedidoVO) iterador.next()).getCantidad(),
							stocks.get(i).toString(), 0 });
		}
	}

	private void validateTable() {
		//ARREGLAR MAS TARDE
		boolean grabar = true;
		int cantCeros = 0;

		for (int i = 0; i < tableArticulos.getModel().getRowCount(); i++) {
			int valor = Integer.parseInt(tableArticulos.getModel().getValueAt(
					i, 6).toString());
			int pedido = Integer.parseInt(tableArticulos.getModel().getValueAt(
					i, 4).toString());

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

	public void update() {
		// aca hay que poner las llamadas a la business delegate
		if (cargarTable) {
			XMLWrapper xml = new XMLWrapper();
			solDisVO = (SolicitudDistribucionVO) xml.parseXMLSD(urlXML);
			ArrayList<Long> codigos = new ArrayList<Long>();
			Iterator arts = (Iterator) solDisVO.getArticulosPedidos()
					.iterator();
			while (arts.hasNext()) {
				codigos.add(((ArticuloPedidoVO) arts.next()).getArt()
						.getCodigo());
			}
			ArrayList<String> descripciones = this.ref.getVistaSolDis()
					.getModelo().getDescripciones(codigos);
			ArrayList<Integer> stocks = this.ref.getVistaSolDis().getModelo()
					.getStocks(codigos);
			cargarTable(solDisVO, codigos, descripciones, stocks);
			ref.getJTextArea1().append("Archivo Cargado\n");
		} else {
			// Falta generar las solicitudes
			Collection<ArticuloAFabricarVO> artiAFab = (Collection<ArticuloAFabricarVO>) articulosFabricarDeTabla();
			Collection<ArticuloAEnviarVO> artiAEnv = (Collection<ArticuloAEnviarVO>) articulosEnviarDeTabla();
			((ControladorPanelSolDis) vistaSolDis.getControlador())
					.doGuardarSolicitud(solDisVO);
			((ControladorPanelSolDis) vistaSolDis.getControlador())
					.doGuardarArticulosAEnviar(artiAEnv);
			((ControladorPanelSolDis) vistaSolDis.getControlador())
					.doGuardarArticulosPendientes(artiAFab);
			vaciarTabla();
			ref.getJTextArea1().append("Solicitudes Guardadas\n");
			new Dialogo3Opciones("Operacion concretada", this)
					.setVisible(true);
		}
	}

	/*
	 * Falta completar la linea comentada
	 * 
	 * 
	 */
	public Collection<ArticuloAEnviarVO> articulosEnviarDeTabla() {
		Collection<ArticuloAEnviarVO> art = new ArrayList<ArticuloAEnviarVO>();
		ArticuloHeaderVO arti;
		for (int i = 0; i < tableArticulos.getRowCount(); i++) {
			long cod = (Long
					.parseLong((String) ((DefaultTableModel) tableArticulos
							.getModel()).getValueAt(i, 2)));
			arti = ((ControladorPanelSolDis) vistaSolDis.getControlador())
					.doGetArticulo(cod);
			ArticuloAEnviarVO aEnv = new ArticuloAEnviarVO();
			aEnv.setArt(arti);
			aEnv.setCantidadAEnviar(Integer.parseInt((((DefaultTableModel) tableArticulos.getModel()).getValueAt(i, 6)).toString()));
			aEnv.setSolDis(solDisVO);
			// aEnv.setIdAAE();
			art.add(aEnv);
		}
		return art;
	}

	/*
	 * Falta completar la linea comentada
	 * 
	 * la fabrica se va obtener de un combo
	 */
	public Collection<ArticuloAFabricarVO> articulosFabricarDeTabla() {
		Collection<ArticuloAFabricarVO> art = new ArrayList<ArticuloAFabricarVO>();
		ArticuloHeaderVO arti;
		for (int i = 0; i < tableArticulos.getRowCount(); i++) {
			int ped = Integer.parseInt((((DefaultTableModel) tableArticulos
					.getModel()).getValueAt(i, 4)).toString());
			int sel = Integer.parseInt((((DefaultTableModel) tableArticulos
					.getModel()).getValueAt(i, 6)).toString());
			if (ped > sel) {
				long cod = (Long
						.parseLong((String) ((DefaultTableModel) tableArticulos
								.getModel()).getValueAt(i, 2)));
				arti = ((ControladorPanelSolDis) vistaSolDis.getControlador())
						.doGetArticulo(cod);
				ArticuloAFabricarVO aFab = new ArticuloAFabricarVO();
				aFab.setArt(arti);
				aFab.setCantidadPedida(ped - sel);
				aFab.setCantidadRecibida(0);
				// aFab.setFabrica()
				// aFab.setIdAAF();
				aFab.setSol(solDisVO);
				art.add(aFab);
			} else {
				ref
						.getJTextArea1()
						.append(
								"Error al cargar los articulos a fabricar, xq lo seleccionado es mayor que lo pedido\n");
			}
		}
		return art;
	}

	public void vaciarTabla() {
		for (int i = 0; i < tableArticulos.getRowCount(); i++) {
			((DefaultTableModel) tableArticulos.getModel()).removeRow(i);
		}
	}

	// Variables declaration - do not modify
	private javax.swing.JButton buttonCargarXML;

	private javax.swing.JButton buttonGuardarPedido;

	private javax.swing.JLabel jLabel1;

	private javax.swing.JLabel jLabel2;

	private javax.swing.JScrollPane jScrollPane1;

	private javax.swing.JLabel labelValidacion;

	private javax.swing.JComboBox comboFabrica;

	private javax.swing.JTable tableArticulos;

	private FileChooser chooser;

	// End of variables declaration

	public boolean getCargarTable() {
		return cargarTable;
	}

	public void setCargarTable(boolean cargarTable) {
		this.cargarTable = cargarTable;
	}

}
