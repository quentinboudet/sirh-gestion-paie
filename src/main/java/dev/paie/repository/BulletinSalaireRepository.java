package dev.paie.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import dev.paie.entite.BulletinSalaire;

@Service
public interface BulletinSalaireRepository extends JpaRepository<BulletinSalaire, Integer>{	

	public List<BulletinSalaire> findAll() ;
	
	
}
