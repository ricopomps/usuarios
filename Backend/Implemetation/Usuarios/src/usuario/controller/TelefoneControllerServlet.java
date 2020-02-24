package usuario.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import usuario.dao.TelefoneDAO;
import usuarios.model.Telefone;

public class TelefoneControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1l;
	private TelefoneDAO telefoneDAO;

	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");

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
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insertTelefone(request, response);
				break;
			case "/delete":
				deleteTelefone(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				updateTelefone(request, response);
				break;
			default:
				listTelefones(request, response);
				break;
			}
		} catch (SQLException e) {
			throw new ServletException(e);
		} catch (Exception e) {
			throw new ServletException(e);
		}

	}

	private void listTelefones(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Telefone> listaTelefone = telefoneDAO.listAllTelefones();
		request.setAttribute("listaTelefone", listaTelefone);
		RequestDispatcher dispatcher = request.getRequestDispatcher("TelefoneForm.jsp");
		dispatcher.forward(request, response);

	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("TelefoneForm.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		int id = Integer.parseInt(request.getParameter("id"));
		Telefone existingTelefone = telefoneDAO.getTelefone(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("TelefoneForm.jsp");
		request.setAttribute("telefone", existingTelefone);
		dispatcher.forward(request, response);
	}

	private void insertTelefone(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		String ddd = request.getParameter("ddd");
		String numero = request.getParameter("numero");
		String tipo = request.getParameter("tipo");
		String id_usuario = request.getParameter("id_usuario");
		Telefone novoTelefone = new Telefone();
		novoTelefone.setDdd(Integer.parseInt(ddd));
		novoTelefone.setNumero(numero);
		novoTelefone.setTipo(tipo);
		novoTelefone.setId_usuario(Integer.parseInt(id_usuario));
		telefoneDAO.insertTelefone(novoTelefone);
		response.sendRedirect("list");
	}

	private void updateTelefone(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String ddd = request.getParameter("ddd");
		String numero = request.getParameter("numero");
		String tipo = request.getParameter("tipo");
		String id_usuario = request.getParameter("id_usuario");
		Telefone telefone = new Telefone();
		telefone.setId(id);
		telefone.setDdd(Integer.parseInt(ddd));
		telefone.setNumero(numero);
		telefone.setTipo(tipo);
		telefone.setId_usuario(Integer.parseInt(id_usuario));
		telefoneDAO.updateTelefone(telefone);
		response.sendRedirect("list");
	}

	private void deleteTelefone(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Telefone telefone = new Telefone();
		telefone.setId(id);
		telefoneDAO.deleteTelefone(telefone);
		response.sendRedirect("list");
	}

}
