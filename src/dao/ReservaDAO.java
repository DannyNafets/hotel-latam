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
import modelo.Reserva;

public class ReservaDAO {
	
final private Connection con;
	
	public ReservaDAO(Connection con) {
		this.con = con;
	}
	
	public void guardar(Reserva reserva) {
		try(con) {
			final PreparedStatement statement = con.prepareStatement(
					"INSERT INTO RESERVAS " 
						+ "(fechaEntrada, fechaSalida, valor, formaPago)"
						+ " VALUES (?, ?, ?, ?)",
						Statement.RETURN_GENERATED_KEYS);
			
			try (statement){		
				ejecutaRegistro(reserva, statement);
			}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
	}

	private void ejecutaRegistro(Reserva reserva, PreparedStatement statement) throws SQLException {
		statement.setDate(1, Date.valueOf(reserva.getFechaEntrada()));
		statement.setDate(2, Date.valueOf(reserva.getFechaSalida()));
		statement.setDouble(3, Double.parseDouble(reserva.getValor()));
		statement.setString(4, reserva.getFormaPago());
		
		statement.execute();
		
		final ResultSet resultSet = statement.getGeneratedKeys();
		
		try(resultSet){
			while (resultSet.next()) {
				reserva.setId(resultSet.getInt(1));
				System.out.println(
						String.format("Fue insertado el producto de %s", reserva));
			}
		}
	}

	public List<Reserva> listar() {
		List<Reserva> resultado = new ArrayList<>();
		
		final Connection con = new ConnectionFactory().recuperarConexion();
		
		try (con){
		
			final PreparedStatement statement = con.prepareStatement("SELECT ID, fechaEntrada, FechaSalida, "
					+ "valor, formaPago FROM RESERVAS");
			
			try (statement){
			
				statement.execute();
				
				final ResultSet resultSet = statement.getResultSet();
				
				try (resultSet){
					while (resultSet.next()) {
						Reserva fila = new Reserva(resultSet.getInt("ID"), 
								resultSet.getString("FECHAENTRADA"),
								resultSet.getString("FECHASALIDA"), 
								resultSet.getString("VALOR"),
								resultSet.getString("FORMAPAGO"));
						
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
		
			final PreparedStatement statement = con.prepareStatement("DELETE FROM RESERVAS WHERE ID = ?");
			
			try (statement){
				statement.setInt(1, id);
				
				statement.execute(); 
					
				return statement.getUpdateCount();
			} 
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public int editar(Integer id, Object fechaEntrada, Object fechaSalida, Double valor, String formaPago) {
		final Connection con = new ConnectionFactory().recuperarConexion();
		
		try (con){
		
			final PreparedStatement statement = con.prepareStatement("UPDATE RESERVAS SET " 
					+ " FECHAENTRADA = ? " 
					+ ", FECHASALIDA = ? "
					+ ", VALOR = ? "
					+ ", FORMAPAGO = ?"
					+ " WHERE ID = ? ");
			
			try (statement){
				statement.setObject(1, fechaEntrada);
				statement.setObject(2, fechaSalida);
				statement.setDouble(3, valor);
				statement.setString(4, formaPago);
				statement.setInt(5, id);
				
				statement.execute();
				
				int updateCount = statement.getUpdateCount();   
		
			    return updateCount;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
