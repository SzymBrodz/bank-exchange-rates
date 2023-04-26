package brodzin.szymon.bankservice;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class BankService {

    /**
     * Makes a call to NBP API to get average exchange rate between PLN and foreign currency on a given day.
     * @param countryCode - code of the foreign currency's country.
     * @param date - date to get the currency from in YYYY-MM-DD format.
     * @return Response entity with double value of average exchange rate if the parameters are correct. Returns bad request when the call fails to find anything.
     */
    public ResponseEntity<Double> getExchangeRate(String countryCode, String date) {
        RestTemplate restTemplate = new RestTemplate();
        String url = String.format("http://api.nbp.pl/api/exchangerates/rates/A/%s/%s/", countryCode, date);
        ExchangeRateDTO response = restTemplate.getForObject(url, ExchangeRateDTO.class);

        if (response == null) {
            return ResponseEntity.badRequest().body(null);
        }
        else {
            return ResponseEntity.ok().body(response.rates().get(0).mid());
        }
    }

    /**
     * Makes a call to NBP API to get maximum and minimum average exchange rates in the last N number of days.
     * @param countryCode - code of the foreign currency's country.
     * @param dayBack - last quotation days to check.
     * @return Response entity with Map containing maximum and minimum values. Returns bad request when the call fails to find anything.
     */
    public ResponseEntity<Map<String, Double>> getMaxMinExchangeRate(String countryCode, Integer dayBack) {
        RestTemplate restTemplate = new RestTemplate();
        String url = String.format("http://api.nbp.pl/api/exchangerates/rates/A/%s/last/%d", countryCode, dayBack);
        ExchangeRateDTO response = restTemplate.getForObject(url, ExchangeRateDTO.class);

        if (response == null) {
            return ResponseEntity.badRequest().body(null);
        }
        Optional<Double> max = response.rates().stream()
                .map(RateDTO::mid)
                .max(Double::compareTo);

        Optional<Double> min = response.rates().stream()
                .map(RateDTO::mid)
                .min(Double::compareTo);

        Map<String, Double> answer = new HashMap<>();
        max.ifPresent(maxVal -> answer.put("max", maxVal));
        min.ifPresent(minVal -> answer.put("min", minVal));

        return ResponseEntity.ok().body(answer);
    }
    /**
     * Makes a call to NBP API to get major diffence between ask and buy values in the last N number of days.
     * @param countryCode - code of the foreign currency's country.
     * @param dayBack - last quotation days to check.
     * @return Response entity with double value of the difference. Returns bad request when the call fails to find anything.
     */
    public ResponseEntity<Double> getAskBuyMajorDiff(String countryCode, int dayBack) {
        RestTemplate restTemplate = new RestTemplate();
        String url = String.format("http://api.nbp.pl/api/exchangerates/rates/C/%s/last/%d", countryCode, dayBack);
        ExchangeRateDTO response = restTemplate.getForObject(url, ExchangeRateDTO.class);

        if (response == null) {
            return ResponseEntity.badRequest().body(null);
        }
        Optional<Double> max = response.rates().stream()
                .map(rate -> (Math.abs(rate.ask()-rate.bid())))
                .max(Double::compareTo);

        return ResponseEntity.ok().body(max.orElseThrow());
    }


}
