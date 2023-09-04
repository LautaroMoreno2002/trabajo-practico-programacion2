package empresaDeServicios;

public class ServicioPinturaEnAltura extends ServicioPintura{
	private int piso;
	private double seguro;
	private double alqAndamios;

	public ServicioPinturaEnAltura(int codServicio,int dniCliente,int nroEspecialista,String direccion,int metrosCuadrados,double precioPorMetroCuadrado,int piso,double seguro,double alqAndamios){
		super(codServicio,dniCliente,nroEspecialista,direccion,metrosCuadrados,precioPorMetroCuadrado);
		this.piso = piso;
		this.seguro = seguro;
		this.alqAndamios = alqAndamios;
	}

	public double calcularCostoServicio(double costoMateriales){
		double tarifa;
		if (piso > 5){
			tarifa = metrosCuadrados * precioPorMetroCuadrado + seguro + alqAndamios + (alqAndamios * 0.20) + costoMateriales;
		} 
		else{
			tarifa = metrosCuadrados * precioPorMetroCuadrado + seguro + alqAndamios + costoMateriales;
		} 
		return tarifa;
	}

	@Override
	public String tipoServicio(){
		return "PinturaEnAltura";
	}
}
