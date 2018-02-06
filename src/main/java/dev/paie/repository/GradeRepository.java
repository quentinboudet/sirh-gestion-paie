package dev.paie.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import dev.paie.entite.Grade;

@Service
public interface GradeRepository extends JpaRepository<Grade, Integer>{	

	public List<Grade> findAll() ;
}
