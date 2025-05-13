package it.uniroma3.siw.model;

import java.time.Duration;
import java.util.Objects;

public class PilotaGP {
	private int posizione;
	private Duration migliorTempo;
	
	
	
	
	public int getPosizione() {
		return posizione;
	}
	public void setPosizione(int posizione) {
		this.posizione = posizione;
	}
	
	
	public Duration getMigliorTempo() {
		return migliorTempo;
	}
	public void setMigliorTempo(Duration migliorTempo) {
		this.migliorTempo = migliorTempo;
	}
	
	
	
	
	@Override
	public int hashCode() {
		return Objects.hash(migliorTempo, posizione);
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PilotaGP other = (PilotaGP) obj;
		return Objects.equals(migliorTempo, other.migliorTempo) && posizione == other.posizione;
	}
	
	
}
