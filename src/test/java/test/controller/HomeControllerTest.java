package test.controller;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.uniovi.controllers.HomeController;

public class HomeControllerTest {
	
		@InjectMocks
	    private HomeController homeController;

	    private MockMvc mockMvc;

	    @Before
	    public void setUp() throws Exception {
	        MockitoAnnotations.initMocks(this);
	        this.mockMvc = MockMvcBuilders.standaloneSetup(homeController).build();
	    }
	    
	    @Test
	    public void testHome() throws Exception {
			MockHttpServletRequestBuilder request = get("/");
			
		    int status = mockMvc.perform(request)
					.andExpect(forwardedUrl("login"))
					.andReturn()
					.getResponse()
					.getStatus();
			
			assertEquals(HttpStatus.OK.value(), status);
	    }
}
