package com.javatpoint.microservices.currencyconversionsevice.controller;

import com.javatpoint.microservices.currencyconversionsevice.model.CurrencyConversion;
import com.javatpoint.microservices.currencyconversionsevice.service.CurrencyConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyConversionController {

    @Value("${server.port}")
    private int port;

    @Autowired
    private CurrencyConversionService service;

    @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion convertCurreny(@PathVariable String from, @PathVariable String to,
                                             @PathVariable BigDecimal quantity) {

        /*
         * CurrencyConversion currencyConversion = new CurrencyConversion(1001L, from,
         * to, BigDecimal.valueOf(115), quantity, BigDecimal.valueOf(1150), port);
         */
        CurrencyConversion currencyConversion = service.getCurrencyConsersion(from, to, quantity);
        currencyConversion.setPort(port);
        return currencyConversion;
    }

    @GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion convertCurrenyFeign(@PathVariable String from, @PathVariable String to,
                                                  @PathVariable BigDecimal quantity) {
        CurrencyConversion currencyConversion = service.getCurrencyConsersionFeign(from, to, quantity);
        currencyConversion.setPort(port);
        return currencyConversion;
    }
}
