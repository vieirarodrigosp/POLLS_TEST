package com.example.votingSessionManager.service;

import com.example.votingSessionManager.dto.PollDTO;
import com.example.votingSessionManager.enums.ProjectionEnum;

import java.util.Collection;

public interface PollService {
  PollDTO start(PollDTO pollDTO);
  PollDTO close(PollDTO pollDTO);
  Collection<PollDTO> findByProjection(ProjectionEnum projectionEnum);
  PollDTO findById(Integer pollId);
  boolean validatesDeadline(Integer pollId);
}