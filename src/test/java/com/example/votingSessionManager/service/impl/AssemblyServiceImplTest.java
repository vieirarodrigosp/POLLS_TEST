package com.example.votingSessionManager.service.impl;

import com.example.votingSessionManager.dto.AssemblyDTO;
import com.example.votingSessionManager.entity.Assembly;
import com.example.votingSessionManager.exception.AssemblyException;
import com.example.votingSessionManager.repository.AssemblyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.example.votingSessionManager.constants.BaseMocksConstant.AGENDA_MEETING_TEST;
import static com.example.votingSessionManager.constants.BaseMocksConstant.ERROR_CREATING_ASSEMBLY;
import static com.fasterxml.jackson.databind.type.LogicalType.Collection;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class AssemblyServiceImplTest {
  @InjectMocks
  private AssemblyServiceImpl assemblyServiceImpl;
  @Mock
  private AssemblyRepository assemblyRepository;
  @Test
  void createAssembly_ShouldBeCreated() {
    var assemblyDTO = new AssemblyDTO(1, AGENDA_MEETING_TEST, 200);
    when(assemblyRepository.save(any())).thenReturn(assemblyDTO.toEntity());
    var actual = assemblyServiceImpl.createAssembly(assemblyDTO);
    assertEquals(actual.getAssemblyId(), assemblyDTO.getAssemblyId());
  }
  @Test
  void createAssembly_ShouldBeAssemblyException() {
    var assemblyDTO = new AssemblyDTO(1, AGENDA_MEETING_TEST, 200);
    when(assemblyRepository.save(any())).thenThrow(AssemblyException.class);
    var thrown = assertThrows(AssemblyException.class, () -> assemblyServiceImpl.createAssembly(assemblyDTO));
    assertTrue(thrown.getMessage().contentEquals(ERROR_CREATING_ASSEMBLY));
  }

  @Test
  void findAll_shouldBeListAll() {
    var assemblyDTOList = List.of(new AssemblyDTO(1, AGENDA_MEETING_TEST, 200).toEntity());
    when(assemblyRepository.findAll()).thenReturn(assemblyDTOList);
    var actual = assemblyServiceImpl.findAll();
    assertEquals(actual.size(), assemblyDTOList.size());
  }

  @Test
  void findById_shouldBeJustOne() {
    var assemblyDTO = new AssemblyDTO(1, AGENDA_MEETING_TEST, 200);
    when(assemblyRepository.findByAssemblyId(anyInt())).thenReturn(assemblyDTO.toEntity());
    var actual = assemblyServiceImpl.findById(1);
    assertEquals(actual.getAgenda(), assemblyDTO.getAgenda());
  }
}