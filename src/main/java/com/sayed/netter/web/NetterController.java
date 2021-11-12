package com.sayed.netter.web;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.io.File;
import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sayed.netter.Netter;
import com.sayed.netter.data.NetterRepository;

@Controller
@RequestMapping("/netter")
public class NetterController {

  private NetterRepository netterRepository;

  @Autowired
  public NetterController(NetterRepository netterRepository) {
    this.netterRepository = netterRepository;
  }
  
  @RequestMapping(value="/register", method=GET)
  public String showRegistrationForm(Model model) {
	  model.addAttribute(new Netter());
    return "registerForm";
  }
  
  @RequestMapping(value="/register", method=POST)
  public String processRegistration(
	  @RequestPart("profilePicture") MultipartFile profilePicture,	  
      @Valid Netter netter, 
      Errors errors,
      RedirectAttributes model) {
    if (errors.hasErrors()) {
      return "registerForm";
    }
    try {
		profilePicture.transferTo(
				new File("/" + profilePicture.getOriginalFilename()));
	} catch (IllegalStateException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    Netter savedNetter = netterRepository.save(netter);
    model.addAttribute("username", savedNetter.getUsername());
    model.addFlashAttribute(savedNetter);    
    return "redirect:/netter/{username}";
  }
  
  @RequestMapping(value="/{username}", method=GET)
  public String showNetterProfile(@PathVariable String username, Model model) {
	if(!model.containsAttribute("netter")) {
	    Netter netter = netterRepository.findByUsername(username);
	    model.addAttribute(netter);
	}
    return "profile";
  }
  
}
