package com.example.javaOjt.controllers;

import com.example.javaOjt.beans.responses.author.GetAuthorResponse;
import com.example.javaOjt.exceptions.OjtNotFoundException;
import com.example.javaOjt.services.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthorController {

  private final AuthorService authorService;

  /**
   * Retrieves an author by his ID.
   *
   * @param id        the ID of the author to retrieve
   * @param withBooks whether to include the author's books in the response
   * @return a ResponseEntity containing the GetAuthorResponse
   * @throws OjtNotFoundException if the author does not exist
   */
  @GetMapping("/{id}")
  public ResponseEntity<GetAuthorResponse> getAuthor(
    @PathVariable(value = "id") Integer id,
    @RequestParam(value = "withBooks", required = false) boolean withBooks
  ) {
    GetAuthorResponse response = authorService.getAuthorById(id, withBooks);
    if (!response.isExists()) {
      throw new OjtNotFoundException("Author not found with ID: " + id);
    }
    return ResponseEntity.ok(response);
  }
}
