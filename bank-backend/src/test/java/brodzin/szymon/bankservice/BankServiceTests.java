package brodzin.szymon.bankservice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class BankServiceTests {

    private final BankService bankService = new BankService();
    @Test
    public void givenCorrectPrompt_getExchangeRate_returnsExpectedValue() {
        String countryCode = "MXN";
        String date = "2023-01-02";
        Double expectedValue = 0.2246; // This is the expected value for MXN on 2023-01-02
        Double actualValue = bankService.getExchangeRate(countryCode, date).getBody();
        Assertions.assertEquals(expectedValue, actualValue, 0.0001);
    }

    @Test
    public void givenCorrectPrompt_getMaxMinExchangeRate_returnsMaxMinValues() {
        String countryCode = "MXN";
        Integer dayBack = 8;
        Map<String, Double> result = bankService.getMaxMinExchangeRate(countryCode, dayBack).getBody();
        Assertions.assertNotNull(result.get("max"));
        Assertions.assertNotNull(result.get("min"));
        Assertions.assertTrue(result.get("max") >= result.get("min"));
    }
}
