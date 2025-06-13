package com.example.javaOjt.enums;

import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

public enum AuthorSortBy implements EnumBase {
  ID,
  NAME;

  static public AuthorSortBy getDefaultValue() {
    return AuthorSortBy.ID;
  }

  @Override
  public String getCompareValue() {
    return StringUtils.replace(this.name(), "_", "");
  }
}

@Component
class AuthorSortByConverter implements Converter<String, AuthorSortBy> {

  @Override
  public AuthorSortBy convert(@NonNull String source) {
    for (AuthorSortBy e : AuthorSortBy.values()) {
      if (e.getCompareValue().equalsIgnoreCase(
        StringUtils.replace(source, "_", ""))
      ) {
        return e;
      }
    }
    throw new IllegalArgumentException("Invalid AuthorSortBy value: " + source);
  }
}