package jp.upward.javaOjt.controllers;

import jp.upward.javaOjt.beans.GetAuthorResponse;
import jp.upward.javaOjt.exceptions.OjtNotFoundException;
import jp.upward.javaOjt.services.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authors/")
@RequiredArgsConstructor
@Validated
public class AuthorController {

  private final AuthorService authorService;

  @GetMapping("/{id}")
  public GetAuthorResponse getAuthor(
    @PathVariable(value = "id") Integer id,
    @RequestParam(value = "withBooks", required = false) boolean withBooks
  ) {
    GetAuthorResponse response = authorService.getAuthorById(id, withBooks);
    if (!response.isExists()) {
      throw new OjtNotFoundException("Author not found with ID: " + id);
    }
    return response;
  }
}
