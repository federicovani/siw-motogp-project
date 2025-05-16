package it.uniroma3.siw.model;

import java.time.LocalDate;

public class Contratto {
	private int importo;
	private LocalDate dataInizioContratto;
	private LocalDate dataFineContratto;
	
	
	
	
	public int getImporto() {
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
