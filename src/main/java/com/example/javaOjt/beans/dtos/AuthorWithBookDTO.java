package com.example.javaOjt.beans.dtos;

import com.example.javaOjt.beans.entities.Author;
import com.example.javaOjt.beans.entities.Book;

public record AuthorWithBookDTO(Author author, Book book) {

}
