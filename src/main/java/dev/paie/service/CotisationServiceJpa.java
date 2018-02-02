package dev.paie.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.paie.entite.Cotisation;

@Service
public class CotisationServiceJpa implements CotisationService {
	
	@PersistenceContext
	private EntityManager em;

	@Transactional
	@Override
	public void sauvegarder(Cotisation nouvelleCotisation) {
		em.persist(nouvelleCotisation);
	}

	@Transactional
	@Override
	public void mettreAJour(Cotisation cotisation) {
		Cotisation cot = em.find(Cotisation.class, cotisation.getId());
		cot.setCode(cotisation.getCode());
		cot.setLibelle(cotisation.getLibelle());
		cot.setTauxPatronal(cotisation.getTauxPatronal());
		cot.setTauxSalarial(cotisation.getTauxSalarial());
	}

	@Override
	public List<Cotisation> lister() {
		List<Cotisation> Cotisations = new ArrayList<>();
		
		Cotisations = (List<Cotisation>) em.createQuery("SELECT c FROM Cotisation c").getResultList();
		return Cotisations;
	}
}
