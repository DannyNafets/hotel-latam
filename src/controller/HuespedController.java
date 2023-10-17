package controller;

import java.util.List;

import dao.HuespedDAO;
import factory.ConnectionFactory;
import modelo.Huesped;

public class HuespedController {
	
	private HuespedDAO huespedDAO;
	
	public HuespedController() {
		this.huespedDAO = new HuespedDAO(new ConnectionFactory().recuperarConexion());
	}

	public void guardar(Huesped huesped) {
		HuespedDAO huespedDAO = new HuespedDAO(new ConnectionFactory().recuperarConexion());
		huespedDAO.guardar(huesped);
		
	}

	public List<Huesped> listar() {
		return huespedDAO.listar();
	}

	public int eliminar(Integer id) {
		return huespedDAO.eliminar(id);
	}

	public int editar(Integer id, String nombre, String apellido, Object fechaNacimiento, String nacionalidad,
			String telefono, Integer nReserva) {
		return huespedDAO.editar(id, nombre, apellido, fechaNacimiento, nacionalidad, telefono, nReserva);
	}

	public List<Huesped> buscar(String buscar, Integer buscarNum) {
		return huespedDAO.buscar(buscar, buscarNum);
	}

	public List<Huesped> cargarRelacion() {
		return this.huespedDAO.relacionConReservas();
	}

}
