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

import BusinessLogic.BusinessDelegate;
import GUI.Dialogo3Opciones;
import GUI.FileChooser;
import GUI.MenuPrincipal;
import VO.ArticuloAFabricarVO;
import VO.ArticuloAReponerVO;
import VO.ArticuloHeaderVO;
import VO.CentroDistribucionVO;
import VO.SolicitudDeReposicionVO;
import VO.SolicitudFabricaVO;
import Varios.Constantes;
import Varios.XMLWrapper;
import Vistas.VistaRepArt;
import controladores.ControladorPanelRepArt;

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
	private SolicitudFabricaVO solFabVO;

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
	@SuppressWarnings({ "unchecked", "serial" })
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

	       jScrollPane1 = new javax.swing.JScrollPane();
	        tableArticulosFabrica = new javax.swing.JTable();
	        buttonCargarXML = new javax.swing.JButton();
	        buttonGuardar = new javax.swing.JButton();

	        tableArticulosFabrica.setModel(new javax.swing.table.DefaultTableModel(
	            new Object [][] {

	            },
	            new String [] {
	                "Solicitud", "Fabrica", "Codigo", "Descripcion", "Cantidad pedida", "Cantidad a fabricar", "Cantidad recibida", "Cantidad a reponer"
	            }
	        ) {
	            Class[] types = new Class [] {
	                java.lang.Integer.class, java.lang.String.class, java.lang.Long.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
	            };
	            boolean[] canEdit = new boolean [] {
	                false, false, false, false, false, false, false, false
	            };

	            public Class getColumnClass(int columnIndex) {
	                return types [columnIndex];
	            }

	            public boolean isCellEditable(int rowIndex, int columnIndex) {
	                return canEdit [columnIndex];
	            }
	        });
	        tableArticulosFabrica.setRowSelectionAllowed(false);
	        tableArticulosFabrica.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
	        tableArticulosFabrica.getTableHeader().setResizingAllowed(false);
	        tableArticulosFabrica.getTableHeader().setReorderingAllowed(false);
	        jScrollPane1.setViewportView(tableArticulosFabrica);

	        buttonCargarXML.setText("Cargar Solicitud de Reposicion");
	        buttonCargarXML.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                buttonCargarXMLActionPerformed(evt);
	            }
	        });

	        buttonGuardar.setText("Guardar Articulos a Reponer");
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
	                .add(25, 25, 25)
	                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
	                    .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 833, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
	                    .add(layout.createSequentialGroup()
	                        .add(13, 13, 13)
	                        .add(buttonCargarXML)
	                        .add(64, 64, 64)
	                        .add(buttonGuardar)))
	                .addContainerGap(181, Short.MAX_VALUE))
	        );
	        layout.setVerticalGroup(
	            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
	            .add(layout.createSequentialGroup()
	                .add(80, 80, 80)
	                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 199, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
	                .add(39, 39, 39)
	                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
	                    .add(buttonCargarXML)
	                    .add(buttonGuardar))
	                .addContainerGap(108, Short.MAX_VALUE))
	        ); 
	}// </editor-fold>

	private void buttonCargarXMLActionPerformed(java.awt.event.ActionEvent evt) {
		// Carga archivo XML
		chooser = new FileChooser(ref, true, ref.getDefaltXmlPath());
		urlXML = chooser.getPath();
		String chooserButton = chooser.getButton();
		if (chooserButton.equals("Cancel")) {
		
		}
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
		Collection<ArticuloAFabricarVO> articulos = solFabVO.getArticulosAFabricar();
		Iterator itArt = articulos.iterator();
		while(itArt.hasNext()){
			ArticuloAFabricarVO art = (ArticuloAFabricarVO)itArt.next();
			if(arti.getArt().getCodigo() == (art.getArt().getCodigo())){
				int cantidad = art.getCantidadRecibida();
				art.setCantidadRecibida(cantidad + arti.getCantidad());
			}
		}
	}
	
	
	private void cargarTable(long codsol,SolicitudDeReposicionVO solRepVO,ArrayList<Long> codigos, ArrayList<String> descripciones,SolicitudFabricaVO solFab, String fabrica) 
	{
		Iterator iteradorRep = (Iterator) solRepVO.getArticulosAReponer().iterator();
		Iterator iteradorFab = (Iterator) solFab.getArticulosAFabricar().iterator();
		int cantidpedida = 0;
		int cantidadareponer = 0;
		int catidadafabricar = 0;
		int cantidadrecibida = 0;
		for (int i = 0; i < codigos.size(); i++) {
			while (iteradorRep.hasNext()) {
				ArticuloAReponerVO arti = (((ArticuloAReponerVO) iteradorRep.next()));
				if((arti.getArt().getCodigo()) == codigos.get(i)){
					cargarArticuloEnSolFab(arti);
					cantidadareponer = arti.getCantidad();
				}
			}
			while (iteradorFab.hasNext()) {
				ArticuloAFabricarVO arti2 = (((ArticuloAFabricarVO) iteradorFab.next()));
				if((arti2.getArt().getCodigo()) == codigos.get(i)){
					catidadafabricar = (arti2.getCantidadAFabricar());
					cantidpedida = (arti2.getCantidadPedida());
					cantidadrecibida = (arti2.getCantidadRecibida());
				}
			}
			((DefaultTableModel) tableArticulosFabrica.getModel())
			.addRow(new Object[] {codsol,fabrica,codigos.get(i),descripciones.get(i),cantidpedida,catidadafabricar,cantidadrecibida,cantidadareponer});
			iteradorRep = (Iterator) solRepVO.getArticulosAReponer().iterator();
			iteradorFab = (Iterator) solFab.getArticulosAFabricar().iterator();
		}
	}

	public void update() {
		//aca hay que poner las llamadas a la business delegate
		//ARREGLAR LA FECHA QUE SE SETEA
		if(cargarTable){
			XMLWrapper xml = new XMLWrapper();
			solRepVO = (SolicitudDeReposicionVO) xml.parseXMLSR(urlXML);
			if(solRepVO == null){
				JOptionPane.showMessageDialog(this,"Archivo incorrecto",Constantes.APPLICATION_NAME,JOptionPane.ERROR_MESSAGE);
			}else{
				if(((BusinessDelegate)vistaRepArt.getModelo()).existeSolRep(solRepVO.getIdRep())){
					vaciarTabla();
					ref.getJTextArea1().append(ref.getDate()+": Solicitud de Reposicion 'existente' en el Centro de Distribucion \n");
					this.buttonCargarXML.setEnabled(true);
					this.buttonGuardar.setEnabled(false);
					JOptionPane.showMessageDialog(this,"La solicitud de reposicion ya existe",Constantes.APPLICATION_NAME,JOptionPane.ERROR_MESSAGE);
				}else{
					int numSolFab = solRepVO.getSolFab().getIdFab();
					if(!((BusinessDelegate)vistaRepArt.getModelo()).existeSolFab(numSolFab)){
						vaciarTabla();
						ref.getJTextArea1().append(ref.getDate()+": La Solicitud de Reposicion contiene una Solicitud de Fabricacion que no existe\n");
						this.buttonCargarXML.setEnabled(true);
						this.buttonGuardar.setEnabled(false);
						JOptionPane.showMessageDialog(this,"La Solicitud de Reposicion contiene una Solicitud de Fabricacion que no existe\n");
					}else{
						ArrayList<Long> codigos = new ArrayList<Long>();
						Iterator arts = (Iterator) solRepVO.getArticulosAReponer().iterator();
						while (arts.hasNext()) {
							codigos.add(((ArticuloAReponerVO) arts.next()).getArt().getCodigo());
						}
						ArrayList<Long>  verCod = ((BusinessDelegate)vistaRepArt.getModelo()).existenArts(codigos);
						if(!verCod.isEmpty()){
							String codsfalse = "Cod. "+verCod.get(0);
							for(int q = 1 ; q < verCod.size() ; q++){
								codsfalse = codsfalse + " Cod. "+verCod.get(q);
							}
							vaciarTabla();
							ref.getJTextArea1().append(ref.getDate()+": La solicitud contiene articulos que no existen en el Centro de Distribucion\n");
							this.buttonCargarXML.setEnabled(true);
							this.buttonGuardar.setEnabled(false);
							JOptionPane.showMessageDialog(this,"La solicitud contiene articulos que no existen en el Centro de Distribucion\n" +
									"("+codsfalse+")",Constantes.APPLICATION_NAME,JOptionPane.ERROR_MESSAGE);
						}else{
							int idAR = this.ref.getVistaSolDis().getModelo().getNextIdARep();
							int id = this.ref.getVistaSolDis().getModelo().getNextId();
							id++;
							solRepVO.setId(id);
							Iterator itit = solRepVO.getArticulosAReponer().iterator();
							ArrayList<ArticuloAReponerVO> artsRep = new ArrayList<ArticuloAReponerVO>();
							while(itit.hasNext()){
								ArticuloAReponerVO aRep = (ArticuloAReponerVO) itit.next();
								idAR++;
								aRep.setIdAAR(idAR);
								artsRep.add(aRep);
							}
							solRepVO.setArticulosAReponer(artsRep);
							
							ArrayList<ArticuloHeaderVO> articulos = this.ref.getVistaRepArt().getModelo().getArticulos(codigos);				
							Iterator itit2 = solRepVO.getArticulosAReponer().iterator();
							Collection<ArticuloAReponerVO> artsReVO = new ArrayList<ArticuloAReponerVO>();
							while(itit2.hasNext()){
								ArticuloAReponerVO artRe = (ArticuloAReponerVO) itit2.next();
								long co = artRe.getArt().getCodigo();
								for(int j=0 ; j < articulos.size() ; j++){
									if(co == articulos.get(j).getCodigo()){
										artRe.setArt(articulos.get(j));
									}
								}
								artsReVO.add(artRe);
							}
							solRepVO.setArticulosAReponer(artsReVO);
							ArrayList<String> descripciones = new ArrayList<String>();
							for(int k=0 ; k < articulos.size() ; k++){
								descripciones.add(articulos.get(k).getDescripcion());
							}
							int idSolFab = solRepVO.getSolFab().getIdFab();
							solFabVO = this.ref.getVistaRepArt().getModelo().getSolicitudFabricacion(idSolFab);
							solRepVO.setSolFab(solFabVO);
							solRepVO.setFechaEmision(ref.getDate());
							CentroDistribucionVO centroVO = this.ref.getVistaSolDis().getModelo().getCentro();
							solRepVO.setCdVO(centroVO);
							long codigoSolRep = solRepVO.getIdRep();
							String fabrica = solRepVO.getFabrica().getNombreFabrica();

							vaciarTabla();
							cargarTable(codigoSolRep,solRepVO, codigos, descripciones, solFabVO,fabrica);
							ref.getJTextArea1().append(ref.getDate()+": Solicitud de Reposicion Cargada\n");
							this.buttonGuardar.setEnabled(true);
							this.buttonCargarXML.setEnabled(false);
						}	
					}	
				}
			}
		}else{
			//Falta generar las solicitudes
			ArrayList<ArticuloAReponerVO> artsRep = collectionToArrayList(solRepVO.getArticulosAReponer());
			this.ref.getVistaRepArt().getModelo().guardarSolicitudReposicion(solRepVO);
			this.ref.getVistaRepArt().getModelo().actualizarSolicitudFabricacion(solFabVO);
			this.ref.getVistaRepArt().getModelo().actualizarStock(artsRep);
			vaciarTabla();
			ref.getJTextArea1().append(ref.getDate()+": Solicitudes de Reposicion guardada en el Centro de Distribucion\n");
			this.buttonGuardar.setEnabled(false);
			this.buttonCargarXML.setEnabled(true);
			String mensaje = codificarDetalle(artsRep);
			new Dialogo3Opciones("Operacion concretada", this, mensaje).setVisible(true);
		}
	}

	private String codificarDetalle(ArrayList<ArticuloAReponerVO> artsRep) {
		String msj = new String();
		msj = "Articulos a Reponer:\n";
		for(int i = 0 ; i < artsRep.size() ; i++){
			ArticuloAReponerVO art = artsRep.get(i);
			msj = msj + "Cod: "+art.getArt().getCodigo()+" Cant: "+art.getCantidad()+"\n";
		}
		return msj;
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
