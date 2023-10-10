package com.moneyservice.adapter.out.service;

import com.common.CommonHttpClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.moneyservice.application.port.out.GetMembershipPort;
import com.moneyservice.application.port.out.MembershipStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MembershipServiceAdapter implements GetMembershipPort {
    private final CommonHttpClient commonHttpClient;
    private final String membershipServiceUrl;

    public MembershipServiceAdapter(CommonHttpClient commonHttpClient,
                                    @Value("${service.membership.url}") String membershipServiceUrl) {
        this.commonHttpClient = commonHttpClient;
        this.membershipServiceUrl = membershipServiceUrl;
    }

    @Override
    public MembershipStatus getMembership(String membershipId) {
        // http Call

        String url = String.join("/", membershipServiceUrl, "membership", membershipId);
        try{
            String jsonResponse = commonHttpClient.sendGetRequest(url).body();
            //json Membership
            ObjectMapper mapper = new ObjectMapper();
            Membership membership = mapper.readValue(jsonResponse, Membership.class);

            if (membership.isValid()){
                return new MembershipStatus(membership.getMembershipId(), true);
            }
            //
        }catch (Exception e){
            throw new RuntimeException(e);
        }

        return null;
    }
}