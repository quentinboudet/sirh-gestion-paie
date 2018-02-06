package dev.paie.service;

import java.time.LocalDate;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.paie.entite.Cotisation;
import dev.paie.entite.Entreprise;
import dev.paie.entite.Grade;
import dev.paie.entite.Periode;
import dev.paie.entite.ProfilRemuneration;

@Service
public class InitialiserDonneesServiceDev implements InitialiserDonneesService{

	@PersistenceContext
	private EntityManager em;
	
	@Transactional
	@Override
	public void initialiser() {	
		System.out.println("init");

		ClassPathXmlApplicationContext context;
		
		context = new ClassPathXmlApplicationContext(
				"cotisations-imposables.xml", 
				"cotisations-non-imposables.xml", 
				"grades.xml", 
				"entreprises.xml",
				"profils-remuneration.xml"
		);
		
		Map<String, Cotisation> cotisations = context.getBeansOfType(Cotisation.class);
		cotisations.values().forEach(c -> em.persist(c));
		
		Map<String, Grade> grades = context.getBeansOfType(Grade.class);
		grades.values().forEach(c -> em.persist(c));
		
		Map<String, Entreprise> entreprises = context.getBeansOfType(Entreprise.class);
		entreprises.values().forEach(c -> em.persist(c));
		
		Map<String, ProfilRemuneration> prs = context.getBeansOfType(ProfilRemuneration.class);
		System.out.println(prs.get("profil-technicien").getCotisationsImposables());
		System.out.println(prs.get("profil-technicien").getCotisationsNonImposables());
		prs.values().forEach(c -> em.persist(c));
		
		//création des périodes de chaque mois de 2018
		for(int i = 1; i<=12; i++) {
			Periode per = new Periode();
			per.setDateDebut(LocalDate.of(2018, i, 01));
			per.setDateFin(LocalDate.of(2018, i, 01).plusMonths(1).minusDays(1));
			em.persist(per);
		}
		
		context.close();
		
	}

}
