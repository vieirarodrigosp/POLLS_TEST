package com.example.votingSessionManager.controller;

import com.example.votingSessionManager.dto.PollDTO;
import com.example.votingSessionManager.enums.ProjectionEnum;
import com.example.votingSessionManager.service.PollService;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PollController.class)
class PollControllerTest {
  @Autowired
  private MockMvc mvc;
  @MockBean
  private PollService pollService;
  @Autowired
  private Gson gson;
  @Test
  void start_ShopuldBeIsCreated() throws Exception {
    var pollDTO = new PollDTO(1, 42, null, null);
    var payload = gson.toJson(pollDTO);

    when(pollService.start(any())).thenReturn(pollDTO);

    mvc.perform(MockMvcRequestBuilders
        .post("/polls/start")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(payload))
        .andDo(print())
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.pollId").value(1))
        .andExpect(jsonPath("$.assemblyId").value(42))
        .andExpect(jsonPath("$.startSession").doesNotExist())
        .andExpect(jsonPath("$.endSession").doesNotExist());
  }

  @Test
  void close_ShopuldBeIsOk() throws Exception {
    var pollDTO = new PollDTO(544, 5442, null, null);
    var payload = gson.toJson(pollDTO);

    when(pollService.close(any())).thenReturn(pollDTO);

    mvc.perform(MockMvcRequestBuilders
            .put("/polls/close")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(payload))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.pollId").value(544))
        .andExpect(jsonPath("$.assemblyId").value(5442))
        .andExpect(jsonPath("$.startSession").doesNotExist())
        .andExpect(jsonPath("$.endSession").doesNotExist());
  }

  @Test
  void findByProjection_ShouldBeIsOK() throws Exception {
    var pollDTOList = List.of(
        new PollDTO(12, 442, null, null)
        , new PollDTO(41, 4872, null, null)
        , new PollDTO(98, 54442, null, null));

    when(pollService.findByProjection(any())).thenReturn(pollDTOList);

    mvc.perform(MockMvcRequestBuilders
            .get("/polls")
            .param("projection", ProjectionEnum.ALL.name()))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.[0].pollId").value(12))
        .andExpect(jsonPath("$.[1].pollId").value(41))
        .andExpect(jsonPath("$.[1].assemblyId").value(4872))
        .andExpect(jsonPath("$.[2].assemblyId").value(54442));

    mvc.perform(MockMvcRequestBuilders
            .get("/polls")
            .param("projection", ProjectionEnum.OPEN.name()))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.[0].pollId").value(12))
        .andExpect(jsonPath("$.[1].pollId").value(41))
        .andExpect(jsonPath("$.[1].assemblyId").value(4872))
        .andExpect(jsonPath("$.[2].assemblyId").value(54442));

    mvc.perform(MockMvcRequestBuilders
            .get("/polls")
            .param("projection", ProjectionEnum.FINISHED.name()))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.[0].pollId").value(12))
        .andExpect(jsonPath("$.[1].pollId").value(41))
        .andExpect(jsonPath("$.[1].assemblyId").value(4872))
        .andExpect(jsonPath("$.[2].assemblyId").value(54442));
  }
  @Test
  void findByProjection_ShouldBeIsBadRequest() throws Exception {
    var pollDTOList = List.of(
        new PollDTO(12, 442, null, null)
        , new PollDTO(41, 4872, null, null)
        , new PollDTO(98, 54442, null, null));

    when(pollService.findByProjection(any())).thenReturn(pollDTOList);

    mvc.perform(MockMvcRequestBuilders
            .get("/polls")
            .param("projection", "ERROR"))
        .andDo(print())
        .andExpect(status().isBadRequest());
  }
}