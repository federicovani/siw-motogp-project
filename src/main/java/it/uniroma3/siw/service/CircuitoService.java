package it.uniroma3.siw.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siw.model.Circuito;
import it.uniroma3.siw.repository.CircuitoRepository;

@Service
public class CircuitoService {
	
	@Autowired CircuitoRepository circuitoRepository;
	@Autowired private ImmagineService immagineService;
	
	
	public Circuito getCircuitoById(Long id) {
		return circuitoRepository.findById(id).orElse(null);
	}
	
	public List<Circuito> getAllCircuiti() {
		List<Circuito> result = new ArrayList<>();
		circuitoRepository.findAll().forEach(result::add);
		return result;
	}
	
	@Transactional
    public void save(Circuito circuito) {
        circuitoRepository.save(circuito);
    }
	
	@Transactional
    public void saveImmagine(Circuito circuito, MultipartFile file) {
        //Verifica se è già presente un'immagine ed eliminala
        String immaginePrecedente = circuito.getImmagine();
        if (immaginePrecedente != null) {
            immagineService.deleteImage(immaginePrecedente);
        }

        // Salva l'immagine sul file system
        String fileName = immagineService.saveImage(file);

        // Collega l'immagine al libro
        circuito.setImmagine(fileName);
    }

}
