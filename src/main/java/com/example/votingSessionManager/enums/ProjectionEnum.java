package com.example.votingSessionManager.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.stream.Stream;

public enum ProjectionEnum {
  ALL, OPEN, FINISHED;
  @JsonCreator
  public static ProjectionEnum decode(final String value) {
    return Stream.of(ProjectionEnum.values())
        .filter(projectionEnum -> projectionEnum.name().equalsIgnoreCase(value))
        .findFirst()
        .orElseThrow(() -> new IllegalArgumentException("No projection found " + value));
  }
}