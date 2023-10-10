package com.moneyservice.application.port.in;

import com.common.UseCase;
import com.moneyservice.domain.MoneyChangingRequest;

@UseCase
public interface IncreaseMoneyRequestUseCase {
    MoneyChangingRequest increaseMoneyRequest(IncreasemoneyRequestCommand command);
    MoneyChangingRequest increaseMoneyRequestAsync(IncreasemoneyRequestCommand command);
}
