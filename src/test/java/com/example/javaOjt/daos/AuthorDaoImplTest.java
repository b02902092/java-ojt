package com.example.javaOjt.daos;

import java.time.LocalDate;
import java.util.List;
import com.example.javaOjt.DBTestBase;
import com.example.javaOjt.beans.dtos.AuthorWithBookDTO;
import com.example.javaOjt.repositories.daos.AuthorDaoImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class AuthorDaoImplTest extends DBTestBase {

  @Autowired
  private AuthorDaoImpl authorDaoImpl;

  @Test
  void getAuthorWithBookBeanById() {
    int targetId = 1;

    List<AuthorWithBookDTO> result = authorDaoImpl.getAuthorWithBookDTOsById(targetId);

    Assertions.assertEquals(1, result.size());
    Assertions.assertEquals(targetId, result.getFirst().author().getId());
    Assertions.assertEquals("門畑顕博", result.getFirst().author().getName());
    Assertions.assertEquals(1, result.getFirst().book().getId());
    Assertions.assertEquals("AWSコスト最適化ガイドブック", result.getFirst().book().getTitle());
    Assertions.assertEquals(LocalDate.parse("2023-03-29"),
      result.getFirst().book().getPublishedAt());
  }
}