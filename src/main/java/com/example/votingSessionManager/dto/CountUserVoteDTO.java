package com.example.votingSessionManager.dto;

public interface CountUserVoteDTO {
  Integer getPollId();
  Integer getCountTrue();
  Integer getCountFalse();
}