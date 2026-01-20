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

        String url =
                "https://api.exchangerate.host/latest?base="
                        + fromCurrency
                        + "&symbols="
                        + toCurrency;

        Map<?, ?> response = restTemplate.getForObject(url, Map.class);
        Map<?, ?> rates = (Map<?, ?>) response.get("rates");

        BigDecimal rate = new BigDecimal(rates.get(toCurrency).toString());

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
