package com.bankingservice.adapter.out.persistence;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name="request_firmbanking")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestFirmbankingJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long requestFirmbankingId;


    private  String fromBankName;
    private  String fromBankAccountNumber;
    private  String toBankName;
    private  String toBankAccountNumber;
    private int moneyAmount;
    private int firmbankingstatus;
    private UUID uuid;


    public RequestFirmbankingJpaEntity(String fromBankName, String fromBankAccountNumber, String toBankName, String toBankAccountNumber, int moneyAmount, int firmbankingstatus,UUID uuid) {
        this.fromBankName = fromBankName;
        this.fromBankAccountNumber = fromBankAccountNumber;
        this.toBankName = toBankName;
        this.toBankAccountNumber = toBankAccountNumber;
        this.moneyAmount = moneyAmount;
        this.firmbankingstatus = firmbankingstatus;
        this.uuid = uuid;

    }
}
