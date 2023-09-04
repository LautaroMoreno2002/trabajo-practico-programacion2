package empresaDeServicios;

public abstract class Servicio {
	private int codServicio;
	private int dniCliente;
	private int nroEspecialista;
	private double costoServicio;
	private String direccion;
	private boolean finalizado;

	public Servicio(int codServicio, int dniCliente,int nroEspecialista,String direccion){
		this.codServicio=codServicio;
		this.dniCliente = dniCliente;
		this.nroEspecialista = nroEspecialista;
		this.direccion = direccion;
		this.costoServicio = 0;
		this.finalizado = false;
	}

	public void cambiarResponsable(int nroEspecialista){
		this.nroEspecialista=nroEspecialista;
	} 

	public double terminarServicio(double costoMateriales){
		finalizado=true;
		costoServicio = calcularCostoServicio(costoMateriales);
		return costoServicio;
	}

	public abstract double calcularCostoServicio(double costoMateriales);

	public int getEspecialista() {
		return nroEspecialista;
	}

	public boolean getEstado(){
		return finalizado;
	}

	public double getCosto() {
		return costoServicio;
	}

	public int getCodServicio() {
		return this.codServicio;
	}

	public String tipoServicio() {
		return "Servicio";
	}

	public String toString() {
		return " + [ " + this.codServicio + " - " + this.tipoServicio() + " ] " + this.direccion;
	}

}

