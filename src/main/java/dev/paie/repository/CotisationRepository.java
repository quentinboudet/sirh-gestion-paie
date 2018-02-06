package dev.paie.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import dev.paie.entite.Cotisation;

@Service
public interface CotisationRepository extends JpaRepository<Cotisation, Integer>{	

	public List<Cotisation> findAll() ;
}
