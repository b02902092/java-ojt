package jp.upward.javaOjt.beans.responses.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

/**
 * Response class for OJT exceptions.
 */
@Getter
public class OjtExceptionResponse {

  @JsonProperty("Error")
  private final Error error;

  public OjtExceptionResponse(String message, int code) {
    this.error = new Error(message, code);
  }

  public record Error(@JsonProperty("Message") String message,
                      @JsonProperty("Code") int code) {

  }
}