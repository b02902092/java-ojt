package jp.upward.javaOjt.beans;

import jp.upward.javaOjt.entities.Author;
import jp.upward.javaOjt.entities.Book;

public record AuthorWithBookBean(Author author, Book book) {

}
