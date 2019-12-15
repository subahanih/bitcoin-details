/**
 * File Name 		: BitcoinService.java 
 * Description 		: To handle BitcoinService interface wep page request.
 * Author 			: Mahaboob Subahan J
 * Date 			: 12-Dec-2019
 * 
 * Version     Date           Modified By             Remarks
 * 0.1         12-Dec-2019    Mahaboob Subahan J      
 */
package com.mahaboob.bitcoindetails.service;

import java.io.IOException;
import java.util.List;
import com.mahaboob.bitcoindetails.domain.SupportedCurrencies;
import com.mahaboob.bitcoindetails.exception.BitcoinException;

public interface BitcoinService {
	
	public float getCurrentBitcoinRate(String currency) throws BitcoinException, IOException;
	
	public float getLast30DaysHighestBitcoinRate(String currency) throws BitcoinException, IOException;
	
	public float getLast30DaysLowestBitcoinRate(String currency) throws BitcoinException, IOException;

	public List<SupportedCurrencies> getAllSupportedCurrencies() throws BitcoinException, IOException;
	
	public boolean isValidCurrency(String currency) throws BitcoinException, IOException;

}
