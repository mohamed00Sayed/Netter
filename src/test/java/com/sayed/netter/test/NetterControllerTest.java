package com.sayed.netter.test;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

import com.sayed.netter.Netter;
import com.sayed.netter.data.NetterRepository;
import com.sayed.netter.web.NetterController;


public class NetterControllerTest {


  public static class ShouldShowRegistration{
	  @Test
	  public void shouldShowRegistration() throws Exception {
		NetterRepository mockRepository = mock(NetterRepository.class);
		NetterController controller = new NetterController(mockRepository);
	    MockMvc mockMvc = standaloneSetup(controller).build();
	    mockMvc.perform(get("/netter/register"))
	    	   .andExpect(status().isOk())
	           .andExpect(view().name("registerForm"));
	  }
  }

  public static class ShouldProcessRegistration{
	  @Test
	  public void shouldProcessRegistration() throws Exception {
		NetterRepository mockRepository = mock(NetterRepository.class);
	    Netter unsaved = new Netter("jbauer", "24hours", "Jack", "Bauer", "jbauer@ctu.gov");
	    Netter saved = new Netter(24L, "jbauer", "24hours", "Jack", "Bauer", "jbauer@ctu.gov");
	    when(mockRepository.save(unsaved)).thenReturn(saved);
	    
	    NetterController controller = new NetterController(mockRepository);
	    MockMvc mockMvc = standaloneSetup(controller).build();

	    mockMvc.perform(post("/netter/register")
	           .param("firstName", "Jack")
	           .param("lastName", "Bauer")
	           .param("username", "jbauer")
	           .param("password", "24hours")
	           .param("email", "jbauer@ctu.gov"))
	    	   .andExpect(status().isFound())
	           .andExpect(redirectedUrl("/netter/jbauer"));
	    
	    verify(mockRepository, atLeastOnce()).save(unsaved);
	  }
  }

  public static class ShouldFailValidationWithNoData{
	  @Test
	  public void shouldFailValidationWithNoData() throws Exception {
		NetterRepository netterRepository = mock(NetterRepository.class);
		NetterController controller = new NetterController(netterRepository);
	    MockMvc mockMvc = standaloneSetup(controller).build();
	    
	    mockMvc.perform(post("/netter/register"))
	        .andExpect(status().isOk())
	        .andExpect(view().name("registerForm"))
	        .andExpect(model().errorCount(5))
	        .andExpect(model().attributeHasFieldErrors(
	            "netter", "firstName", "lastName", "username", "password", "email"));
	  }
  }


}
