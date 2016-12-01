package ar.edu.itba.paw.validators;

import ar.edu.itba.paw.utils.Pair;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserPasswordValidator {
  public static Pair<Boolean, String> validate(String password, String confirmation) {
    if (password == null || confirmation == null) {
      return new Pair<>(false, "Password missing");
    }
    if (password.isEmpty() || password.length() < 8) {
      return new Pair<>(false, "Password lenght must be 8 or more");
    }
    Pattern pattern = Pattern.compile("\\s");
    Matcher matcher = pattern.matcher(password);
    if (matcher.find()) {
      return new Pair<>(false, "Password must not contain whitespaces");
    }
    if (password.equals(confirmation)) {
      return new Pair<>(true, "");
    } else {
      return new Pair<>(false, "Passwords don't match");
    }
  }
}
