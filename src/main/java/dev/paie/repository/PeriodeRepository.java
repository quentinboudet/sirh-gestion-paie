package dev.paie.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import dev.paie.entite.Periode;

@Service
public interface PeriodeRepository extends JpaRepository<Periode, Integer>{	

	public List<Periode> findAll() ;
}
