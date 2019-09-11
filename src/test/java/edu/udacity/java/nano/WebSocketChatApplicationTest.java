package edu.udacity.java.nano;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import edu.udacity.java.nano.chat.Message;


@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(WebSocketChatApplicationTest.class)
@WebAppConfiguration

public class WebSocketChatApplicationTest {
	
	
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	WebApplicationContext wac;
	
	@Before
	public void setUp() throws Exception{
		mvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}
	
	 @Test
	 public void verifyHomepageLoads() throws Exception {
		 
	  mvc.perform(MockMvcRequestBuilders.get("/")).andExpect(MockMvcResultMatchers.status().isOk());
		 	 
	 }
	 
	 @Test
	 public void verifyEnter() throws Exception {

		 mvc.perform(MockMvcRequestBuilders.get("/index"))
		 .andExpect(MockMvcResultMatchers.view().name("/chat"))
		 .andExpect(MockMvcResultMatchers.status().isOk());
	 }
	 
	 
	 @Test
	 public void veriyChat() throws Exception {
		 
		 mvc.perform(MockMvcRequestBuilders.get("/"))
		 .andExpect(MockMvcResultMatchers.view().name("/login"))
		 .andExpect(MockMvcResultMatchers.status().isOk());
		 
		 mvc.perform(MockMvcRequestBuilders.get("/index"))
		 .andExpect(MockMvcResultMatchers.view().name("/chat"))
		 .andExpect(MockMvcResultMatchers.status().isOk());
		 	 
	 }
	 
	 
	 @Test
	 public void veriyLeave() throws Exception {
		 
		 mvc.perform(MockMvcRequestBuilders.get("/"))
		 .andExpect(MockMvcResultMatchers.view().name("/login"))
		 .andExpect(MockMvcResultMatchers.status().isOk());
		 
		
		 	 
	 }
	 
	 @Test
	 public void veriyUserName() throws Exception {
		 mvc.perform(MockMvcRequestBuilders.get("/index?username=Latif")).
				 andExpect(content().string(containsString("Latif")));
		 
	 }
}