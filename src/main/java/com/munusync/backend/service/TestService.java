package com.munusync.backend.service;

import com.munusync.backend.dto.ApiInfoResponseDto;
import com.munusync.backend.dto.HealthResponseDto;
import com.munusync.backend.dto.TestRequestDto;
import com.munusync.backend.dto.TestResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class TestService {

  public TestResponseDto getTestMessage() {
    log.info("Getting default test message");
    return new TestResponseDto(
        "Hello from MunuSync Backend!",
        "SUCCESS",
        Instant.now().toEpochMilli());
  }

  public TestResponseDto getTestMessageWithParam(String message) {
    log.info("Getting test message with parameter: {}", message);
    return new TestResponseDto(
        "Echo: " + message,
        "SUCCESS",
        Instant.now().toEpochMilli());
  }

  public TestResponseDto processTestMessage(TestRequestDto request) {
    log.info("Processing test message: {}", request.getMessage());
    String processedMessage = request.getMessage() != null ? "Processed: " + request.getMessage()
        : "No message provided";

    return new TestResponseDto(
        processedMessage,
        "SUCCESS",
        Instant.now().toEpochMilli());
  }

  public HealthResponseDto getHealthStatus() {
    log.info("Health check requested");

    Map<String, Object> details = new HashMap<>();
    details.put("application", "MunuSync Backend");
    details.put("version", "0.0.1-SNAPSHOT");
    details.put("uptime", "Running");
    details.put("database", "Connected");

    return new HealthResponseDto(
        "UP",
        "Application is running normally",
        Instant.now().toEpochMilli(),
        details);
  }
}
