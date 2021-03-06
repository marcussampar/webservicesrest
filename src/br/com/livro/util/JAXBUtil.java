package br.com.livro.util;

import java.io.IOException;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import br.com.livro.domain.Carro;
import br.com.livro.domain.ListaCarros;

public class JAXBUtil {
	private static JAXBUtil instance;
	private static JAXBContext context;
	
	public static JAXBUtil getInstance(){
		return instance;
	}
	
	static{
		try{
			//Informa ao JAXB que dever�o ser criados XML da classe
			context = JAXBContext.newInstance(ListaCarros.class,Carro.class);			
		}catch(JAXBException e){
			throw new RuntimeException(e);
		}
	}
	
	public static String toXML(Object object) throws IOException{
		try{
			StringWriter writer = new StringWriter();
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			m.marshal(object, writer);
			String xml = writer.toString();
			return xml;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
}