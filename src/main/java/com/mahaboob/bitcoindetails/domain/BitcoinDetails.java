/**
 * File Name 		: BitcoinDetails.java 
 * Description 		: This java class is used for BitcoinDetails domain.
 * Author 			: Mahaboob Subahan J
 * Date 			: 12-Dec-2019
 * 
 * Version     Date           Modified By             Remarks
 * 0.1         12-Dec-2019    Mahaboob Subahan J      
 */
package com.mahaboob.bitcoindetails.domain;

import java.io.Serializable;

public class BitcoinDetails implements Serializable{

	private static final long serialVersionUID = 1187768101902717586L;

	private String code;

	private String rate;

	private String description;

	private float rate_float;

	public BitcoinDetails() {

	}
	
	public BitcoinDetails(String code, String rate, String description, float rate_float) {
		this.code = code;
		this.rate = rate;
		this.description = description;
		this.rate_float = rate_float;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getRate_float() {
		return rate_float;
	}

	public void setRate_float(float rate_float) {
		this.rate_float = rate_float;
	}

	@Override
	public String toString() {
		return "BitcoinDetails [code=" + code + ", rate=" + rate + ", description=" + description + ", rate_float="
				+ rate_float + "]";
	}

}
