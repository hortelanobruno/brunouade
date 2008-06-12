/*
 * PanelRepArt.java
 *
 * Created on 22 de mayo de 2008, 22:55
 */

package Paneles;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import controladores.ControladorPanelRepArt;
import VO.ArticuloAFabricarVO;
import VO.ArticuloAReponerVO;
import VO.SolicitudDeReposicionVO;
import VO.SolicitudFabricaVO;
import Varios.Constantes;
import Varios.XMLWrapper;
import Vistas.VistaRepArt;
import GUI.Dialogo3Opciones;
import GUI.FileChooser;
import GUI.MenuPrincipal;

/**
 * 
 * @author Administrador
 */
public class PanelRepArt extends javax.swing.JPanel {

	/** Creates new form PanelRepArt */
	private static final long serialVersionUID = 1L;
	private String urlXML;
	private MenuPrincipal ref;
	private SolicitudDeReposicionVO solRepVO;
	private VistaRepArt vistaRepArt;
	private boolean cargarTable;
	private FileChooser chooser;
	private SolicitudFabricaVO solFab;

	public PanelRepArt(MenuPrincipal m, VistaRepArt vista) {
		initComponents();
		this.ref = m;
		this.vistaRepArt = vista;
		this.buttonGuardar.setEnabled(false);
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
		jScrollPane1 = new javax.swing.JScrollPane();
		tableArticulosFabrica = new javax.swing.JTable();
		buttonCargarXML = new javax.swing.JButton();
		buttonGuardar = new javax.swing.JButton();

		jLabel1.setText("Articulos recibidos de fabrica");

		tableArticulosFabrica.setModel(new javax.swing.table.DefaultTableModel(
				new Object[][] {

				}, new String[] {"Solicitud", "Fabrica","Codigo", "Descripcion", "Cantidad Pedida",
						"Cantidad Recibida" }) {
							private static final long serialVersionUID = 1L;
			Class[] types = new Class[] { java.lang.Integer.class,java.lang.String.class,java.lang.Integer.class,
					java.lang.String.class, java.lang.Integer.class,
					java.lang.Integer.class };

			public Class getColumnClass(int columnIndex) {
				return types[columnIndex];
			}
		});
		jScrollPane1.setViewportView(tableArticulosFabrica);

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

		org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(
				this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(
				org.jdesktop.layout.GroupLayout.LEADING).add(
				layout.createSequentialGroup().add(25, 25, 25).add(
						layout.createParallelGroup(
								org.jdesktop.layout.GroupLayout.LEADING).add(
								layout.createSequentialGroup().add(
										buttonCargarXML).add(74, 74, 74).add(
										buttonGuardar)).add(jScrollPane1,
								org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
								org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
								org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
								.add(jLabel1)).addContainerGap(562,
						Short.MAX_VALUE)));
		layout
				.setVerticalGroup(layout
						.createParallelGroup(
								org.jdesktop.layout.GroupLayout.LEADING)
						.add(
								layout
										.createSequentialGroup()
										.add(28, 28, 28)
										.add(jLabel1)
										.add(38, 38, 38)
										.add(
												jScrollPane1,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
												154,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
										.add(63, 63, 63)
										.add(
												layout
														.createParallelGroup(
																org.jdesktop.layout.GroupLayout.BASELINE)
														.add(buttonCargarXML)
														.add(buttonGuardar))
										.addContainerGap(129, Short.MAX_VALUE)));
	}// </editor-fold>

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
			((ControladorPanelRepArt) vistaRepArt.getControlador()).doCargarXML(true);
		}
	}

	private void buttonGuardarActionPerformed(java.awt.event.ActionEvent evt) 
	{
		// Guardar articulos reciibidos
		((ControladorPanelRepArt) vistaRepArt.getControlador()).doCargarXML(false);
	}
	
	private void cargarArticuloEnSolFab(ArticuloAReponerVO arti)
	{
		Collection<ArticuloAFabricarVO> articulos = solFab.getArticulosAFabricar();
		Iterator itArt = articulos.iterator();
		while(itArt.hasNext()){
			ArticuloAFabricarVO art = (ArticuloAFabricarVO)itArt.next();
			if(arti.getArt().getCodigo() == (art.getArt().getCodigo())){
				int cantidad = art.getCantidadRecibida();
				art.setCantidadRecibida(cantidad + arti.getCantidad());
			}
		}
	}
	
	
	private void cargarTable(long solicitud,SolicitudDeReposicionVO solRepVO,ArrayList<Long> codigos, ArrayList<String> descripciones,SolicitudFabricaVO solFab, String fabrica) 
	{
		Iterator iteradorRep = (Iterator) solRepVO.getArticulosAReponer().iterator();
		Iterator iteradorFab = (Iterator) solFab.getArticulosAFabricar().iterator();
		Integer cantidpedida = 0;
		Integer cantidadrecibida = 0;
		for (int i = 0; i < codigos.size(); i++) {
			while (iteradorRep.hasNext()) {
				if((((ArticuloAReponerVO) iteradorRep.next()).getArt().getCodigo()) == codigos.get(i)){
					ArticuloAReponerVO arti = (((ArticuloAReponerVO) iteradorRep.next()));
					cargarArticuloEnSolFab(arti);
					cantidadrecibida = arti.getCantidad();
				}
			}
			while (iteradorFab.hasNext()) {
				if((((ArticuloAFabricarVO) iteradorFab.next()).getArt().getCodigo()) == codigos.get(i)){
					cantidpedida = (((ArticuloAFabricarVO) iteradorRep.next()).getCantidadPedida());
				}
			}
			((DefaultTableModel) tableArticulosFabrica.getModel())
			.addRow(new Object[] {solicitud,fabrica,codigos.get(i),descripciones.get(i),cantidpedida,cantidadrecibida});
			iteradorRep = (Iterator) solRepVO.getArticulosAReponer().iterator();
			iteradorFab = (Iterator) solFab.getArticulosAFabricar().iterator();
		}
	}

	public void update() {
		//aca hay que poner las llamadas a la business delegate
		if(cargarTable){
			XMLWrapper xml = new XMLWrapper();
			solRepVO = (SolicitudDeReposicionVO) xml.parseXMLSR(urlXML);
			
			long codigoSolFab = solRepVO.getNumero();
			solFab = (SolicitudFabricaVO) solRepVO.getSolFab();
			String fabrica = solRepVO.getFabrica().getNombreFabrica();
			ArrayList<Long> codigos = new ArrayList<Long>();
			Iterator arts = (Iterator) solRepVO.getArticulosAReponer().iterator();
			while (arts.hasNext()) {
				codigos.add(((ArticuloAReponerVO) arts.next()).getArt().getCodigo());
			}
			ArrayList<String> descripciones = this.ref.getVistaSolDis().getModelo().getDescripciones(codigos);
			vaciarTabla();
			cargarTable(codigoSolFab,solRepVO, codigos, descripciones, solFab,fabrica);
			ref.getJTextArea1().append("Archivo Cargado\n");
			this.buttonGuardar.setEnabled(true);
			this.buttonCargarXML.setEnabled(false);
		}else{
			//Falta generar las solicitudes
			ArrayList<ArticuloAReponerVO> vecArt = collectionToArrayList(solRepVO.getArticulosAReponer());
			((ControladorPanelRepArt)vistaRepArt.getControlador()).doCargarStocks(vecArt);
			((ControladorPanelRepArt)vistaRepArt.getControlador()).doGuardarSolicitudFabricacion(solFab);
			((ControladorPanelRepArt)vistaRepArt.getControlador()).doGuardarSolicitudReposicion(solRepVO);
			vaciarTabla();
			ref.getJTextArea1().append("Solicitudes Guardadas\n");
			this.buttonGuardar.setEnabled(false);
			this.buttonCargarXML.setEnabled(true);
			new Dialogo3Opciones("Operacion concretada", this).setVisible(true);
		}
	}

	public ArrayList<ArticuloAReponerVO> collectionToArrayList(Collection<ArticuloAReponerVO> col){
		ArrayList<ArticuloAReponerVO> vec = new ArrayList<ArticuloAReponerVO>();
		Iterator it = (Iterator)col.iterator();
		while(it.hasNext()){
			vec.add((ArticuloAReponerVO) it.next());
		}
		return vec;
	}
	public void vaciarTabla(){
		((DefaultTableModel)tableArticulosFabrica.getModel()).getDataVector().removeAllElements();
	}
	
	// Variables declaration - do not modify
	private javax.swing.JButton buttonCargarXML;

	private javax.swing.JButton buttonGuardar;

	private javax.swing.JLabel jLabel1;

	private javax.swing.JScrollPane jScrollPane1;

	private javax.swing.JTable tableArticulosFabrica;

	// End of variables declaration

	public MenuPrincipal getRef() {
		return ref;
	}

	public void setRef(MenuPrincipal ref) {
		this.ref = ref;
	}

	public VistaRepArt getVistaRepArt() {
		return vistaRepArt;
	}

	public void setVistaRepArt(VistaRepArt vistaRepArt) {
		this.vistaRepArt = vistaRepArt;
	}

	public boolean isCargarTable() {
		return cargarTable;
	}

	public void setCargarTable(boolean cargarTable) {
		this.cargarTable = cargarTable;
	}

}
