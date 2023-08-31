package com.example.votingSessionManager.service.impl;

import com.example.votingSessionManager.dto.CountUserVoteDTO;
import com.example.votingSessionManager.dto.UserVoteDTO;
import com.example.votingSessionManager.exception.UserVoterException;
import com.example.votingSessionManager.repository.UserVoteRepository;
import com.example.votingSessionManager.service.PollService;
import com.example.votingSessionManager.service.UserService;
import com.example.votingSessionManager.service.UserVoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserVoteImpl  implements UserVoteService {
  @Autowired
  private UserService userService;
  @Autowired
  private PollService pollService;
  @Autowired
  private UserVoteRepository userVoteRepository;

  @Override
  public UserVoteDTO add(UserVoteDTO userVoteDTO) {
    var result = pollService.validatesDeadline(userVoteDTO.getPollId());
    if(result){
      return this.validateUserPermissionAndSave(userVoteDTO);
    }
    throw new UserVoterException("voting has closed");
  }
  private UserVoteDTO findAndSave(UserVoteDTO userVoteDTO){
    var userVoteByDb = userVoteRepository.findByPollIdAndUserId(userVoteDTO.getPollId(), userVoteDTO.getUserId());
    if(userVoteByDb==null){
      return UserVoteDTO.of(userVoteRepository.save(userVoteDTO.toEntity()));
    }
    throw new UserVoterException("user has already polled");
  }
  private UserVoteDTO validateUserPermissionAndSave(UserVoteDTO userVoteDTO){
    var permissionUser = userService.validateUserPermission(userVoteDTO.getUserId());
    if(permissionUser){
      return this.findAndSave(userVoteDTO);
    }
    throw new UserVoterException("User does not have voting permission");
  }
  @Override
  public CountUserVoteDTO voteCountById(Integer pollId) {
    return userVoteRepository.voteCountById(pollId);
  }
}