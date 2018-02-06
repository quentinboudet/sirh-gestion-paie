package dev.paie.service;

import java.util.List;

import dev.paie.entite.RemunerationEmploye;

public interface RemunerationEmployeService {
	void sauvegarder(RemunerationEmploye nouvelleCotisation);
	void mettreAJour(RemunerationEmploye cotisation);
	List<RemunerationEmploye> lister();
}
