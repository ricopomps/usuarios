package usuario.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import usuarios.model.Usuario;

public class UsuarioDAO {
	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;

	public UsuarioDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
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
			} catch (Exception e) {
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

	public boolean insertUsuario(Usuario usuario) throws SQLException {
		String sql = "INSERT INTO usuarios (nome,email,senha) VALUES (?,?,?)";
		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, usuario.getNome());
		statement.setString(2, usuario.getEmail());
		statement.setString(3, usuario.getSenha());

		boolean inserted = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return inserted;

	}

	public List<Usuario> listAllUsuarios() throws SQLException {
		List<Usuario> listaUsuarios = new ArrayList<>();

		String sql = "SELECT * FROM usuarios";

		connect();

		Statement statement = jdbcConnection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		while (resultSet.next()) {
			int id = resultSet.getInt("id");
			String nome = resultSet.getString("nome");
			String email = resultSet.getString("email");
//			String senha = resultSet.getString("senha");
			// Showing the password would result in a security flaw, hence the list wont
			// return any passwords

			Usuario usuario = new Usuario();
			usuario.setId(id);
			usuario.setNome(nome);
			usuario.setEmail(email);
			usuario.setSenha(null);

			listaUsuarios.add(usuario);

		}
		resultSet.close();
		statement.close();

		disconnect();

		return listaUsuarios;
	}

	public boolean deleteUsuario(Usuario usuario) throws SQLException {
		String sql = "DELETE FROM usuarios WHERE id = ?";

		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, usuario.getId());

		boolean deleted = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return deleted;
	}

	public boolean updateUsuario(Usuario usuario) throws SQLException {
		String sql = "UPDATE usuarios SET nome = ?, email = ?, senha = ? WHERE id = ?";
		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, usuario.getNome());
		statement.setString(2, usuario.getEmail());
		statement.setString(3, usuario.getSenha());
		statement.setInt(4, usuario.getId());

		boolean updated = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return updated;
	}

	public Usuario getUsuario(int id) throws SQLException {
		Usuario usuario = null;
		String sql = "SELECT * FROM usuarios WHERE id = ?";
		connect();
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, id);
		ResultSet resultSet = statement.executeQuery();

		if (resultSet.next()) {
			String nome = resultSet.getString("nome");
			String email = resultSet.getString("email");
			String senha = resultSet.getString("senha");

			usuario = new Usuario();
			usuario.setId(id);
			usuario.setNome(nome);
			usuario.setEmail(email);
			usuario.setSenha(senha);
		}

		resultSet.close();
		statement.close();
		disconnect();
		return usuario;
	}

}
