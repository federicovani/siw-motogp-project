package it.uniroma3.siw.service;

import it.uniroma3.siw.model.GranPremio;
import it.uniroma3.siw.model.Pilota;
import it.uniroma3.siw.model.PilotaGP;
import it.uniroma3.siw.repository.GranPremioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PilotaGPService {

    @Autowired
    private GranPremioRepository granPremioService;
    @Autowired
    private GranPremioRepository granPremioRepository;

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
