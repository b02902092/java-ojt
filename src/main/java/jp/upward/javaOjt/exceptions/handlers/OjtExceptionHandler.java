package jp.upward.javaOjt.exceptions.handlers;

import jp.upward.javaOjt.beans.responses.common.OjtExceptionResponse;
import jp.upward.javaOjt.exceptions.OjtExceptionBase;
import lombok.extern.slf4j.Slf4j;
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
  public ResponseEntity<Object> handleOjtException(OjtExceptionBase ex) {
    return ResponseEntity
      .status(ex.getStatusCode())
      .body(new OjtExceptionResponse(
        ex.getExceptionMessage(),
        ex.getStatusCode().value())
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
    return ResponseEntity.internalServerError()
      .body(new OjtExceptionResponse(
        ex.getMessage(),
        HttpStatus.INTERNAL_SERVER_ERROR.value())
      );
  }

}
