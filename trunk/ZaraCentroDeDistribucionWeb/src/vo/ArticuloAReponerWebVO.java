package vo;

public class ArticuloAReponerWebVO {

	private long codSolRep;
	private int codSolFab;
	private String fabrica;
	private long codigoArticulo;
	private String descripcion;
	private int cantidadPedida;
	private int cantidadAFabricar;
	private int cantidadRecibida;
	private int cantidadAReponer;
	
	public ArticuloAReponerWebVO() {
		
	}
	
	public ArticuloAReponerWebVO(long codSolRep,int codSolFab,String fabrica,long codigoArticulo,
			String descripcion,int cantidadPedida,int cantidadAFabricar,int cantidadRecibida,int cantidadAReponer) {
		this.codSolRep = codSolRep;
		this.codSolFab = codSolFab;
		this.fabrica = fabrica;
		this.codigoArticulo = codigoArticulo;
		this.descripcion = descripcion;
		this.cantidadPedida = cantidadPedida;
		this.cantidadAFabricar = cantidadAFabricar;
		this.cantidadRecibida = cantidadRecibida;
		this.cantidadAReponer = cantidadAReponer;
	}
	
	

	public long getCodSolRep() {
		return codSolRep;
	}

	public void setCodSolRep(long codSolRep) {
		this.codSolRep = codSolRep;
	}

	public int getCodSolFab() {
		return codSolFab;
	}

	public void setCodSolFab(int codSolFab) {
		this.codSolFab = codSolFab;
	}

	public String getFabrica() {
		return fabrica;
	}

	public void setFabrica(String fabrica) {
		this.fabrica = fabrica;
	}

	public long getCodigoArticulo() {
		return codigoArticulo;
	}

	public void setCodigoArticulo(long codigoArticulo) {
		this.codigoArticulo = codigoArticulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getCantidadPedida() {
		return cantidadPedida;
	}

	public void setCantidadPedida(int cantidadPedida) {
		this.cantidadPedida = cantidadPedida;
	}

	public int getCantidadAFabricar() {
		return cantidadAFabricar;
	}

	public void setCantidadAFabricar(int cantidadAFabricar) {
		this.cantidadAFabricar = cantidadAFabricar;
	}

	public int getCantidadRecibida() {
		return cantidadRecibida;
	}

	public void setCantidadRecibida(int cantidadRecibida) {
		this.cantidadRecibida = cantidadRecibida;
	}

	public int getCantidadAReponer() {
		return cantidadAReponer;
	}

	public void setCantidadAReponer(int cantidadAReponer) {
		this.cantidadAReponer = cantidadAReponer;
	}
	
}
