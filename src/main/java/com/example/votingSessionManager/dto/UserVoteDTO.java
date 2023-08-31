package com.example.votingSessionManager.dto;

import com.example.votingSessionManager.entity.UserVote;
import com.opengamma.strata.collect.ArgChecker;
import org.springframework.beans.BeanUtils;

public class UserVoteDTO {
  private Integer id;
  private Integer pollId;
  private Integer userId;
  private boolean voteValue;

  public UserVoteDTO() { }

  public UserVoteDTO(Integer id, Integer pollId, Integer userId, boolean voteValue) {
    this.id = id;
    this.pollId = pollId;
    this.userId = userId;
    this.voteValue = voteValue;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
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

  public UserVote toEntity() {
    UserVote userVote = new UserVote();
    BeanUtils.copyProperties(this, userVote);
    return userVote;
  }

  public static UserVoteDTO of(UserVote userVote) {
    ArgChecker.notNull(userVote, "userVote");
    UserVoteDTO dto = new UserVoteDTO();
    BeanUtils.copyProperties(userVote, dto);
    return dto;
  }
}