package com.salem.stockalert;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.charset.StandardCharsets;
import java.time.Instant;

import com.google.gson.Gson;

public class FinnhubPriceDataProvider implements PriceDataProvider {
    private static final String API_KEY_ENV = "FINNHUB_API_KEY";
    private static final String BASE_URL = "https://finnhub.io/api/v1/quote";
    private final HttpClient http = HttpClient.newHttpClient();
    private final Gson gson = new Gson();
    
    private static class QuoteDto{
        BigDecimal c; 
        Long t;
    }
    
    @Override
    public PriceQuote fetch(Symbol symbol) {
        // validate inputs
        if (symbol == null){
            throw new IllegalArgumentException("symbol");
        }
        
        // read the API key, and throw error if null/blank
        String key = System.getenv("FINNHUB_API_KEY");
        if (key == null || key.isEmpty()){
            throw new IllegalStateException("Missing FINNHUB_API_KEY");
        }

        // requesting url
        String encoded = URLEncoder.encode(symbol.getTicker(), StandardCharsets.UTF_8);
        String url = BASE_URL + "?symbol=" + encoded + "&token=" + key;
        
        // sending http request
        HttpRequest request = HttpRequest.newBuilder(URI.create(url)).GET().build();
        HttpResponse<String> response;
        try {
            response = http.send(request, BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Failed to fetch price data", e);
        }
        int status = response.statusCode();

        // non-200 status
        if (status != 200){
            throw new RuntimeException("HTTP " + status + ". Check FINNHUB_API_KEY");
        }

        // parsing JSON
        QuoteDto dto = gson.fromJson(response.body(), QuoteDto.class);

        // validating parsed fields
        if (dto == null || dto.c == null || dto.t == null || dto.t <= 0){
            throw new IllegalArgumentException();
        }

        return new PriceQuote(symbol, dto.c, Instant.ofEpochSecond(dto.t));

    }
}
