package com.membershipservice.adapter.in.web;

import com.membershipservice.application.port.in.FindMembershipCommand;
import com.membershipservice.application.port.in.FindMembershipUseCase;
import com.membershipservice.application.port.in.RegisterMembershipCommand;
import com.membershipservice.application.port.in.RegisterMembershipUseCase;
import com.membershipservice.common.WebAdapter;
import com.membershipservice.domain.Membership;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@WebAdapter
public class findMembershipController {
    private final FindMembershipUseCase findMembershipUseCase;
    @GetMapping("/membership/{membershipId}")
    ResponseEntity<Membership> findMembershipByMemberId(@PathVariable String membershipId){
        // request

        // request -> Command
        FindMembershipCommand command = FindMembershipCommand.builder()
                .membershipId(membershipId)
                .build();
        // Usecase ~~(request)
        return ResponseEntity.ok(findMembershipUseCase.findMembership(command));
    }
}
