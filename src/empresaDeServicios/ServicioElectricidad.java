package empresaDeServicios;

public class ServicioElectricidad extends Servicio {
	private double precioPorHora;
	private int horasTrabajadas;

	public ServicioElectricidad(int codServicio,int dniCliente,int nroEspecialista,String direccion,double precioPorHora,int horasTrabajadas){
		super(codServicio,dniCliente,nroEspecialista,direccion);
		this.precioPorHora = precioPorHora;
		this.horasTrabajadas = horasTrabajadas;
	}

	public double calcularCostoServicio(double costoMateriales){
		double tarifa = (precioPorHora * horasTrabajadas) + costoMateriales;
		return tarifa;
	}

	@Override
	public String tipoServicio() {
		return "Electricidad";
	}
}
