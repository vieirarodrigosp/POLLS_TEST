package com.example.votingSessionManager;

import com.example.votingSessionManager.dto.AssemblyDTO;

public class BaseMocks {
  public AssemblyDTO getAssemblyDTO(Integer assemblyId, String agenda, Integer duration){
    return new AssemblyDTO(assemblyId, agenda, duration);
  }
}