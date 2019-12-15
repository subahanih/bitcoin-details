/**
 * File Name 		: BitcoinServiceImpl.java 
 * Description 		: To handle BitcoinService interface wep page request.
 * Author 			: Mahaboob Subahan J
 * Date 			: 12-Dec-2019
 * 
 * Version     Date           Modified By             Remarks
 * 0.1         12-Dec-2019    Mahaboob Subahan J      
 */
package com.mahaboob.bitcoindetails.serviceimpl;

import static com.mahaboob.bitcoindetails.utils.DateUtils.getDateBefore;
import static com.mahaboob.bitcoindetails.utils.DateUtils.getcurrentdate;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.mahaboob.bitcoindetails.domain.Bitcoin;
import com.mahaboob.bitcoindetails.domain.BitcoinDetails;
import com.mahaboob.bitcoindetails.domain.BitcoinHistory;
import com.mahaboob.bitcoindetails.domain.SupportedCurrencies;
import com.mahaboob.bitcoindetails.exception.BitcoinException;
import com.mahaboob.bitcoindetails.service.BitcoinService;
import com.mahaboob.bitcoindetails.utils.BitcoinUtility;

@Service
public class BitcoinServiceImpl implements BitcoinService {

	@Autowired
	private BitcoinUtility bitcoinUtility;

	/**
	 * This method will fetch selected currency details from API
	 * https://api.coindesk.com/v1/bpi/currentprice/INR.json
	 * 
	 * @param String currency
	 * @return float getCurrentBitcoinRate
	 * @throws BitcoinException, IOException
	 */
	@Override
	@CacheEvict(value = "currentBitcoinRate", allEntries = true)
	public float getCurrentBitcoinRate(String currency) throws BitcoinException, IOException {
		List<BitcoinDetails> listBitcoinDetails = new ArrayList<>();
		if (isValidCurrency(currency)) {
			Bitcoin objBitcoin = new ObjectMapper().readValue(
					bitcoinUtility.callGetURL("https://api.coindesk.com/v1/bpi/currentprice/" + currency + ".json"),
					Bitcoin.class);
			Map<String, BitcoinDetails> mapBpi = objBitcoin.getBpi();
			mapBpi.forEach((k, v) -> {
				if (currency.equals(k)) {
					listBitcoinDetails
							.add(new BitcoinDetails(v.getCode(), v.getRate(), v.getDescription(), v.getRate_float()));
				}
			});
		}
		return listBitcoinDetails.get(0).getRate_float();
	}

	/**
	 * This method will fetch all supported currencies from API
	 * https://api.coindesk.com/v1/bpi/supported-currencies.json
	 * 
	 * @return List<SupportedCurrencies> getAllSupportedCurrencies
	 * @throws BitcoinException, IOException
	 */
	@Override
	@Cacheable(value = "supportedCurrencies")
	public List<SupportedCurrencies> getAllSupportedCurrencies() throws BitcoinException, IOException {
		SupportedCurrencies[] objSuppCurrencies = new ObjectMapper().readValue(
				bitcoinUtility.callGetURL("https://api.coindesk.com/v1/bpi/supported-currencies.json"),
				SupportedCurrencies[].class);
		return new ArrayList<>(Arrays.asList(objSuppCurrencies));
	}

	/**
	 * This method will return float value of highest Bitcoin rate.
	 * @param String currency
	 * @return float getHighestBitcoinRateOfSelectedDays
	 * @throws BitcoinException, IOException
	 */
	@Override
	@CacheEvict(value = "highestBitcoinRate", allEntries = true)
	public float getLast30DaysHighestBitcoinRate(String currency) throws BitcoinException, IOException {
		List<Float> listLast30DaysHighestBitcoinRate = getBitcoinRatesForLast30Days(currency);
		return listLast30DaysHighestBitcoinRate.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList()).get(0);
	}

	/**
	 * This method will return float value of lowest Bitcoin rate.
	 * @param String currency
	 * @return float getLast30DaysLowestBitcoinRate
	 * @throws BitcoinException, IOException
	 */
	@Override
	@CacheEvict(value = "lowestBitcoinRate", allEntries = true)
	public float getLast30DaysLowestBitcoinRate(String currency) throws BitcoinException, IOException {
		List<Float> listLast30DaysLowestBitcoinRate = getBitcoinRatesForLast30Days(currency);
		return listLast30DaysLowestBitcoinRate.stream().sorted().collect(Collectors.toList()).get(0);
	}

	/**
	 * This method will check the given currency is present in the supported-currencies
	 * (https://api.coindesk.com/v1/bpi/supported-currencies.json), if currency is
	 * present then it will return true, if not it will return false.
	 * 
	 * @param String currency
	 * @return boolean isValidCurrency
	 * @throws BitcoinException, IOException
	 */
	@Override
	@CacheEvict(value = "validCurrency", allEntries = true)
	public boolean isValidCurrency(String currency) throws BitcoinException, IOException {
		if (getAllSupportedCurrencies().stream().map(SupportedCurrencies::getCurrency).filter(currency::equals)
				.findAny().isPresent()) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * This method will return list of 30 days history of Bitcoin rates from
	 * https://api.coindesk.com/v1/bpi/historical/close.json?start=<startDate>&end=<endDate>
	 * @param currency
	 * @return List<Float> getBitcoinRatesForLast30Days
	 * @throws BitcoinException, IOException
	 */
	private List<Float> getBitcoinRatesForLast30Days(String currency) throws BitcoinException, IOException {
		String startDate = getDateBefore(30).toString();
		String endDate = getcurrentdate().toString();
		List<Float> listBitcoin = new ArrayList<>();
		if (isValidCurrency(currency)) {
			BitcoinHistory bitcoinHistory = new ObjectMapper().readValue(bitcoinUtility.callGetURL(
				"https://api.coindesk.com/v1/bpi/historical/close.json?start=" + startDate + "&end=" + endDate),
				BitcoinHistory.class);
			bitcoinHistory.getBpi().forEach((k, v) -> {
				listBitcoin.add(v);
			});
		}

		return listBitcoin;
	}

}
