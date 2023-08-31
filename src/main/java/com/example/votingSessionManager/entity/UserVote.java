package com.example.votingSessionManager.entity;

import javax.persistence.*;

@Entity
public class UserVote {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_vote_id")
  private Integer userVoteid;
  @Column(name = "poll_id")
  private Integer pollId;
  @Column(name = "user_id")
  private Integer userId;
  private boolean voteValue;

  public UserVote() { }

  public UserVote(Integer userVoteid, Integer pollId, Integer userId, boolean voteValue) {
    this.userVoteid = userVoteid;
    this.pollId = pollId;
    this.userId = userId;
    this.voteValue = voteValue;
  }

  public Integer getUserVoteid() {
    return userVoteid;
  }

  public void setUserVoteid(Integer userVoteid) {
    this.userVoteid = userVoteid;
  }

  public Integer getPollId() {
    return pollId;
  }

  public void setPollId(Integer pollId) {
    this.pollId = pollId;
  }

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public boolean isVoteValue() {
    return voteValue;
  }

  public void setVoteValue(boolean voteValue) {
    this.voteValue = voteValue;
  }
}