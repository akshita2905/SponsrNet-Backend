package com.sponsrnet.dto;

public class DashboardDTO {

    private long totalUsers;
    private long totalOpportunities;
    private long totalOffers;
    private long acceptedOffers;
    private double totalFundingRaised;

    public DashboardDTO() {
    }

    public DashboardDTO(long totalUsers,
                        long totalOpportunities,
                        long totalOffers,
                        long acceptedOffers,
                        double totalFundingRaised) {
        this.totalUsers = totalUsers;
        this.totalOpportunities = totalOpportunities;
        this.totalOffers = totalOffers;
        this.acceptedOffers = acceptedOffers;
        this.totalFundingRaised = totalFundingRaised;
    }

    public long getTotalUsers() {
        return totalUsers;
    }

    public void setTotalUsers(long totalUsers) {
        this.totalUsers = totalUsers;
    }

    public long getTotalOpportunities() {
        return totalOpportunities;
    }

    public void setTotalOpportunities(long totalOpportunities) {
        this.totalOpportunities = totalOpportunities;
    }

    public long getTotalOffers() {
        return totalOffers;
    }

    public void setTotalOffers(long totalOffers) {
        this.totalOffers = totalOffers;
    }

    public long getAcceptedOffers() {
        return acceptedOffers;
    }

    public void setAcceptedOffers(long acceptedOffers) {
        this.acceptedOffers = acceptedOffers;
    }

    public double getTotalFundingRaised() {
        return totalFundingRaised;
    }

    public void setTotalFundingRaised(double totalFundingRaised) {
        this.totalFundingRaised = totalFundingRaised;
    }
}