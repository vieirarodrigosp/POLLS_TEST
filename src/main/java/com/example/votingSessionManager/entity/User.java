package com.example.votingSessionManager.entity;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")
  private Integer userId;
  @Column(length=11)
  private String cpf;

  public User() { }

  public User(Integer userId, String cpf) {
    this.userId = userId;
    this.cpf = cpf;
  }

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }
}
