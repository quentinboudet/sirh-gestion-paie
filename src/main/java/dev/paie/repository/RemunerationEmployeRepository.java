package dev.paie.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import dev.paie.entite.RemunerationEmploye;

@Service
public interface RemunerationEmployeRepository extends JpaRepository<RemunerationEmploye, Integer>{	

	public List<RemunerationEmploye> findAll() ;
}
