package com.example.javaOjt.exceptions;

import org.springframework.http.HttpStatus;

public class OjtConflictException extends OjtExceptionBase {

  public OjtConflictException(String message) {
    super(HttpStatus.CONFLICT, message);
  }
}
