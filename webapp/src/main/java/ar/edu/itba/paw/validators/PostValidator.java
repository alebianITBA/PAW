package ar.edu.itba.paw.validators;

import ar.edu.itba.paw.api.v1.ErrorCodes;
import ar.edu.itba.paw.api.v1.parameters.PostParams;
import ar.edu.itba.paw.utils.Pair;

import static ar.edu.itba.paw.api.v1.ErrorCodes.POST_TITLE_LENGTH;

public class PostValidator {
  public static Pair<Boolean, ErrorCodes> validate(final PostParams input) {
    if (input.title == null || input.title.isEmpty() || input.title.length() > 255) {
      return new Pair<>(false, ErrorCodes.POST_TITLE_LENGTH);
    }
    if (input.description == null || input.description.isEmpty()) {
      return new Pair<>(false, ErrorCodes.POST_DESCRIPTION_LENGTH);
    }
    return new Pair<>(true, ErrorCodes.NO_ERROR);
  }
}
