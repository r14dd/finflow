package com.finflow.wallet.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

@Service
public class CurrencyService {

    private final RestTemplate restTemplate = new RestTemplate();

    @CircuitBreaker(name = "exchangeRate", fallbackMethod = "fallback")
    public BigDecimal convert(
            String fromCurrency,
            String toCurrency,
            BigDecimal amount
    ) {
        if (fromCurrency.equals(toCurrency)) {
            return amount;
        }

        String url = "https://open.er-api.com/v6/latest/" + fromCurrency;

        Map<?, ?> response = restTemplate.getForObject(url, Map.class);

        Map<?, ?> rates = (Map<?, ?>) response.get("rates");
        Object rateObj = rates.get(toCurrency);

        if (rateObj == null) {
            throw new IllegalStateException("Rate not available for " + toCurrency);
        }

        BigDecimal rate = new BigDecimal(rateObj.toString());

        return amount
                .multiply(rate)
                .setScale(4, RoundingMode.HALF_UP);
    }

    // Called when circuit breaker is OPEN or API fails
    public BigDecimal fallback(
            String fromCurrency,
            String toCurrency,
            BigDecimal amount,
            Throwable ex
    ) {
        throw new IllegalStateException("Currency conversion unavailable");
    }
}
