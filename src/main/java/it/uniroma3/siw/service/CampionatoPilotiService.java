package it.uniroma3.siw.service;

import it.uniroma3.siw.model.Campionato;
import it.uniroma3.siw.model.Pilota;
import it.uniroma3.siw.repository.PilotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.CampionatoPiloti;
import it.uniroma3.siw.repository.CampionatoPilotiRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CampionatoPilotiService {

    @Autowired
    private CampionatoPilotiRepository campionatoPilotiRepository;
    @Autowired
    private CampionatoService campionatoService;
    @Autowired
    private PilotaRepository pilotaRepository;

    public Iterable<CampionatoPiloti> getAllCampionati() {
        return campionatoPilotiRepository.findAll();
    }

    public CampionatoPiloti getCampionatoById(Long id) {
        return campionatoPilotiRepository.findById(id).orElse(null);
    }

    @Transactional
    public List<Integer> getPunteggioEPosizionePilota(Long idPilota) {
        List<Integer> posizionePunti = new java.util.ArrayList<>();

        Pilota pilota = pilotaRepository.findById(idPilota).orElse(null);

        int anno = java.time.Year.now().getValue();
        Campionato campionato = campionatoService.getCampionatoByAnno(anno);

        if (pilota == null || campionato == null) {
            throw new IllegalArgumentException("Pilota o campionato non possono essere null.");
        }

        List<CampionatoPiloti> classifica = campionato.getClassifica();
        // Ordina la classifica per punti in ordine decrescente
        classifica.sort((cp1, cp2) -> Integer.compare(cp2.getPuntiTotali(), cp1.getPuntiTotali()));

        int posizione=1;
        int punti = 0;
        for(CampionatoPiloti cp : classifica) {
            if(cp.getPilota().equals(pilota)){
                punti = cp.getPuntiTotali();
                posizionePunti.add(posizione);
                posizionePunti.add(punti);
                return posizionePunti;
            }
            posizione++;
        }
        posizionePunti.add(0);
        posizionePunti.add(0);
        return posizionePunti;
    }

}
