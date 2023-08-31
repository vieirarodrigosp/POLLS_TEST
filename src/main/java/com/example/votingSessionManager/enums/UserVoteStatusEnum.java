package com.example.votingSessionManager.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.stream.Stream;

public enum UserVoteStatusEnum {
  ABLE_TO_VOTE, UNABLE_TO_VOTE;
  @JsonCreator
  public static UserVoteStatusEnum decode(final String value) {
    return Stream.of(UserVoteStatusEnum.values())
        .filter(userVoteStatusEnum -> userVoteStatusEnum.name().equalsIgnoreCase(value))
        .findFirst()
        .orElseThrow(() -> new IllegalArgumentException("No projection found " + value));
  }
}
