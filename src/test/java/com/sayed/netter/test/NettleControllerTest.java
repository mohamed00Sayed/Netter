package com.sayed.netter.test;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.view.InternalResourceView;

import com.sayed.netter.Nettle;
import com.sayed.netter.data.NettleRepository;
import com.sayed.netter.web.NettleController;

public class NettleControllerTest {

  @Test
  public void shouldShowRecentSpittles() throws Exception {
    List<Nettle> expectedNettles = createNettleList(20);
    NettleRepository mockRepository = mock(NettleRepository.class);
    when(mockRepository.findNettles(Long.MAX_VALUE, 20))
        .thenReturn(expectedNettles);

    NettleController controller = new NettleController(mockRepository);
    MockMvc mockMvc = standaloneSetup(controller)
        .setSingleView(new InternalResourceView("/WEB-INF/views/nettles.jsp"))
        .build();

    mockMvc.perform(get("/nettles"))
       .andExpect(view().name("nettles"))
       .andExpect(model().attributeExists("nettleList"))
       .andExpect(model().attribute("nettleList", 
                  hasItems(expectedNettles.toArray())));
  }

  @Test
  public void shouldShowPagedSpittles() throws Exception {
    List<Nettle> expectedNettles = createNettleList(50);
    NettleRepository mockRepository = mock(NettleRepository.class);
    when(mockRepository.findNettles(238900, 50))
        .thenReturn(expectedNettles);
    
    NettleController controller = new NettleController(mockRepository);
    MockMvc mockMvc = standaloneSetup(controller)
        .setSingleView(new InternalResourceView("/WEB-INF/views/nettles.jsp"))
        .build();

    mockMvc.perform(get("/nettles?max=238900&count=50"))
      .andExpect(view().name("nettles"))
      .andExpect(model().attributeExists("nettleList"))
      .andExpect(model().attribute("nettleList", 
                 hasItems(expectedNettles.toArray())));
  }
  
  @Test
  public void testSpittle() throws Exception {
	Nettle expectedNettle = new Nettle("Hello", new Date());
    NettleRepository mockRepository = mock(NettleRepository.class);
    when(mockRepository.findOne(12345)).thenReturn(expectedNettle);
    
    NettleController controller = new NettleController(mockRepository);
    MockMvc mockMvc = standaloneSetup(controller).build();

    mockMvc.perform(get("/nettles/12345"))
      .andExpect(view().name("nettle"))
      .andExpect(model().attributeExists("nettle"))
      .andExpect(model().attribute("nettle", expectedNettle));
  }

  @Test
  public void saveSpittle() throws Exception {
    NettleRepository mockRepository = mock(NettleRepository.class);
    NettleController controller = new NettleController(mockRepository);
    MockMvc mockMvc = standaloneSetup(controller).build();

    mockMvc.perform(post("/nettles")
           .param("message", "Hello World") // this works, but isn't really testing what really happens
           .param("longitude", "-81.5811668")
           .param("latitude", "28.4159649")
           )
           .andExpect(redirectedUrl("/nettles"));
    
    verify(mockRepository, atLeastOnce()).save(new Nettle(null, "Hello World", new Date(), -81.5811668, 28.4159649));
  }
  
  private List<Nettle> createNettleList(int count) {
    List<Nettle> nettles = new ArrayList<Nettle>();
    for (int i=0; i < count; i++) {
    	nettles.add(new Nettle("Nettle " + i, new Date()));
    }
    return nettles;
  }
}
