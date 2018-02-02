package dev.paie.entite;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="sirh_entreprise")
public class Entreprise {

	@Id
	private Integer id;
	
	@Column(name="siret")
	private String siret;
	
	@Column(name="denomination")
	private String denomination;
	
	@Column(name="adresse")
	private String adresse;
	
	@Column(name="urssaf")
	private String urssaf;
	
	@Column(name="code_naf")
	private String codeNaf;
	
	public String getDenomination() {
		return denomination;
	}
	public void setDenomination(String denomination) {
		this.denomination = denomination;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getSiret() {
		return siret;
	}
	public void setSiret(String siret) {
		this.siret = siret;
	}
	public String getUrssaf() {
		return urssaf;
	}
	public void setUrssaf(String urssaf) {
		this.urssaf = urssaf;
	}
	public String getCodeNaf() {
		return codeNaf;
	}
	public void setCodeNaf(String codeNaf) {
		this.codeNaf = codeNaf;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	
}
