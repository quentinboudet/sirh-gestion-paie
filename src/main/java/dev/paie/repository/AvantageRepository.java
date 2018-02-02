package dev.paie.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import dev.paie.entite.Avantage;

@Service
public interface AvantageRepository extends JpaRepository<Avantage, Integer>{	
	public default Avantage getByCode(String code) {
		Avantage avantage = (Avantage) this.findAll().stream()
				.filter(avantages -> avantages.getCode().equals(code))
				.collect(Collectors.groupingBy(t -> t.getId())).get(1).get(0);
		return avantage;
	}
}
