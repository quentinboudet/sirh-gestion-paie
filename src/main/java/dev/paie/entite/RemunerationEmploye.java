package dev.paie.entite;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="sirh_remuneration_employe")
public class RemunerationEmploye {

	@Id
    private Integer id;
    @Column(name = "code", length = 30)
    private String matricule;
    
    @ManyToOne
    @JoinColumn(name="entr_id")
    private Entreprise entreprise;
    
    @ManyToOne
    @JoinColumn(name="profil_id")
    private ProfilRemuneration profilRemuneration;
    
    @ManyToOne
    @JoinColumn(name="grade_id")
    private Grade grade;
	
	public String getMatricule() {
		return matricule;
	}
	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}
	
	public Entreprise getEntreprise() {
		return entreprise;
	}
	public void setEntreprise(Entreprise entreprise) {
		this.entreprise = entreprise;
	}
	public ProfilRemuneration getProfilRemuneration() {
		return profilRemuneration;
	}
	public void setProfilRemuneration(ProfilRemuneration profilRemuneration) {
		this.profilRemuneration = profilRemuneration;
	}
	public Grade getGrade() {
		return grade;
	}
	public void setGrade(Grade grade) {
		this.grade = grade;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	
	
	

}
