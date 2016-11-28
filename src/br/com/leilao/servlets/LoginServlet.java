package br.com.leilao.servlets;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.leilao.entities.Usuario;

@WebServlet(urlPatterns="/login")
public class LoginServlet extends HttpServlet {
	
	public static final String USUARIO_LOGADO = "usuario.logado";
	private static final long serialVersionUID = -4365742339727719653L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.sendRedirect("login.html");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nomeUsuario = req.getParameter("nome_usuario");
		String senha = req.getParameter("senha");
		
		Usuario usuarioLogado = findUsuario(req, nomeUsuario);
		
		if (usuarioLogado == null) {
			resp.sendRedirect("usuario_inexistente.html");
		} else {
			if (senha != null && !"".equals(senha)) {
				if (senha.equals(usuarioLogado.getSenha())) {
					req.getSession().setAttribute(USUARIO_LOGADO, usuarioLogado);				
					System.out.println("usuario logado com sucesso");
					resp.sendRedirect("/LeiloesOnline");
				}
			}
		}
	}
	
	private Usuario findUsuario(HttpServletRequest req, String nomeUsuario) {
		ServletContext context =  req.getServletContext();

		Map<String, Usuario> usuariosSalvos = null;
		
		usuariosSalvos = (Map<String, Usuario>) context.getAttribute(CadastroUsuarioServlet.USUARIOS_SALVOS);
		
		if (usuariosSalvos == null) {
			return null;
		}
		
		return usuariosSalvos.get(nomeUsuario);
	}
}
