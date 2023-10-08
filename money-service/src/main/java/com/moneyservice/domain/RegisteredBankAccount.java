package com.moneyservice.domain;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RegisteredBankAccount {
    /**
     * The baseline balance of the account. This was the balance of the account before the first
     * activity in the activityWindow.
     */
    @Getter private final String registeredBankAccountId;
    @Getter private final String membershipId;
    @Getter private final String bankName;
    @Getter private final String bankAccountNumber;
    @Getter private final boolean linkedStatusIsValid;


    public static RegisteredBankAccount generatedRegisteredBankAccount(
            RegisteredBankAccountId registeredBankAccountId,
            MembershipId membershipId,
            RegisteredBankAccountName registeredBankAccountName,
            BankAccountNumber bankAccountNumber,
            LinkedStatusIsValid linkedStatusIsValid

            ){
        return new RegisteredBankAccount(registeredBankAccountId.registeredBankAccountId,
                membershipId.membershipId,
                registeredBankAccountName.bankName,
                bankAccountNumber.bankAccountNumber,
                linkedStatusIsValid.linkedStatusIsValid);
    }

    @Value
    public static class RegisteredBankAccountId {
        public RegisteredBankAccountId(String value) {
            this.registeredBankAccountId = value;
        }
        String registeredBankAccountId ;
    }

    @Value
    public static class MembershipId {
        public MembershipId(String value) {
            this.membershipId = value;
        }
        String membershipId ;
    }

    @Value
    public static class RegisteredBankAccountName {
        public RegisteredBankAccountName(String value) {
            this.bankName = value;
        }

        String bankName;
    }
    @Value
    public static class BankAccountNumber {
        public BankAccountNumber(String value) {
            this.bankAccountNumber = value;
        }
        String bankAccountNumber;
    }


    @Value
    public static class LinkedStatusIsValid {
        public LinkedStatusIsValid(boolean value) {
            this.linkedStatusIsValid = value;
        }
        boolean linkedStatusIsValid;
    }
}