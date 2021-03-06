package usuario.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import usuarios.model.Telefone;

public class TelefoneDAO {

	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;

	public TelefoneDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
		this.jdbcURL = jdbcURL;
		this.jdbcUsername = jdbcUsername;
		this.jdbcPassword = jdbcPassword;
	}

	protected void connect() throws SQLException {
		if (jdbcConnection == null || jdbcConnection.isClosed()) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				throw new SQLException(e);
			}
			jdbcConnection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/db_usuarios?useTimezone=true&serverTimezone=America/Sao_Paulo&useSSL=false",
					"root", "12345678");
		}
	}

	protected void disconnect() throws SQLException {
		if (jdbcConnection != null && !jdbcConnection.isClosed()) {
			jdbcConnection.close();
		}
	}

	public boolean insertTelefone(Telefone telefone) throws SQLException {
		String sql = "INSERT INTO telefones (ddd,numero,tipo,id_usuario) VALUES (?,?,?,?)";
		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, telefone.getDdd());
		statement.setString(2, telefone.getNumero());
		statement.setString(3, telefone.getTipo());
		statement.setInt(4, telefone.getId_usuario());

		boolean inserted = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return inserted;

	}

	public List<Telefone> listAllTelefones() throws SQLException {
		List<Telefone> listaTelefones = new ArrayList<>();

		String sql = "SELECT * FROM telefones";

		connect();

		Statement statement = jdbcConnection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		while (resultSet.next()) {
			int id = resultSet.getInt("id");
			int ddd = resultSet.getInt("ddd");
			String numero = resultSet.getString("numero");
			String tipo = resultSet.getString("tipo");
			int id_usuario = resultSet.getInt("id_usuario");

			Telefone telefone = new Telefone();
			telefone.setId(id);
			telefone.setDdd(ddd);
			telefone.setNumero(numero);
			telefone.setTipo(tipo);
			telefone.setId_usuario(id_usuario);

			listaTelefones.add(telefone);

		}
		resultSet.close();
		statement.close();

		disconnect();

		return listaTelefones;

	}

	public List<Telefone> listAllTelefones(int id) throws SQLException {
		List<Telefone> listaTelefones = new ArrayList<>();

		String sql = "SELECT * FROM telefones WHERE id_usuario = " + id;

		connect();

		Statement statement = jdbcConnection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		while (resultSet.next()) {
			int id1 = resultSet.getInt("id");
			int ddd = resultSet.getInt("ddd");
			String numero = resultSet.getString("numero");
			String tipo = resultSet.getString("tipo");
			int id_usuario = resultSet.getInt("id_usuario");

			Telefone telefone = new Telefone();
			telefone.setId(id1);
			telefone.setDdd(ddd);
			telefone.setNumero(numero);
			telefone.setTipo(tipo);
			telefone.setId_usuario(id_usuario);

			listaTelefones.add(telefone);

		}
		resultSet.close();
		statement.close();

		disconnect();

		return listaTelefones;
	}

	public List<Telefone> listAllTelefones(int id, int ddd, String numero, String tipo, int id_usuario)
			throws SQLException {
		List<Telefone> listaTelefones = new ArrayList<>();
		String sql = "SELECT * FROM telefones ";
		connect();
		boolean hasPrevious = false;
		if (id > 0) {
			sql += "WHERE id = " + id;
			hasPrevious = true;
		}
		if (ddd > 0) {
			if (hasPrevious) {
				sql += " AND ddd = " + ddd;
			} else {
				sql += "WHERE ddd = " + ddd;
				hasPrevious = true;
			}
		}
		if (numero != null) {
			sql += hasPrevious ? " AND numero = '" + numero + "'" : "WHERE numero = '" + numero + "'";
			hasPrevious = true;
		}
		if (tipo != null) {
			sql += hasPrevious ? " AND tipo = '" + tipo + "'" : "WHERE tipo = '" + tipo + "'";
			hasPrevious = true;
		}
		if (id_usuario > 0) {
			sql += hasPrevious ? " AND id_usuario = " + id_usuario : "WHERE id_usuario = " + id_usuario;
		}

		Statement statement = jdbcConnection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		while (resultSet.next()) {
			id = resultSet.getInt("id");
			ddd = resultSet.getInt("ddd");
			numero = resultSet.getString("numero");
			tipo = resultSet.getString("tipo");
			id_usuario = resultSet.getInt("id_usuario");

			Telefone telefone = new Telefone();
			telefone.setId(id);
			telefone.setDdd(ddd);
			telefone.setNumero(numero);
			telefone.setTipo(tipo);
			telefone.setId_usuario(id_usuario);

			listaTelefones.add(telefone);

		}
		resultSet.close();
		statement.close();

		disconnect();

		return listaTelefones;
	}

	public List<Telefone> listAllTelefonesIdUsuario(int id_usuario) throws SQLException {
		List<Telefone> listaTelefones = new ArrayList<>();

		String sql = "SELECT * FROM telefones WHERE id_usuario = " + id_usuario;

		connect();

		Statement statement = jdbcConnection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		while (resultSet.next()) {
			int id = resultSet.getInt("id");
			int ddd = resultSet.getInt("ddd");
			String numero = resultSet.getString("numero");
			String tipo = resultSet.getString("tipo");
			id_usuario = resultSet.getInt("id_usuario");

			Telefone telefone = new Telefone();
			telefone.setId(id);
			telefone.setDdd(ddd);
			telefone.setNumero(numero);
			telefone.setTipo(tipo);
			telefone.setId_usuario(id_usuario);

			listaTelefones.add(telefone);

		}
		resultSet.close();
		statement.close();

		disconnect();

		return listaTelefones;

	}

	public boolean deleteTelefone(Telefone telefone) throws SQLException {
		String sql = "DELETE FROM telefones WHERE id = ?";

		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, telefone.getId());

		boolean deleted = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return deleted;

	}

	public boolean updateTelefone(Telefone telefone) throws SQLException {
		String sql = "UPDATE telefones SET ddd = ?, numero = ?, tipo = ?, id_usuario = ? WHERE id = ?";
		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, telefone.getDdd());
		statement.setString(2, telefone.getNumero());
		statement.setString(3, telefone.getTipo());
		statement.setInt(4, telefone.getId_usuario());
		statement.setInt(5, telefone.getId());

		boolean updated = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return updated;

	}

	public Telefone getTelefone(int id) throws SQLException {
		Telefone telefone = null;
		String sql = "SELECT * FROM telefones WHERE id=?";
		connect();
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, id);
		ResultSet resultSet = statement.executeQuery();

		if (resultSet.next()) {
			int ddd = resultSet.getInt("ddd");
			String numero = resultSet.getString("numero");
			String tipo = resultSet.getString("tipo");
			int id_usuario = resultSet.getInt("id_usuario");

			telefone = new Telefone();
			telefone.setId(id);
			telefone.setDdd(ddd);
			telefone.setNumero(numero);
			telefone.setTipo(tipo);
			telefone.setId_usuario(id_usuario);
		}

		resultSet.close();
		statement.close();
		disconnect();
		return telefone;
	}

}
