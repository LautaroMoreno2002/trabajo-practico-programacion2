package empresaDeServicios;

public class ServicioGasistaRevision extends ServicioGasista{

	public ServicioGasistaRevision(int codServicio,int dniCliente,int nroEspecialista,String direccion,int cantArtefactos,double precioPorArtefacto){
		super(codServicio,dniCliente,nroEspecialista,direccion,cantArtefactos,precioPorArtefacto);
	}

	public double calcularCostoServicio(double costoMateriales){
		double tarifa;
		if (cantidadArtefactos() > 5 || cantidadArtefactos() < 10){
			tarifa = (precioPorArtefacto() * cantidadArtefactos()) + costoMateriales;
			tarifa = tarifa - (tarifa * 0.10);
		}
		else if (cantidadArtefactos() > 10){
			tarifa = (precioPorArtefacto() * cantidadArtefactos()) + costoMateriales; 
			tarifa = tarifa - (tarifa * 0.15);
		}
		else {
			tarifa = (precioPorArtefacto() * cantidadArtefactos()) + costoMateriales;
		}
		return tarifa;
	}
	
	@Override
	public String tipoServicio(){
		return "GasistaRevision";
	}
}
