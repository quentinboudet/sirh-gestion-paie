package dev.paie.service;

import java.sql.ResultSet;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import dev.paie.entite.Grade;

@Service
public class GradeServiceJdbcTemplate implements GradeService {
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public GradeServiceJdbcTemplate(DataSource dataSource) {
		super();
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void sauvegarder(Grade nouveauGrade) {
		String sqlInsert = "INSERT INTO sirh_grade(id, code, nb_heure_base, taux_base) VALUES(?, ?, ?, ?)";
		jdbcTemplate.update(sqlInsert, 
				nouveauGrade.getId(), 
				nouveauGrade.getCode(), 
				nouveauGrade.getNbHeuresBase().toString(),
				nouveauGrade.getTauxBase().toString()
		);
	}

	@Override
	public void mettreAJour(Grade grade) {
		String sqlUpdate = "UPDATE sirh_grade SET code = ?, nb_heure_base = ?, taux_base = ? WHERE id = ?";
		jdbcTemplate.update(sqlUpdate,
				grade.getCode(), 
				grade.getNbHeuresBase().toString(),
				grade.getTauxBase().toString(),
				grade.getId()
		);
	}
	
	@Override
	public void supprimer(Grade grade) {
		String sqlDelete = "DELETE FROM sirh_grade WHERE id = ?";
		jdbcTemplate.update(sqlDelete, grade.getId());
	}

	@Override
	public List<Grade> lister() {
		RowMapper<Grade> mapperGrade = (ResultSet rs, int rowNum) -> {
			Grade g = new Grade();
			g.setId(rs.getInt("id"));
			g.setCode(rs.getString("code"));
			g.setNbHeuresBase(rs.getBigDecimal("nb_heure_base"));
			g.setTauxBase(rs.getBigDecimal("taux_base"));
			return g;
		};
		String sqlSelect = "SELECT * FROM sirh_grade";
		return jdbcTemplate.query(sqlSelect, mapperGrade);
	}
}