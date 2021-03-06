package org.formacio;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.formacio.component.RepositoriAlumnes;
import org.formacio.component.ServeiAlumnat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class Setmana1Tests {

	@Autowired(required=false)
	private RepositoriAlumnes repositori;

	@Autowired(required=false)
	private ServeiAlumnat servei;


    @Autowired
    private MockMvc mockMvc;

	@Test
	public void test_respositori_component() {
		assertNotNull("RepositoriAlumnes es un component", repositori);
	}


	@Test
	public void test_servei_component() {
		assertNotNull("ServeiAlumnat es un component", servei);
	}


	@Test
	public void test_matricula() {
		
	    boolean insertaNull = servei.matricula(4, null);
	    assertFalse("Inserir null ha de retornar false", insertaNull);
	    
	    assertFalse("null no s'ha inserit",repositori.llistaAlumnes().contains(null));
	    
	    boolean insertaNoNull = servei.matricula(4, "Laura");
	    assertTrue("Na Laura se pot matricular",insertaNoNull);
	    assertEquals("Laura", repositori.obteAlumne(4));
	    		
	}
	
	@Test
	public void test_creacio_dades_inicials() {
		assertEquals("Antonia", repositori.obteAlumne(1));
		assertEquals("Joan", repositori.obteAlumne(2));
	}
	
	
    @Test
    public void test_web_nombre_alumnes() throws Exception {
    	int nombreAlumnes = repositori.llistaAlumnes().size();
    	
        this.mockMvc.perform(get("/alumnes")).andExpect(status().isOk())
                .andExpect(content().string(equalTo(String.valueOf(nombreAlumnes))));
        
        repositori.altaAlumne(5, "Antoni");
 
        this.mockMvc.perform(get("/alumnes")).andExpect(status().isOk())
        .andExpect(content().string(equalTo(String.valueOf(nombreAlumnes + 1))));


    }
	

}
