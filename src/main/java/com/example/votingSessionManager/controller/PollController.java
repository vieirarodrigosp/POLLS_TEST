package com.example.votingSessionManager.controller;

import com.example.votingSessionManager.dto.PollDTO;
import com.example.votingSessionManager.enums.ProjectionEnum;
import com.example.votingSessionManager.service.PollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/polls")
public class PollController {
  @Autowired
  private PollService pollService;
  @PostMapping(value = "/start", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  public PollDTO start(@RequestBody PollDTO pollDTO) {
    return pollService.start(pollDTO);
  }
  @PutMapping(value = "/close", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public PollDTO close(@RequestBody PollDTO pollDTO) {
    return pollService.close(pollDTO);
  }
  @GetMapping(produces = APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public Collection<PollDTO> findByProjection(@RequestParam("projection") ProjectionEnum projection) {
    return pollService.findByProjection(projection);
  }
}