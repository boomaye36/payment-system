package com.membershipservice.adapter.in.web;

import com.common.WebAdapter;
import com.membershipservice.application.port.in.RegisterMembershipCommand;
import com.membershipservice.application.port.in.RegisterMembershipUseCase;
import com.membershipservice.domain.Membership;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@WebAdapter
public class RegisterMembershipController {
    private final RegisterMembershipUseCase registerMembershipUseCase;
    @PostMapping("/membership/register")
    Membership registerMembership(@RequestBody RegisterMembershipRequest request){
        // request

        // request -> Command
        RegisterMembershipCommand command = RegisterMembershipCommand.builder()
                .name(request.getName())
                .address(request.getAddress())
                .email(request.getEmail())
                .isValid(true)
                .isCorp(request.isCorp())
                .build();
        // Usecase ~~(request)
        return registerMembershipUseCase.registerMembership(command);

    }
}
