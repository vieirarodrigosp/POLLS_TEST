package com.example.votingSessionManager.service.impl;

import com.example.votingSessionManager.dto.CountUserVoteDTO;
import com.example.votingSessionManager.dto.UserVoteDTO;
import com.example.votingSessionManager.exception.UserVoterException;
import com.example.votingSessionManager.repository.UserVoteRepository;
import com.example.votingSessionManager.service.PollService;
import com.example.votingSessionManager.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.example.votingSessionManager.constants.BaseMocksConstant.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class UserVoteImplTest {
  @InjectMocks
  private UserVoteImpl userVoteImpl;
  @Mock
  private UserService userService;
  @Mock
  private PollService pollService;
  @Mock
  private UserVoteRepository userVoteRepository;
  @Mock
  private CountUserVoteDTO countUserVoteDTO;
  @Test
  void add_ShouldBeSavedUserVote() {
    var userVoteDTO = new UserVoteDTO(10, 20, 30, true);
    when(pollService.validatesDeadline(anyInt())).thenReturn(true);
    when(userService.validateUserPermission(anyInt())).thenReturn(true);
    when(userVoteRepository.findByPollIdAndUserId(anyInt(), anyInt())).thenReturn(null);
    when(userVoteRepository.save(any())).thenReturn(userVoteDTO.toEntity());
    var actual = userVoteImpl.add(userVoteDTO);
    assertEquals(actual.getUserId(),userVoteDTO.getUserId());
  }
  @Test
  void add_ShouldBeUserVoterException_WhenVoteClosed() {
    var userVoteDTO = new UserVoteDTO(10, 20, 30, true);
    when(pollService.validatesDeadline(anyInt())).thenReturn(false);
    var thrown = assertThrows(UserVoterException.class, () -> userVoteImpl.add(userVoteDTO));
    assertTrue(thrown.getMessage().equals(INFO_VOTING_CLOSED));
  }
  @Test
  void add_ShouldBeUserVoterException_WhenUserDontHavePermission() {
    var userVoteDTO = new UserVoteDTO(10, 20, 30, true);
    when(pollService.validatesDeadline(anyInt())).thenReturn(true);
    when(userService.validateUserPermission(anyInt())).thenReturn(false);
    var thrown = assertThrows(UserVoterException.class, () -> userVoteImpl.add(userVoteDTO));
    assertTrue(thrown.getMessage().equals(INFO_VOTING_NOT_HAVE_PERMISSION));
  }
  @Test
  void add_ShouldBeUserVoterException_WhenUserAlreadyPolled() {
    var userVoteDTO = new UserVoteDTO(10, 20, 30, true);
    when(pollService.validatesDeadline(anyInt())).thenReturn(true);
    when(userService.validateUserPermission(anyInt())).thenReturn(true);
    when(userVoteRepository.findByPollIdAndUserId(anyInt(), anyInt())).thenReturn(userVoteDTO.toEntity());
    var thrown = assertThrows(UserVoterException.class, () -> userVoteImpl.add(userVoteDTO));
    assertTrue(thrown.getMessage().equals(INFO_USER_ALREADY_POLLED));
  }
  @Test
  void voteCountById() {
    when(userVoteRepository.voteCountById(anyInt())).thenReturn(countUserVoteDTO);
    var actual = userVoteImpl.voteCountById(1);
  }
}