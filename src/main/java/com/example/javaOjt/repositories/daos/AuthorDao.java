package com.example.javaOjt.repositories.daos;

import java.util.List;
import com.example.javaOjt.beans.dtos.AuthorWithBookDTO;

public interface AuthorDao {

  List<AuthorWithBookDTO> getAuthorWithBookDTOsById(Integer id);
}
