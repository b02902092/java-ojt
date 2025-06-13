package com.example.javaOjt.exceptions.handlers;

import com.example.javaOjt.beans.responses.common.OjtExceptionResponse;
import com.example.javaOjt.exceptions.OjtBadRequestException;
import com.example.javaOjt.exceptions.OjtExceptionBase;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@Slf4j
public class OjtExceptionHandler extends ResponseEntityExceptionHandler {

  @Override
  protected ResponseEntity<Object> handleTypeMismatch(
    @NonNull TypeMismatchException ex,
    @NonNull HttpHeaders headers,
    @NonNull HttpStatusCode status,
    @NonNull WebRequest request) {

    Object[] args = {ex.getPropertyName(), ex.getValue()};
    String defaultDetail = "Failed to convert '" + args[0] + "' with value: '" + args[1] + "'";

    return handleOjtException(new OjtBadRequestException(defaultDetail));
  }

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
