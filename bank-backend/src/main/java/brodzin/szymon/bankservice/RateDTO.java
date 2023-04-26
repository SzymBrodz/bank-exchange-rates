package brodzin.szymon.bankservice;

public record RateDTO(
        String no,
        String effectiveDate,
        Double mid,
        Double bid,
        Double ask
) {
}
