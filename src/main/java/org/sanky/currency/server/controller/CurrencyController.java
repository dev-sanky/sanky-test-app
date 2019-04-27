package org.sanky.currency.server.controller;

import org.sanky.currency.server.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyController {

	@Autowired
	CurrencyService currencyService;
	
	private static final String DEFAULT_BASE_CURRENCY_CODE = "USD";
	
	@GetMapping("/currency/base/{baseCurrencyCode}")
	public String getLatestExchangeRates(@PathVariable String baseCurrencyCode){
		
		//if(currencyService.isValidApplicationId(applicationId)) {
			if(baseCurrencyCode != null) {
				return currencyService.getLatestExchangeRates(baseCurrencyCode);
			} else {
				return currencyService.getLatestExchangeRates(DEFAULT_BASE_CURRENCY_CODE);
			}
		//} else {
		//	return null;
		//}
	}
}