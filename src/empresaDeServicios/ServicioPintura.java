package empresaDeServicios;

public class ServicioPintura extends Servicio{
	protected int metrosCuadrados;
	protected double precioPorMetroCuadrado;

	public ServicioPintura(int codServicio,int dniCliente,int nroEspecialista,String direccion,int metrosCuadrados,double precioPorMetroCuadrado){
		super(codServicio,dniCliente,nroEspecialista,direccion);
		this.metrosCuadrados = metrosCuadrados;
		this.precioPorMetroCuadrado = precioPorMetroCuadrado;
	}

	public double calcularCostoServicio(double costoMateriales){
		double tarifa = (metrosCuadrados * precioPorMetroCuadrado) + costoMateriales;
		return tarifa;
	}

	@Override
	public String tipoServicio(){
		return "Pintura";
	}
}
