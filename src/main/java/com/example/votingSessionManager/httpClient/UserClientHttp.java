package com.example.votingSessionManager.httpClient;

import com.example.votingSessionManager.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

@FeignClient( name = "authorization", url = "${microservices.user}")
public interface UserClientHttp {
  @GetMapping(value = "/users/{cpf}")
  @ResponseStatus(HttpStatus.OK)
  UserDTO getToken(@PathVariable String cpf);
}