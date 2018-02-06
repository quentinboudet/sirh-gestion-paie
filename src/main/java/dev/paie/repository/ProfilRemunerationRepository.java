package dev.paie.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import dev.paie.entite.ProfilRemuneration;

@Service
public interface ProfilRemunerationRepository extends JpaRepository<ProfilRemuneration, Integer>{	

	public List<ProfilRemuneration> findAll() ;
}
