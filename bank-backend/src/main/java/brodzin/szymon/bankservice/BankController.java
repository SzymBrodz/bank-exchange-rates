package brodzin.szymon.bankservice;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@CrossOrigin(value = {"http://localhost:3000/"})
@RestController
@RequiredArgsConstructor
public class BankController {
    private final BankService bankService;

    @GetMapping("/exchangerates/{code}/{date}/")
    public ResponseEntity<Double> getExchangeRate(@PathVariable String code, @PathVariable String date) {
        if (code.length() != 3) {
            return ResponseEntity.badRequest().body(null);
        }
        else {
            return bankService.getExchangeRate(code, date);
        }
    }

    @GetMapping("/exchangerates/{code}/")
    public ResponseEntity<Map<String, Double>> getMaxMinExchangeRate(@PathVariable String code, @RequestParam("N") Integer daysBack) {
        if (code.length() != 3) {
            return ResponseEntity.badRequest().body(null);
        }
        else {
            return bankService.getMaxMinExchangeRate(code, daysBack);
        }
    }

    @GetMapping("/exchangerates/buyask/{code}/")
    public ResponseEntity<Double> getAskBuyMajorDiff(@PathVariable String code, @RequestParam("N") Integer daysBack) {
        if (code.length() != 3) {
            return ResponseEntity.badRequest().body(null);
        }
        else {
            return bankService.getAskBuyMajorDiff(code, daysBack);
        }
    }
}
