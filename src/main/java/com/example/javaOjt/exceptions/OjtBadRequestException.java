package com.example.javaOjt.exceptions;

import org.springframework.http.HttpStatus;

public class OjtBadRequestException extends OjtExceptionBase {

  public OjtBadRequestException(String message) {
    super(HttpStatus.BAD_REQUEST, message);
  }
}
