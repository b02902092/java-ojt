package com.example.javaOjt.repositories;

import com.example.javaOjt.beans.entities.Author;
import com.example.javaOjt.beans.entities.AuthorPK;
import com.example.javaOjt.repositories.daos.AuthorDao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends CrudRepository<Author, AuthorPK>, AuthorDao {

}
