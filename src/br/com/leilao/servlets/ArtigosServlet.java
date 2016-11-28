package br.com.leilao.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.leilao.entities.Artigo;

@WebServlet(urlPatterns="/artigos")
public class ArtigosServlet extends HttpServlet {
	
	private static final long serialVersionUID = -5846347961851943532L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		List<Artigo> artigos = findAllArtigos(req);
		
		req.setAttribute("artigos", artigos);
		req.getRequestDispatcher("artigos.jsp").forward(req, resp);
	}
	
	public static List<Artigo> findAllArtigos(HttpServletRequest request) {
		ServletContext context =  request.getServletContext();
		
		List<Artigo> artigosList = new ArrayList<Artigo>();
		
		Map<Integer, Artigo> artigosSalvos = (Map<Integer, Artigo>) context.getAttribute(CadastroArtigoServlet.ARTIGOS_SALVOS);
		
		if (artigosSalvos != null) {
			artigosList.addAll(artigosSalvos.values());
			return artigosList;
		}
		
		return null;
	}
}
