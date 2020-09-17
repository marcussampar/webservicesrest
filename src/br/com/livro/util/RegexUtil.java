package br.com.livro.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;

/*
 * Classe utilizada para interpretar a URL e identificar o tipo de requisição
 * */
public class RegexUtil {
	
	private static final Pattern regexAll = Pattern.compile("/carros");
	private static final Pattern regexById = Pattern.compile("/carros/([0-9]*)");
	
	//Verifica o padrão da URL "/carros/id"
	public static Long matchId(String requestUri) throws ServletException {
		//verifica o ID
		Matcher matcher = regexById.matcher(requestUri);
		if(matcher.find() && matcher.groupCount() > 0){
			String s = matcher.group(1);
			if(s != null && s.trim().length() > 0){
				Long id = Long.parseLong(s);
				return id;
			}
		}
		return null;
	}
	
	//verifica se a URL esta no padrão "/carros/id"
	public boolean matchAll(String requestUri) throws ServletException {
		Matcher matcher = regexAll.matcher(requestUri);
		if(matcher.find()){
			return true;
		}
		return false;
	}
	

}
