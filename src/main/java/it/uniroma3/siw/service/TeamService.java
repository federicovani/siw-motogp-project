package it.uniroma3.siw.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Team;
import it.uniroma3.siw.repository.TeamRepository;

@Service
public class TeamService {
	@Autowired
	private TeamRepository teamRepository;

	public List<Team> findAll() {
	    List<Team> result = new ArrayList<>();
	    teamRepository.findAll().forEach(result::add);
	    return result;
	}

    public Team findById(Long id) {
        return teamRepository.findById(id).orElse(null);
    }

    public void save(Team team) {
        teamRepository.save(team);
    }

    public void deleteById(Long id) {
        teamRepository.deleteById(id);
    }
}
