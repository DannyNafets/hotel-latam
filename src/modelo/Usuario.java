package modelo;

public class Usuario {
	
	private int id;
	private String usuario;
	private String nombre;
	private String contrasena;
	private String fechaNacimiento;
	private String nacionalidad;
	private String telefono;

	public Usuario(String usuario, String nombre, String contrasena, String fechaNacimiento, String nacionalidad, String telefono) {
		this.usuario = usuario;
		this.nombre = nombre;
		this.contrasena = contrasena;
		this.fechaNacimiento = fechaNacimiento;
		this.nacionalidad = nacionalidad;
		this.telefono = telefono;
	}


	public Usuario(int id, String usuario, String nombre, String fechaNacimiento, String nacionalidad, String telefono) {
		this.id = id;
		this.usuario = usuario;
		this.nombre = nombre;
		this.fechaNacimiento = fechaNacimiento;
		this.nacionalidad = nacionalidad;
		this.telefono = telefono;	
	}

	public Usuario(int id, String usuario, String contrasena) {
		this.id = id;
		this.usuario = usuario;
		this.contrasena = contrasena;
		
	}


	public void setId(int id) {
		this.id = id;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public String getNombre() {
		return this.nombre;
	}

	public String getContrasena() {
		return this.contrasena;
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


	public Integer getId() {
		return this.id;
	}
	
	

}
