package br.com.livro.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletUtil {
	
	public static void writeXML(HttpServletResponse response, String xml) throws ServletException, IOException{
		if(xml != null){
			PrintWriter writer = response.getWriter();
			response.setContentType("application/xml;charset=UTF-8");
			writer.write(xml);
			writer.close();
		}		
	}
	
	public static void writeJSON(HttpServletResponse response, String json) throws ServletException, IOException{
		if(json != null){
			PrintWriter writer = response.getWriter();
			response.setContentType("application/json;charset=UTF-8");
			writer.write(json);
			writer.close();
		}		
	}

}
