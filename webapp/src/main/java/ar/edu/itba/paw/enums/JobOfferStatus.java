package ar.edu.itba.paw.enums;

public enum JobOfferStatus {
	
    READY_TO_APPLY("READY_TO_APPLY"),
    ALREADY_APPLIED("ALREADY_APPLIED"),
    OFFER_OWNER("OFFER_OWNER");

    private String name;

    private JobOfferStatus(String name) {
        this.name = name;
    }

    public String getJobOfferStatusEnum() {
        return this.name;
    }
    
}
