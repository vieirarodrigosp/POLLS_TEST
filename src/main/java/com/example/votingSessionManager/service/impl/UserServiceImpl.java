package com.example.votingSessionManager.service.impl;

import com.example.votingSessionManager.dto.UserDTO;
import com.example.votingSessionManager.enums.UserVoteStatusEnum;
import com.example.votingSessionManager.exception.UserException;
import com.example.votingSessionManager.httpClient.UserClientHttp;
import com.example.votingSessionManager.repository.UserRepository;
import com.example.votingSessionManager.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private UserClientHttp userClientHttp;
  public UserDTO findById(Integer userId){
    try {
      return UserDTO.of(userRepository.findByUserId(userId));
    } catch (UserException exception) {
      throw new UserException(exception.getMessage());
    } catch (IllegalArgumentException exception) {
      throw new UserException(exception.getLocalizedMessage());
    }
  }
  public boolean validateUserPermission(Integer userId){
    var user = this.findById(userId);
    return this.getUserVoteStatus(user.getCpf());
  }
  public boolean getUserVoteStatusFallback(String cpf) {
    return false;
  }
  @CircuitBreaker(name = "UserVoteStatusFallback", fallbackMethod = "getUserVoteStatusFallback")
  public boolean getUserVoteStatus(String cpf) {
    return userClientHttp.getToken(cpf).getStatus().equals(UserVoteStatusEnum.ABLE_TO_VOTE);
  }
}