package jp.upward.javaOjt.repositories.daos;

import java.util.List;
import jp.upward.javaOjt.beans.dtos.AuthorWithBookDTO;

public interface AuthorDao {

  List<AuthorWithBookDTO> getAuthorWithBookDTOsById(Integer id);
}
