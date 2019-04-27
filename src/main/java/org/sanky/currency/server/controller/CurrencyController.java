package org.sanky.currency.server.controller;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.sanky.currency.server.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@RestController
public class CurrencyController {

	@Autowired
	CurrencyService currencyService;
	
	@Value("${spring.datasource.url}")
  	private String dbUrl;

  	@Autowired
  	private DataSource dataSource;

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

	@Bean
	public DataSource dataSource() throws SQLException {
	  if (dbUrl == null || dbUrl.isEmpty()) {
		return new HikariDataSource();
	  } else {
		HikariConfig config = new HikariConfig();
		config.setJdbcUrl(dbUrl);
		return new HikariDataSource(config);
	  }
	}

}