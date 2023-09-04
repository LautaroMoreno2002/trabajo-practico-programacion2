package empresaDeServicios;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class EmpresaDeServicios {
	private HashMap<Integer, Servicio> servicios;
	private HashMap<Integer, Especialista> especialistas;
	private HashMap<Integer, Cliente> clientes;
	private HashMap<String, Double> costosPorTipo;
	private int contadorServicios; // Sirve para asignar el código a los servicios

	public EmpresaDeServicios() {
		servicios = new HashMap<Integer, Servicio>();
		especialistas = new HashMap<Integer, Especialista>();
		clientes = new HashMap<Integer, Cliente>();
		costosPorTipo=new HashMap<String,Double>();
		contadorServicios = 1000; // Inicializo en 1000 para que los códigos tengan 4 dígitos o más
	}

	public void registrarCliente(int dni, String nombre, String telefono) {
		if (clienteEstaRegistrado(dni)) {
			throw new RuntimeException("El cliente ya se encuentra registrado");
		}
		if (dni < 1000000 || dni > 99999999) { //chequea que el dni sea de 7 u 8 digitos
			throw new RuntimeException("DNI inválido");
		} 
		Cliente cliente = new Cliente(nombre, telefono, dni);
		this.clientes.put(dni, cliente);
	}

	public void registrarEspecialista(int nroEspecialista, String nombre, String telefono, String especialidad) {
		if (especialistaEstaRegistrado(nroEspecialista)) {
			throw new RuntimeException("El especialista ya se encuentra registrado");
		}
		if (!servicioValido(especialidad)) {
			throw new RuntimeException("El especialista posee una especialidad desconocida");
		}
		Especialista especialista = new Especialista(nroEspecialista, nombre, telefono, especialidad);
		this.especialistas.put(nroEspecialista, especialista);
	}

	public int solicitarServicioElectricidad(int dni, int nroEspecialista, String direccion, double precioPorHora,
			int horasTrabajadas) {
		validarDatos(dni, nroEspecialista, "Electricidad", horasTrabajadas, precioPorHora);
		int codigo = generadorDeCodigo();
		Servicio electricidad = new ServicioElectricidad(codigo, dni, nroEspecialista, direccion, precioPorHora,
				horasTrabajadas);
		this.servicios.put(codigo, electricidad);
		return codigo;
	}

	public int solicitarServicioPintura(int dni, int nroEspecialista, String direccion, int metrosCuadrados,
			double precioPorMetroCuadrado) {
		validarDatos(dni, nroEspecialista, "Pintura", metrosCuadrados, precioPorMetroCuadrado);
		int codigo = generadorDeCodigo();
		Servicio pintura = new ServicioPintura(codigo, dni, nroEspecialista, direccion, metrosCuadrados,
				precioPorMetroCuadrado);
		this.servicios.put(codigo, pintura);
		return codigo;
	}

	public int solicitarServicioPintura(int dni, int nroEspecialista, String direccion, int metrosCuadrados,
			double precioPorMetroCuadrado, int piso, double seguro, double alqAndamios) {
		validarDatos(dni, nroEspecialista, "PinturaEnAltura", piso, alqAndamios, precioPorMetroCuadrado,
				metrosCuadrados, seguro);
		int codigo = generadorDeCodigo();
		Servicio pinturaEnAltura = new ServicioPinturaEnAltura(codigo, dni, nroEspecialista, direccion, 
				metrosCuadrados,precioPorMetroCuadrado, piso, seguro, alqAndamios);
		this.servicios.put(codigo, pinturaEnAltura);
		return codigo;
	}

	public int solicitarServicioGasistaInstalacion(int dni, int nroEspecialista, String direccion, 
			int cantArtefactos,double precioPorArtefacto) {
		validarDatos(dni, nroEspecialista, "GasistaInstalacion", cantArtefactos, precioPorArtefacto);
		int codigo = generadorDeCodigo();
		Servicio gasistaInstalacion = new ServicioGasistaInstalacion(codigo, dni, nroEspecialista, direccion,
				cantArtefactos, precioPorArtefacto);
		this.servicios.put(codigo, gasistaInstalacion);
		return codigo;
	}

	public int solicitarServicioGasistaRevision(int dni, int nroEspecialista, String direccion, int cantArtefactos,
			double precioPorArtefacto) {
		validarDatos(dni, nroEspecialista, "GasistaRevision", cantArtefactos, precioPorArtefacto);
		int codigo = generadorDeCodigo();
		Servicio gasistaRevision = new ServicioGasistaRevision(codigo, dni, nroEspecialista, direccion, 
				cantArtefactos,precioPorArtefacto);
		this.servicios.put(codigo, gasistaRevision);
		return codigo;
	}

	public double finalizarServicio(int codServicio, double costoMateriales) {
		servicioNoRegistradoYFinalizado(codServicio); //si el servicio no esta registrado o ya esta finalizado lanza una excepcion
		Servicio servicio = servicios.get(codServicio);
		double precio = servicio.terminarServicio(costoMateriales);
		acumularCostoPorTipo(servicio.tipoServicio(),precio);
		return precio;
	}

	public Map<String, Integer> cantidadDeServiciosRealizadosPorTipo() {
		Integer contServPintura = 0, contServPinturaEnAltura = 0, contServElectricidad = 0,
				contServGasistaInstalacion = 0, contServGasistaRevision = 0;

		Iterator<Servicio> iterador = servicios.values().iterator();
		Map<String, Integer> cantServiciosRealizados = new HashMap<String, Integer>();
		while (iterador.hasNext()) {
			Servicio servicio = iterador.next();
			if (servicio instanceof ServicioPinturaEnAltura && servicio.getEstado()) {
				contServPinturaEnAltura++;
			} else if (servicio instanceof ServicioPintura && servicio.getEstado()) {
				contServPintura++;
			} else if (servicio instanceof ServicioElectricidad && servicio.getEstado()) {
				contServElectricidad++;
			} else if (servicio instanceof ServicioGasistaInstalacion && servicio.getEstado()) {
				contServGasistaInstalacion++;
			} else if (servicio instanceof ServicioGasistaRevision && servicio.getEstado()) {
				contServGasistaRevision++;
			}
		}
		cantServiciosRealizados.put("Pintura", contServPintura);
		cantServiciosRealizados.put("PinturaEnAltura", contServPinturaEnAltura);
		cantServiciosRealizados.put("Electricidad", contServElectricidad);
		cantServiciosRealizados.put("GasistaInstalacion", contServGasistaInstalacion);
		cantServiciosRealizados.put("GasistaRevision", contServGasistaRevision);
		return cantServiciosRealizados;
	}

	public double facturacionTotalPorTipo(String tipoServicio) {
		if (!servicioValido(tipoServicio)) {
			throw new RuntimeException("El servicio es inválido");
		}
		if (costosPorTipo.containsKey(tipoServicio)){
			return costosPorTipo.get(tipoServicio); 
		}
		return 0;
	}

	public double facturacionTotal() {
		double costoTotal = 0;
		costoTotal += facturacionTotalPorTipo("GasistaInstalacion") + facturacionTotalPorTipo("GasistaRevision")
		+ facturacionTotalPorTipo("Pintura") + facturacionTotalPorTipo("PinturaEnAltura")
		+ facturacionTotalPorTipo("Electricidad");
		return costoTotal;
	}

	public void cambiarResponsable(int codServicio, int nroEspecialista) {
		validarCambio(nroEspecialista, codServicio);
		servicios.get(codServicio).cambiarResponsable(nroEspecialista);
	}

	public String listadoServiciosAtendidosPorEspecialista(int nroEspecialista) {
		StringBuilder historialEspecialista = new StringBuilder();
		if (!especialistaEstaRegistrado(nroEspecialista)) {
			throw new RuntimeException("El especialista no se encuentra registrado");
		}
		Iterator<Servicio> iterador = servicios.values().iterator();
		while (iterador.hasNext()) {
			Servicio servicio = iterador.next();
			if (servicio.getEspecialista() == nroEspecialista) {
				historialEspecialista.append(servicio.toString()).append("\n");
			}
		}
		return historialEspecialista.toString();
	}

	public String toString() {
		StringBuilder estado = new StringBuilder("Estado de la empresa:\n\n");
		estado.append("Especialistas registrados:\n");
		for (Especialista especialista : especialistas.values()) {
			estado.append(especialista.toString()).append("\n");
		}
		estado.append("\nClientes registrados:\n");
		for (Cliente cliente : clientes.values()) {
			estado.append(cliente.toString() + "\n");
		}
		estado.append("\nServicios registrados:\n");
		for (Servicio servicio : servicios.values()) {
			String estadoServicio;
			estadoServicio = servicio.getEstado() == true ? "finalizado" : "en curso";
			estado.append(servicio.toString()).append(". Estado: ").append(estadoServicio).append("\n");
		}
		return estado.toString();
	}

	// ----------------------------------------------AUXILIARES-----------------------------------------------

	private void acumularCostoPorTipo(String tipoDeServicio, double costo){
		double nuevoDato;
		if (costosPorTipo.containsKey(tipoDeServicio)) {
			nuevoDato = costosPorTipo.get(tipoDeServicio) + costo;
			costosPorTipo.put(tipoDeServicio, nuevoDato);    		
		}
		else costosPorTipo.put(tipoDeServicio, costo);
	}

	private int generadorDeCodigo() {
		contadorServicios++;
		return contadorServicios;
	}

	private void validarDatos(int dni, int nroEspecialista, String especialidad, double valor1, double valor2) {
		if (!clienteEstaRegistrado(dni)) {
			throw new RuntimeException("El cliente no se encuentra registrado.");
		}
		if (!especialistaEstaRegistrado(nroEspecialista)) {
			throw new RuntimeException("El especialista no se encuentra registrado.");
		}
		if (!especialistaSeEspecializaEn(nroEspecialista, especialidad)) {
			throw new RuntimeException("La especialidad de el especialista no es " + especialidad);
		}
		if (valor1 <= 0 || valor2 <= 0) {
			throw new RuntimeException("Los valores deben ser mayores a 0.");
		}
	}

	private void validarDatos(int dni, int nroEspecialista, String especialidad, double valor1, double valor2,
			double valor3, double valor4, double valor5) {
		validarDatos(dni, nroEspecialista, especialidad, valor1, valor2);
		if (valor3 <= 0 || valor4 <= 0 || valor5 <= 0) {
			throw new RuntimeException("Los valores deben ser mayores a 0.");
		}
	}

	private boolean especialistaEstaRegistrado(int nroEspecialista) {
		return this.especialistas.containsKey(nroEspecialista);
	}

	private boolean clienteEstaRegistrado(int dni) {
		return this.clientes.containsKey(dni);
	}

	private boolean especialistaSeEspecializaEn(int nroEspecialista, String especialidad) {
		return this.especialistas.get(nroEspecialista).getEspecialidad().equals(especialidad);
	}

	private void servicioNoRegistradoYFinalizado(int codServicio) {
		if (!servicios.containsKey(codServicio)) {
			throw new RuntimeException("Servicio no registrado.");
		}
		if (servicios.get(codServicio).getEstado()) {
			throw new RuntimeException("Servicio inválido");
		}
	}

	private void validarCambio(int nroEspecialista, int codServicio) {
		servicioNoRegistradoYFinalizado(codServicio);
		if (!especialistaEstaRegistrado(nroEspecialista)) {
			throw new RuntimeException("El especialista no se encuentra registrado.");
		}
		if (!especialistas.get(nroEspecialista).getEspecialidad().equals(servicios.get(codServicio).tipoServicio())) {
			throw new RuntimeException("El especialista no se especializa en este tipo de servicios.");
		}
	}

	private boolean servicioValido(String especialidad) {
		return especialidad.equals("Pintura") || especialidad.equals("PinturaEnAltura")
				|| especialidad.equals("Electricidad") || especialidad.equals("GasistaRevision")
				|| especialidad.equals("GasistaInstalacion");
	}
}
