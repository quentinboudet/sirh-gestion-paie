package dev.paie.service;

import java.util.List;

import dev.paie.entite.Grade;

public interface GradeService {
	void sauvegarder(Grade nouveauGrade);

	void mettreAJour(Grade grade);

	void supprimer(Grade grade);

	List<Grade> lister();
}
