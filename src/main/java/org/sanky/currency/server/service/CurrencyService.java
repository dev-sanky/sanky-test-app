package org.sanky.currency.server.service;

import org.springframework.stereotype.Service;

@Service
public class CurrencyService {
	public String getLatestExchangeRates(String baseCurrencyCode){
		return baseCurrencyCode+" to all currency rates";
	}
	
	public boolean isValidApplicationId(String applicationId) {
		return true;
	}
}
