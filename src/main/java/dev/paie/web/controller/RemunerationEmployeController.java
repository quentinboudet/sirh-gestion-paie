package dev.paie.web.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import dev.paie.entite.RemunerationEmploye;
import dev.paie.repository.EntrepriseRepository;
import dev.paie.repository.GradeRepository;
import dev.paie.repository.ProfilRemunerationRepository;
import dev.paie.repository.RemunerationEmployeRepository;
import dev.paie.service.RemunerationEmployeService;

@Controller
@RequestMapping("/employes")
public class RemunerationEmployeController {
	@PersistenceContext private EntityManager em;
	
	@Autowired
	GradeRepository gr;
	
	@Autowired
	EntrepriseRepository er;

	@Autowired
	ProfilRemunerationRepository prr;
	
	@Autowired
	RemunerationEmployeRepository rer;
	
	@Autowired
	RemunerationEmployeService reS;
	
	@RequestMapping(method = RequestMethod.GET, path = "/creer")
	public ModelAndView creerEmploye() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("employes/creerEmploye");
		mv.addObject("prefixMatricule", "M00");
		
		mv.addObject("grades", gr.findAll());
		mv.addObject("entreprises", er.findAll());
        mv.addObject("profils", prr.findAll());
        mv.addObject("employe", new RemunerationEmploye());
        
		return mv;
	}
	
    @RequestMapping(method = RequestMethod.POST, path = "/creer") 
    public String submit(@ModelAttribute("employe") RemunerationEmploye employe) {
    	employe.setDateCreation(LocalDateTime.now());
    	reS.sauvegarder(employe);
        
       return "redirect:lister";
   }
        
    
    @RequestMapping(method = RequestMethod.GET, path = "/lister") 
    public ModelAndView listerEmploye() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("employes/listerEmployes");
        mv.addObject("listRe", rer.findAll());
        mv.addObject("dateTimeFormatter", DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));
        return mv;
    }
}