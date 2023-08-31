package com.example.votingSessionManager.controller;

import com.example.votingSessionManager.dto.AssemblyDTO;
import com.example.votingSessionManager.service.AssemblyService;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static com.example.votingSessionManager.constants.BaseMocksConstant.AGENDA_MEETING_TEST;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AssemblyController.class)
class AssemblyControllerTest {
  @Autowired
  private MockMvc mvc;
  @MockBean
  private AssemblyService assemblyService;
  @Autowired
  Gson gson;
  @Test
  void create_shouldBeCreated() throws Exception {
    var assemblyDTO = new AssemblyDTO(1, AGENDA_MEETING_TEST, 200);
    var payload = gson.toJson(assemblyDTO);

    when(assemblyService.createAssembly(any())).thenReturn(assemblyDTO);

    mvc.perform(MockMvcRequestBuilders
        .post("/assemblies/create")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(payload))
        .andDo(print())
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.assemblyId").isNotEmpty())
        .andExpect(jsonPath("$.agenda").value(AGENDA_MEETING_TEST))
        .andExpect(jsonPath("$.duration").value(200));
  }

  @Test
  void findAll_shouldBeIsOK() throws Exception {
    var assemblyDTOList = List.of(
        new AssemblyDTO(1, AGENDA_MEETING_TEST, 200)
        ,new AssemblyDTO(7, AGENDA_MEETING_TEST+"07", 50)
        ,new AssemblyDTO(32, AGENDA_MEETING_TEST+"32", 32) );

    when(assemblyService.findAll()).thenReturn(assemblyDTOList);

    mvc.perform(MockMvcRequestBuilders
            .get("/assemblies"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.[0].assemblyId").isNotEmpty())
        .andExpect(jsonPath("$.[1].agenda").value(AGENDA_MEETING_TEST+"07"))
        .andExpect(jsonPath("$.[2].duration").value(32));
  }
}