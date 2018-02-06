package dev.paie.entite;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="sirh_remuneration_employe")
public class RemunerationEmploye {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "code", length = 30)
	private String matricule;
	
    @Column(name = "date_creation")
    private LocalDateTime dateCreation;
    
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
	public LocalDateTime getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(LocalDateTime dateCreation) {
		this.dateCreation = dateCreation;
	}
	
	
	
	

}
