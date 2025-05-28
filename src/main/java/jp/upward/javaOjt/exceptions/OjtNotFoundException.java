package jp.upward.javaOjt.exceptions;

import org.springframework.http.HttpStatus;

public class OjtNotFoundException extends OjtExceptionBase {

  public OjtNotFoundException(String message) {
    super(HttpStatus.NOT_FOUND, message);
  }
}
