package com.javatpoint.microservices.currencyconversionsevice.feighnproxy;

import com.javatpoint.microservices.currencyconversionsevice.model.CurrencyConversion;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


//@FeignClient(name = "currency-zuul-api-gateway-service")
@FeignClient(name = "currency-zuul-api-gateway-service")
@RibbonClient(name = "currency-exchange-service")
public interface CurrencyConversionServiceProxy {
	//@GetMapping("/currency-exchange/from/{from}/to/{to}")
	@GetMapping("/currency-exchange/currency-exchange/from/{from}/to/{to}")
	public CurrencyConversion getExchangeValue(@PathVariable("from") String from, @PathVariable("to") String to);

}
