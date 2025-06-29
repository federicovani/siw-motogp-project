package it.uniroma3.siw.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Entity
public class GranPremio {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private int numeroDiGiri;
	private LocalDate data;
	@ManyToOne
	private Circuito circuito;
	@ElementCollection
	@CollectionTable(name = "granpremio_classifica",
			joinColumns = @JoinColumn(name = "granpremio_id"))
	@MapKeyJoinColumn(name = "pilota_id")
	@Column(name = "punti")
	private Map<Pilota, Integer> classifica = new HashMap<>();
	@ManyToMany
	private List<Sponsor> sponsor;
	
	
	public int getNumeroDiGiri() {
		return numeroDiGiri;
	}
	public void setNumeroDiGiri(int numeroDiGiri) {
		this.numeroDiGiri = numeroDiGiri;
	}
	
	
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	
	public Circuito getCircuito() {
		return circuito;
	}
	public void setCircuito(Circuito circuito) {
		this.circuito = circuito;
	}
	
	
	public String getCittà() {
		return circuito.getCittà();
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Map<Pilota, Integer> getClassifica() {
		return classifica;
	}
	public void setClassifica(Map<Pilota, Integer> classifica) {
		this.classifica = classifica;
	}
	public List<Sponsor> getSponsor() {
		return sponsor;
	}
	public void setSponsor(List<Sponsor> sponsor) {
		this.sponsor = sponsor;
	}
	@Override
	public int hashCode() {
		return Objects.hash(data, numeroDiGiri);
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GranPremio other = (GranPremio) obj;
		return Objects.equals(data, other.data) && numeroDiGiri == other.numeroDiGiri;
	}
}
