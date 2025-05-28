package jp.upward.javaOjt.exceptionHandlers;

import jp.upward.javaOjt.beans.OjtExceptionResponse;
import jp.upward.javaOjt.exceptions.OjtExceptionBase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@Slf4j
public class OjtExceptionHandler extends ResponseEntityExceptionHandler {
  
  /**
   * OJT Exception Handler
   *
   * @param ex exception
   * @return error response
   */
  @ExceptionHandler({OjtExceptionBase.class})
  public ResponseEntity<Object> handleNotFoundException(OjtExceptionBase ex) {
    return new ResponseEntity<>(
      new OjtExceptionResponse(ex.getExceptionMessage(), ex.getStatusCode().value()),
      new HttpHeaders(),
      ex.getStatusCode()
    );
  }

  /**
   * Process exceptions except OJT exceptions
   *
   * @param ex exception
   * @return error response
   */
  @ExceptionHandler({Exception.class})
  public ResponseEntity<Object> handleInternalServerException(Exception ex) {
    log.error("Internal server error: {}", ex.getMessage(), ex);
    return new ResponseEntity<>(
      new OjtExceptionResponse(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value()),
      new HttpHeaders(),
      HttpStatus.INTERNAL_SERVER_ERROR
    );
  }

}
