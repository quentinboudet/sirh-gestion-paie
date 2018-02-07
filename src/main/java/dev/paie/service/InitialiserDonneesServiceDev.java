package dev.paie.service;

import java.time.LocalDate;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.paie.entite.Cotisation;
import dev.paie.entite.Entreprise;
import dev.paie.entite.Grade;
import dev.paie.entite.Periode;
import dev.paie.entite.ProfilRemuneration;
import dev.paie.entite.Utilisateur;
import dev.paie.entite.Utilisateur.ROLES;

@Service
public class InitialiserDonneesServiceDev implements InitialiserDonneesService{

	@PersistenceContext
	private EntityManager em;
	
	@Autowired private PasswordEncoder passwordEncoder;
	
	@Transactional
	@Override
	public void initialiser() {	

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
		prs.values().forEach(c -> em.persist(c));
		
		//création des périodes de chaque mois de 2018
		for(int i = 1; i<=12; i++) {
			Periode per = new Periode();
			per.setDateDebut(LocalDate.of(2018, i, 01));
			per.setDateFin(LocalDate.of(2018, i, 01).plusMonths(1).minusDays(1));
			em.persist(per);
		}
		
		Utilisateur u1 = new Utilisateur();
		u1.setNomUtilisateur("admin");
		u1.setMotDePasse(this.passwordEncoder.encode("admin"));
		u1.setRole(ROLES.ROLE_ADMINISTRATEUR);
		u1.setEstActif(true);
		em.persist(u1);
		
		Utilisateur u2 = new Utilisateur();
		u2.setNomUtilisateur("moi");
		u2.setMotDePasse(this.passwordEncoder.encode("moi"));
		u2.setRole(ROLES.ROLE_UTILISATEUR);
		u2.setEstActif(true);
		em.persist(u2);
		
		context.close();
		
	}

}
