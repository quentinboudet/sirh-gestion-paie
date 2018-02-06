package dev.paie.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.paie.entite.RemunerationEmploye;

@Service
public class RemunerationEmployeServiceJpa implements RemunerationEmployeService {

	@PersistenceContext
	private EntityManager em;

	@Transactional
	@Override
	public void sauvegarder(RemunerationEmploye re) {
		em.persist(re);
	}

	@Transactional
	@Override
	public void mettreAJour(RemunerationEmploye re) {
//		RemunerationEmployeService cot = em.find(RemunerationEmployeService.class, cotisation.getId());
//		cot.setCode(cotisation.getCode());
//		cot.setLibelle(cotisation.getLibelle());
//		cot.setTauxPatronal(cotisation.getTauxPatronal());
//		cot.setTauxSalarial(cotisation.getTauxSalarial());
	}

	@Override
	public List<RemunerationEmploye> lister() {
		List<RemunerationEmploye> re = new ArrayList<>();
//
//		Cotisations = (List<Cotisation>) em.createQuery("SELECT c FROM Cotisation c").getResultList();
		return re;
	}
}