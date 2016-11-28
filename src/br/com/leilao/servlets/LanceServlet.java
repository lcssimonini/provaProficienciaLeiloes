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

import br.com.leilao.entities.Artigo;
import br.com.leilao.entities.Lance;
import br.com.leilao.entities.Usuario;

@WebServlet(urlPatterns="/lance")
public class LanceServlet extends HttpServlet{
	
	private static final String LANCES_SALVOS = "lances.salvos";
	private static final long serialVersionUID = -3223179524200411755L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		if (!verificaLogin(req)) {
			resp.sendRedirect("usuario_deslogado.html");
		} else {
			
			List<Lance> lances = findAllLances(req);
			List<Artigo> artigos = ArtigosServlet.findAllArtigos(req);
			
			req.setAttribute("lances", lances);
			req.setAttribute("artigos", artigos);
			req.getRequestDispatcher("lances.jsp").forward(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String numeroArtigo = req.getParameter("numero_artigo");
		String valorLance = req.getParameter("valor_lance");
		
		Lance lance = new Lance(Integer.parseInt(numeroArtigo), Float.parseFloat(valorLance));
		saveLance(req, lance);
		
		List<Lance> lances = findAllLances(req);
		List<Artigo> artigos = ArtigosServlet.findAllArtigos(req);
		
		req.setAttribute("lances", lances);
		req.setAttribute("artigos", artigos);
		req.getRequestDispatcher("lances.jsp").forward(req, resp);
		
	}
	
	public static List<Lance> findAllLances(HttpServletRequest request) {
		ServletContext context =  request.getServletContext();
		
		List<Lance> lancesSalvos = (List<Lance>) context.getAttribute(LANCES_SALVOS);
		
		if (lancesSalvos != null) {
			return lancesSalvos;
		}
		
		return null;
	}
	
	private void saveLance(HttpServletRequest request, Lance lance) {
		
		ServletContext context =  request.getServletContext();

		List<Lance> lancesSalvos = (List<Lance>) context.getAttribute(LANCES_SALVOS);
		
		if (lancesSalvos  == null) {
			lancesSalvos  = new ArrayList<Lance>();
			context.setAttribute(LANCES_SALVOS, lancesSalvos);
		}
		
		lancesSalvos.add(lance);
	}
	
	private boolean verificaLogin(HttpServletRequest req) {
		boolean isLogado = false;
		Usuario usuario = (Usuario)req.getSession().getAttribute(LoginServlet.USUARIO_LOGADO);	
		if (usuario != null) {
			isLogado = true;
		}
		return isLogado;
	}
}
