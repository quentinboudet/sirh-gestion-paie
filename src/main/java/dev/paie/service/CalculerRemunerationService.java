package dev.paie.service;

import dev.paie.entite.BulletinSalaire;
import dev.paie.entite.ResultatCalculRemuneration;

public interface CalculerRemunerationService {
	ResultatCalculRemuneration calculer(BulletinSalaire bulletin);
}
