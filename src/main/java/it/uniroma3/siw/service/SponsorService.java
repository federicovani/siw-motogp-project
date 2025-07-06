package it.uniroma3.siw.service;

import it.uniroma3.siw.model.Pilota;
import it.uniroma3.siw.model.Sponsor;
import it.uniroma3.siw.model.Team;
import it.uniroma3.siw.repository.SponsorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class SponsorService {
    @Autowired
    private SponsorRepository sponsorRepository;
    @Autowired
    private ImmagineService immagineService;

    public List<Sponsor> getAllSponsors() {
        return sponsorRepository.findAll();
    }

    public List<Sponsor> findAllById(List<Long> sponsorIds) {
        return sponsorRepository.findAllById(sponsorIds);
    }

    @Transactional
    public void saveImmagine(Sponsor sponsor, MultipartFile file) {
        //Verifica se è già presente un'immagine ed eliminala
        String immaginePrecedente = sponsor.getImmagine();
        if (immaginePrecedente != null) {
            immagineService.deleteImage(immaginePrecedente);
        }

        // Salva l'immagine sul file system
        String fileName = immagineService.saveImage(file);

        // Collega l'immagine al libro
        sponsor.setImmagine(fileName);
    }

    public boolean existsByNome(String nome) {
        return sponsorRepository.existsByNomeIgnoreCase(nome);
    }

    public void save(Sponsor sponsor) {
        sponsorRepository.save(sponsor);
    }

    public Sponsor getSponsorById(Long id) {
        return sponsorRepository.findById(id).orElse(null);
    }
}
