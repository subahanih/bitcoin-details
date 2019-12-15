/**
 * File Name 		: BitcoinController.java 
 * Description 		: This Java class is used for provide Bitcoin current and historical details.
 * Author 			: Mahaboob Subahan J
 * Date 			: 12-Dec-2019
 * 
 * Version     Date           Modified By             Remarks
 * 0.1         12-Dec-2019    Mahaboob Subahan J      
 */
package com.mahaboob.bitcoindetails.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.mahaboob.bitcoindetails.exception.BitcoinException;
import com.mahaboob.bitcoindetails.serviceimpl.BitcoinServiceImpl;

@RestController
@RequestMapping("/bitcoin")
public class BitcoinController {

	@Autowired
	private BitcoinServiceImpl bitcoinServiceImpl;

	@RequestMapping(value = "/getBitcoinDetails", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody String getBitcoinDetails(String currency) throws BitcoinException, IOException {
		if (bitcoinServiceImpl.isValidCurrency(currency)) {
			ObjectMapper objectMapper = new ObjectMapper();
			Map<String, String> bitcoinDetails = new LinkedHashMap<String, String>();
			bitcoinDetails.put("Currency", currency);
			bitcoinDetails.put("Current Bitcoin rate", String.valueOf(bitcoinServiceImpl.getCurrentBitcoinRate(currency)));
			bitcoinDetails.put("Last 30 day's lowest Bitcoin rate USD", String.valueOf(bitcoinServiceImpl.getLast30DaysLowestBitcoinRate(currency)));
			bitcoinDetails.put("Last 30 day's highest Bitcoin rate USD", String.valueOf(bitcoinServiceImpl.getLast30DaysHighestBitcoinRate(currency)));
			return objectMapper.writeValueAsString(bitcoinDetails).toString();
		} else {
			return "Sorry, your requested currency " + currency + " is not supported or is invalid";
		}
	}

}
