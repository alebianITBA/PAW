package ar.edu.itba.paw.helpers;

public enum PaginationHelper {
	INSTANCE;

	private static final Integer DEFAULT_PER_PAGE = 20;
	private static final Integer DEFAULT_PAGE = 1;

	public Integer page(Integer pageParam) {
		return (pageParam == null) ? DEFAULT_PAGE : pageParam;
	}

	public Integer perPage(Integer perPage) {
		return DEFAULT_PER_PAGE;
	}

	public Integer perPage() {
		return DEFAULT_PER_PAGE;
	}

}
