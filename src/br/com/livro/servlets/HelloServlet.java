package br.com.livro.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/hello")
public class HelloServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		//Pegando par�metros da URL
		//http://localhost:8080/Carros/hello?nome=Marcus&sobrenome=Sampar
		String nome = req.getParameter("nome");
		String sobrenome = req.getParameter("sobrenome");		
		resp.getWriter().print("Ol� mundo GET: "+nome+" "+sobrenome);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		//Pega os par�metros do corpo da requisi��o		
		//http://localhost:8080/Carros
		String nome = req.getParameter("nome");
		String sobrenome = req.getParameter("sobrenome");		
		resp.getWriter().print("Ol� mundo POST: "+nome+" "+sobrenome);
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{				
		resp.getWriter().print("Ol� PUT");
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{				
		resp.getWriter().print("Ol� DELETE");
	}
}
