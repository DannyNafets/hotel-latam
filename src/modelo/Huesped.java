package modelo;

public class Huesped {
	
	private Integer id;
	private String nombre;
	private String apellido;
	private String fechaNacimiento;
	private String nacionalidad;
	private String telefono;
	private Integer nreserva;
	private String buscar;

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

	public int getId() {
		return this.id;
	}
	
	
	
}
