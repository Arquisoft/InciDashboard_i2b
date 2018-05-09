package test.dashboard;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.uniovi.dashboard.OperatorController;
import com.uniovi.entities.Incident;
import com.uniovi.main.InciDashboardI2bApplication;
import com.uniovi.services.IncidentsService;
import com.uniovi.services.OperatorsService;

@SpringBootTest(classes = { InciDashboardI2bApplication.class })
@RunWith(SpringJUnit4ClassRunner.class)
public class OperatorsControllerTest {

	@Mock
	public IncidentsService incidentsService;

	@Mock
	public OperatorsService operatorsService;

	@InjectMocks
	private OperatorController operatorsController;

	private MockMvc mockMvc;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		when(incidentsService.getIncidentByName("good")).thenReturn(new Incident());
		when(incidentsService.getIncidentByName("detailed")).thenReturn(new Incident());
		this.mockMvc = MockMvcBuilders.standaloneSetup(operatorsController).build();
	}

	@Test
	public void testAddComment() throws Exception {
		MockHttpServletRequestBuilder request = post("/incident/addComment")
				.param("name", "good")
				.param("comment","test");

		String response = mockMvc
				.perform(request)
				.andReturn().getResponse()
				.getContentAsString();
		assertEquals("Comment added", response);

		request = post("/incident/addComment")
				.param("name", "bad")
				.param("comment", "test");

		response = mockMvc.perform(request)
				.andReturn()
				.getResponse()
				.getContentAsString();
		assertEquals("Error adding comment!", response);
	}

	@Test
	public void testChangeState() throws Exception {
		MockHttpServletRequestBuilder request = post("/incident/changeState").param("name", "good").param("state",
				"OPEN");

		String response = mockMvc.perform(request).andReturn().getResponse().getContentAsString();
		assertEquals("State changed", response);
	}

}
