package modelo;

public class Reserva {
	
	private Integer id;
	private String fechaEntrada;
	private String fechaSalida;
	private String valor;
	private String formaPago;
	
	public Reserva(String fechaEntrada, String fechaSalida, String valor, String formaPago) {
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.valor = valor;
		this.formaPago = formaPago;
	}

	public Reserva(int id, String fechaEntrada, String fechaSalida, String valor, String formaPago) {
		this.id = id;
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.valor = valor;
		this.formaPago = formaPago;
	}

	public Reserva() {

	}

	public String getFechaEntrada() {
		return this.fechaEntrada;
	}

	public String getFechaSalida() {
		return this.fechaSalida;
	}

	public String getValor() {
		return this.valor;
	}

	public String getFormaPago() {
		return this.formaPago;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return this.id;
	}
}
