package com.example.javaOjt.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health-check")
@RequiredArgsConstructor
@Validated
public class HealthCheckController {

  @RequestMapping("")
  public ResponseEntity<String> healthCheck() {
    return ResponseEntity.ok("OK");
  }
}
