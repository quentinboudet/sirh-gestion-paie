package dev.paie.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import dev.paie.entite.Avantage;

@Service
public interface AvantageRepository extends JpaRepository<Avantage, Integer>{	
	
}
