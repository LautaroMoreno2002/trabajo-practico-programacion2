package empresaDeServicios;

public class Especialista {
    private int nroEspecialista;   
    private String nombre;
    private String telefono;
    private String especialidad;

    public Especialista(int nroEspecialista, String nombre, String telefono, String especialidad) {
		this.nroEspecialista = nroEspecialista;
		this.nombre = nombre;
		this.telefono = telefono;
		this.especialidad = especialidad;
	}

	public String getEspecialidad() {
		return especialidad;
	}
	
	public String toString() {
			return "- "+nroEspecialista+" [ Especialidad: "+ especialidad+" -  Telefono: "+telefono+" ]" ;
	}
}
