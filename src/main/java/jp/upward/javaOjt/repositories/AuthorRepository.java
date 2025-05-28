package jp.upward.javaOjt.repositories;

import jp.upward.javaOjt.daos.AuthorDao;
import jp.upward.javaOjt.entities.Author;
import jp.upward.javaOjt.entities.AuthorPK;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends CrudRepository<Author, AuthorPK>, AuthorDao {

}
