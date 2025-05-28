package jp.upward.javaOjt.services;

import java.util.List;
import java.util.Optional;
import jp.upward.javaOjt.beans.AuthorWithBookBean;
import jp.upward.javaOjt.beans.GetAuthorResponse;
import jp.upward.javaOjt.entities.Author;
import jp.upward.javaOjt.entities.AuthorPK;
import jp.upward.javaOjt.repositories.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorService {

  final private AuthorRepository authorRepository;

  /**
   * Fetches an author by his ID.
   *
   * @param id        author ID
   * @param withBooks if true, fetches the author along with their books; if false, fetches only the
   *                  author details
   * @return GetAuthorResponse containing author details and optionally their books
   */
  public GetAuthorResponse getAuthorById(Integer id, boolean withBooks) {
    GetAuthorResponse response;
    // If withBooks is true, fetch the author along with their books
    if (withBooks) {
      List<AuthorWithBookBean> beans = authorRepository.getAuthorWithBookBeansById(id);
      if (beans.isEmpty()) {
        return GetAuthorResponse.notFoundResponse();
      }
      response = new GetAuthorResponse();
      response.setExists(true);
      response.setId(beans.getFirst().author().getId());
      response.setName(beans.getFirst().author().getName());
      response.setBooks(beans.stream().map(
          bean -> new GetAuthorResponse.Book(
            bean.book().getId(),
            bean.book().getTitle(),
            bean.book().getPublishedAt() == null ? null : bean.book().getPublishedAt().toString()
          )
        ).toList()
      );
    }
    // If withBooks is false, fetch only the author details
    else {
      Optional<Author> author = authorRepository.findById(new AuthorPK(id));
      if (author.isEmpty()) {
        return GetAuthorResponse.notFoundResponse();
      } else {
        response = new GetAuthorResponse(author.get());
      }
    }
    return response;
  }


}
