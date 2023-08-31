package com.example.votingSessionManager.controller;

import com.example.votingSessionManager.dto.CountUserVoteDTO;
import com.example.votingSessionManager.dto.UserVoteDTO;
import com.example.votingSessionManager.service.UserVoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/vote")
public class UserVoteController {
  @Autowired
  private UserVoteService userVoteService;
  @PostMapping(value = "/add", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  public UserVoteDTO add(@RequestBody UserVoteDTO userVoteDTO) throws NoSuchFieldException {
    return userVoteService.add(userVoteDTO);
  }
  @GetMapping(value = "/count/{pollId}",produces = APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public CountUserVoteDTO voteCount(@PathVariable int pollId) {
    return userVoteService.voteCountById(pollId);
  }
}