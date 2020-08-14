package com.javatpoint.microservices.currencyconversionsevice.service;

import com.javatpoint.microservices.currencyconversionsevice.model.CurrencyConversion;

import java.math.BigDecimal;

public interface CurrencyConversionService {

    CurrencyConversion getCurrencyConsersion(String from, String to, BigDecimal quantity);

    CurrencyConversion getCurrencyConsersionFeign(String from, String to, BigDecimal quantity);
}
