package brodzin.szymon.bankservice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public class BankControllerTests {
    private final BankService bankService = new BankService();
    private final BankController bankController = new BankController(bankService);
    @Test
    public void givenTooLongCountryCode_getAskBuyMajorDiff_returnsBadRequest() {
        String countryCode = "MXNDD";
        Integer dayBack = 8;
        ResponseEntity<Double> response = bankController.getAskBuyMajorDiff(countryCode, dayBack);
        Assertions.assertTrue(response.getStatusCode() == HttpStatus.BAD_REQUEST);
    }

    @Test
    public void givenTooLongCountryCode_getExchangeRate_returnsBadRequest() {
        String countryCode = "MXNDD";
        String date = "2023-01-02";
        ResponseEntity<Double> response = bankController.getExchangeRate(countryCode, date);
        Assertions.assertTrue(response.getStatusCode() == HttpStatus.BAD_REQUEST);
    }

    @Test
    public void givenTooLongCountryCode_getMaxMinExchangeRate_returnsBadRequest() {
        String countryCode = "MXNDD";
        Integer dayBack = 8;
        ResponseEntity<Map<String, Double>> response = bankController.getMaxMinExchangeRate(countryCode, dayBack);
        Assertions.assertTrue(response.getStatusCode() == HttpStatus.BAD_REQUEST);
    }
}
