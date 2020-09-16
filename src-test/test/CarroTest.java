package test;

import java.util.List;

import org.junit.Test;

import br.com.livro.domain.Carro;
import br.com.livro.domain.CarroService;
import junit.framework.TestCase;

public class CarroTest extends TestCase {

	private CarroService carroService = new CarroService();
	
	@Test
	public void testListaCarros() {
		List<Carro> carros = carroService.getCarros();
		//Valida se retornou a lista
		assertNotNull(carros);
		
		//valida se encontrou algo
		assertTrue(carros.size() > 0);
		
		//Valida se encontrou o Tucker
		Carro tucker = carroService.findByName("Tucker 1948").get(0);
		assertEquals("Tucker 1948",tucker.getNome());
		
		//Valida se encontrou ferrari
		Carro ferrari = carroService.findByName("Ferrari FF").get(0);
		assertEquals("Ferrari FF",ferrari.getNome());
	}
	
	@Test
	public void testSalvarDeletarCarro(){
		Carro c = new Carro();		
		c.setNome("Teste");
		c.setDesc("Teste desc");
		c.setUrlFoto("URL Foto");
		c.setUrlVideo("URL video");		
		c.setLatitude("Latitude");
		c.setLongitude("Longitude");
		c.setTipo("Tipo");
		carroService.save(c);

		//id do carro salvo
		Long id = c.getId();
		assertNotNull(id);
		
		//Busca no banco para confirmar que foi salvo
		c = carroService.getCarro(id);
		assertEquals("Teste",c.getNome());
		assertEquals("Teste desc",c.getDesc());
		assertEquals("URL Foto",c.getUrlFoto());
		assertEquals("URL video",c.getUrlVideo());
		assertEquals("Latitude",c.getLatitude());
		assertEquals("Longitude",c.getLongitude());
		assertEquals("Tipo",c.getTipo());
		
		//Atualiza carro
		c.setNome("Teste update");
		carroService.save(c);
		
		//Busca o carro novamente após atualização
		c = carroService.getCarro(id);
		assertEquals("Teste update",c.getNome());
		
		//Deleta o carro 
		carroService.delete(id);
		c = carroService.getCarro(id);
		
		//certifica que o carro foi excluido
		assertNull(c);
	}

}
