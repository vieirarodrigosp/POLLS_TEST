package com.example.votingSessionManager.dto;

import com.example.votingSessionManager.entity.Poll;
import com.opengamma.strata.collect.ArgChecker;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

public class PollDTO {
  private Integer pollId;
  private Integer assemblyId;
  private LocalDateTime startSession;
  private LocalDateTime endSession;

  public PollDTO() { }

  public PollDTO(Integer pollId, Integer assemblyId, LocalDateTime startSession, LocalDateTime endSession) {
    this.pollId = pollId;
    this.assemblyId = assemblyId;
    this.startSession = startSession;
    this.endSession = endSession;
  }

  public Integer getPollId() {
    return pollId;
  }

  public void setPollId(Integer pollId) {
    this.pollId = pollId;
  }

  public Integer getAssemblyId() {
    return assemblyId;
  }

  public void setAssemblyId(Integer assemblyId) {
    this.assemblyId = assemblyId;
  }

  public LocalDateTime getStartSession() {
    return startSession;
  }

  public void setStartSession(LocalDateTime startSession) {
    this.startSession = startSession;
  }

  public LocalDateTime getEndSession() {
    return endSession;
  }

  public void setEndSession(LocalDateTime endSession) {
    this.endSession = endSession;
  }

  public Poll toEntity() {
    Poll poll = new Poll();
    BeanUtils.copyProperties(this, poll);
    return poll;
  }

  public static PollDTO of(Poll poll) {
    ArgChecker.notNull(poll, "poll");
    PollDTO dto = new PollDTO();
    BeanUtils.copyProperties(poll, dto);
    return dto;
  }
}