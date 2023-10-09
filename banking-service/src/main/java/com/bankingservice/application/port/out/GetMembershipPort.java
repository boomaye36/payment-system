package com.bankingservice.application.port.out;

public interface GetMembershipPort {
    public MembershipStatus getMembership(String membershipId);
}
