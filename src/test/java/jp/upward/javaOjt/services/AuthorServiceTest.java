package jp.upward.javaOjt.services;

import java.util.List;
import java.util.Optional;
import jp.upward.javaOjt.beans.AuthorWithBookBean;
import jp.upward.javaOjt.beans.GetAuthorResponse;
import jp.upward.javaOjt.entities.Author;
import jp.upward.javaOjt.entities.AuthorPK;
import jp.upward.javaOjt.entities.Book;
import jp.upward.javaOjt.repositories.AuthorRepository;
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

    // Mocking the AuthorWithBooksBean response
    Author mockAuthor = new Author();
    mockAuthor.setId(targetId);
    mockAuthor.setName("Mock Author");
    Book mockBook = new Book();
    mockBook.setId(2);
    mockBook.setTitle("Mock Book Title");

    AuthorWithBookBean mockBean = new AuthorWithBookBean(mockAuthor, mockBook);
    Mockito.when(authorRepository.getAuthorWithBookBeansById(targetId))
      .thenReturn(List.of(mockBean));

    // Call the service method
    GetAuthorResponse response = authorService.getAuthorById(targetId, true);

    // Assertions to verify the response
    Assertions.assertNotNull(response);
    Assertions.assertEquals(mockBean.author().getId(), response.getId());
    Assertions.assertEquals(1, response.getBooks().size());
    Assertions.assertEquals(mockBean.book().getId(), response.getBooks().getFirst().id());
    Mockito.verify(authorRepository, Mockito.times(1)).getAuthorWithBookBeansById(targetId);
    Mockito.verify(authorRepository, Mockito.never()).findById(Mockito.any());
  }

  @Test
  void getAuthorById_withBooks_false() {
    int targetId = 1;

    // Mocking the Author response
    Author mockAuthor = new Author();
    mockAuthor.setId(targetId);
    AuthorPK authorPK = new AuthorPK(targetId);
    Mockito.when(authorRepository.findById(authorPK)).thenReturn(Optional.of(mockAuthor));

    // Call the service method
    GetAuthorResponse response = authorService.getAuthorById(targetId, false);

    // Assertions to verify the response
    Assertions.assertNotNull(response);
    Assertions.assertEquals(mockAuthor.getId(), response.getId());
    Assertions.assertTrue(response.isExists());
    Mockito.verify(authorRepository, Mockito.never()).getAuthorWithBookBeansById(targetId);
    Mockito.verify(authorRepository, Mockito.times(1)).findById(Mockito.any());
  }
}