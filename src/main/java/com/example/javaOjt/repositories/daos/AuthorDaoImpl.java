package com.example.javaOjt.repositories.daos;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import com.example.javaOjt.beans.dtos.AuthorWithBookDTO;
import com.example.javaOjt.beans.entities.QAuthor;
import com.example.javaOjt.beans.entities.QBook;
import lombok.RequiredArgsConstructor;

// @Repository
@RequiredArgsConstructor
public class AuthorDaoImpl implements AuthorDao {

  private final JPAQueryFactory jpaQueryFactory;

  public List<AuthorWithBookDTO> getAuthorWithBookDTOsById(Integer id) {
    return jpaQueryFactory.select(
        Projections.constructor(
          AuthorWithBookDTO.class,
          QAuthor.author,
          QBook.book
        )
      )
      .from(QAuthor.author)
      .leftJoin(QBook.book)
      .on(QAuthor.author.id.eq(QBook.book.authorId))
      .where(QAuthor.author.id.eq(id))
      .fetch();
  }
}
