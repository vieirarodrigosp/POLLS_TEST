package com.example.votingSessionManager.service;

import com.example.votingSessionManager.dto.AssemblyDTO;

import java.util.Collection;

public interface AssemblyService {
  AssemblyDTO createAssembly(AssemblyDTO assemblyDTO);
  Collection<AssemblyDTO> findAll();
  AssemblyDTO findById(Integer assemblyId);
}