package modelo;

import java.util.ArrayList;
import java.util.List;

public class Huesped {
	
	private Integer id;
	private String nombre;
	private String apellido;
	private String fechaNacimiento;
	private String nacionalidad;
	private String telefono;
	private Integer nreserva;
	private String buscar;
	private List<Reserva> reservas;

	public Huesped(String nombre, String apellido, String fechaNacimiento, String nacionalidad, String telefono, Integer nreserva) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
		this.nacionalidad = nacionalidad;
		this.telefono = telefono;
		this.nreserva = nreserva;
	}

	public Huesped(Integer id, String nombre, String apellido, String fechaNacimiento, String nacionalidad, 
			String telefono, Integer nreserva) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
		this.nacionalidad = nacionalidad;
		this.telefono = telefono;
		this.nreserva = nreserva;
	}

	public Huesped(String buscar) {
		this.buscar = buscar;
	}

	public Huesped(Integer id, String nombre, String apellido, String telefono) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
	}

	public Huesped(Integer id, String telefono) {
		this.id = id;
		this.nombre = telefono;
	}

	public String getNombre() {
		return this.nombre;
	}

	public String getApellido() {
		return this.apellido;
	}

	public String getFechaNacimiento() {
		return this.fechaNacimiento;
	}

	public String getNacionalidad() {
		return this.nacionalidad;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public Integer getNreserva() {
		return this.nreserva;
	}

	public void setId(int id) {
		this.id = id;
		
	}

	public Integer getId() {
		return this.id;
	}

	public void agregar(Reserva reserva) {
		if (this.reservas == null) {
			this.reservas = new ArrayList<>();
		}
	
		this.reservas.add(reserva);
	}

	public List<Reserva> getReservas() {
		return this.reservas;
	}
	
	@Override
	public String toString() {
		return this.nombre;
	}
	
}
