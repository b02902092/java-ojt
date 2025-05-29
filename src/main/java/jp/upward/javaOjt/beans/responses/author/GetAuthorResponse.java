package jp.upward.javaOjt.beans.responses.author;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import jp.upward.javaOjt.beans.entities.Author;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@Data
public class GetAuthorResponse {

  private boolean isExists;

  @JsonProperty("id")
  private Integer id;

  @JsonProperty("Name")
  private String name;

  @JsonProperty("books")
  private List<Book> books;

  public GetAuthorResponse(@NonNull Author author) {
    this.isExists = true;
    this.id = author.getId();
    this.name = author.getName();
  }

  public static GetAuthorResponse notFoundResponse() {
    GetAuthorResponse response = new GetAuthorResponse();
    response.isExists = false;
    return response;
  }

  public record Book(
    @JsonProperty("bookId") Integer id,
    @JsonProperty("title") String title,
    @JsonProperty("publishedAt") String publishedAt) {

  }
}
