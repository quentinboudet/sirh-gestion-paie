package dev.paie.web.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import dev.paie.service.InitialiserDonneesService;

@Component
public class StartUpAppListener {

	@Autowired
	private InitialiserDonneesService initService;

	@EventListener(ContextRefreshedEvent.class)
	public void contextRefreshedEvent() {
		System.out.println("StartUpAppListener");
		// capture du démarrage de l'application
		// à un moment où le contexte Spring est complètement créé
//		initService.initialiser();
	}
}