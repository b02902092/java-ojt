package jp.upward.javaOjt.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "books")
@Getter
@Setter
@IdClass(BookPK.class)
public class Book {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "books_seq_gen")
  @SequenceGenerator(name = "books_seq_gen", sequenceName = "books_id_seq", allocationSize = 1)
  @Column(name = "id", nullable = false)
  private Integer id;

  @Column(name = "title", nullable = false)
  private String title;

  @Column(name = "author_id", nullable = true)
  private Integer authorId;

  @Column(name = "published_at", nullable = true)
  private LocalDate publishedAt;

  @Column(name = "created_timestamp", nullable = false)
  private ZonedDateTime createdTimestamp;

  @Column(name = "updated_timestamp", nullable = false)
  private ZonedDateTime updatedTimestamp;

  @Column(name = "deleted_timestamp", nullable = true)
  private ZonedDateTime deletedTimestamp;
}
