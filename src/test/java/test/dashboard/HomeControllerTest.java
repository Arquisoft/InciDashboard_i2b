package test.dashboard;

import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.uniovi.dashboard.HomeController;

public class HomeControllerTest {
	
		@InjectMocks
	    private HomeController homeController;

	    private MockMvc mockMvc;

	    @Before
	    public void setUp() throws Exception {
	        MockitoAnnotations.initMocks(this);
	        this.mockMvc = MockMvcBuilders.standaloneSetup(homeController).build();
	    }
	    
	    /*
	    @Test
	    public void testHome() throws Exception {
			MockHttpServletRequestBuilder request = get("/");
			
		    int status = mockMvc.perform(request)
					.andExpect(forwardedUrl(""))
					.andReturn()
					.getResponse()
					.getStatus();
			
			assertEquals(HttpStatus.OK.value(), status);
	    }
	    */
}
