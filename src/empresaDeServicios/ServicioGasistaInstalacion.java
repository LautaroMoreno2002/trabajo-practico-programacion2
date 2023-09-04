package empresaDeServicios;

public class ServicioGasistaInstalacion extends ServicioGasista {

	public ServicioGasistaInstalacion(int codServicio,int dniCliente,int nroEspecialista,String direccion,int cantArtefactos,double precioPorArtefacto){
		super(codServicio,dniCliente,nroEspecialista,direccion,cantArtefactos,precioPorArtefacto);
	}

	public double calcularCostoServicio(double costoMateriales){
		double tarifa = (precioPorArtefacto() * cantidadArtefactos()) + costoMateriales;
		return tarifa;
	}

	@Override
	public String tipoServicio(){
		return "GasistaInstalacion";
	}
}
