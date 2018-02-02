package dev.paie.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import dev.paie.config.ServicesConfig;
import dev.paie.entite.Avantage;
import dev.paie.entite.Cotisation;
import dev.paie.util.PaieUtils;

//Sélection des classes de configuration Spring à utiliser lors du test
@ContextConfiguration(classes = { ServicesConfig.class})
//Configuration JUnit pour que Spring prenne la main sur le cycle de vie du test
@RunWith(SpringRunner.class)
public class AvantageRepositoryTest {
	@Autowired
	private AvantageRepository avantageRepository;
	

	PaieUtils pu = new PaieUtils();

	@Test
	public void test_sauvegarder_lister_mettre_a_jour() {
		// TODO sauvegarder un nouvel avantage
		Avantage avantage1 = new Avantage();
		avantage1.setId(1);
		avantage1.setCode("CODE1");
		avantage1.setNom("avantage1");
		avantage1.setMontant(new BigDecimal("10.23"));
		avantageRepository.save(avantage1);
		
		// TODO vérifier qu'il est possible de récupérer le nouvel avantage via la méthode findOne
//		Avantage avantageVerif = avantageRepository.findOne(1);
		Avantage avantageVerif = avantageRepository.getByCode("CODE1");
		assertThat(avantageVerif.getId()).isEqualTo(1);
		assertThat(avantageVerif.getCode()).isEqualTo("CODE1");
		assertThat(avantageVerif.getNom()).isEqualTo("avantage1");
		assertThat(avantageVerif.getMontant()).isEqualTo(pu.formaterBigDecimal(new BigDecimal("10.23")));
		
		// TODO modifier un avantage
		avantage1.setMontant(new BigDecimal("6.34"));
		avantageRepository.save(avantage1);
		
		// TODO vérifier que les modifications sont bien prises en compte via la méthode findOne
//		avantageVerif = avantageRepository.findOne(1);
		avantageVerif = avantageRepository.getByCode("CODE1");
		assertThat(avantageVerif.getId()).isEqualTo(1);
		assertThat(avantageVerif.getCode()).isEqualTo("CODE1");
		assertThat(avantageVerif.getNom()).isEqualTo("avantage1");
		assertThat(avantageVerif.getMontant()).isEqualTo(pu.formaterBigDecimal(new BigDecimal("6.34")));
	}
}