package com.javatpoint.microservices.currencyconversionsevice.service;

import com.javatpoint.microservices.currencyconversionsevice.feighnproxy.CurrencyConversionServiceProxy;
import com.javatpoint.microservices.currencyconversionsevice.model.CurrencyConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@Service
public class CurrencyConversionServiceImp implements CurrencyConversionService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CurrencyConversionServiceProxy proxy;

    private String currency_exchange_service_Url = "http://localhost:8000/currency-exchange/from/";

    @Override
    public CurrencyConversion getCurrencyConsersion(String from, String to, BigDecimal quantity) {
        String complete_currency_exchange_service_Url = currency_exchange_service_Url + from + "/to/" + to;
        ResponseEntity<CurrencyConversion> exchange = restTemplate.getForEntity(complete_currency_exchange_service_Url,
                CurrencyConversion.class);
        BigDecimal conversionMultiple = exchange.getBody().getConversionMultiple();
        CurrencyConversion currencyConversion = new CurrencyConversion();
        currencyConversion.setFrom(from);
        currencyConversion.setTo(to);
        currencyConversion.setQuantity(quantity);
        currencyConversion.setConversionMultiple(conversionMultiple);
        currencyConversion.setTotalcalculatedAmount(quantity.multiply(conversionMultiple));
        return currencyConversion;
    }

    @Override
    public CurrencyConversion getCurrencyConsersionFeign(String from, String to, BigDecimal quantity) {
        CurrencyConversion exchangeValue = proxy.getExchangeValue(from, to);

        BigDecimal conversionMultiple = exchangeValue.getConversionMultiple();
        CurrencyConversion currencyConversion = new CurrencyConversion();
        currencyConversion.setFrom(from);
        currencyConversion.setTo(to);
        currencyConversion.setQuantity(quantity);
        currencyConversion.setConversionMultiple(conversionMultiple);
        currencyConversion.setTotalcalculatedAmount(quantity.multiply(conversionMultiple));
        return currencyConversion;
    }

}
