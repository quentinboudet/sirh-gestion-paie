package dev.paie.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import dev.paie.config.DataSourceMySQLConfig;
import dev.paie.config.JpaConfig;
import dev.paie.config.ServicesConfig;
import dev.paie.entite.Cotisation;
import dev.paie.entite.Grade;
import dev.paie.util.PaieUtils;


//Sélection des classes de configuration Spring à utiliser lors du test
@ContextConfiguration(classes = { ServicesConfig.class, JeuxDeDonneesConfig.class, DataSourceMySQLConfig.class, JpaConfig.class})
//Configuration JUnit pour que Spring prenne la main sur le cycle de vie du test
@RunWith(SpringRunner.class)
public class CotisationServiceJpaTest {
	PaieUtils pu = new PaieUtils();
	
	@Autowired
	private CotisationService cotisationService;

//	@Autowired
//	@Qualifier("cotisation")
//	private Cotisation newCotisation; 
			
	@Test
	public void test_sauvegarder_lister_mettre_a_jour() {
		

		Cotisation newCotisation = new Cotisation(); 
		// TODO sauvegarder une nouvelle cotisation

		newCotisation.setId(6);
		newCotisation.setCode("CODE1");
		newCotisation.setLibelle("UN");
		newCotisation.setTauxPatronal(new BigDecimal("2"));
		newCotisation.setTauxSalarial(new BigDecimal("10"));
		
		cotisationService.sauvegarder(newCotisation);
		
		// TODO vérifier qu'il est possible de récupérer la nouvelle cotisation via la méthode lister
		Cotisation cotisation1 = cotisationService.lister().get(1);
		assertThat(cotisation1.getId()).isEqualTo(2);
		assertThat(cotisation1.getCode()).isEqualTo("CODE1");
		assertThat(cotisation1.getLibelle()).isEqualTo("UN");
		assertThat(cotisation1.getTauxPatronal()).isEqualTo(pu.formaterBigDecimal(new BigDecimal("2")));
		assertThat(cotisation1.getTauxSalarial()).isEqualTo(pu.formaterBigDecimal(new BigDecimal("10")));
		
		// TODO modifier une cotisation
		newCotisation.setCode("Code2");
		newCotisation.setLibelle("deux");
		newCotisation.setTauxPatronal(new BigDecimal("1"));
		newCotisation.setTauxSalarial(new BigDecimal("20"));
		cotisationService.mettreAJour(newCotisation);
		
		// TODO vérifier que les modifications sont bien prises en compte via la méthode lister
		cotisation1 = cotisationService.lister().get(1);
		assertThat(cotisation1.getId()).isEqualTo(2);
		assertThat(cotisation1.getCode()).isEqualTo("Code2");
		assertThat(cotisation1.getLibelle()).isEqualTo("deux");
		assertThat(cotisation1.getTauxPatronal()).isEqualTo(pu.formaterBigDecimal(new BigDecimal("1")));
		assertThat(cotisation1.getTauxSalarial()).isEqualTo(pu.formaterBigDecimal(new BigDecimal("20")));
	}
}
