package com.bankingservice.application.port.out;

import com.bankingservice.adapter.out.external.bank.ExternalFirmbankingRequest;
import com.bankingservice.adapter.out.external.bank.FirmbankingResult;

public interface RequestExternalFirmbankingPort {
    FirmbankingResult requestExternalFirmbanking(ExternalFirmbankingRequest request);
}
