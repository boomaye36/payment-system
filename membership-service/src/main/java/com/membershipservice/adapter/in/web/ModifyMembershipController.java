package com.membershipservice.adapter.in.web;

import com.membershipservice.application.port.in.FindMembershipCommand;
import com.membershipservice.application.port.in.FindMembershipUseCase;
import com.membershipservice.application.port.in.ModifyMembershipCommand;
import com.membershipservice.application.port.in.ModifyMembershipUseCase;
import com.membershipservice.common.WebAdapter;
import com.membershipservice.domain.Membership;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@WebAdapter
public class ModifyMembershipController {
    private final ModifyMembershipUseCase modifyMembershipUseCase;
    @PutMapping("/membership")
    ResponseEntity<Membership> modifyMembershipByMemberId(@RequestBody ModifyMembershipRequest request){
        // request

        // request -> Command
        ModifyMembershipCommand command = ModifyMembershipCommand.builder()
                .membershipId(request.getMembershipId())
                .name(request.getName())
                .address(request.getAddress())
                .email(request.getEmail())
                .isValid(request.isValid())
                .isCorp(request.isCorp())
                .build();
        // Usecase ~~(request)
        return ResponseEntity.ok(modifyMembershipUseCase.modifyMembership(command));
    }
}
