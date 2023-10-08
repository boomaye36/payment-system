package com.moneyservice.adapter.out.persistence;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="member_money")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberMoneyJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberMoneyId;
    private String memberId;
    private int balance;

    public MemberMoneyJpaEntity(String memberId, int balance) {
        this.memberId = memberId;
        this.balance = balance;
    }
}
