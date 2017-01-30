package ar.edu.itba.paw.helpers;

public enum PaginationHelper {
  INSTANCE;

  public static final Integer DEFAULT_PER_PAGE = 20;
  public static final Integer DEFAULT_PAGE = 0;

  public Integer page(Integer pageParam) {
    if (pageParam == null || pageParam <= 0) {
      return DEFAULT_PAGE;
    }
    return pageParam -1;
  }

  public Integer perPage(Integer perPage) {
    if (perPage == null || perPage <= 0) {
      return DEFAULT_PER_PAGE;
    }
    return perPage;
  }

}
