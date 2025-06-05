package com.example.javaOjt.integrationTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.javaOjt.DBTestBase;
import com.example.javaOjt.beans.responses.author.GetAuthorResponse;
import com.example.javaOjt.beans.responses.common.OjtExceptionResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@AutoConfigureMockMvc
class AuthorIntegrationTest extends DBTestBase {

  private static final String AUTHOR_BASE_URL = "/authors/";

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  void getAuthor_isExists() throws Exception {
    int targetId = 1;

    // Perform the GET request to retrieve an existing author
    MvcResult result = mockMvc.perform(
        MockMvcRequestBuilders.get(AUTHOR_BASE_URL + targetId))
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
      .andReturn();

    // Parse the response to GetAuthorResponse object
    GetAuthorResponse resultAuthor = objectMapper.readValue(
      result.getResponse().getContentAsString(),
      GetAuthorResponse.class);

    // Assertions to verify the author details
    Assertions.assertNotNull(resultAuthor);
    Assertions.assertEquals(targetId, resultAuthor.getId());
    Assertions.assertEquals("門畑顕博", resultAuthor.getName());
  }

  @Test
  void getAuthorTest_notFound() throws Exception {
    int targetId = Integer.MAX_VALUE;

    // Attempt to get an author that does not exist
    MvcResult result = mockMvc.perform(
        MockMvcRequestBuilders.get(AUTHOR_BASE_URL + targetId))
      .andExpect(MockMvcResultMatchers.status().isNotFound())
      .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
      .andReturn();

    // Parse the error response
    OjtExceptionResponse resultErr = objectMapper.readValue(
      result.getResponse().getContentAsString(),
      OjtExceptionResponse.class);

    // Assertions to verify the error response
    Assertions.assertNotNull(resultErr);
    Assertions.assertNotNull(resultErr.getError());
    Assertions.assertEquals("Author not found with ID: " + targetId,
      resultErr.getError().message());
    Assertions.assertEquals(404, resultErr.getError().code());
  }

  @Test
  void getAuthorTest_withBooks_true() throws Exception {
    int targetId = 1;

    // Perform the GET request to retrieve an existing author
    MvcResult result = mockMvc.perform(
        MockMvcRequestBuilders.get(AUTHOR_BASE_URL + targetId)
          .param("withBooks", "true"))
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
      .andReturn();

    // Parse the response to GetAuthorResponse object
    GetAuthorResponse resultAuthor = objectMapper.readValue(
      result.getResponse().getContentAsString(),
      GetAuthorResponse.class);

    // Assertions to verify the author details
    Assertions.assertNotNull(resultAuthor);
    Assertions.assertEquals(targetId, resultAuthor.getId());
    Assertions.assertEquals("門畑顕博", resultAuthor.getName());
    Assertions.assertTrue(resultAuthor.isExists());
    Assertions.assertFalse(resultAuthor.getBooks().isEmpty());
    Assertions.assertEquals(1, resultAuthor.getBooks().size());
    Assertions.assertEquals(1, resultAuthor.getBooks().getFirst().id());
    Assertions.assertEquals("AWSコスト最適化ガイドブック",
      resultAuthor.getBooks().getFirst().title());
    Assertions.assertEquals("2023-03-29", resultAuthor.getBooks().getFirst().publishedAt());
  }
}