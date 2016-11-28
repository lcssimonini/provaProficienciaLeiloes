package br.com.leilao.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.leilao.entities.Artigo;

@WebServlet(urlPatterns="/cadastro_produto")
public class CadastroArtigoServlet extends HttpServlet {
	
	private static final long serialVersionUID = -3723226203036167987L;
	public static final String ARTIGOS_SALVOS = "artigos.salvos";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.sendRedirect("cadastro_produto.html");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String numero = req.getParameter("numero");
		String descricao = req.getParameter("descricao");
		String valorMinimo = req.getParameter("valor_minimo");
		String situacao = req.getParameter("situacao");
		
		Artigo artigo = new Artigo(descricao, Float.parseFloat(valorMinimo), "on".equals(situacao) ? true : false, Integer.parseInt(numero));
		
		saveArtigo(req, artigo);
		resp.sendRedirect("artigos");
	}
	
	private void saveArtigo(HttpServletRequest request, Artigo artigo) {
		
		ServletContext context =  request.getServletContext();

		Map<Integer, Artigo> artigosSalvos  = null;
		
		artigosSalvos = (Map<Integer, Artigo>) context.getAttribute(ARTIGOS_SALVOS);
		
		if (artigosSalvos  == null) {
			artigosSalvos  = new HashMap<Integer, Artigo>();
			context.setAttribute(ARTIGOS_SALVOS, artigosSalvos);
		}
		
		artigosSalvos.put(artigo.getNumero(), artigo);
	}
}
