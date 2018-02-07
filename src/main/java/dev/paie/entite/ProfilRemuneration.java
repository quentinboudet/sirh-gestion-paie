package dev.paie.entite;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table(name="sirh_profil_remuneration")
public class ProfilRemuneration {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="code")
	private String code;

	@ManyToMany(fetch=FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
                })
	@JoinTable(name="sirh_profil_remuneration_cotisations_non_imposables",
			joinColumns = @JoinColumn(name = "ID_profil", referencedColumnName = "id"), 
			inverseJoinColumns = @JoinColumn(name = "ID_cotisation", referencedColumnName = "id"))
	private Set<Cotisation> cotisationsNonImposables;

	@ManyToMany(fetch=FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
                })
	@JoinTable(name="sirh_profil_remuneration_cotisations_imposables",
			joinColumns = @JoinColumn(name = "ID_profil", referencedColumnName = "id"), 
			inverseJoinColumns = @JoinColumn(name = "ID_cotisation", referencedColumnName = "id"))
	private Set<Cotisation> cotisationsImposables;

	@ManyToMany(fetch=FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
                })
	@JoinTable(name="sirh_profil_remuneration_avantage",
			joinColumns = @JoinColumn(name = "ID_profil", referencedColumnName = "id"), 
			inverseJoinColumns = @JoinColumn(name = "ID_avantage", referencedColumnName = "id"))
	private Set<Avantage> avantages;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Set<Cotisation> getCotisationsNonImposables() {
		return cotisationsNonImposables;
	}

	public void setCotisationsNonImposables(Set<Cotisation> cotisationsNonImposables) {
		this.cotisationsNonImposables = cotisationsNonImposables;
	}

	public Set<Cotisation> getCotisationsImposables() {
		return cotisationsImposables;
	}

	public void setCotisationsImposables(Set<Cotisation> cotisationsImposables) {
		this.cotisationsImposables = cotisationsImposables;
	}

	public Set<Avantage> getAvantages() {
		return avantages;
	}

	public void setAvantages(Set<Avantage> avantages) {
		this.avantages = avantages;
	}

}
