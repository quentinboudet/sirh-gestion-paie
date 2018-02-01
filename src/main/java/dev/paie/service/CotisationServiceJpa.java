package dev.paie.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.paie.entite.Cotisation;

@Service
public class CotisationServiceJpa implements CotisationService {
	private JdbcTemplate jdbcTemplate;
	
	@PersistenceContext
	private EntityManager em;

	@Transactional
	@Override
	public void sauvegarder(Cotisation nouvelleCotisation) {
		
		em.persist(nouvelleCotisation);
	}

	@Override
	public void mettreAJour(Cotisation cotisation) {
		em.createQuery("UPDATE c FROM Cotisation c where c.id=?");
		
	}

	@Override
	public List<Cotisation> lister() {
		List<Cotisation> Cotisations = new ArrayList<>();
		
		Cotisations = (List<Cotisation>) em.createQuery("SELECT c FROM Cotisation c").getResultList();
		return Cotisations;
	}
}
