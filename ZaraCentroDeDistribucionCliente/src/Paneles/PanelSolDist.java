/*
 * PanelSolDist.java
 *
 * Created on 22 de mayo de 2008, 22:58
 */

package Paneles;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import BusinessLogic.BusinessDelegate;
import GUI.Dialogo3Opciones;
import GUI.FileChooser;
import GUI.MenuPrincipal;
import VO.ArticuloAFabricarVO;
import VO.ArticuloHeaderVO;
import VO.ArticuloPedidoVO;
import VO.ArticuloReservadoVO;
import VO.CentroDistribucionVO;
import VO.FabricaVO;
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
	private ArrayList<FabricaVO> fabricas;
	private SolicitudDistribucionVO solDisVO;

	private VistaSolDis vistaSolDis;

	/** Creates new form PanelSolDist */
	public PanelSolDist(MenuPrincipal mn, VistaSolDis vista) {
		initComponents();
		this.buttonGuardarPedido.setEnabled(false);
		this.ref = mn;
		this.vistaSolDis = vista;
		tableArticulos.getModel().addTableModelListener(
				new TableModelListener() {
					public void tableChanged(TableModelEvent e) {
						if (e.getColumn() > -1){
							int valor = Integer.parseInt(((DefaultTableModel)tableArticulos.getModel()).getValueAt(e.getFirstRow(), e.getColumn()).toString());
							if(valor != 0){
								validateTable();	
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

        buttonCargarXML = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableArticulos = new javax.swing.JTable();
        buttonGuardarPedido = new javax.swing.JButton();

        buttonCargarXML.setText("Cargar Solicitud de Distribucion");
        buttonCargarXML.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCargarXMLActionPerformed(evt);
            }
        });

        tableArticulos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Numero Solicitud", "Tienda", "Codigo Art.", "Descripcion", "Cantidad Pedida", "Stock Actual", "Cantidad a Enviar"
            }
        ) {
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
        tableArticulos.getTableHeader().setResizingAllowed(false);
        tableArticulos.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tableArticulos);

        buttonGuardarPedido.setText("Guardar Pedido");
        buttonGuardarPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGuardarPedidoActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(43, 43, 43)
                        .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 705, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(layout.createSequentialGroup()
                        .add(55, 55, 55)
                        .add(buttonCargarXML)
                        .add(29, 29, 29)
                        .add(buttonGuardarPedido)))
                .addContainerGap(292, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(90, 90, 90)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 204, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(50, 50, 50)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(buttonCargarXML)
                    .add(buttonGuardarPedido))
                .addContainerGap(82, Short.MAX_VALUE))
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
		String chooserButton = chooser.getButton();
		if (chooserButton.equals("Cancel")) {
		
		}else {
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
							solDisVO.getIdDis(),
							solDisVO.getTienda().getNombreTienda(),
							codigos.get(i).toString(),
							descripciones.get(i).toString(),
							((ArticuloPedidoVO) iterador.next()).getCantidad(),
							stocks.get(i).toString(), 0 });
		}
	}

	private void validateTable() {
		int cantCeros = 0;

		for (int i = 0; i < tableArticulos.getModel().getRowCount(); i++) {
			int valor = Integer.parseInt(tableArticulos.getModel().getValueAt(
					i, 6).toString());
			int pedido = Integer.parseInt(tableArticulos.getModel().getValueAt(
					i, 4).toString());
			int stock = Integer.parseInt(tableArticulos.getModel().getValueAt(
					i, 5).toString());

			if (valor == 0)
				cantCeros++;
			if ((valor < 0) || (valor > pedido)) {
				if (valor > pedido){
					tableArticulos.getModel().setValueAt(0,i, 6);
					JOptionPane.showMessageDialog(this,
							"El valor ingresado es mayor al pedido.",
							Constantes.APPLICATION_NAME,
							JOptionPane.ERROR_MESSAGE);
					break;
				}
				tableArticulos.getModel().setValueAt(0,i, 6);
				JOptionPane.showMessageDialog(this,
						"El valor ingresado tiene que ser un numero positivo.",
						Constantes.APPLICATION_NAME,
						JOptionPane.ERROR_MESSAGE);
				break;
			}
			if(stock<valor){
				tableArticulos.getModel().setValueAt(0,i, 6);
				JOptionPane.showMessageDialog(this,
						"El valor ingresado es mayor al stock actual.",
						Constantes.APPLICATION_NAME,
						JOptionPane.ERROR_MESSAGE);
				break;
			}
		}
		if (cantCeros < tableArticulos.getModel().getRowCount()) {
			buttonGuardarPedido.setEnabled(true);
		} else {
			buttonGuardarPedido.setEnabled(false);
		}
	}

	public void update() {
		// aca hay que poner las llamadas a la business delegate
		// hay que arreglar LA FECHA DE SOLDIS
		if (cargarTable) {
			XMLWrapper xml = new XMLWrapper();
			solDisVO = (SolicitudDistribucionVO) xml.parseXMLSD(urlXML);
			if(solDisVO == null){
				JOptionPane.showMessageDialog(this,"Archivo incorrecto",Constantes.APPLICATION_NAME,JOptionPane.ERROR_MESSAGE);
			}else{
				if(((BusinessDelegate)vistaSolDis.getModelo()).existeSolDis(solDisVO.getIdDis())){
					vaciarTabla();
					ref.getJTextArea1().append(ref.getDate()+": Solicitud de Distribucion 'existente' en el Centro de Distribucion \n");
					this.buttonCargarXML.setEnabled(true);
					this.buttonGuardarPedido.setEnabled(false);
					JOptionPane.showMessageDialog(this,"La Solicitud de Distribucion ya existe",Constantes.APPLICATION_NAME,JOptionPane.ERROR_MESSAGE);
				}else{
					ArrayList<Long> codigos = new ArrayList<Long>();
					int idMax = this.ref.getVistaSolDis().getModelo().getNextIdArtPed();
					Iterator arts = (Iterator) solDisVO.getArticulosPedidos().iterator();
					ArrayList<ArticuloPedidoVO> artsVO = new ArrayList<ArticuloPedidoVO>();
					while (arts.hasNext()) {
						ArticuloPedidoVO artVO = ((ArticuloPedidoVO) arts.next());
						artVO.setIdAP(idMax);
						idMax++;
						codigos.add(artVO.getArt().getCodigo());
						artsVO.add(artVO);
					}
					ArrayList<Long>  verCod = ((BusinessDelegate)vistaSolDis.getModelo()).existenArts(codigos);
					if(!verCod.isEmpty()){
						String codsfalse = "Cod. "+verCod.get(0);
						for(int q = 1 ; q < verCod.size() ; q++){
							codsfalse = codsfalse + " Cod. "+verCod.get(q);
						}
						vaciarTabla();
						ref.getJTextArea1().append(ref.getDate()+": La solicitud contiene articulos que no existen en el Centro de Distribucion\n");
						this.buttonCargarXML.setEnabled(true);
						this.buttonGuardarPedido.setEnabled(false);
						JOptionPane.showMessageDialog(this,"La solicitud contiene articulos que no existen en el Centro de Distribucion\n" +
								"("+codsfalse+")",Constantes.APPLICATION_NAME,JOptionPane.ERROR_MESSAGE);
					}else{
						solDisVO.setArticulosPedidos(artsVO);
						int id = this.ref.getVistaSolDis().getModelo().getNextId();
						//id++;
						solDisVO.setId(id);
						//solDisVO.setFechaEmision(ref.getDate());
						ArrayList<String> descripciones = this.ref.getVistaSolDis()
								.getModelo().getDescripciones(codigos);
						ArrayList<Integer> stocks = this.ref.getVistaSolDis().getModelo().getStocks(codigos) ;
						CentroDistribucionVO centroVO = this.ref.getVistaSolDis().getModelo().getCentro();
						solDisVO.setCdVO(centroVO);
						solDisVO.setCerrada(false);
						cargarTable(solDisVO, codigos, descripciones, stocks);
						this.buttonCargarXML.setEnabled(false);
						ref.getJTextArea1().append(ref.getDate()+": Solicitud de Distribucion Cargada\n");
					}
				}
			}
		} else {
			// Falta generar las solicitudes
			Collection<ArticuloAFabricarVO> artiAFab = (Collection<ArticuloAFabricarVO>) articulosFabricarDeTabla();
			Collection<ArticuloReservadoVO> artiReser = (Collection<ArticuloReservadoVO>) articulosEnviarDeTabla();
			((BusinessDelegate) vistaSolDis.getModelo()).guardarSolicitud(solDisVO);
			((BusinessDelegate) vistaSolDis.getModelo()).guardarArticulosReservados(artiReser);
			((BusinessDelegate) vistaSolDis.getModelo()).guardarArticulosAFabricar(artiAFab);
			((BusinessDelegate) vistaSolDis.getModelo()).modificarStock(artiReser);
			vaciarTabla();
			this.buttonCargarXML.setEnabled(true);
			this.buttonGuardarPedido.setEnabled(false);
			ref.getJTextArea1().append(ref.getDate()+": Solicitudes de Distribucion guardada en el Centro de Distribucion\n");
			String mensaje = codificarDetalle(artiReser,artiAFab);
			new Dialogo3Opciones("Operacion concretada", this,mensaje).setVisible(true);	

		}
	}
	
	public String codificarDetalle(Collection<ArticuloReservadoVO> artiReser, Collection<ArticuloAFabricarVO> artiAFab){
		String msj = new String();
		msj = "Articulos reservados:\n";
		Iterator arts = artiReser.iterator();
		while(arts.hasNext()){
			ArticuloReservadoVO art = (ArticuloReservadoVO) arts.next();
				msj = msj + "Cod: "+art.getArt().getCodigo()+" Cantidad: "+art.getCantidadReservada()+"\n";
		}
		msj = msj + "\n";
		arts = artiAFab.iterator();
		msj = msj + "Articulos a fabricar:\n";
		while(arts.hasNext()){
			ArticuloAFabricarVO art = (ArticuloAFabricarVO) arts.next();
			msj = msj + "Cod: "+art.getArt().getCodigo()+" Cantidad: "+art.getCantidadPedida()+"\n";
		}	
		return msj;
	}


	public Collection<ArticuloReservadoVO> articulosEnviarDeTabla() {
		Collection<ArticuloReservadoVO> art = new ArrayList<ArticuloReservadoVO>();
		ArticuloHeaderVO arti;
		int idMax = this.ref.getVistaSolDis().getModelo().getNextIdARes();
		for (int i = 0; i < tableArticulos.getRowCount(); i++) {
			int cant = Integer.parseInt((((DefaultTableModel) tableArticulos.getModel()).getValueAt(i, 6)).toString());
			if(cant > 0){
				long cod = (Long
						.parseLong((String) ((DefaultTableModel) tableArticulos
								.getModel()).getValueAt(i, 2)));
				arti = ((BusinessDelegate) vistaSolDis.getModelo()).getArticulo(cod);
				ArticuloReservadoVO aRes = new ArticuloReservadoVO();
				aRes.setIdAR(idMax);
				idMax++;
				aRes.setArt(arti);
				aRes.setCantidadReservada(cant);
				aRes.setSolDis(solDisVO);
				art.add(aRes);
			}
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
		int idMax = this.ref.getVistaSolDis().getModelo().getNextIdAFab();
		for (int i = 0; i < tableArticulos.getRowCount(); i++) {
			int ped = Integer.parseInt((((DefaultTableModel) tableArticulos
					.getModel()).getValueAt(i, 4)).toString());
			int sel = Integer.parseInt((((DefaultTableModel) tableArticulos
					.getModel()).getValueAt(i, 6)).toString());
			if (ped > sel) {
				long cod = (Long
						.parseLong((String) ((DefaultTableModel) tableArticulos
								.getModel()).getValueAt(i, 2)));
				arti = ((BusinessDelegate) vistaSolDis.getModelo())
						.getArticulo(cod);
				ArticuloAFabricarVO aFab = new ArticuloAFabricarVO();
				aFab.setArt(arti);
				aFab.setCantidadPedida(ped - sel);
				aFab.setCantidadRecibida(0);
				aFab.setCantidadAFabricar(0);
				// aFab.setFabrica()
				// aFab.setIdAAF();
				aFab.setIdAAF(idMax);
				idMax++;
				aFab.setSol(solDisVO);
				art.add(aFab);
			}
		}
		return art;
	}

	public void vaciarTabla() {
		((DefaultTableModel)tableArticulos.getModel()).getDataVector().removeAllElements();
	}

	// Variables declaration - do not modify
	private javax.swing.JButton buttonCargarXML;

	private javax.swing.JButton buttonGuardarPedido;


	private javax.swing.JScrollPane jScrollPane1;

	private javax.swing.JTable tableArticulos;

	private FileChooser chooser;

	// End of variables declaration

	public boolean getCargarTable() {
		return cargarTable;
	}

	public void setCargarTable(boolean cargarTable) {
		this.cargarTable = cargarTable;
	}

	public ArrayList<FabricaVO> getFabricas() {
		return fabricas;
	}

	public void setFabricas(ArrayList<FabricaVO> fabricas) {
		this.fabricas = fabricas;
	}

	public void setRef(MenuPrincipal ref) {
		this.ref = ref;
	}

	public MenuPrincipal getRef() {
		return ref;
	}

}
