package com.example.votingSessionManager.constants;

public class BaseMocksConstant {
  private BaseMocksConstant() {
    throw new UnsupportedOperationException(CANNOT_BE_INSTANTIATED);
  }
  public static final String CANNOT_BE_INSTANTIATED = "This is a utility class and cannot be instantiated";
  public static final String AGENDA_MEETING_TEST = "agenda of the meeting teste";
  public static final String ERROR_CREATING_ASSEMBLY = "Error when creating a new assembly";
  public static final String ERROR_ALREADY_EXISTS_ASSEMBLY = "There is already a vote for this assembly";
  public static final String ERROR_POLL_CLOSED = "This poll is closed";
  public static final String INFO_VOTING_CLOSED = "voting has closed";
  public static final String INFO_VOTING_NOT_HAVE_PERMISSION = "User does not have voting permission";
  public static final String INFO_USER_ALREADY_POLLED = "user has already polled";
}