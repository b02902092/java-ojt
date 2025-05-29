package jp.upward.javaOjt.beans.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.time.ZonedDateTime;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "authors")
@Getter
@Setter
@IdClass(AuthorPK.class)
public class Author {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "authors_seq_gen")
  @SequenceGenerator(name = "authors_seq_gen", sequenceName = "authors_id_seq", allocationSize = 1)
  @Column(name = "id", nullable = false)
  private Integer id;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "created_timestamp", nullable = false)
  private ZonedDateTime createdTimestamp;

  @Column(name = "updated_timestamp", nullable = false)
  private ZonedDateTime updatedTimestamp;

  @Column(name = "deleted_timestamp", nullable = true)
  private ZonedDateTime deletedTimestamp;
}
