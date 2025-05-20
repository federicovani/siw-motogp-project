package it.uniroma3.siw.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

public class Contratto {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private float importo;
	private LocalDate dataInizioContratto;
	private LocalDate dataFineContratto;
	
	
	public float getImporto() {
		return importo;
	}
	public void setImporto(int importo) {
		this.importo = importo;
	}
	
	
	public LocalDate getDataInizioContratto() {
		return dataInizioContratto;
	}
	public void setDataInizioContratto(LocalDate dataInizioContratto) {
		this.dataInizioContratto = dataInizioContratto;
	}
	
	
	public LocalDate getDataFineContratto() {
		return dataFineContratto;
	}
	public void setDataFineContratto(LocalDate dataFineContratto) {
		this.dataFineContratto = dataFineContratto;
	}
}
