package ar.edu.itba.paw.validators;

import ar.edu.itba.paw.api.v1.ErrorCodes;
import ar.edu.itba.paw.utils.Pair;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserPasswordValidator {
  public static Pair<Boolean, ErrorCodes> validate(String password, String confirmation) {
    if (password == null || confirmation == null) {
      return new Pair<>(false, ErrorCodes.PASSWORD_MISSING);
    }
    if (password.isEmpty() || password.length() < 8) {
      return new Pair<>(false, ErrorCodes.PASSWORD_LENGTH);
    }
    Pattern pattern = Pattern.compile("\\s");
    Matcher matcher = pattern.matcher(password);
    if (matcher.find()) {
      return new Pair<>(false, ErrorCodes.PASSWORD_WHITESPACE);
    }
    if (password.equals(confirmation)) {
      return new Pair<>(true, ErrorCodes.NO_ERROR);
    } else {
      return new Pair<>(false, ErrorCodes.PASSWORDS_DO_NOT_MATCH);
    }
  }
}
