package com.example.votingSessionManager.dto;

import com.example.votingSessionManager.entity.User;
import com.example.votingSessionManager.enums.UserVoteStatusEnum;
import com.opengamma.strata.collect.ArgChecker;
import org.springframework.beans.BeanUtils;

public class UserDTO {
  private Integer userId;
  private String cpf;
  private UserVoteStatusEnum status;

  public UserDTO() { }

  public UserDTO(Integer userId, String cpf, UserVoteStatusEnum status) {
    this.userId = userId;
    this.cpf = cpf;
    this.status = status;
  }

  public Integer getUserId() { return userId; }

  public void setUserId(Integer userId) { this.userId = userId; }

  public String getCpf() { return cpf; }

  public void setCpf(String cpf) { this.cpf = cpf; }

  public UserVoteStatusEnum getStatus() { return status; }

  public void setStatus(UserVoteStatusEnum status) { this.status = status; }

  public static UserDTO of(User user) {
    ArgChecker.notNull(user, "user");
    UserDTO dto = new UserDTO();
    BeanUtils.copyProperties(user, dto);
    return dto;
  }
}