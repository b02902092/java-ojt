package jp.upward.javaOjt.beans.dtos;

import jp.upward.javaOjt.beans.entities.Author;
import jp.upward.javaOjt.beans.entities.Book;

public record AuthorWithBookDTO(Author author, Book book) {

}
