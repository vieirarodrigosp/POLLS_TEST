package com.example.votingSessionManager.entity;

import javax.persistence.*;

@Entity
@Table(name = "assembly")
public class Assembly {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "assembly_id")
  private Integer assemblyId;
  @Column(length=100)
  private String agenda;
  private Integer duration;

  public Assembly() { }

  public Assembly(Integer assemblyId, String agenda, Integer duration) {
    this.assemblyId = assemblyId;
    this.agenda = agenda;
    this.duration = duration;
  }

  public Integer getAssemblyId() {
    return assemblyId;
  }

  public void setAssemblyId(Integer assemblyId) {
    this.assemblyId = assemblyId;
  }

  public String getAgenda() {
    return agenda;
  }

  public void setAgenda(String agenda) {
    this.agenda = agenda;
  }

  public Integer getDuration() {
    return duration;
  }

  public void setDuration(Integer duration) {
    this.duration = duration;
  }
}