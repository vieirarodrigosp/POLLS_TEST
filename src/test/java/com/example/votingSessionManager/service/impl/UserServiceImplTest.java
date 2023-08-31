package com.example.votingSessionManager.service.impl;

import com.example.votingSessionManager.dto.UserDTO;
import com.example.votingSessionManager.entity.User;
import com.example.votingSessionManager.enums.UserVoteStatusEnum;
import com.example.votingSessionManager.exception.UserException;
import com.example.votingSessionManager.httpClient.UserClientHttp;
import com.example.votingSessionManager.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class UserServiceImplTest {
  @InjectMocks
  private UserServiceImpl userServiceImpl;
  @Mock
  private UserRepository userRepository;
  @Mock
  private UserClientHttp userClientHttp;
  @Test
  void findById_ShouldBeReturnJustOne() {
    var user = new User(1, "60732627010");
    when(userRepository.findByUserId(anyInt())).thenReturn(user);
    var actual = userServiceImpl.findById(1);
    assertEquals(actual.getCpf(),user.getCpf());
  }
  @Test
  void findById_ShouldBeUserException() {
    when(userRepository.findByUserId(anyInt())).thenThrow(UserException.class);
    assertThrows(UserException.class, () -> userServiceImpl.findById(1) );
  }
  @Test
  void findById_ShouldBeIllegalArgumentException() {
    when(userRepository.findByUserId(anyInt())).thenThrow(IllegalArgumentException.class);
    assertThrows(UserException.class, () -> userServiceImpl.findById(1) );
  }
  @Test
  void validateUserPermission_UserShouldBeABLE() {
    var user = new User(1, "60732627010");
    var userDTO = new UserDTO(1, "60732627010", UserVoteStatusEnum.ABLE_TO_VOTE);
    when(userRepository.findByUserId(anyInt())).thenReturn(user);
    when(userClientHttp.getToken(anyString())).thenReturn(userDTO);
    assertTrue(userServiceImpl.validateUserPermission(1));
  }
  @Test
  void validateUserPermission_UserShouldBeUNABLE() {
    var user = new User(1, "60732627010");
    var userDTO = new UserDTO(1, "60732627010", UserVoteStatusEnum.UNABLE_TO_VOTE);
    when(userRepository.findByUserId(anyInt())).thenReturn(user);
    when(userClientHttp.getToken(anyString())).thenReturn(userDTO);
    assertFalse(userServiceImpl.validateUserPermission(1));
  }
  @Test
  void getUserVoteStatus_UserShouldBeABLE() {
    var cpf = "60732627010";
    var userDTO = new UserDTO(1, cpf, UserVoteStatusEnum.ABLE_TO_VOTE);
    when(userClientHttp.getToken(anyString())).thenReturn(userDTO);
    assertTrue(userServiceImpl.getUserVoteStatus(cpf));
  }
  @Test
  void getUserVoteStatus_UserShouldBeUNABLE() {
    var cpf = "60732627010";
    var userDTO = new UserDTO(1, cpf, UserVoteStatusEnum.UNABLE_TO_VOTE);
    when(userClientHttp.getToken(anyString())).thenReturn(userDTO);
    assertFalse(userServiceImpl.getUserVoteStatus(cpf));
  }
}