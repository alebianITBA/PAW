package ar.edu.itba.paw.api.v1;

public enum ErrorCodes {
  NO_ERROR(0, ""),
  DATABASE_ERROR(500, "Database error"),
  RESOURCE_NOT_OWNED(501, "Resource not owned."),
  EMAIL_ALREADY_IN_USE(1000, "Email already in use."),
  PASSWORDS_DO_NOT_MATCH(1001, "Passwords don't match."),
  PASSWORD_WHITESPACE(1002, "Password must not contain whitespaces."),
  PASSWORD_LENGTH(1003, "Password lenght must be 8 or more."),
  PASSWORD_MISSING(1004, "Password missing."),
  LOGIN_ERROR(1005, "Email or password do not match."),
  POST_TITLE_LENGTH(1006, "Title should not be blank or longer than 255 characters."),
  POST_DESCRIPTION_LENGTH(1007, "Description cannot be blank."),
  JOB_OFFER_TITLE_LENGTH(1008, "Title should not be blank or longer than 255 characters."),
  JOB_OFFER_DESCRIPTION_LENGTH(1009, "Description cannot be blank."),
  APPLICATION_YOURSELF(1010, "You can't apply to an offer you own.");

  public final int code;
  public final String message;

  ErrorCodes(int code, String message) {
    this.code = code;
    this.message = message;
  }
}
