package com.example.javaOjt.services;

import com.example.javaOjt.beans.dtos.AuthorWithBookDTO;
import com.example.javaOjt.beans.entities.Author;
import com.example.javaOjt.beans.entities.AuthorPK;
import com.example.javaOjt.beans.entities.Book;
import com.example.javaOjt.beans.responses.author.GetAuthorResponse;
import com.example.javaOjt.repositories.AuthorRepository;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AuthorServiceTest {

  private AuthorService authorService;

  @Mock
  private AuthorRepository authorRepository;

  @BeforeEach
  void setUp() {
    authorService = new AuthorService(authorRepository);
  }

  @Test
  void getAuthorById_withBooks_true() {
    int targetId = 1;

    // Mocking the AuthorWithBooksDTO response
    Author mockAuthor = new Author();
    mockAuthor.setId(targetId);
    mockAuthor.setName("Mock Author");
    Book mockBook = new Book();
    mockBook.setId(2);
    mockBook.setTitle("Mock Book Title");

    AuthorWithBookDTO mock = new AuthorWithBookDTO(mockAuthor, mockBook);
    Mockito.when(authorRepository.getAuthorWithBookDTOsById(targetId))
      .thenReturn(List.of(mock));

    // Call the service method
    GetAuthorResponse response = authorService.getAuthorById(targetId, true);

    // Assertions to verify the response
    Assertions.assertNotNull(response);
    Assertions.assertEquals(mock.author().getId(), response.getId());
    Assertions.assertEquals(1, response.getBooks().size());
    Assertions.assertEquals(mock.book().getId(), response.getBooks().getFirst().id());
    Mockito.verify(authorRepository, Mockito.times(1)).getAuthorWithBookDTOsById(targetId);
    Mockito.verify(authorRepository, Mockito.never()).findById(Mockito.any());
  }

  @Test
  void getAuthorById_withBooks_false() {
    int targetId = 1;

    // Mocking the Author response
    Author mock = new Author();
    mock.setId(targetId);
    AuthorPK authorPK = new AuthorPK(targetId);
    Mockito.when(authorRepository.findById(authorPK)).thenReturn(Optional.of(mock));

    // Call the service method
    GetAuthorResponse response = authorService.getAuthorById(targetId, false);

    // Assertions to verify the response
    Assertions.assertNotNull(response);
    Assertions.assertEquals(mock.getId(), response.getId());
    Assertions.assertTrue(response.isExists());
    Mockito.verify(authorRepository, Mockito.never()).getAuthorWithBookDTOsById(targetId);
    Mockito.verify(authorRepository, Mockito.times(1)).findById(Mockito.any());
  }
}