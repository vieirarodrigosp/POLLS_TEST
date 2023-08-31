package com.example.votingSessionManager.service.impl;

import com.example.votingSessionManager.dto.PollDTO;
import com.example.votingSessionManager.enums.ProjectionEnum;
import com.example.votingSessionManager.exception.PollException;
import com.example.votingSessionManager.repository.PollRepository;
import com.example.votingSessionManager.service.AssemblyService;
import com.example.votingSessionManager.service.PollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class PollServiceImpl implements PollService {
  @Autowired
  private PollRepository pollRepository;
  @Autowired
  private AssemblyService assemblyService;
  @Override
  public PollDTO start(PollDTO pollDTO){
    var pollByDB = pollRepository.findByAssemblyId(pollDTO.getAssemblyId());
    if(pollByDB==null) {
      pollDTO.setStartSession(LocalDateTime.now());
      return PollDTO.of(pollRepository.save(pollDTO.toEntity()));
    } else {
      throw new PollException("There is already a vote for this assembly");
    }
  }
  @Override
  public PollDTO close(PollDTO pollDTO) {
    var pollByDB = pollRepository.findByAssemblyIdAndAndEndSession(pollDTO.getAssemblyId(), null);
    return this.closeAndSend(PollDTO.of(pollByDB));
  }
  @Override
  public Collection<PollDTO> findByProjection(ProjectionEnum projectionEnum) {
    switch (projectionEnum) {
      case OPEN:
        return pollRepository.findByEndSessionIsNull().stream().map(poll -> PollDTO.of(poll)).collect(Collectors.toList());
      case FINISHED:
        return pollRepository.findByEndSessionNotIsNull().stream().map(poll -> PollDTO.of(poll)).collect(Collectors.toList());
      default:
        return pollRepository.findAll().stream().map(poll -> PollDTO.of(poll)).collect(Collectors.toList());
    }
  }
  @Override
  public PollDTO findById(Integer pollId) {
    try {
      return PollDTO.of(pollRepository.findByPollId(pollId));
    } catch (IllegalArgumentException exception) {
     throw new PollException(exception.getMessage());
    }
  }
  @Override
  public boolean validatesDeadline(Integer pollId) {
    var poll = this.findById(pollId);
    if(poll.getEndSession()==null){
      var assembly = assemblyService.findById(poll.getAssemblyId());
      var endSession = poll.getStartSession().plusMinutes(assembly.getDuration());
      var deadline = LocalDateTime.now().isAfter(endSession);
      if(deadline) {
        this.closeAndSend(poll);
        return false;
      }
      return true;
    }
    return false;
  }
  private PollDTO closeAndSend(PollDTO pollDTO) {
    if(pollDTO!=null){
      pollDTO.setEndSession(LocalDateTime.now());
      return PollDTO.of(pollRepository.save(pollDTO.toEntity()));
    } else {
      throw new PollException("This poll is closed");
    }
  }
}