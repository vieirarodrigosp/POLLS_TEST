package com.example.votingSessionManager.controller;

import com.example.votingSessionManager.dto.CountUserVoteDTO;
import com.example.votingSessionManager.dto.UserVoteDTO;
import com.example.votingSessionManager.service.UserVoteService;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserVoteController.class)
class UserVoteControllerTest {
  @Autowired
  private MockMvc mvc;
  @MockBean
  private UserVoteService userVoteService;
  @Autowired
  private Gson gson;
  @Test
  void add_ShouldBeCreated() throws Exception {
    var userVoteDTO = new UserVoteDTO(10, 20, 30, true);
    var payload = gson.toJson(userVoteDTO);

    when(userVoteService.add(any())).thenReturn(userVoteDTO);

    mvc.perform(MockMvcRequestBuilders
        .post("/vote/add")
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .content(payload))
        .andDo(print())
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.id").value(10))
        .andExpect(jsonPath("$.pollId").value(20))
        .andExpect(jsonPath("$.userId").value(30))
        .andExpect(jsonPath("$.voteValue").value(true));
  }

//  @Test
//  void voteCount() throws Exception {
//    CountUserVoteDTO countUserVoteDTO;
//    when(userVoteService.voteCountById(anyInt())).thenReturn(countUserVoteDTO);
//
//    mvc.perform(MockMvcRequestBuilders
//            .get("/vote/count/{pollId}", 1))
//        .andExpect(status().isOk());
//  }
}