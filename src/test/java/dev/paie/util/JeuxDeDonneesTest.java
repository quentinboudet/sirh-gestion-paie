package dev.paie.util;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dev.paie.entite.BulletinSalaire;
import dev.paie.entite.Cotisation;

public class JeuxDeDonneesTest {
	private ClassPathXmlApplicationContext context;
	private BulletinSalaire bulletin1;

	@Before
	public void onSetup() {
		context = new ClassPathXmlApplicationContext("jdd-config.xml");
		bulletin1 = context.getBean("bulletin1", BulletinSalaire.class);
	}

	@Test
	public void test_primeExceptionnelle() {

		assertThat(bulletin1.getPrimeExceptionnelle(), equalTo(new BigDecimal("1000")));
	}

	@Test
	public void test_employe() {
		assertThat(bulletin1.getRemunerationEmploye().getMatricule(), equalTo("M01"));
	}

	@Test
	public void test_entreprise() {
		assertThat(bulletin1.getRemunerationEmploye().getEntreprise().getSiret(), equalTo("80966785000022"));
		assertThat(bulletin1.getRemunerationEmploye().getEntreprise().getDenomination(), equalTo("Dev Entreprise"));
		assertThat(bulletin1.getRemunerationEmploye().getEntreprise().getCodeNaf(), equalTo("6202A"));
	}

	@Test
	public void test_cotisationsNonImposables() {
		List<Cotisation> cotisationsNonImposables = bulletin1.getRemunerationEmploye().getProfilRemuneration()
				.getCotisationsNonImposables();
		Stream.of("EP01", "EP02", "EP03", "EP04", "EP05", "EP06", "EP07", "EP12", "EP19", "EP20", "EPR1", "E900",
				"EP28", "EP37")
				.forEach(code -> assertTrue("verification code " + code,
						cotisationsNonImposables.stream().filter(c -> c.getCode().equals(code)).findAny().isPresent()));
	}

	@Test
	public void test_cotisationImposables() {
		List<Cotisation> cotisationsImposables = bulletin1.getRemunerationEmploye().getProfilRemuneration()
				.getCotisationsImposables();
		Stream.of("SP01", "SP02").forEach(code -> assertTrue("verification code " + code,
				cotisationsImposables.stream().filter(c -> c.getCode().equals(code)).findAny().isPresent()));
	}

	@Test
	public void test_grade() {
		assertThat(bulletin1.getRemunerationEmploye().getGrade().getNbHeuresBase(), equalTo(new BigDecimal("151.67")));
		assertThat(bulletin1.getRemunerationEmploye().getGrade().getTauxBase(), equalTo(new BigDecimal("11.0984")));
	}

	@After
	public void onExit() {
		context.close();
	}
}
