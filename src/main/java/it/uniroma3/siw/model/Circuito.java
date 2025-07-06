package it.uniroma3.siw.model;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Circuito {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private double lunghezza;
	private double larghezza;
	private double rettilineo;
	private int curveDx;
	private int curveSx;
	private String città;
	private String paese;
	private String immagine;
	
	// Getter e Setter
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public double getLunghezza() {
		return lunghezza;
	}
	public void setLunghezza(double lunghezza) {
		this.lunghezza = lunghezza;
	}
	
	public double getLarghezza() {
		return larghezza;
	}
	public void setLarghezza(double larghezza) {
		this.larghezza = larghezza;
	}
	
	public double getRettilineo() {
		return rettilineo;
	}
	public void setRettilineo(double rettilineo) {
		this.rettilineo = rettilineo;
	}
	
	public int getCurveDx() {
		return curveDx;
	}
	public void setCurveDx(int curveDx) {
		this.curveDx = curveDx;
	}
	
	public int getCurveSx() {
		return curveSx;
	}
	public void setCurveSx(int curveSx) {
		this.curveSx = curveSx;
	}
	
	public String getCitta() {
		return città;
	}
	public void setCitta(String città) {
		this.città = città;
	}
	
	public String getPaese() {
		return paese;
	}
	public void setPaese(String paese) {
		this.paese = paese;
	}

	public String getImmagine() {
		return immagine;
	}

	public void setImmagine(String immagine) {
		this.immagine = immagine;
	}

	// Equals e hashCode
	@Override
	public int hashCode() {
		return Objects.hash(città, curveDx, curveSx, id, larghezza, lunghezza, paese, rettilineo);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Circuito other = (Circuito) obj;
		return Objects.equals(città, other.città) && curveDx == other.curveDx && curveSx == other.curveSx
				&& Objects.equals(id, other.id)
				&& Double.doubleToLongBits(larghezza) == Double.doubleToLongBits(other.larghezza)
				&& Double.doubleToLongBits(lunghezza) == Double.doubleToLongBits(other.lunghezza)
				&& Objects.equals(paese, other.paese)
				&& Double.doubleToLongBits(rettilineo) == Double.doubleToLongBits(other.rettilineo);
	}
}
