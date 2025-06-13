package com.example.javaOjt.enums;

import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

public enum Order implements EnumBase {
  ASC,
  DESC;

  static public Order getDefaultValue() {
    return Order.ASC;
  }

  @Override
  public String getCompareValue() {
    return StringUtils.replace(this.name(), "_", "");
  }
}


@Component
class OrderConverter implements Converter<String, Order> {

  @Override
  public Order convert(@NonNull String source) {
    for (Order e : Order.values()) {
      if (e.getCompareValue().equalsIgnoreCase(
        StringUtils.replace(source, "_", ""))
      ) {
        return e;
      }
    }
    throw new IllegalArgumentException("Invalid Order value: " + source);
  }
}