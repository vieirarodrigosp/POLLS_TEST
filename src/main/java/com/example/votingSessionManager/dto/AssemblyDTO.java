package com.example.votingSessionManager.dto;

import com.example.votingSessionManager.entity.Assembly;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.opengamma.strata.collect.ArgChecker;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.Size;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AssemblyDTO {
  private Integer assemblyId;
  @Size(max = 100, message = "{validation.name.size.too_long}")
  private String agenda;
  private Integer duration = 1;

  public AssemblyDTO() { }

  public AssemblyDTO(Integer assemblyId, String agenda, Integer duration) {
    this.assemblyId = assemblyId;
    this.agenda = agenda;
    this.duration = duration;
  }

  public Assembly toEntity() {
    Assembly assembly = new Assembly();
    BeanUtils.copyProperties(this, assembly);
    return assembly;
  }

  public static AssemblyDTO of(Assembly assembly) {
    ArgChecker.notNull(assembly, "assembly");
    AssemblyDTO dto = new AssemblyDTO();
    BeanUtils.copyProperties(assembly, dto);
    return dto;
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