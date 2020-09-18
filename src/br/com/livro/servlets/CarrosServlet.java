package br.com.livro.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.livro.domain.Carro;
import br.com.livro.domain.CarroService;
import br.com.livro.domain.ListaCarros;
import br.com.livro.util.JAXBUtil;
import br.com.livro.util.RegexUtil;
import br.com.livro.util.Response;
import br.com.livro.util.ServletUtil;

@WebServlet("/carros/*")
public class CarrosServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	private CarroService carroService = new CarroService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		
		String requestUri = req.getRequestURI();
		Long id = RegexUtil.matchId(requestUri);
		
		//Informou o ID do carro
		if(id != null){
			Carro carro = carroService.getCarro(id);
			if(carro != null){
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				String json = gson.toJson(carro);
				ServletUtil.writeJSON(resp, json);				
			}else{
				resp.sendError(404,"Carro não encontrado");
			}
		}else{		
			List<Carro> carros = carroService.getCarros();
			
			/*			  
			ListaCarros lista = new ListaCarros();
			lista.setCarros(carros);
			//Gera o XML
			String xml = JAXBUtil.toXML(lista);
			
			//Escreve o XML na response do servlet
			ServletUtil.writeXML(resp, xml);
			*/
			
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String json = gson.toJson(carros);			
			ServletUtil.writeJSON(resp, json);
		}		
	}	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{		
		//Cria o carro
		Carro carro = getCarroFromRequest(req);
		
		//Salva o carro
		carroService.save(carro);
		
		//Escreve o JSON do novo carro salvo
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(carro);
		ServletUtil.writeJSON(resp, json);
	}
	
	//http://localhost:8080/Carros/carros/62
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String requestUri = req.getRequestURI();
		Long id = RegexUtil.matchId(requestUri);
		
		if(id != null){
			carroService.delete(id);
			Response r = Response.Ok("Carro excluído com sucesso");
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String json = gson.toJson(r);
			ServletUtil.writeJSON(resp, json);
		}else{
			resp.sendError(404,"URL Inválida");
		}
	}
	
	//Lê os parâmetros da request e cria o objeto carro
	private Carro getCarroFromRequest(HttpServletRequest request){
		Carro c = new Carro();
		String id = request.getParameter("id");
		
		if(id != null){
			//Se informou o ID, busca no banco de dados
			c = carroService.getCarro(Long.parseLong(id));
		}
		
		c.setNome(request.getParameter("nome"));
		c.setDesc(request.getParameter("descricao"));
		c.setUrlFoto(request.getParameter("url_foto"));
		c.setUrlVideo(request.getParameter("url_video"));		
		c.setLatitude(request.getParameter("latitude"));
		c.setLongitude(request.getParameter("longitude"));
		c.setTipo(request.getParameter("tipo"));
		return c;
	}
}