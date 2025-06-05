package com.example.javaOjt.beans.entities;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import java.time.ZonedDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@MappedSuperclass
public abstract class EntityBase {

  @Column(name = "created_timestamp", nullable = false)
  private ZonedDateTime createdTimestamp;

  @Column(name = "updated_timestamp", nullable = false)
  private ZonedDateTime updatedTimestamp;

  @Setter
  @Column(name = "deleted_timestamp", nullable = true)
  private ZonedDateTime deletedTimestamp;

  @PrePersist
  protected void onPrePersist() {
    ZonedDateTime now = ZonedDateTime.now();
    this.createdTimestamp = now;
    this.updatedTimestamp = now;
  }

  @PreUpdate
  protected void onPreUpdate() {
    this.updatedTimestamp = ZonedDateTime.now();
  }
}
