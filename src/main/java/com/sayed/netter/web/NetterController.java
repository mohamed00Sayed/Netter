package com.sayed.netter.web;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
  public String showRegistrationForm() {
    return "registerForm";
  }
  
  @RequestMapping(value="/register", method=POST)
  public String processRegistration(
      @Valid Netter netter, 
      Errors errors) {
    if (errors.hasErrors()) {
      return "registerForm";
    }
    
    netterRepository.save(netter);
    return "redirect:/netter/" + netter.getUsername();
  }
  
  @RequestMapping(value="/{username}", method=GET)
  public String showNetterProfile(@PathVariable String username, Model model) {
    Netter netter = netterRepository.findByUsername(username);
    model.addAttribute(netter);
    return "profile";
  }
  
}
