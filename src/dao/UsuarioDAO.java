package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import factory.ConnectionFactory;
import modelo.Usuario;

public class UsuarioDAO {
	
	final private Connection con;
	
	public UsuarioDAO(Connection con) {
		this.con = con;
	}
	
	public void guardar(Usuario usuario) {
		try(con) {
			final PreparedStatement statement = con.prepareStatement(
					"INSERT INTO USUARIOS " 
						+ "(usuario, nombre, contraseña, fechaNacimiento, nacionalidad, telefono)"
						+ " VALUES (?, ?, ?, ?, ?, ?)",
						Statement.RETURN_GENERATED_KEYS);
			
			try (statement){		
				ejecutaRegistro(usuario, statement);
			}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
	}

	private void ejecutaRegistro(Usuario usuario, PreparedStatement statement) throws SQLException {
		statement.setString(1, usuario.getUsuario());
		statement.setString(2, usuario.getNombre());
		statement.setString(3, usuario.getContrasena());
		System.out.println(usuario.getFechaNacimiento());
		statement.setDate(4, Date.valueOf(usuario.getFechaNacimiento()));
		statement.setString(5, usuario.getNacionalidad());
		statement.setString(6, usuario.getTelefono());
		
		statement.execute();
		
		final ResultSet resultSet = statement.getGeneratedKeys();
		
		try(resultSet){
			while (resultSet.next()) {
				usuario.setId(resultSet.getInt(1));
				System.out.println(
						String.format("Fue insertado el producto de %s", usuario));
			}
		}
	}

	public List<Usuario> listar() {
		List<Usuario> resultado = new ArrayList<>();
		
		final Connection con = new ConnectionFactory().recuperarConexion();
		
		try (con){
		
			final PreparedStatement statement = con.prepareStatement("SELECT ID, USUARIO, NOMBRE, "
					+ "FECHANACIMIENTO, NACIONALIDAD, TELEFONO FROM USUARIOS");
			
			try (statement){
			
				statement.execute();
				
				final ResultSet resultSet = statement.getResultSet();
				
				try (resultSet){
					while (resultSet.next()) {
						Usuario fila = new Usuario(resultSet.getInt("ID"), 
								resultSet.getString("USUARIO"),
								resultSet.getString("NOMBRE"), 
								resultSet.getString("FECHANACIMIENTO"),
								resultSet.getString("NACIONALIDAD"), 
								resultSet.getString("TELEFONO"));
						
						resultado.add(fila);
					}
				}
			}
		} catch (SQLException e){
			throw new RuntimeException(e);
		}
		return resultado;
	}

	public List<Usuario> login() {
		List<Usuario> resultado = new ArrayList<>();
		
		final Connection con = new ConnectionFactory().recuperarConexion();
		
		try (con){
		
			final PreparedStatement statement = con.prepareStatement("SELECT ID, USUARIO, CONTRASEÑA FROM USUARIOS");
			
			try (statement){
			
				statement.execute();
				
				final ResultSet resultSet = statement.getResultSet();
				
				try (resultSet){
					while (resultSet.next()) {
						Usuario fila = new Usuario(resultSet.getInt("ID"), 
								resultSet.getString("USUARIO"),
								resultSet.getString("CONTRASEÑA"));
						
						resultado.add(fila);
					}
				}
			}
		} catch (SQLException e){
			throw new RuntimeException(e);
		}
		return resultado;
	}

	public int eliminar(Integer id) {
		final Connection con = new ConnectionFactory().recuperarConexion();
		
		try (con){
		
			final PreparedStatement statement = con.prepareStatement("DELETE FROM USUARIOS WHERE ID = ?");
			
			try (statement){
				statement.setInt(1, id);
				
				statement.execute(); 
					
				return statement.getUpdateCount();
			} 
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public int editar(Integer id, String usuario, String nombre, Object fechaNacimiento, String nacionalidad,
			String telefono) {
		final Connection con = new ConnectionFactory().recuperarConexion();
		
		try (con){
		
			final PreparedStatement statement = con.prepareStatement("UPDATE USUARIOS SET " 
					+ " USUARIO = ? " 
					+ ", NOMBRE= ? "
					+ ", FECHANACIMIENTO = ? "
					+ ", NACIONALIDAD = ?"
					+ ", TELEFONO = ?"
					+ " WHERE ID = ? ");
			
			try (statement){
				statement.setString(1, usuario);
				statement.setString(2, nombre);
				statement.setObject(3, fechaNacimiento);
				statement.setString(4, nacionalidad);
				statement.setString(5, telefono);
				statement.setInt(6, id);
				
				statement.execute();
				
				int updateCount = statement.getUpdateCount();   
		
			    return updateCount;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
}
