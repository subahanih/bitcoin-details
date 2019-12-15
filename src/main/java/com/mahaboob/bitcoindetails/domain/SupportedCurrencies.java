/**
 * File Name 		: SupportedCurrencies.java 
 * Description 		: This java class is used for SupportedCurrencies domain.
 * Author 			: Mahaboob Subahan J
 * Date 			: 12-Dec-2019
 * 
 * Version     Date           Modified By             Remarks
 * 0.1         12-Dec-2019    Mahaboob Subahan J      
 */
package com.mahaboob.bitcoindetails.domain;

import java.io.Serializable;

public class SupportedCurrencies implements Serializable{

	private static final long serialVersionUID = 1799958560027211999L;

	private String currency;

	private String country;

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "SupportedCurrencies [currency=" + currency + ", country=" + country + "]";
	}

}
