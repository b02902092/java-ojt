package jp.upward.javaOjt.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;

@Getter
@RequiredArgsConstructor
public class OjtExceptionBase extends RuntimeException {

  private final HttpStatusCode statusCode;

  private final String exceptionMessage;
}
