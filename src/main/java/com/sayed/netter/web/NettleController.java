package com.sayed.netter.web;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sayed.netter.Nettle;
import com.sayed.netter.data.NettleRepository;

@Controller
@RequestMapping("/nettles")
public class NettleController {

  private static final String MAX_LONG_AS_STRING = "9223372036854775807";
  private NettleRepository nettleRepository;

  @Autowired
  public NettleController(NettleRepository nettleRepository) {
    this.nettleRepository = nettleRepository;
  }

  @RequestMapping(method=RequestMethod.GET)
  public List<Nettle> nettles(
      @RequestParam(value="max", defaultValue=MAX_LONG_AS_STRING) long max,
      @RequestParam(value="count", defaultValue="20") int count) {
    return nettleRepository.findNettles(max, count);
  }

  @RequestMapping(value="/{nettleId}", method=RequestMethod.GET)
  public String nettle(
      @PathVariable("nettleId") long nettleId, 
      Model model) {
	  Nettle nettle = nettleRepository.findOne(nettleId);
	  model.addAttribute(nettle);
    return "nettle";
  }

  @RequestMapping(method=RequestMethod.POST)
  public String saveNettle(NettleForm form, Model model) throws Exception {
    nettleRepository.save(new Nettle(null, form.getMessage(), new Date(), 
        form.getLongitude(), form.getLatitude()));
    return "redirect:/nettles";
  }
  
  @ExceptionHandler(NettleNotFoundException.class)
  public String handleNettleNotFound() {
	  return "error/notFound";
  }

}
