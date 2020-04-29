package org.formacio.component;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServeiAlumnat {

	/**
	 * ha de donar d'alta a la base de dades d'alumnes l'alumne indicat amb el
	 * corresponent codi. Si el nom de l'alumne es null, no l'ha de donar d'alta
	 * Retorna true si l'alumne s'ha inserit, false si no.
	 */

	@Autowired
	RepositoriAlumnes matriculats = new RepositoriAlumnesMemoria();
	
	@PostConstruct
	public void init() {
		matriculats.altaAlumne(1, "Antonia");
		matriculats.altaAlumne(2, "Joan");
	}

	public boolean matricula(int id, String alumne) {
		if (alumne == null) {
			return false;
		} else {
			matriculats.altaAlumne(id, alumne);
			return true;
		}
	}

	public int size() {
		return matriculats.llistaAlumnes().size();
	}
}
