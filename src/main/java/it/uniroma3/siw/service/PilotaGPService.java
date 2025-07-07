package it.uniroma3.siw.service;

import it.uniroma3.siw.model.GranPremio;
import it.uniroma3.siw.model.Pilota;
import it.uniroma3.siw.model.PilotaGP;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.repository.GranPremioRepository;
import it.uniroma3.siw.repository.PilotaGPRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class PilotaGPService {

    @Autowired
    private GranPremioRepository granPremioService;
    @Autowired
    private GranPremioRepository granPremioRepository;
    @Autowired
    private PilotaGPRepository pilotaGPRepository;

    public void addVotoRiderOfTheRace(GranPremio gp, Pilota pilota, User user){
        if (pilota == null || gp == null) {
            throw new IllegalArgumentException("Pilota o GranPremio non possono essere null.");
        }
        if (gp.getRisultati() != null && gp.getUtentiVotanti().contains(user)) {
            throw new IllegalArgumentException("Puoi votare un solo pilota per ogni Gran Premio.");
        }
        if(gp.getRisultati() != null && !gp.getRisultati().isEmpty()) {
            for (PilotaGP risultato : gp.getRisultati()) {
                if (risultato.getPilota().equals(pilota)) {
                    gp.getUtentiVotanti().add(user);
                    risultato.addVoto();
                    granPremioService.save(gp);
                    pilotaGPRepository.save(risultato);
                    break;
                }
            }
        }
    }

    public Pilota getRiderOfTheRace(GranPremio gp) {
        if (gp == null) {
            throw new IllegalArgumentException("Gran Premio non può essere nullo");
        }
        if(gp.getRisultati() == null || gp.getRisultati().isEmpty()){
            return null;
        }

        // Trova il risultato con il numero di voti massimo
        PilotaGP riderOfTheRace = gp.getRisultati().stream()
                .max(Comparator.comparingInt(PilotaGP::getVoti))
                .orElseThrow(() -> new IllegalStateException("Impossibile trovare un pilota con più voti."));

        // Ritorna il pilota associato a quel risultato
        if(riderOfTheRace.getVoti() == 0){
            return null;
        }
        return riderOfTheRace.getPilota();
    }

    public int getPosizionePilota(GranPremio gp, Pilota pilota){
        if (pilota == null || gp == null) {
            throw new IllegalArgumentException("Pilota o GranPremio non possono essere null.");
        }
        if(gp.getRisultati() != null && !gp.getRisultati().isEmpty()) {
            for (PilotaGP risultato : gp.getRisultati()) {
                if (risultato.getPilota().equals(pilota)) {
                    return risultato.getPosizione();
                }
            }
        }
        return 0;
    }

    public int getPartecipazioniTotaliPilota(Pilota pilota){
        List<GranPremio> granPremi = granPremioRepository.findAll();
        int partecipazioniTotali = 0;
        for(GranPremio gp : granPremi){
            if(gp.getRisultati() != null && !gp.getRisultati().isEmpty()){
                for(PilotaGP risultato : gp.getRisultati()){
                    if(risultato.getPilota().equals(pilota)){
                        partecipazioniTotali++;
                    }
                }
            }
        }
        return partecipazioniTotali;
    }

    public int getVittorieTotaliPilota(Pilota pilota){
        List<GranPremio> granPremi = granPremioRepository.findAll();
        int vittorie = 0;
        for(GranPremio gp : granPremi){
            if(gp.getRisultati() != null && !gp.getRisultati().isEmpty()){
                for(PilotaGP risultato : gp.getRisultati()){
                    if(risultato.getPilota().equals(pilota) && risultato.getPosizione()==1){
                        vittorie++;
                    }
                }
            }
        }
        return vittorie;
    }

    public int getPodiTotaliPilota(Pilota pilota){
        List<GranPremio> granPremi = granPremioRepository.findAll();
        int podi = 0;
        for(GranPremio gp : granPremi){
            if(gp.getRisultati() != null && !gp.getRisultati().isEmpty()){
                for(PilotaGP risultato : gp.getRisultati()){
                    if(risultato.getPilota().equals(pilota) && risultato.getPosizione() > 0 && risultato.getPosizione() < 4){
                        podi++;
                    }
                }
            }
        }
        return podi;
    }
}
