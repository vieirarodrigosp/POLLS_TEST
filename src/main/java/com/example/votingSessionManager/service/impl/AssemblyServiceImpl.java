package com.example.votingSessionManager.service.impl;

import com.example.votingSessionManager.dto.AssemblyDTO;
import com.example.votingSessionManager.exception.AssemblyException;
import com.example.votingSessionManager.repository.AssemblyRepository;
import com.example.votingSessionManager.service.AssemblyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class AssemblyServiceImpl implements AssemblyService {
  @Autowired
  private AssemblyRepository assemblyRepository;
  @Override
  public AssemblyDTO createAssembly(AssemblyDTO assemblyDTO){
    try {
      return AssemblyDTO.of(assemblyRepository.save(assemblyDTO.toEntity()));
    } catch (AssemblyException exception) {
      throw new AssemblyException("Error when creating a new assembly");
    }

  }
  @Override
  public Collection<AssemblyDTO> findAll() {
    return assemblyRepository.findAll().stream().map(assembly -> AssemblyDTO.of(assembly)).collect(Collectors.toList());
  }
  @Override
  public AssemblyDTO findById(Integer assemblyId) {
    return AssemblyDTO.of(assemblyRepository.findByAssemblyId(assemblyId));
  }
}