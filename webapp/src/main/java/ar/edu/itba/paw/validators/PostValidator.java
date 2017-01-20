package ar.edu.itba.paw.validators;

import ar.edu.itba.paw.api.v1.parameters.PostParams;
import ar.edu.itba.paw.utils.Pair;

public class PostValidator {
  public static Pair<Boolean, String> validate(final PostParams input) {
    if (input.title == null || input.title.isEmpty() || input.title.length() > 255) {
      return new Pair<>(false, "Title should not be blank or longer than 255 characters.");
    }
    if (input.description == null || input.description.isEmpty()) {
      return new Pair<>(false, "Description cannot be blank.");
    }
    return new Pair<>(true, "");
  }
}