package controller;

import java.util.List;

import dao.UsuarioDAO;
import factory.ConnectionFactory;
import modelo.Usuario;

public class UsuarioController {
	
	private UsuarioDAO usuarioDAO;
	
	public UsuarioController() {
		this.usuarioDAO = new UsuarioDAO(new ConnectionFactory().recuperarConexion());
	}

	public void guardar(Usuario usuarios) {
		UsuarioDAO usuarioDAO = new UsuarioDAO(new ConnectionFactory().recuperarConexion());
		usuarioDAO.guardar(usuarios);
	}

	public List<Usuario> listar() {
		return usuarioDAO.listar();
	}

	public List<Usuario> login() {
		return usuarioDAO.login();
	}

	public int eliminar(Integer id) {
		return usuarioDAO.eliminar(id);
	}

	public int editar(Integer id, String usuario, String nombre, Object fechaNacimiento, String nacionalidad, String telefono) {
		return usuarioDAO.editar(id, usuario, nombre, fechaNacimiento, nacionalidad, telefono);
	}
}
