package com.example.votingSessionManager.controller;

import com.example.votingSessionManager.dto.AssemblyDTO;
import com.example.votingSessionManager.service.AssemblyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Validated
@RestController
@RequestMapping(value = "/assemblies")
public class AssemblyController {
  @Autowired
  private AssemblyService assemblyService;
  @PostMapping(value = "/create", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  public AssemblyDTO create(@Valid @RequestBody AssemblyDTO assemblyDTO) {
    return assemblyService.createAssembly(assemblyDTO);
  }
  @GetMapping(produces = APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public Collection<AssemblyDTO> findAll() {
    return assemblyService.findAll();
  }
}