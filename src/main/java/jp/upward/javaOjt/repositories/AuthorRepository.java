package jp.upward.javaOjt.repositories;

import jp.upward.javaOjt.beans.entities.Author;
import jp.upward.javaOjt.beans.entities.AuthorPK;
import jp.upward.javaOjt.repositories.daos.AuthorDao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends CrudRepository<Author, AuthorPK>, AuthorDao {

}
