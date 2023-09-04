package empresaDeServicios;

public class Cliente {
    private String nombre;
    private String telefono;
    private int dni;

    public Cliente(String nombre,String telefono,int dni){
        this.nombre=nombre;
        this.telefono=telefono;
        this.dni=dni;
    }
    
    public String toString(){
    	return "-"+nombre+" [ DNI: "+dni+" - Telefono: "+telefono+" ]";
    }
}
