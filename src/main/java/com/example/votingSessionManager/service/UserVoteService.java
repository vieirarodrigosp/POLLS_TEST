package com.example.votingSessionManager.service;

import com.example.votingSessionManager.dto.CountUserVoteDTO;
import com.example.votingSessionManager.dto.UserVoteDTO;

public interface UserVoteService {
  UserVoteDTO add(UserVoteDTO userVoteDTO);
  CountUserVoteDTO voteCountById(Integer pollId);
}