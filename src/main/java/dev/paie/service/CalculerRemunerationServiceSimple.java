package dev.paie.service;

import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.paie.entite.BulletinSalaire;
import dev.paie.entite.Cotisation;
import dev.paie.entite.Grade;
import dev.paie.entite.ProfilRemuneration;
import dev.paie.entite.ResultatCalculRemuneration;
import dev.paie.util.PaieUtils;

@Service
public class CalculerRemunerationServiceSimple implements CalculerRemunerationService{
	private PaieUtils pu = new PaieUtils();
	
	@PersistenceContext EntityManager em;

	@Transactional
	@Override
	public ResultatCalculRemuneration calculer(BulletinSalaire bulletin) {
		ResultatCalculRemuneration resultat = new ResultatCalculRemuneration();
		Grade g = bulletin.getRemunerationEmploye().getGrade();
		ProfilRemuneration pr = bulletin.getRemunerationEmploye().getProfilRemuneration();

		BigDecimal salaireBase = 
				new BigDecimal(g.getNbHeuresBase().multiply(g.getTauxBase()).toString());
		resultat.setSalaireDeBase(pu.formaterBigDecimal(salaireBase));
		BigDecimal salaireBrut = 
				new BigDecimal(new BigDecimal(resultat.getSalaireDeBase()).add(bulletin.getPrimeExceptionnelle()).toString());
		resultat.setSalaireBrut(pu.formaterBigDecimal(salaireBrut));
		BigDecimal totalRetenueSalarial = 
				calculSommeCotisationSalarial(
						pr.getCotisationsNonImposables(), 
						resultat.getSalaireBrut()
				);
		resultat.setTotalRetenueSalarial(pu.formaterBigDecimal(totalRetenueSalarial));
		BigDecimal totalCotisationsPatronales = 
				calculSommeCotisationPatronal(
						pr.getCotisationsNonImposables(),
						resultat.getSalaireBrut()
				);
		resultat.setTotalCotisationsPatronales(pu.formaterBigDecimal(totalCotisationsPatronales));
		BigDecimal netImposable = new BigDecimal(new BigDecimal(resultat.getSalaireBrut()).subtract(new BigDecimal(resultat.getTotalRetenueSalarial())).toString());
		resultat.setNetImposable(pu.formaterBigDecimal(netImposable));
		BigDecimal netAPayer = 
				new BigDecimal(new BigDecimal(resultat.getNetImposable()).subtract(
						calculSommeCotisationSalarial(
								pr.getCotisationsImposables(), 
								resultat.getSalaireBrut()
						)
				).toString());
		resultat.setNetAPayer(pu.formaterBigDecimal(netAPayer));

		return resultat;
	}
	@Transactional
	private BigDecimal calculSommeCotisationSalarial(Set<Cotisation> cotisations, String salaireBrut) {
		return cotisations
			.stream()
			.filter(c -> c.getTauxSalarial()!=null)
			.map(c -> c.getTauxSalarial().multiply(new BigDecimal(salaireBrut)))
			.reduce((a,b) -> a.add(b)).orElse(new BigDecimal(0));
	}

	@Transactional
	private BigDecimal calculSommeCotisationPatronal(Set<Cotisation> cotisations, String salaireBrut) {
		return cotisations
			.stream()
			.filter(c -> c.getTauxPatronal()!=null)
			.map(c -> c.getTauxPatronal().multiply(new BigDecimal(salaireBrut)))
			.reduce((a,b) -> a.add(b)).orElse(new BigDecimal(0));
	}

}
