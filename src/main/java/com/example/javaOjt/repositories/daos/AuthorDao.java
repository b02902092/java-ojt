package com.example.javaOjt.repositories.daos;

import com.example.javaOjt.beans.dtos.AuthorWithBookDTO;
import java.util.List;

public interface AuthorDao {

  List<AuthorWithBookDTO> getAuthorWithBookDTOsById(Integer id);
}
