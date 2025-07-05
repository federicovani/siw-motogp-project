package it.uniroma3.siw.service;

import java.util.ArrayList;
import java.util.List;

import it.uniroma3.siw.model.Sponsor;
import it.uniroma3.siw.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Pilota;
import it.uniroma3.siw.repository.PilotaRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PilotaService {

    @Autowired
    private PilotaRepository pilotaRepository;
    @Autowired
    private TeamService teamService;
    @Autowired
    private ImmagineService immagineService;
    @Autowired
    private SponsorService sponsorService;

    @Transactional
    public Pilota getPilotaById(Long id) {
        return pilotaRepository.findById(id).orElse(null);
    }

    @Transactional
    public List<Pilota> getAllPiloti() {
        List<Pilota> result = new ArrayList<>();
        pilotaRepository.findAll().forEach(result::add);
        return result;
    }

    @Transactional
    public void save(Pilota pilota) {
        pilotaRepository.save(pilota);
    }

    @Transactional
    public void deleteById(Long id) {
        Pilota pilota = getPilotaById(id);
        Team team = pilota.getTeam();

        if(team != null) {
            teamService.removePilotaFromTeam(team, pilota);
            teamService.save(team);
        }

        // Elimina l'immagine associata, se presente
        String immagine = pilota.getImmagine();
        if (immagine != null) {
            immagineService.deleteImage(immagine);
        }

        pilotaRepository.deleteById(id);
    }

    @Transactional
    public boolean existsByNomeAndCognome(String nome, String cognome) {
        return pilotaRepository.existsByNomeAndCognomeIgnoreCase(nome, cognome);
    }

    @Transactional
    public void saveImmagine(Pilota pilota, MultipartFile file) {
        //Verifica se è già presente un'immagine ed eliminala
        String immaginePrecedente = pilota.getImmagine();
        if (immaginePrecedente != null) {
            immagineService.deleteImage(immaginePrecedente);
        }

        // Salva l'immagine sul file system
        String fileName = immagineService.saveImage(file);

        // Collega l'immagine al libro
        pilota.setImmagine(fileName);
    }

    @Transactional
    public Pilota getCompagnoDiSquadra(Long id) {
        Pilota pilota = getPilotaById(id);
        Team team = pilota.getTeam();
        if (team != null) {
            List<Pilota> piloti = team.getPilotiUfficiali();
            if (piloti != null) {
                for (Pilota p : piloti) {
                    if (p.getId() != id) {
                        return p;
                    }
                }
            }
        }
        return null;
    }

    @Transactional
    public List<Pilota> getPilotiDisponibili() {
        List<Pilota> pilotiDisponibili = new ArrayList<>();

        //Restituisci tutti i piloti che non fanno parte di un team
        pilotaRepository.findAll().forEach(pilota -> {
            if (pilota.getTeam() == null) {
                pilotiDisponibili.add(pilota);
            }
        });
        return pilotiDisponibili;
    }

    @Transactional
    public List<Pilota> findAllById(List<Long> pilotiIds) {
        // Usa il metodo già fornito da CrudRepository
        Iterable<Pilota> pilotiIterable = pilotaRepository.findAllById(pilotiIds);
        // Converti da Iterable a List
        List<Pilota> piloti = new ArrayList<>();
        pilotiIterable.forEach(piloti::add);
        return piloti;
    }
}
