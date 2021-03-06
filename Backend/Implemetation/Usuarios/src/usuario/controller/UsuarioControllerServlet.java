package usuario.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import usuario.dao.TelefoneDAO;
import usuario.dao.UsuarioDAO;
import usuarios.model.Telefone;
import usuarios.model.Usuario;

public class UsuarioControllerServlet extends HttpServlet {

	private static final long serialVersionUID = 1l;
	private UsuarioDAO usuarioDAO;
	private TelefoneDAO telefoneDAO;

	public void init() {
		String jdbcURL = getServletContext().getInitParameter(
				"jdbc:mysql://localhost:3306/db_usuarios?useTimezone=true&serverTimezone=America/Sao_Paulo&useSSL=false");
		String jdbcUsername = getServletContext().getInitParameter("root");
		String jdbcPassword = getServletContext().getInitParameter("12345678");
		usuarioDAO = new UsuarioDAO(jdbcURL, jdbcUsername, jdbcPassword);
		telefoneDAO = new TelefoneDAO(jdbcURL, jdbcUsername, jdbcPassword);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/":
				login(request, response);
				break;
			case "/new":
				showNewForm(request, response);
				break;
			case "/newTelefone":
				showNewTelefoneForm(request, response);
				break;
			case "/newTelefoneComId":
				showTelefoneEditFormComId(request, response);
				break;
			case "/insert":
				insertUsuario(request, response);
				break;
			case "/insertTelefone":
				insertTelefone(request, response);
				break;
			case "/delete":
				deleteUsuario(request, response);
				break;
			case "/deleteTelefone":
				deleteTelefone(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/editTelefone":
				showTelefoneEditForm(request, response);
				break;
			case "/update":
				updateUsuario(request, response);
				break;
			case "/updateTelefone":
				updateTelefone(request, response);
				break;
			case "/listTelefones":
				listTelefones(request, response);
				break;
			case "/searchTelefone":
				listTelefonesFromUsuario(request, response);
				break;
			case "/searchUsuarios":
				procurarUsuarios(request, response);
				break;
			case "/searchTelefones":
				procurarTelefones(request, response);
				break;
			case "/login":
				login(request, response);
				break;
			case "/loginExecute":
				loginExecute(request, response);
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

	private void listTelefones(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Telefone> listaTelefone = telefoneDAO.listAllTelefones();
		request.setAttribute("listaTelefone", listaTelefone);
		RequestDispatcher dispatcher = request.getRequestDispatcher("ListaTelefone.jsp");
		dispatcher.forward(request, response);
	}

	private void listTelefonesFromUsuario(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		int id = Integer.parseInt(request.getParameter("id"));
		List<Telefone> listaTelefone = telefoneDAO.listAllTelefones(id);
		request.setAttribute("listaTelefone", listaTelefone);
		RequestDispatcher dispatcher = request.getRequestDispatcher("ListaTelefone.jsp");
		dispatcher.forward(request, response);
	}

	private void procurarUsuarios(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		int id;
		String nome;
		String email;

		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (Exception e) {
			id = 0;
		}
		try {
			nome = request.getParameter("nome");
			if (nome.equals("")) {
				throw new Exception();
			}
		} catch (Exception e) {
			nome = null;
		}
		try {
			email = request.getParameter("email");
			if (email.equals("")) {
				throw new Exception();
			}
		} catch (Exception e) {
			email = null;
		}
		List<Usuario> listaUsuario = usuarioDAO.listAllUsuarios(id, nome, email);
		request.setAttribute("listaUsuario", listaUsuario);
		RequestDispatcher dispatcher = request.getRequestDispatcher("SearchUsuarios.jsp");
		dispatcher.forward(request, response);
	}

	private void procurarTelefones(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		int id;
		int ddd;
		String numero;
		String tipo;
		int id_usuario;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (Exception e) {
			id = 0;
		}
		try {
			ddd = Integer.parseInt(request.getParameter("ddd"));

		} catch (Exception e) {
			ddd = 0;
		}
		try {
			numero = request.getParameter("numero");
			if (numero.equals("")) {
				throw new Exception();
			}
		} catch (Exception e) {
			numero = null;
		}
		try {
			tipo = request.getParameter("tipo");
			if (tipo.equals("")) {
				throw new Exception();
			}
		} catch (Exception e) {
			tipo = null;
		}
		try {
			id_usuario = Integer.parseInt(request.getParameter("id_usuario"));
		} catch (Exception e) {
			id_usuario = 0;
		}
		List<Telefone> listaTelefone = telefoneDAO.listAllTelefones(id, ddd, numero, tipo, id_usuario);
		request.setAttribute("listaTelefone", listaTelefone);
		RequestDispatcher dispatcher = request.getRequestDispatcher("SearchTelefones.jsp");
		dispatcher.forward(request, response);
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("Login.jsp");
		dispatcher.forward(request, response);
	}

	private void loginExecute(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		HttpSession session = request.getSession();
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
		Usuario usuario = usuarioDAO.getUsuario(nome, email, senha);
		if (usuario != null) {
			if (usuario.getNome().equals(nome) && usuario.getEmail().equals(email)
					&& usuario.getSenha().equals(senha)) {
//				request.setAttribute("usuario", usuario);
				int id = usuario.getId();
				List<Telefone> listaTelefone = telefoneDAO.listAllTelefonesIdUsuario(usuario.getId());
				request.setAttribute("listaTelefone", listaTelefone);
				session.setAttribute("id", id);
				session.setAttribute("nome", nome);
				session.setAttribute("email", email);
				session.setAttribute("senha", senha);
				RequestDispatcher dispatcher = request.getRequestDispatcher("Logado.jsp");
				dispatcher.forward(request, response);
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher("LoginComFracasso.jsp");
				dispatcher.forward(request, response);
			}
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("LoginComFracasso.jsp");
			dispatcher.forward(request, response);
		}
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("UsuarioForm.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewTelefoneForm(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("TelefoneForm.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		int id = Integer.parseInt(request.getParameter("id"));
		Usuario existingUsuario = usuarioDAO.getUsuario(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("UsuarioForm.jsp");
		request.setAttribute("usuario", existingUsuario);
		dispatcher.forward(request, response);
	}

	private void showTelefoneEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		int id = Integer.parseInt(request.getParameter("id"));
		Telefone existingTelefone = telefoneDAO.getTelefone(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("TelefoneForm.jsp");
		request.setAttribute("telefone", existingTelefone);
		dispatcher.forward(request, response);
	}

	private void showTelefoneEditFormComId(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		int id = Integer.parseInt(request.getParameter("id"));
		Telefone telefone = new Telefone();
		telefone.setId_usuario(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("NewTelefoneComId.jsp");
		request.setAttribute("telefone", telefone);
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

	private void insertTelefone(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		int ddd = Integer.parseInt(request.getParameter("ddd"));
		String numero = request.getParameter("numero");
		String tipo = request.getParameter("tipo");
		int id_usuario = Integer.parseInt(request.getParameter("id_usuario"));
		Telefone novoTelefone = new Telefone();
		novoTelefone.setDdd(ddd);
		novoTelefone.setNumero(numero);
		novoTelefone.setTipo(tipo);
		novoTelefone.setId_usuario(id_usuario);
		telefoneDAO.insertTelefone(novoTelefone);

		response.sendRedirect("listTelefones");
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

	private void updateTelefone(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		int ddd = Integer.parseInt(request.getParameter("ddd"));
		String numero = request.getParameter("numero");
		String tipo = request.getParameter("tipo");
		int id_usuario = Integer.parseInt(request.getParameter("id_usuario"));
		Telefone telefone = new Telefone();
		telefone.setId(id);
		telefone.setDdd(ddd);
		telefone.setNumero(numero);
		telefone.setTipo(tipo);
		telefone.setId_usuario(id_usuario);
		telefoneDAO.updateTelefone(telefone);
		response.sendRedirect("listTelefones");
	}

	private void deleteUsuario(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Usuario usuario = new Usuario();
		usuario.setId(id);
		usuarioDAO.deleteUsuario(usuario);
		response.sendRedirect("list");
	}

	private void deleteTelefone(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Telefone telefone = new Telefone();
		telefone.setId(id);
		telefoneDAO.deleteTelefone(telefone);
		response.sendRedirect("listTelefones");
	}

}
