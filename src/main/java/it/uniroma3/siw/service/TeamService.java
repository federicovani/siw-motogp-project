package it.uniroma3.siw.service;

import java.util.ArrayList;
import java.util.List;

import it.uniroma3.siw.model.Pilota;
import it.uniroma3.siw.model.Sponsor;
import it.uniroma3.siw.repository.PilotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Team;
import it.uniroma3.siw.repository.TeamRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class TeamService {
	@Autowired
	private TeamRepository teamRepository;
    @Autowired
    private PilotaRepository pilotaRepository;
    @Autowired
    private ImmagineService immagineService;

    @Transactional
	public List<Team> findAll() {
	    List<Team> result = new ArrayList<>();
	    teamRepository.findAll().forEach(result::add);
	    return result;
	}

    @Transactional
    public Team findById(Long id) {
        return teamRepository.findById(id).orElse(null);
    }

    @Transactional
    public void save(Team team) {
        teamRepository.save(team);
    }

    @Transactional
    public void deleteById(Long id) {
        Team team = getTeamById(id);
        List<Pilota> piloti = team.getPilotiUfficiali();
        if (piloti != null) {
            for(Pilota pilota : piloti){
                pilota.setTeam(null);
                pilotaRepository.save(pilota);
            }
        }

        // Elimina l'immagine associata, se presente
        String immagine = team.getImmagine();
        if (immagine != null) {
            immagineService.deleteImage(immagine);
        }

        teamRepository.deleteById(id);
    }

    @Transactional
    public Team getTeamById(Long id) {
        return teamRepository.findById(id).orElse(null);
    }

    @Transactional
    public boolean existsByNome(String nome) {
        return teamRepository.existsByNomeIgnoreCase(nome);
    }

    @Transactional
    public void saveImmagine(Team team, MultipartFile file) {
        //Verifica se è già presente un'immagine ed eliminala
        String immaginePrecedente = team.getImmagine();
        if (immaginePrecedente != null) {
            immagineService.deleteImage(immaginePrecedente);
        }

        // Salva l'immagine sul file system
        String fileName = immagineService.saveImage(file);

        // Collega l'immagine al libro
        team.setImmagine(fileName);
    }

    @Transactional
    public void removePilotaFromTeam(Team team, Pilota pilota) {
        List<Pilota> piloti = team.getPilotiUfficiali();
        if (piloti != null && piloti.contains(pilota)) {
            piloti.remove(pilota);
            team.setPilotiUfficiali(piloti);
        }
        teamRepository.save(team);
    }

    @Transactional
    public List<Team> findAllAvailable() {
        List<Team> teamDisponibili = new ArrayList<>();
        //Restituisci tutti i team con meno di due piloti al loro interno
        teamRepository.findAll().forEach(team -> {
            if (team.getPilotiUfficiali() == null || team.getPilotiUfficiali().size() < 2) {
                teamDisponibili.add(team);
            }
        });
        return teamDisponibili;
    }

    @Transactional
    public void rimuoviPilotiUfficiali(Team team) {
        List<Pilota> piloti = team.getPilotiUfficiali();
        if (piloti != null) {
            for(Pilota pilota : piloti){
                pilota.setTeam(null);
                pilotaRepository.save(pilota);
            }
        }
        team.setPilotiUfficiali(null);
        teamRepository.save(team);
    }

    @Transactional
    public void rimuoviSponsor(Team team, Sponsor sponsor) {
        if(team!=null && team.getSponsor() != null && team.getSponsor().contains(sponsor)) {
            team.getSponsor().remove(sponsor);
            teamRepository.save(team);
        }
    }
}
