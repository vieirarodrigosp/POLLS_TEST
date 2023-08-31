package com.example.votingSessionManager.exception;

import org.hibernate.exception.SQLGrammarException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.time.Instant;
import java.util.Set;

@ControllerAdvice
public class ExceptionHandler {
  @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
  protected ResponseEntity<StandardError> methodArgumentNotValid(
      MethodArgumentNotValidException ex, HttpServletRequest request) {

    String message = ex.getBindingResult().getFieldErrorCount() + " invalid argument(s). Message: "
        + ex.getBindingResult().getFieldErrors();
    var err = new StandardError(HttpStatus.BAD_REQUEST.value(), "Argument(s) not valid"
        , message, request.getRequestURI());
    return ResponseEntity.status(err.getStatus()).body(err);
  }
  @org.springframework.web.bind.annotation.ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity handle(ConstraintViolationException constraintViolationException) {
    Set<ConstraintViolation<?>> violations = constraintViolationException.getConstraintViolations();
    String errorMessage = "";
    if (!violations.isEmpty()) {
      StringBuilder builder = new StringBuilder();
      violations.forEach(violation -> builder.append(" " + violation.getMessage()));
      errorMessage = builder.toString();
    } else {
      errorMessage = "ConstraintViolationException occured.";
    }
    return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
  }
  @org.springframework.web.bind.annotation.ExceptionHandler(SQLGrammarException.class)
  public ResponseEntity handle(SQLGrammarException sQLGrammarException, HttpServletRequest request) {
    String errorMessage = "";
    String message = sQLGrammarException.getLocalizedMessage() + " invalid argument(s). Message: "
        + sQLGrammarException.getSQLException();
    var err = new StandardError(HttpStatus.BAD_REQUEST.value(), "Argument(s) not valid"
        , message, request.getRequestURI());
    return ResponseEntity.status(err.getStatus()).body(err);
  }
  @org.springframework.web.bind.annotation.ExceptionHandler(PollException.class)
  public ResponseEntity PollException(PollException e, HttpServletRequest request) {
    StandardError err = new StandardError();
    err.setTimestamp(Instant.now());
    err.setStatus(HttpStatus.CONFLICT.value());
    err.setError(e.getLocalizedMessage());
    err.setMessage(e.getMessage());
    err.setPath(request.getRequestURI());
    return ResponseEntity.status(HttpStatus.CONFLICT).body(err);
  }
  @org.springframework.web.bind.annotation.ExceptionHandler(UserVoterException.class)
  public ResponseEntity UserVoterException(UserVoterException e, HttpServletRequest request) {
    StandardError err = new StandardError();
    err.setTimestamp(Instant.now());
    err.setStatus(HttpStatus.CONFLICT.value());
    err.setError(e.getLocalizedMessage());
    err.setMessage(e.getMessage());
    err.setPath(request.getRequestURI());
    return ResponseEntity.status(HttpStatus.CONFLICT).body(err);
  }
  @org.springframework.web.bind.annotation.ExceptionHandler(UserException.class)
  public ResponseEntity UserVoterException(UserException e, HttpServletRequest request) {
    StandardError err = new StandardError();
    err.setTimestamp(Instant.now());
    err.setStatus(HttpStatus.CONFLICT.value());
    err.setError(e.getLocalizedMessage());
    err.setMessage(e.getMessage());
    err.setPath(request.getRequestURI());
    return ResponseEntity.status(HttpStatus.CONFLICT).body(err);
  }
  @org.springframework.web.bind.annotation.ExceptionHandler(AssemblyException.class)
  public ResponseEntity UserVoterException(AssemblyException e, HttpServletRequest request) {
    StandardError err = new StandardError();
    err.setTimestamp(Instant.now());
    err.setStatus(HttpStatus.CONFLICT.value());
    err.setError(e.getLocalizedMessage());
    err.setMessage(e.getMessage());
    err.setPath(request.getRequestURI());
    return ResponseEntity.status(HttpStatus.CONFLICT).body(err);
  }
}