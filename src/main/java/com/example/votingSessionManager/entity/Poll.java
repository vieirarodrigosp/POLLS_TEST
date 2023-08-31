package com.example.votingSessionManager.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "poll")
public class Poll {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "poll_id")
  private Integer pollId;
  @Column(name = "assembly_id")
  private Integer assemblyId;
  @Column(name = "start_session")
  private LocalDateTime startSession;
  @Column(name = "end_session")
  private LocalDateTime endSession;

  public Poll() {}

  public Poll(Integer pollId, Integer assemblyId, LocalDateTime startSession, LocalDateTime endSession) {
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
}