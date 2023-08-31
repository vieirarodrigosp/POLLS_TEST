package com.example.votingSessionManager.service.impl;

import com.example.votingSessionManager.dto.AssemblyDTO;
import com.example.votingSessionManager.dto.PollDTO;
import com.example.votingSessionManager.enums.ProjectionEnum;
import com.example.votingSessionManager.exception.PollException;
import com.example.votingSessionManager.repository.PollRepository;
import com.example.votingSessionManager.service.AssemblyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;

import static com.example.votingSessionManager.constants.BaseMocksConstant.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class PollServiceImplTest {
  @InjectMocks
  private PollServiceImpl pollServiceImpl;
  @Mock
  private PollRepository pollRepository;
  @Mock
  private AssemblyService assemblyService;
  @Test
  void start_ShouldBeStarted() {
    var pollDTO = new PollDTO(10, 20, null, null);
    when(pollRepository.findByAssemblyId(anyInt())).thenReturn(null);
    when(pollRepository.save(any())).thenReturn(pollDTO.toEntity());
    var actual = pollServiceImpl.start(pollDTO);
    assertEquals(actual.getAssemblyId(), pollDTO.getAssemblyId());
  }
  @Test
  void start_ShouldBePollException() {
    var pollDTO = new PollDTO(10, 20, null, null);
    when(pollRepository.findByAssemblyId(anyInt())).thenReturn(pollDTO.toEntity());
    when(pollRepository.save(any())).thenReturn(pollDTO.toEntity());
    var thrown = assertThrows(PollException.class, () -> pollServiceImpl.start(pollDTO));
    assertTrue(thrown.getMessage().contentEquals(ERROR_ALREADY_EXISTS_ASSEMBLY));
  }
  @Test
  void close_ShouldBeClosed() {
    var pollDTO = new PollDTO(10, 20, null, null);
    when(pollRepository.findByAssemblyIdAndAndEndSession(anyInt(), any())).thenReturn(pollDTO.toEntity());
    when(pollRepository.save(any())).thenReturn(pollDTO.toEntity());
    var actual = pollServiceImpl.close(pollDTO);
    assertEquals(actual.getPollId(), pollDTO.getPollId());
  }
  @Test
  void close_ShouldBePollException() {
    var pollDTO = new PollDTO(10, 20, null, null);
    when(pollRepository.findByAssemblyIdAndAndEndSession(anyInt(), any())).thenReturn(null);
    var thrown = assertThrows( PollException.class, () -> pollServiceImpl.close(pollDTO) );
    assertTrue(thrown.getMessage().contentEquals(ERROR_POLL_CLOSED));
  }
  @Test
  void findByProjection_OPEN_ShouldOk() {
    var pollDTOList = List.of(new PollDTO(10, 20, null, null).toEntity());
    when(pollRepository.findByEndSessionIsNull()).thenReturn(pollDTOList);
    var actual = pollServiceImpl.findByProjection(ProjectionEnum.OPEN);
    assertEquals(actual.size(),pollDTOList.size());
  }
  @Test
  void findByProjection_FINISHED_ShouldOk() {
    var pollDTOList = List.of(new PollDTO(10, 20, null, null).toEntity());
    when(pollRepository.findByEndSessionNotIsNull()).thenReturn(pollDTOList);
    var actual = pollServiceImpl.findByProjection(ProjectionEnum.FINISHED);
    assertEquals(actual.size(),pollDTOList.size());
  }
  @Test
  void findByProjection_ALL_ShouldOk() {
    var pollDTOList = List.of(new PollDTO(10, 20, null, null).toEntity());
    when(pollRepository.findAll()).thenReturn(pollDTOList);
    var actual = pollServiceImpl.findByProjection(ProjectionEnum.ALL);
    assertEquals(actual.size(),pollDTOList.size());
  }
  @Test
  void findById_ShouldBeFindOne() {
    var pollDTO = new PollDTO(10, 20, null, null);
    when(pollRepository.findByPollId(anyInt())).thenReturn(pollDTO.toEntity());
    var actual = pollServiceImpl.findById(1);
    assertEquals(actual.getPollId(),pollDTO.getPollId());
  }
  @Test
  void findById_ShouldBeIllegalArgumentException() {
    when(pollRepository.findByPollId(anyInt())).thenThrow(IllegalArgumentException.class);
    assertThrows( PollException.class, () -> pollServiceImpl.findById(1) );
  }
  @Test
  void validatesDeadline_ShouldBeClosedAndSavePoll() {
    var pollDTO = new PollDTO(10, 20, LocalDateTime.now().minusMinutes(3), null);
    var assemblyDTO = new AssemblyDTO(1, AGENDA_MEETING_TEST, 2);
    when(pollRepository.findByPollId(anyInt())).thenReturn(pollDTO.toEntity());
    when(assemblyService.findById(anyInt())).thenReturn(assemblyDTO);
    when(pollRepository.save(any())).thenReturn(pollDTO.toEntity());
    assertFalse(pollServiceImpl.validatesDeadline(1));
  }
  @Test
  void validatesDeadline_ShouldBeTRUE() {
    var pollDTO = new PollDTO(10, 20, LocalDateTime.now(), null);
    var assemblyDTO = new AssemblyDTO(1, AGENDA_MEETING_TEST, 2);
    when(pollRepository.findByPollId(anyInt())).thenReturn(pollDTO.toEntity());
    when(assemblyService.findById(anyInt())).thenReturn(assemblyDTO);
    when(pollRepository.save(any())).thenReturn(pollDTO.toEntity());
    assertTrue(pollServiceImpl.validatesDeadline(1));
  }
  @Test
  void validatesDeadline_ShouldBFALSE() {
    var now = LocalDateTime.now();
    var pollDTO = new PollDTO(10, 20, now, now);
    when(pollRepository.findByPollId(anyInt())).thenReturn(pollDTO.toEntity());
    assertFalse(pollServiceImpl.validatesDeadline(1));
  }
}