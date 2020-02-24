package usuario.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import usuario.dao.UsuarioDAO;
import usuarios.model.Usuario;

public class UsuarioControllerServlet extends HttpServlet {

	private static final long serialVersionUID = 1l;
	private UsuarioDAO usuarioDAO;

	public void init() {
		String jdbcURL = getServletContext().getInitParameter(
				"jdbc:mysql://localhost:3306/db_usuarios?useTimezone=true&serverTimezone=America/Sao_Paulo&useSSL=false");
		String jdbcUsername = getServletContext().getInitParameter("root");
		String jdbcPassword = getServletContext().getInitParameter("12345678");

		usuarioDAO = new UsuarioDAO(jdbcURL, jdbcUsername, jdbcPassword);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insertUsuario(request, response);
				break;
			case "/delete":
				deleteUsuario(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				updateUsuario(request, response);
				break;
			default:
				listUsuarios(request, response);
				break;
			}
		} catch (SQLException e) {
			throw new ServletException(e);
		} catch (Exception e) {
			throw new ServletException(e);
		}

	}

	private void listUsuarios(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Usuario> listaUsuario = usuarioDAO.listAllUsuarios();
		request.setAttribute("listaUsuario", listaUsuario);
		RequestDispatcher dispatcher = request.getRequestDispatcher("ListaDeUsuario.jsp");
		dispatcher.forward(request, response);

	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("UsuarioForm.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		// int id = Integer.parseInt(request.getParameter("id"));
//		int id = (Integer) request.getAttribute("id");
		int id = Integer.parseInt(request.getParameter("id"));
		Usuario existingUsuario = usuarioDAO.getUsuario(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("UsuarioForm.jsp");
		request.setAttribute("usuario", existingUsuario);
		dispatcher.forward(request, response);
	}

	private void insertUsuario(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
		Usuario novoUsuario = new Usuario();
		novoUsuario.setNome(nome);
		novoUsuario.setEmail(email);
		novoUsuario.setSenha(senha);
		usuarioDAO.insertUsuario(novoUsuario);
		response.sendRedirect("list");
	}

	private void updateUsuario(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
		Usuario usuario = new Usuario();
		usuario.setId(id);
		usuario.setNome(nome);
		usuario.setEmail(email);
		usuario.setSenha(senha);
		usuarioDAO.updateUsuario(usuario);
		response.sendRedirect("list");
	}

	private void deleteUsuario(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Usuario usuario = new Usuario();
		usuario.setId(id);
		usuarioDAO.deleteUsuario(usuario);
		response.sendRedirect("list");
	}

}
