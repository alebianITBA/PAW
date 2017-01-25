package ar.edu.itba.paw.validators;

import ar.edu.itba.paw.api.v1.ErrorCodes;
import ar.edu.itba.paw.api.v1.parameters.JobOfferParams;
import ar.edu.itba.paw.api.v1.parameters.PostParams;
import ar.edu.itba.paw.utils.Pair;

public class JobOfferValidator {
  public static Pair<Boolean, ErrorCodes> validate(final JobOfferParams input) {
    if (input.title == null || input.title.isEmpty() || input.title.length() > 255) {
      return new Pair<>(false, ErrorCodes.JOB_OFFER_TITLE_LENGTH);
    }
    if (input.description == null || input.description.isEmpty() || input.description.length() > 2048) {
      return new Pair<>(false, ErrorCodes.JOB_OFFER_DESCRIPTION_LENGTH);
    }
    return new Pair<>(true, ErrorCodes.NO_ERROR);
  }
}
