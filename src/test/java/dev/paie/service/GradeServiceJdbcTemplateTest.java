package dev.paie.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import dev.paie.config.DataSourceMySQLConfig;
import dev.paie.config.ServicesConfig;
import dev.paie.entite.Grade;

//TODO compléter la configuration

//Sélection des classes de configuration Spring à utiliser lors du test
@ContextConfiguration(classes = { ServicesConfig.class, JeuxDeDonneesConfig.class, DataSourceMySQLConfig.class})
//Configuration JUnit pour que Spring prenne la main sur le cycle de vie du test
@RunWith(SpringRunner.class)
public class GradeServiceJdbcTemplateTest {
	@Autowired
	private GradeService gradeService;
	
	@Autowired
	@Qualifier("grade")
	private Grade nouveauGrade;

	@Test
	public void test_sauvegarder_lister_mettre_a_jour() {
	//TODO sauvegarder un nouveau grade
	//TODO vérifier qu'il est possible de récupérer le nouveau grade via la méthode lister
	//TODO modifier un grade
	//TODO vérifier que les modifications sont bien prises en compte via la méthode lister
		
		nouveauGrade.setId(1);
		nouveauGrade.setCode("CODE1");

		// sauvegarder un nouveau grade
		gradeService.sauvegarder(nouveauGrade);

		// vérifier qu'il est possible de récupérer le nouveau grade via la méthode
		// lister
		Grade gradeliste0 = gradeService.lister().get(0);
		assertThat(gradeliste0.getId()).isEqualTo(1);
		assertThat(gradeliste0.getCode()).isEqualTo("CODE1");
		assertThat(gradeliste0.getNbHeuresBase()).isEqualTo("151.67");
		assertThat(gradeliste0.getTauxBase()).isEqualTo("11.0984");

		// modifier un grade
		Grade gradeUp = new Grade();
		gradeUp.setId(1);
		gradeUp.setCode("Code2");
		gradeUp.setNbHeuresBase(new BigDecimal("115.76"));
		gradeUp.setTauxBase(new BigDecimal("12.9048"));
		gradeService.mettreAJour(gradeUp);

		// vérifier que les modifications sont bien prises en compte via la méthode
		// lister
		Grade gradeliste1 = gradeService.lister().get(0);
		assertThat(gradeliste1.getId()).isEqualTo(1);
		assertThat(gradeliste1.getCode()).isEqualTo("Code2");
		assertThat(gradeliste1.getNbHeuresBase()).isEqualTo("115.76");
		assertThat(gradeliste1.getTauxBase()).isEqualTo("12.9048");

		// vérifier que la suppression est bien prise en compte
		gradeService.supprimer(gradeliste1);
		List<Grade> listeGrade = gradeService.lister();
		assertThat(listeGrade.size() == 0).isTrue();
	}
}