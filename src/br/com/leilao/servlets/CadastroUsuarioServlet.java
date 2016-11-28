package br.com.leilao.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.leilao.entities.Usuario;

@WebServlet(urlPatterns= {"/cadastro_usuario"})
public class CadastroUsuarioServlet extends HttpServlet {
	
	public static final String USUARIOS_SALVOS = "usuarios.salvos";
	private static final long serialVersionUID = -754870572594202934L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.sendRedirect("cadastro_usuario.html");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome = request.getParameter("nome_completo");
		String nomeUsuario = request.getParameter("nome_usuario");
		String senha = request.getParameter("senha");
		
		Usuario usuario = new Usuario(nome, nomeUsuario, senha);
		saveUsuario(request, usuario);
		
		response.sendRedirect("/LeiloesOnline");
	}
	
	public Map<String, Usuario> findAllUsuarios(HttpServletRequest request) {
		ServletContext context =  request.getServletContext();

		Map<String, Usuario> usuariosSalvos = null;
		
		usuariosSalvos = (Map<String, Usuario>) context.getAttribute(USUARIOS_SALVOS);
		
		if (usuariosSalvos != null) {
			return usuariosSalvos;
		}
		
		return null;
	}
 	
	private void saveUsuario(HttpServletRequest request, Usuario usuario) {
		
		ServletContext context =  request.getServletContext();

		Map<String, Usuario> usuariosSalvos = null;
		
		usuariosSalvos = (Map<String, Usuario>) context.getAttribute(USUARIOS_SALVOS);
		
		if (usuariosSalvos == null) {
			usuariosSalvos = new HashMap<String, Usuario>();
			context.setAttribute(USUARIOS_SALVOS, usuariosSalvos);
		}
		
		usuariosSalvos.put(usuario.getNomeUsuario(), usuario);
	}
}
