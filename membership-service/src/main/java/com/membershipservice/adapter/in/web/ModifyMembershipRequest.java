package com.membershipservice.adapter.in.web;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ModifyMembershipRequest {
    private String membershipId;
    private String name;
    private String address;
    private String email;
    private boolean isCorp;
    private boolean isValid;
}
