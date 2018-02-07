package dev.paie.web.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import dev.paie.entite.BulletinSalaire;
import dev.paie.entite.ResultatCalculRemuneration;
import dev.paie.repository.BulletinSalaireRepository;
import dev.paie.repository.PeriodeRepository;
import dev.paie.repository.RemunerationEmployeRepository;
import dev.paie.service.CalculerRemunerationService;

@Controller
@RequestMapping("/bulletins")
public class BulletinSalaireController {
	@PersistenceContext
	private EntityManager em;

	@Autowired
	PeriodeRepository pR;

	@Autowired
	RemunerationEmployeRepository reR;

	@Autowired
	BulletinSalaireRepository bsR;

	@Autowired
	CalculerRemunerationService calculerRSS;

	@RequestMapping(method = RequestMethod.GET, path = "/creer")
	@Secured("ROLE_ADMINISTRATEUR")
	public ModelAndView creerBulletin() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("bulletins/creerBulletin");

		mv.addObject("listPeriode", pR.findAll());
		mv.addObject("listRe", reR.findAll());
		mv.addObject("bulletin", new BulletinSalaire());

		return mv;
	}

	@Transactional
	@RequestMapping(method = RequestMethod.POST, path = "/creer")
	@Secured("ROLE_ADMINISTRATEUR")
	public String submit(@ModelAttribute("bulletin") BulletinSalaire bulletin) {
		bulletin.setDateCreation(LocalDateTime.now());
		em.persist(bulletin);

		return "redirect:lister";
	}

	@Transactional
	@RequestMapping(method = RequestMethod.GET, path = "/lister")
	@Secured({"ROLE_UTILISATEUR", "ROLE_ADMINISTRATEUR"})
	public ModelAndView listerBulletin() {
		List<BulletinSalaire> listBulletin = bsR.findAll();
		List<ResultatCalculRemuneration> listCalculBulletin = new ArrayList<>();
		for (BulletinSalaire bs : listBulletin) {
			listCalculBulletin.add(calculerRSS.calculer(bs));
		}

		ModelAndView mv = new ModelAndView();
		mv.setViewName("bulletins/listerBulletins");
		mv.addObject("listBulletin", listBulletin);
		mv.addObject("listCalculBulletin", listCalculBulletin);
		mv.addObject("dateTimeFormatter", DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));
		mv.addObject("dateFormatter", DateTimeFormatter.ofPattern("yyyy/MM/dd"));

		return mv;
	}

	@Transactional
	@RequestMapping(method = RequestMethod.GET, path = "/visualiser")
	@Secured({"ROLE_UTILISATEUR", "ROLE_ADMINISTRATEUR"})
	public ModelAndView visualiserBulletin(@RequestParam("id") int id) {
		System.out.println(id);
		ModelAndView mv = new ModelAndView();
		if(id == 0 || bsR.count() < id) {
			mv.setViewName("redirect:lister");
			return mv;
		}
		
		BulletinSalaire bulletin = bsR.findOne(id);
		ResultatCalculRemuneration calculBulletin = calculerRSS.calculer(bulletin);

		mv.setViewName("bulletins/visualiserBulletin");
		mv.addObject("bulletin", bulletin);
		mv.addObject("calculBulletin", calculBulletin);
		mv.addObject("dateTimeFormatter", DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));
		mv.addObject("dateFormatter", DateTimeFormatter.ofPattern("yyyy/MM/dd"));

		return mv;
	}
}