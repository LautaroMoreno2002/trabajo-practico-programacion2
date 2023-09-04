package empresaDeServicios;

public abstract class ServicioGasista extends Servicio {
	private int cantArtefactos;
	private double precioPorArtefacto;

	public ServicioGasista(int codServicio,int dniCliente,int nroEspecialista,String direccion,int cantArtefactos,double precioPorArtefacto){
		super(codServicio,dniCliente,nroEspecialista,direccion);
		this.cantArtefactos = cantArtefactos;
		this.precioPorArtefacto = precioPorArtefacto;
	}

	int cantidadArtefactos(){
		return cantArtefactos;
	}

	double precioPorArtefacto(){
		return precioPorArtefacto;
	}
}
