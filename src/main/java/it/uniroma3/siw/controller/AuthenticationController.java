package it.uniroma3.siw.controller;

import it.uniroma3.siw.model.*;
import it.uniroma3.siw.service.*;
import it.uniroma3.siw.sessionData.SessionData;
import jakarta.validation.Valid;

import static it.uniroma3.siw.model.Credentials.ADMIN_ROLE;
import static it.uniroma3.siw.model.Credentials.DEFAULT_ROLE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AuthenticationController {

    @Autowired
    private CredentialsService credentialsService;
    @Autowired 
    private UserService userService;
	@Autowired
	private SponsorService sponsorService;
	@Autowired
	private GranPremioService granPremioService;
	@Autowired
	private CampionatoService campionatoService;
    @Autowired 
    private SessionData sessionData;

    @GetMapping("/")
    public String index(Model model) {
		model.addAttribute("granPremi", granPremioService.getGranPremiFuturiOrdinatiPerData());
		model.addAttribute("sponsors", sponsorService.getAllSponsors());

		int anno = java.time.Year.now().getValue();
		Campionato campionato = campionatoService.getCampionatoByAnno(anno);

		List<CampionatoPiloti> classifica = campionato.getClassifica();
		// Ordina la classifica per punti in ordine decrescente
		classifica.sort((cp1, cp2) -> Integer.compare(cp2.getPuntiTotali(), cp1.getPuntiTotali()));

		model.addAttribute("classifica", classifica);

        return "homepage.html";
    }
    
    @GetMapping("/login")
	public String login() {
		return "login.html";
	}
    
    @GetMapping("/success")
	public String defaultAfterLogin(Model model) {
		UserDetails userDetails = null;
		Credentials credentials = null;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if(auth instanceof AnonymousAuthenticationToken) {
			return "redirect:/";
		}
		else {
			userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			credentials = this.credentialsService.getCredentials(userDetails.getUsername());
			User loggedUser = this.sessionData.getLoggedUser();
			model.addAttribute("user", loggedUser);
			
			if(credentials.getRole().trim().equals(ADMIN_ROLE)) {
				return "redirect:/";
			}
		}
		
		return "redirect:/";
	}
    
    @GetMapping("/register")
	public String showRegisterUser(Model model) {
		UserDto userDto = new UserDto();
		model.addAttribute("userDto", userDto);
		return "register.html";
	}
	
	@PostMapping("/register")
	public String registraUtente(@Valid @ModelAttribute("user") UserDto userDto, BindingResult userBindingResult,
			Model model) {
		if(!userBindingResult.hasErrors()) {
			User user = this.userService.saveUser(userDto.getName(), userDto.getSurname(), userDto.getEmail());
			Credentials credentials = this.credentialsService.saveCredentials(userDto.getUsername(), userDto.getPassword(), DEFAULT_ROLE, user);
			model.addAttribute("user", user);
			return "login.html";
		}
		return "register.html";
	}
}
