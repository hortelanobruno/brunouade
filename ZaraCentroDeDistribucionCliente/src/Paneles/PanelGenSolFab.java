/*
 * PanelGenSolFab.java
 *
 * Created on 22 de mayo de 2008, 22:38
 */

package Paneles;

import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import BusinessLogic.BusinessDelegate;
import GUI.Dialogo3Opciones;
import GUI.MenuPrincipal;
import VO.ArticuloAFabricarVO;
import VO.CentroDistribucionVO;
import VO.FabricaVO;
import VO.SolicitudFabricaVO;
import Varios.Constantes;
import Varios.XMLWrapper;
import Vistas.VistaGenSolFab;
import controladores.ControladorPanelGenSolFab;

/**
 * 
 * @author Administrador
 */
public class PanelGenSolFab extends javax.swing.JPanel {
	private static final long serialVersionUID = -8198521566525463103L;

	private MenuPrincipal ref;

	private VistaGenSolFab vistaGenSolFab;
	private ArrayList<ArticuloAFabricarVO> articulosAFabricar;
	private boolean cargarTabla;
	private javax.swing.JComboBox comboFabricas;
    private javax.swing.JLabel jLabel1;
    private ArrayList<FabricaVO> fabricas;

	/** Creates new form PanelGenSolFab */
	public PanelGenSolFab(MenuPrincipal r, VistaGenSolFab vista) {
		initComponents();
		this.ref = r;
		this.vistaGenSolFab = vista;
		cargarTabla = false;
		tablaArticulos.getModel().addTableModelListener(
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
	@SuppressWarnings({ "unchecked", "serial" })
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

        buttonCargarPendientes = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaArticulos = new javax.swing.JTable();
        buttonEnviar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        comboFabricas = new javax.swing.JComboBox();

        buttonCargarPendientes.setText("Cargar Articulos Pendientes");
        buttonCargarPendientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCargarPendientesActionPerformed(evt);
            }
        });

        tablaArticulos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Descripcion", "Stock actual", "Stock pedido", "Stock recibido", "Stock minimio a pedir", "Cantidad a fabricar"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Long.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
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
        tablaArticulos.setRowSelectionAllowed(false);
        tablaArticulos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tablaArticulos.getTableHeader().setResizingAllowed(false);
        tablaArticulos.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tablaArticulos);

        buttonEnviar.setText("Generar Solicitud de Fabricacion");
        buttonEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEnviarActionPerformed(evt);
            }
        });

        jLabel1.setText("Fabrica");

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(31, 31, 31)
                        .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 763, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(layout.createSequentialGroup()
                        .add(55, 55, 55)
                        .add(jLabel1)
                        .add(76, 76, 76)
                        .add(comboFabricas, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 106, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(34, 34, 34)
                        .add(buttonCargarPendientes)
                        .add(18, 18, 18)
                        .add(buttonEnviar)))
                .addContainerGap(245, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(94, 94, 94)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 225, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(32, 32, 32)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel1)
                    .add(comboFabricas, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(buttonCargarPendientes)
                    .add(buttonEnviar))
                .addContainerGap(76, Short.MAX_VALUE))
        );
	}// </editor-fold>

	private void buttonCargarPendientesActionPerformed(java.awt.event.ActionEvent evt) {
		// Cargar articulos pendientes
		((ControladorPanelGenSolFab) vistaGenSolFab.getControlador())
				.doCargarArticulosPendientes(true);
	}

	private void buttonEnviarActionPerformed(java.awt.event.ActionEvent evt) {
		// Generar SolFab
		((ControladorPanelGenSolFab) vistaGenSolFab.getControlador())
		.doCargarArticulosPendientes(false);
	}
	
	private void validateTable() {
		int cantCeros = 0;
		for (int i = 0; i < tablaArticulos.getModel().getRowCount(); i++) {
			int valor = Integer.parseInt(tablaArticulos.getModel().getValueAt(
					i, 6).toString());
			int minimo = Integer.parseInt(tablaArticulos.getModel().getValueAt(
					i, 5).toString());

			if (valor == 0)
				cantCeros++;
			if(valor<minimo){
				//tablaArticulos.getModel().setValueAt(0,i, 6);
				JOptionPane.showMessageDialog(this,
						"El valor ingresado es menor a la cantidad minima a pedir.",
						Constantes.APPLICATION_NAME,
						JOptionPane.ERROR_MESSAGE);
				break;
			}
			if(valor<0){
				//tablaArticulos.getModel().setValueAt(0,i, 6);
				JOptionPane.showMessageDialog(this,
						"El valor ingresado tiene que ser un numero positivo.",
						Constantes.APPLICATION_NAME,
						JOptionPane.ERROR_MESSAGE);
				break;
			}
		}
		if (cantCeros < tablaArticulos.getModel().getRowCount()) {
			buttonEnviar.setEnabled(true);
		} else {
			buttonEnviar.setEnabled(false);
		}
	}
	
	public void cargarCombo(){
		fabricas = ((BusinessDelegate) vistaGenSolFab.getModelo()).getFabricas();
    	if(!fabricas.isEmpty()){
    		for (int i = 0 ; i< fabricas.size() ; i++){
    			FabricaVO fabrica = fabricas.get(i);
    			comboFabricas.addItem(fabrica.getNombreFabrica());
    		}
    	}else{
    		JOptionPane.showMessageDialog(this,
					"No se encontraron fabricas registradas en el Centro de Distribucion.",
					Constantes.APPLICATION_NAME,
					JOptionPane.ERROR_MESSAGE);
    	}
	}
	
	
	public void update() {
		
		if(cargarTabla){
			ArrayList<ArticuloAFabricarVO> arts = this.ref.getVistaGenSolFab().getModelo().getArticulosAFabricarVO();
			this.setArticulosAFabricar(arts);
			cargarTabla(arts);
			cargarCombo();
			ref.getJTextArea1().append(ref.getDate()+": Articulos a Fabricar Cargados\n");
		}else{
			ArrayList<ArticuloAFabricarVO> arts = leerArticulosDeTabla();
			SolicitudFabricaVO solFab = new SolicitudFabricaVO();
			int id = this.ref.getVistaSolDis().getModelo().getNextId();
			id++;
			solFab.setId(id);
			solFab.setArticulosAFabricar(arts);
			CentroDistribucionVO centroVO = this.ref.getVistaGenSolFab().getModelo().getCentro();
			solFab.setCdVO(centroVO);
			//aca hay que arreglar que la fabrica se setee de una combo o algo asi
			String fab = comboFabricas.getSelectedItem().toString();
			for(int j=0 ; j<fabricas.size() ; j++){
				if(fabricas.get(j).getNombreFabrica().equals(fab)){
					solFab.setFabrica(fabricas.get(j));
				}
			}
			//aca hay que arreglar la fecha...
			solFab.setFechaEmision(ref.getDate());
			int idSolFab = this.ref.getVistaGenSolFab().getModelo().getNumeroSolFab();
			idSolFab++;
			solFab.setIdFab(idSolFab);
			this.ref.getVistaGenSolFab().getModelo().guardarSolicitudFabricacion(solFab);
			vaciarTabla();
			XMLWrapper xml = new XMLWrapper();
			xml.parseXMLSolFab(solFab);
			ref.getJTextArea1().append(ref.getDate()+": Solicitud de Fabricacion generada\n");
			String msj = codificarDetalle(solFab);
			new Dialogo3Opciones("Operacion concretada", this,msj).setVisible(true);
		}
	}
	
	private String codificarDetalle(SolicitudFabricaVO solFab) {
		String msj = new String();
		msj = "Solicitud de Fabricacion\n\n";
		msj = msj + "Numero: "+solFab.getIdFab()+"\n";
		msj = msj + "Fecha: "+solFab.getFechaEmision().toString()+"\n";
		msj = msj + "Fabrica: "+solFab.getFabrica().getNombreFabrica()+"\n";
		msj = msj + "Centro Distribucion: "+solFab.getCdVO().getNombreCentro()+"\n";
		msj = msj + "Articulos:\n ";
		Iterator arts = solFab.getArticulosAFabricar().iterator();
		while(arts.hasNext()){
			ArticuloAFabricarVO art = (ArticuloAFabricarVO) arts.next();
			msj = msj +"Cod: "+art.getArt().getCodigo()+" Cant: "+art.getCantidadPedida()+"\n";
		}
		return msj;
	}

	public ArrayList<ArticuloAFabricarVO> leerArticulosDeTabla(){
		ArrayList<ArticuloAFabricarVO> arts = new ArrayList<ArticuloAFabricarVO>();
		for (int i = 0 ; i < articulosAFabricar.size() ; i++){
			ArticuloAFabricarVO artVO = articulosAFabricar.get(i);
			int cantAFabricar = Integer.parseInt(((DefaultTableModel)tablaArticulos.getModel()).getValueAt(i, 6).toString());
			artVO.setCantidadAFabricar(cantAFabricar);
			arts.add(artVO);
		}
		return arts;
	}
	 /*"Codigo", "Descripcion", "Stock Actual",
		"Stock Pedido","Stock Recibido", "Stock Minimo a Pedir",
		"Cantidad a Fabricar"*/
	public void cargarTabla(ArrayList<ArticuloAFabricarVO> arts){
		for (int i = 0 ; i < arts.size() ; i++ ){
			ArticuloAFabricarVO artVO = arts.get(i);
			long codigo = artVO.getArt().getCodigo();
			String descripcion = artVO.getArt().getDescripcion();
			int stockActual = artVO.getArt().getCantidad();
			int stockPedido = artVO.getCantidadPedida();
			int stockRecibido = artVO.getCantidadRecibida();
			int stockMinimoAPedir = (stockPedido - stockRecibido) * 2;
			int cantAFabricar = 0;
			((DefaultTableModel) tablaArticulos.getModel())
			.addRow(new Object[] { codigo, descripcion, stockActual,stockPedido,stockRecibido,stockMinimoAPedir,cantAFabricar});
		}
	}

	public void vaciarTabla() {
		((DefaultTableModel)tablaArticulos.getModel()).getDataVector().removeAllElements();
	}

	// Variables declaration - do not modify
	private javax.swing.JButton buttonCargarPendientes;

	private javax.swing.JButton buttonEnviar;

	private javax.swing.JScrollPane jScrollPane1;

	private javax.swing.JTable tablaArticulos;

	// End of variables declaration

	public MenuPrincipal getRef() {
		return ref;
	}

	public void setRef(MenuPrincipal ref) {
		this.ref = ref;
	}

	public VistaGenSolFab getVistaGenSolFab() {
		return vistaGenSolFab;
	}

	public void setVistaGenSolFab(VistaGenSolFab vistaGenSolFab) {
		this.vistaGenSolFab = vistaGenSolFab;
	}

	public boolean isCargarTabla() {
		return cargarTabla;
	}

	public void setCargarTabla(boolean cargarTabla) {
		this.cargarTabla = cargarTabla;
	}

	public ArrayList<ArticuloAFabricarVO> getArticulosAFabricar() {
		return articulosAFabricar;
	}

	public void setArticulosAFabricar(
			ArrayList<ArticuloAFabricarVO> articulosAFabricar) {
		this.articulosAFabricar = articulosAFabricar;
	}

}
