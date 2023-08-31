package com.example.votingSessionManager.service;

import com.example.votingSessionManager.dto.UserDTO;

public interface UserService {
  UserDTO findById(Integer userId);
  boolean getUserVoteStatus(String cpf);
  boolean validateUserPermission(Integer userId);
}