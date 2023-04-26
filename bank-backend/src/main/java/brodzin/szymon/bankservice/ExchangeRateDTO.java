package brodzin.szymon.bankservice;

import java.util.List;

public record ExchangeRateDTO (
    Character table,
    String currency,
    String code,
    List<RateDTO> rates
){
}
