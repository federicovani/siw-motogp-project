package it.uniroma3.siw.model;

import java.util.*;

import jakarta.persistence.*;

@Entity
public class Campionato {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	@Column(nullable=false, unique=true)
    private int anno;
	@OneToMany
	private List<GranPremio> granPremi;

	@OneToMany(mappedBy = "campionato", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<CampionatoPiloti> classifica;

	public List<CampionatoPiloti> getClassifica() {
		return classifica;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getAnno() {
		return anno;
	}

	public void setAnno(int anno) {
		this.anno = anno;
	}

	public List<GranPremio> getGranPremi() {
		return granPremi;
	}

	public void setGranPremi(List<GranPremio> granPremi) {
		this.granPremi = granPremi;
	}

	public void setClassifica(List<CampionatoPiloti> classifica) {
		this.classifica = classifica;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass()) return false;
		Campionato that = (Campionato) o;
		return anno == that.anno && Objects.equals(id, that.id) && Objects.equals(granPremi, that.granPremi) && Objects.equals(classifica, that.classifica);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, anno, granPremi, classifica);
	}
}