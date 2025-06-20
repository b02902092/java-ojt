package com.example.javaOjt.controllers;

import com.example.javaOjt.beans.responses.author.GetAuthorResponse;
import com.example.javaOjt.services.AuthorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@WebMvcTest(controllers = AuthorController.class)
class AuthorControllerTest {

  private static final String AUTHOR_BASE_URL = "/authors";

  private MockMvc mockMvc;

  @Autowired
  private AuthorController authorController;

  @MockitoBean
  private AuthorService authorService;

  @BeforeEach
  public void setup() {
    mockMvc = MockMvcBuilders.standaloneSetup(authorController).build();
  }

  @Test
  void getAuthor_noWithBooks() throws Exception {
    int targetId = 1;

    // Mocking the service response
    GetAuthorResponse mockResponse = new GetAuthorResponse();
    mockResponse.setExists(true); // Assuming the response indicates the author exists
    mockResponse.setId(targetId);
    Mockito.when(authorService.getAuthorById(targetId, false)).thenReturn(mockResponse);

    // Perform the GET request
    mockMvc.perform(
        MockMvcRequestBuilders.get(AUTHOR_BASE_URL + "/{id}", targetId))
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
      .andReturn();

    // Verify that the service method was called with the correct parameters
    Mockito.verify(authorService, Mockito.times(1)).getAuthorById(targetId, false);
  }

  @Test
  void getAuthor_withBooks_true() throws Exception {
    int targetId = 1;

    // Mocking the service response
    GetAuthorResponse mockResponse = new GetAuthorResponse();
    mockResponse.setExists(true); // Assuming the response indicates the author exists
    mockResponse.setId(targetId);
    Mockito.when(authorService.getAuthorById(targetId, true)).thenReturn(mockResponse);

    // Perform the GET request with the 'withBooks' parameter set to true
    mockMvc.perform(
        MockMvcRequestBuilders.get(AUTHOR_BASE_URL + "/{id}", targetId)
          .param("withBooks", "true"))
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
      .andReturn();

    // Verify that the service method was called with the correct parameters
    Mockito.verify(authorService, Mockito.times(1)).getAuthorById(targetId, true);
  }

  @Test
  void getAuthor_withBooks_false() throws Exception {
    int targetId = 1;

    // Mocking the service response
    GetAuthorResponse mockResponse = new GetAuthorResponse();
    mockResponse.setExists(true); // Assuming the response indicates the author exists
    mockResponse.setId(targetId);
    Mockito.when(authorService.getAuthorById(targetId, false)).thenReturn(mockResponse);

    // Perform the GET request with the 'withBooks' parameter set to false
    mockMvc.perform(
        MockMvcRequestBuilders.get(AUTHOR_BASE_URL + "/{id}", targetId)
          .param("withBooks", "false"))
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
      .andReturn();

    // Verify that the service method was called with the correct parameters
    Mockito.verify(authorService, Mockito.times(1)).getAuthorById(targetId, false);
  }

  @Test
  void getAuthor_invalidAuthorId() throws Exception {
    // Perform the GET request with an invalid author ID
    mockMvc.perform(
        MockMvcRequestBuilders.get(AUTHOR_BASE_URL + "/{id}", "aaaa"))
      .andExpect(MockMvcResultMatchers.status().isBadRequest())
      .andReturn();

    // Verify that the service method was never called
    Mockito.verify(authorService, Mockito.never())
      .getAuthorById(Mockito.anyInt(), Mockito.anyBoolean());
  }

  @Test
  void getAuthor_invalidWithBooks() throws Exception {
    long targetId = 1;
    // Perform the GET request with an invalid 'withBooks' parameter
    mockMvc.perform(
        MockMvcRequestBuilders.get(AUTHOR_BASE_URL + "/{id}", targetId)
          .param("withBooks", "invalid"))
      .andExpect(MockMvcResultMatchers.status().isBadRequest())
      .andReturn();

    // Verify that the service method was never called
    Mockito.verify(authorService, Mockito.never())
      .getAuthorById(Mockito.anyInt(), Mockito.anyBoolean());
  }
}