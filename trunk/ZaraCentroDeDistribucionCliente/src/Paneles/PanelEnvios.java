/*
 * PanelEnvios.java
 *
 * Created on 22 de mayo de 2008, 22:26
 */

package Paneles;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import BusinessLogic.BusinessDelegate;
import GUI.Dialogo3Opciones;
import GUI.MenuPrincipal;
import VO.ArticuloAEnviarVO;
import VO.ArticuloPedidoVO;
import VO.ArticuloReservadoVO;
import VO.SolicitudDistribucionVO;
import VO.SolicitudEnvioVO;
import VO.TiendaVO;
import Varios.Constantes;
import Varios.XMLWrapper;
import Vistas.VistaEnvios;
import controladores.ControladorPanelEnvios;

/**
 * 
 * @author Administrador
 */
public class PanelEnvios extends javax.swing.JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private MenuPrincipal ref;
	public boolean cargarTable;
	public boolean cargarTree;
	private VistaEnvios vistaEnvios;
	private SolicitudDistribucionVO solDis;
	private ArrayList<ArticuloReservadoVO> articulosReservados;
	private ArrayList<SolicitudDistribucionVO> solicitudes;
	private TreeSelectionEvent eventoTree;
	
	/** Creates new form PanelEnvios */
	public PanelEnvios(MenuPrincipal menu, VistaEnvios vista) {
		this.ref = menu;
		this.vistaEnvios = vista;
		initComponents();
		buttonEnviarTienda.setEnabled(false);
		tablePendientes.getModel().addTableModelListener(
				new TableModelListener() {
					public void tableChanged(TableModelEvent e) {
						if (e.getColumn() > -1){
							int valor = Integer.parseInt(((DefaultTableModel)tablePendientes.getModel()).getValueAt(e.getFirstRow(), e.getColumn()).toString());
							if(valor != 0){
								validateTable();	
							}else{
								int cantCeros = 0;
								for (int i = 0; i < tablePendientes.getModel().getRowCount(); i++) {
									int val = Integer.parseInt(tablePendientes.getModel().getValueAt(
											i, 6).toString());
									if(val == 0){
										cantCeros++;
									}
								}
								if (cantCeros < tablePendientes.getModel().getRowCount()) {
									buttonEnviarTienda.setEnabled(true);
								} else {
									buttonEnviarTienda.setEnabled(false);
								}
							}
						}
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

        jLabel1 = new javax.swing.JLabel();
        comboBoxTiendas = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablePendientes = new javax.swing.JTable();
        buttonEnviarTienda = new javax.swing.JButton();

        jLabel1.setText("Tienda");

        comboBoxTiendas.setModel(new javax.swing.DefaultComboBoxModel(
    			new String[] {}));
    	ArrayList<TiendaVO> tiendas = ((BusinessDelegate) vistaEnvios.getModelo()).obtenerTiendas();
    	Vector<String> cars = null;
    	if(!tiendas.isEmpty()){
    		cars = new Vector<String>();
    		for (int i = 0 ; i< tiendas.size() ; i++){
    			TiendaVO tienda = tiendas.get(i);
    			cars.add(tienda.getNombreTienda());
    		}
    		comboBoxTiendas = new JComboBox(cars);
    	}else{
    		comboBoxTiendas = new JComboBox();
    	}
    	comboBoxTiendas.setPreferredSize(new Dimension(100,20));
    	comboBoxTiendas.addActionListener(new java.awt.event.ActionListener() {
    		public void actionPerformed(java.awt.event.ActionEvent evt) {
    			comboBoxTiendasActionPerformed(evt);
    		}
    	});

    	DefaultMutableTreeNode abuelo = new DefaultMutableTreeNode("Solicitudes");
		DefaultTreeModel modelo = new DefaultTreeModel(abuelo);
		treeArticulos = new JTree(modelo);
        jScrollPane1.setViewportView(treeArticulos);

        tablePendientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Descripcion", "Cantidad pedida", "Cantidad reservada", "Stock", "Cantidad enviada", "Cantidad a enviar"
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
        tablePendientes.setRowSelectionAllowed(false);
        tablePendientes.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tablePendientes.getTableHeader().setResizingAllowed(false);
        tablePendientes.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(tablePendientes);

        buttonEnviarTienda.setText("Generar Solicitud de Envio");
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
                        .add(24, 24, 24)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(layout.createSequentialGroup()
                                .add(jLabel1)
                                .add(29, 29, 29)
                                .add(comboBoxTiendas, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, jScrollPane1))
                        .add(32, 32, 32)
                        .add(jScrollPane3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 740, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(layout.createSequentialGroup()
                        .add(415, 415, 415)
                        .add(buttonEnviarTienda)))
                .addContainerGap(117, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(19, 19, 19)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel1)
                    .add(comboBoxTiendas, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(26, 26, 26)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(jScrollPane3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
                        .add(18, 18, 18)
                        .add(buttonEnviarTienda)
                        .add(34, 34, 34))
                    .add(layout.createSequentialGroup()
                        .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 246, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
	}// </editor-fold>

	private void buttonCargarActionPerformed(javax.swing.event.TreeSelectionEvent evt) {
		// Cargar solicitud en tablas
		this.eventoTree = evt;
		if(!eventoTree.getPath().getLastPathComponent().toString().equals("Solicitudes")){
			((ControladorPanelEnvios) vistaEnvios.getControlador()).doCargarSolicitud(true,false);
		}
		
	}

	private void comboBoxTiendasActionPerformed(java.awt.event.ActionEvent evt) {
		// Se selecciono una tienda y cargo las solicitudes en el tree
		((ControladorPanelEnvios) vistaEnvios.getControlador()).doTiendaSeleccionada(true);
	}

	private void buttonEnviarTiendaActionPerformed(
			java.awt.event.ActionEvent evt) {
			((ControladorPanelEnvios) vistaEnvios.getControlador()).doCargarSolicitud(false,false);
	}

	private void validateTable() {
		int cantCeros = 0;
		for (int i = 0; i < tablePendientes.getModel().getRowCount(); i++) {
			//"Codigo", "Descripcion", "Cantidad pedida", "Cantidad reservada", "Stock", "Cantidad enviada", "Cantidad a enviar"
			int cantEnviar = Integer.parseInt(tablePendientes.getModel().getValueAt(
					i, 6).toString());
			int cantEnviada = Integer.parseInt(tablePendientes.getModel().getValueAt(
					i, 5).toString());
			int stock = Integer.parseInt(tablePendientes.getModel().getValueAt(
					i, 4).toString());
			int cantReservada = Integer.parseInt(tablePendientes.getModel().getValueAt(
					i, 3).toString());
			int cantPedida = Integer.parseInt(tablePendientes.getModel().getValueAt(
					i, 2).toString());
			if (cantEnviar != 0){
				if(cantEnviar<0){
					tablePendientes.getModel().setValueAt(0,i, 6);
					JOptionPane.showMessageDialog(this,
							"El valor ingresado tiene que ser un numero positivo.",
							Constantes.APPLICATION_NAME,
							JOptionPane.ERROR_MESSAGE);
					break;
				}
				if(cantPedida<cantEnviar+cantEnviada){
					tablePendientes.getModel().setValueAt(0,i, 6);
					JOptionPane.showMessageDialog(this,
							"El valor ingresado es mayor a la cantidad pedida.",
							Constantes.APPLICATION_NAME,
							JOptionPane.ERROR_MESSAGE);
					break;
				}
				if(stock+cantReservada<cantEnviar){
					tablePendientes.getModel().setValueAt(0,i, 6);
					JOptionPane.showMessageDialog(this,
							"El valor ingresado es mayor al stock.",
							Constantes.APPLICATION_NAME,
							JOptionPane.ERROR_MESSAGE);
					break;
				}
			}
		}
		for (int i = 0; i < tablePendientes.getModel().getRowCount(); i++) {
			int valor = Integer.parseInt(tablePendientes.getModel().getValueAt(
					i, 6).toString());
			if(valor == 0){
				cantCeros++;
			}
		}
		if (cantCeros < tablePendientes.getModel().getRowCount()) {
			buttonEnviarTienda.setEnabled(true);
		} else {
			buttonEnviarTienda.setEnabled(false);
		}
	}

	
	
	public void cargarArbol(ArrayList<SolicitudDistribucionVO> solicitudes){
		DefaultMutableTreeNode abuelo = new DefaultMutableTreeNode("Solicitudes");
		DefaultTreeModel modelo = new DefaultTreeModel(abuelo);
		treeArticulos = new JTree(modelo);
		DefaultMutableTreeNode[] padre = new DefaultMutableTreeNode[solicitudes.size()];
		Iterator it = solicitudes.iterator();
		int i = 0;
		while(it.hasNext()){
			SolicitudDistribucionVO sol = (SolicitudDistribucionVO) it.next();
			padre[i] = new DefaultMutableTreeNode(sol.getIdDis());
			i++;
		}
		for(int j = 0 ; j < padre.length ; j++){
			modelo.insertNodeInto(padre[j],abuelo, j);
		}
		treeArticulos.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
            public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
            	buttonCargarActionPerformed(evt);
            }
        });
		jScrollPane1.setViewportView(treeArticulos);
	}
	
	
	public void update() {
		if(cargarTree){
			vaciarTabla();
			tablePendientes.updateUI();
			String tienda = comboBoxTiendas.getSelectedItem().toString();
			solicitudes = ((BusinessDelegate) vistaEnvios.getModelo()).obtenerSolicitudesDeTienda(tienda);
			cargarArbol(solicitudes);
			ref.getJTextArea1().append(ref.getDate()+": Solicitudes de Distribucion cargadas\n");
		}else{
			if(cargarTable){
				int codSolDis = Integer.parseInt(eventoTree.getPath().getLastPathComponent().toString());
				for (int i = 0 ; i < solicitudes.size() ; i++){
					solDis = solicitudes.get(i);
					if(solDis.getIdDis() == codSolDis){
						break;
					}
				}
				articulosReservados = ((BusinessDelegate) vistaEnvios.getModelo()).obtenerArticulosReservados(codSolDis);
				ArrayList<Long> codigos = new ArrayList<Long>();
				Iterator solArt = solDis.getArticulosPedidos().iterator();
				while(solArt.hasNext()){
					ArticuloPedidoVO artPed = (ArticuloPedidoVO) solArt.next();
					codigos.add(artPed.getArt().getCodigo());
				}
				ArrayList<Integer> stocks = ((BusinessDelegate) vistaEnvios.getModelo()).getStocks(codigos);
				ArrayList<ArticuloAEnviarVO> artsAEnviar = ((BusinessDelegate) vistaEnvios.getModelo()).getArtsAEnv(codSolDis);
				vaciarTabla();
				cargarTable(solDis,articulosReservados,stocks,artsAEnviar);
				ref.getJTextArea1().append(ref.getDate()+": Solicitud de Distribucion cargada\n");
			}else{
				SolicitudEnvioVO solEnvio = new SolicitudEnvioVO();
				int id = this.ref.getVistaSolDis().getModelo().getNextId();
				solEnvio.setId(id);
				int idAE = ((BusinessDelegate) vistaEnvios.getModelo()).getNextIdAEnv();
				ArrayList<ArticuloAEnviarVO> articulosAEnviar = articulosAEnviarDeTabla(idAE);
				solEnvio.setArticulosAEnviar(articulosAEnviar);
				solEnvio.setFechaEmision(ref.getDate());
				solEnvio.setTienda(solDis.getTienda());
				int numero = ((BusinessDelegate) vistaEnvios.getModelo()).getNumeroSolEnv();
				solEnvio.setIdEnv(numero);
				solEnvio.setCdVO(solDis.getCdVO());
				boolean cerrado = comprobarCerrado();
				if (cerrado){
					solDis.setCerrada(cerrado);
				}
				((BusinessDelegate) vistaEnvios.getModelo()).guardarSolicitudDeEnvio(solEnvio);
				((BusinessDelegate) vistaEnvios.getModelo()).actualizarStock(articulosAEnviar,articulosReservados);
				((BusinessDelegate) vistaEnvios.getModelo()).actualizarArticulosReservados(articulosReservados);
				((BusinessDelegate) vistaEnvios.getModelo()).actualizarSolicitudDistribucion(solDis);
				XMLWrapper xml = new XMLWrapper();
				xml.parseXMLSolEnvio(solEnvio);
				vaciarTabla();
				buttonEnviarTienda.setEnabled(false);
				ref.getJTextArea1().append(ref.getDate()+": Solicitud de Envio generada\n");
				String msj = codificarDetalle(solEnvio);
				new Dialogo3Opciones("Operacion concretada", this,msj).setVisible(true);
			}
		}
	}
	
	private String codificarDetalle(SolicitudEnvioVO solEnvio) {
		String msj = new String();
		msj = "Solicitud de envio: \n\n";
		msj = msj +"Numero: "+solEnvio.getIdEnv()+"\n";
		msj = msj +"Fecha: "+solEnvio.getFechaEmision()+"\n";
		msj = msj +"Tienda: "+solEnvio.getTienda().getNombreTienda()+"\n";
		msj = msj +"Centro Distribucion: "+solEnvio.getCdVO().getNombreCentro()+"\n";
		msj = msj +"Articulos: \n";
		Iterator arts = solEnvio.getArticulosAEnviar().iterator();
		while(arts.hasNext()){
			ArticuloAEnviarVO art = (ArticuloAEnviarVO) arts.next();
			msj = msj + "Cod: "+art.getArt().getCodigo()+" Cant: "+art.getCantidadAEnviar()+"\n";
		}
		return msj;
	}

	public void vaciarTabla() {
		((DefaultTableModel)tablePendientes.getModel()).getDataVector().removeAllElements();
	}

	public boolean comprobarCerrado(){
		boolean cerrado = true;
		for(int i = 0 ; i < tablePendientes.getRowCount() ; i++){
			int cantPed = Integer.parseInt((((DefaultTableModel) tablePendientes.getModel()).getValueAt(i, 2)).toString());
			int cantEnv = Integer.parseInt((((DefaultTableModel) tablePendientes.getModel()).getValueAt(i, 6)).toString()) +
			Integer.parseInt((((DefaultTableModel) tablePendientes.getModel()).getValueAt(i, 5)).toString());
			if(cantPed != cantEnv){
				cerrado = false;
			}
		}
		return cerrado;
	}
	
	private ArrayList<ArticuloAEnviarVO> articulosAEnviarDeTabla(int idAE) {
		ArrayList<ArticuloAEnviarVO> articulosAEnviar = new ArrayList<ArticuloAEnviarVO>();
		Iterator art = solDis.getArticulosPedidos().iterator();
		for(int i = 0 ; i < tablePendientes.getRowCount() ; i++){
			int cantEnv = Integer.parseInt((((DefaultTableModel) tablePendientes.getModel()).getValueAt(i, 6)).toString());
			if(cantEnv > 0){
				long codigo = Long.parseLong((((DefaultTableModel) tablePendientes.getModel()).getValueAt(i, 0)).toString());
				ArticuloAEnviarVO artEnv = new ArticuloAEnviarVO();
				artEnv.setSolDis(solDis);
				artEnv.setCantidadAEnviar(cantEnv);
				artEnv.setIdAAE(idAE);
				while(art.hasNext()){
					ArticuloPedidoVO artPed = (ArticuloPedidoVO) art.next();
					if(artPed.getArt().getCodigo() == codigo){
						artEnv.setArt(artPed.getArt());
					}
				}
				articulosAEnviar.add(artEnv);
				art = solDis.getArticulosPedidos().iterator();
				idAE++;
			}
		}
		return articulosAEnviar;
	}

	private void cargarTable(SolicitudDistribucionVO solDis2, ArrayList<ArticuloReservadoVO> articulosReservados2, ArrayList<Integer> stocks, ArrayList<ArticuloAEnviarVO> artsAEnviar) {
		Iterator artsReserv = articulosReservados2.iterator();
		Iterator artsPed = solDis2.getArticulosPedidos().iterator();
		Iterator artsEnvs = artsAEnviar.iterator();
		ArticuloAEnviarVO artEnv;
		int count = 0;
		ArticuloReservadoVO artRes;
		int cantres = 0;
		int cantenv = 0;
		while(artsPed.hasNext()){
			ArticuloPedidoVO artPed = (ArticuloPedidoVO) artsPed.next();
			int stock = stocks.get(count);
			long codigo = artPed.getArt().getCodigo();
			while(artsReserv.hasNext()){
				artRes = (ArticuloReservadoVO) artsReserv.next();
				if(codigo == artRes.getArt().getCodigo()){
					cantres = artRes.getCantidadReservada();
					break;
				}
			}
			while(artsEnvs.hasNext()){
				artEnv = (ArticuloAEnviarVO) artsEnvs.next();
				if(codigo == artEnv.getArt().getCodigo()){
					cantenv = artEnv.getCantidadAEnviar();
					break;
				}
			}
			((DefaultTableModel) tablePendientes.getModel())
			.addRow(new Object[] {codigo,artPed.getArt().getDescripcion(),artPed.getCantidad(),cantres,stock,cantenv,0});
			artsReserv = articulosReservados2.iterator();
			artsEnvs = artsAEnviar.iterator();
			count++;
			cantres = 0;
			cantenv = 0;
		}
	}

	// Variables declaration - do not modify

	private javax.swing.JButton buttonEnviarTienda;

	private javax.swing.JComboBox comboBoxTiendas;

	private javax.swing.JLabel jLabel1;

	private javax.swing.JScrollPane jScrollPane1;

	private javax.swing.JScrollPane jScrollPane3;

	private javax.swing.JTable tablePendientes;

	private javax.swing.JTree treeArticulos;

	// End of variables declaration

	public VistaEnvios getVistaEnvios() {
		return vistaEnvios;
	}

	public void setVistaEnvios(VistaEnvios vistaEnvios) {
		this.vistaEnvios = vistaEnvios;
	}

	public MenuPrincipal getRef() {
		return ref;
	}

	public void setRef(MenuPrincipal ref) {
		this.ref = ref;
	}

	public void setCargarTable(boolean b) {
		this.cargarTable = b;
	}
	
	public boolean getCargarTable(){
		return this.cargarTable;
	}

	public void setCargarTree(boolean b) {
		this.cargarTree = b;
	}
	
	public boolean getCargarTree(){
		return this.cargarTree;
	}

}
