package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import factory.ConnectionFactory;
import modelo.Huesped;
import modelo.Reserva;

public class HuespedDAO {
	
	private final Connection con;
	
	public HuespedDAO(Connection con) {
		this.con = con;
	}

	public void guardar(Huesped huesped) {
		try(con) {
			final PreparedStatement statement = con.prepareStatement(
					"INSERT INTO HUESPEDES " 
						+ "(nombre, apellido, fechaNacimiento, nacionalidad, telefono, nreserva)"
						+ " VALUES (?, ?, ?, ?, ?, ?)",
						Statement.RETURN_GENERATED_KEYS);
			
			try (statement){		
				ejecutaRegistro(huesped, statement);
			}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
	}

	private void ejecutaRegistro(Huesped huesped, PreparedStatement statement) throws SQLException {
		statement.setString(1, huesped.getNombre());
		statement.setString(2, huesped.getApellido());
		statement.setDate(3, Date.valueOf(huesped.getFechaNacimiento()));
		statement.setString(4, huesped.getNacionalidad());
		statement.setString(5, huesped.getTelefono());
		statement.setInt(6, huesped.getNreserva());
		
		statement.execute();
		
		final ResultSet resultSet = statement.getGeneratedKeys();
		
		try(resultSet){
			while (resultSet.next()) {
				huesped.setId(resultSet.getInt(1));
				System.out.println(
						String.format("Fue insertado el producto de %s", huesped));
			}
		}
	}

	public List<Huesped> listar() {
		List<Huesped> resultado = new ArrayList<>();
		
		final Connection con = new ConnectionFactory().recuperarConexion();
		
		try (con){
		
			final PreparedStatement statement = con.prepareStatement("SELECT ID, NOMBRE, APELLIDO,"
					+ "FECHANACIMIENTO, NACIONALIDAD, TELEFONO, ID_RESERVA FROM HUESPEDES");
			
			try (statement){
			
				statement.execute();
				
				final ResultSet resultSet = statement.getResultSet();
				
				try (resultSet){
					while (resultSet.next()) {
						Huesped fila = new Huesped(resultSet.getInt("ID"), 
								resultSet.getString("NOMBRE"),
								resultSet.getString("APELLIDO"), 
								resultSet.getString("FECHANACIMIENTO"),
								resultSet.getString("NACIONALIDAD"), 
								resultSet.getString("TELEFONO"),
								resultSet.getInt("ID_RESERVA"));
						
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
		
			final PreparedStatement statement = con.prepareStatement("DELETE FROM HUESPEDES WHERE ID = ?");
			
			try (statement){
				statement.setInt(1, id);
				
				statement.execute(); 
					
				return statement.getUpdateCount();
			} 
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public int editar(Integer id, String nombre, String apellido, Object fechaNacimiento, String nacionalidad,
			String telefono, Integer nReserva) {
		
		final Connection con = new ConnectionFactory().recuperarConexion();
		
		try (con){
		
			final PreparedStatement statement = con.prepareStatement("UPDATE HUESPEDES SET " 
					+ " NOMBRE = ? " 
					+ ", APELLIDO = ? "
					+ ", FECHANACIMIENTO = ? "
					+ ", NACIONALIDAD = ?"
					+ ", TELEFONO = ?"
					+ ", ID_RESERVA = ?"
					+ " WHERE ID = ? ");
			
			try (statement){
				statement.setString(1, nombre);
				statement.setString(2, apellido);
				statement.setObject(3, fechaNacimiento);
				statement.setString(4, nacionalidad);
				statement.setString(5, telefono);
				statement.setInt(6, nReserva);
				statement.setInt(7, id);
				
				statement.execute();
				
				int updateCount = statement.getUpdateCount();   
		
			    return updateCount;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Huesped> buscar(String buscarStr, Integer buscarNum) {
		
		List<Huesped> resultado = new ArrayList<>();
		
		final Connection con = new ConnectionFactory().recuperarConexion();

		try (con){
			
			final PreparedStatement statement = con.prepareStatement("SELECT ID, NOMBRE, APELLIDO, FECHANACIMIENTO, "
					+ " NACIONALIDAD, TELEFONO, "
					+ " ID_RESERVA FROM HUESPEDES "
					+ " WHERE APELLIDO = ? || ID_RESERVA = ?");
			
			Huesped fila = null;
			
			try(statement){
				
				statement.setString(1, buscarStr);
				statement.setInt(2, buscarNum);
				
				statement.execute();
				
				final ResultSet resultSet = statement.getResultSet();

				try (resultSet){
					while (resultSet.next()) {
						fila = new Huesped(resultSet.getInt("ID"), 
								resultSet.getString("NOMBRE"),
								resultSet.getString("APELLIDO"), 
								resultSet.getString("FECHANACIMIENTO"),
								resultSet.getString("NACIONALIDAD"), 
								resultSet.getString("TELEFONO"),
								resultSet.getInt("ID_RESERVA"));
					}
				}
			}
			
			resultado.add(0, fila);
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
		return resultado;
	}

	public List<Huesped> relacionConReservas() {
		List<Huesped> resultado = new ArrayList<>();
		
		try {
			
			var querySelect = "SELECT H.ID, H.NOMBRE, H.APELLIDO, H.TELEFONO, "
					+ "R.ID, R.FECHAENTRADA, R.FECHASALIDA, R.VALOR, R.FORMAPAGO"
					+ " FROM HUESPEDES H "
					+ "INNER JOIN RESERVAS R ON H.ID = R.HUESPED_ID";
			System.out.println(querySelect);
			
			final PreparedStatement statement = con.prepareStatement(querySelect);
			System.out.println(statement);
			
			try(statement){
				
				statement.execute();
				
				final ResultSet resultSet = statement.executeQuery();
				System.out.println(resultSet);
				
				try(resultSet){
					
					while(resultSet.next()) {
						System.out.println("Entra al while");
						Integer huespedId = resultSet.getInt("ID");
						String huespedNombre = resultSet.getString("NOMBRE");
						String huespedApellido = resultSet.getString("APELLIDO");
						String huespedTelefono = resultSet.getString("TELEFONO");
						System.out.println(huespedNombre);
						
						var huesped = resultado
								.stream()
								.filter(hue -> hue.getId().equals(huespedId))
								.findAny().orElseGet(() -> {
									Huesped hue = new Huesped(huespedId, huespedTelefono); 
									
									resultado.add(hue);
									
									return hue;
								});
						System.out.println(huesped.getNombre());
						
						Reserva reserva = new Reserva(resultSet.getInt("R.ID"), resultSet.getString("R.FECHAENTRADA"),
								resultSet.getString("R.FECHASALIDA"), resultSet.getString("R.VALOR"), 
								resultSet.getString("R.FORMAPAGO"));
						
						huesped.agregar(reserva);
					}
				};
			}
		} catch (SQLException e){
			throw new RuntimeException(e);
		}
		System.out.println(resultado);
		return resultado;

	}
}
