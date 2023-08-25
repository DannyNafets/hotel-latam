package controller;

import java.sql.Date;
import java.util.List;

import dao.ReservaDAO;
import factory.ConnectionFactory;
import modelo.Reserva;

public class ReservaController {
	
	ReservaDAO reservaDAO;
	
	public ReservaController() {
		this.reservaDAO = new ReservaDAO(new ConnectionFactory().recuperarConexion());
	}


	public void guardar(Reserva reservas) {
		this.reservaDAO = new ReservaDAO(new ConnectionFactory().recuperarConexion());
		reservaDAO.guardar(reservas);
	}

	public List<Reserva> listar() {
		return reservaDAO.listar();
	}


	public int eliminar(Integer id) {
		return reservaDAO.eliminar(id);
	}


	public int editar(Integer id, Object fechaEntrada, Object fechaSalida, Double valor, String formaPago) {
		return reservaDAO.editar(id, fechaEntrada, fechaSalida, valor, formaPago);
	}
}
