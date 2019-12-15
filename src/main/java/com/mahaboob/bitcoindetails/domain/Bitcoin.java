/**
 * File Name 		: Bitcoin.java 
 * Description 		: This java class is used for Bitcoin domain.
 * Author 			: Mahaboob Subahan J
 * Date 			: 12-Dec-2019
 * 
 * Version     Date           Modified By             Remarks
 * 0.1         12-Dec-2019    Mahaboob Subahan J      
 */
package com.mahaboob.bitcoindetails.domain;

import java.io.Serializable;
import java.util.Map;

public class Bitcoin implements Serializable{

	private static final long serialVersionUID = -4326813393860280363L;

	private Map<String, String> time;

	private String disclaimer;

	private Map<String, BitcoinDetails> bpi;

	public Map<String, String> getTime() {
		return time;
	}

	public void setTime(Map<String, String> time) {
		this.time = time;
	}

	public String getDisclaimer() {
		return disclaimer;
	}

	public void setDisclaimer(String disclaimer) {
		this.disclaimer = disclaimer;
	}

	public Map<String, BitcoinDetails> getBpi() {
		return bpi;
	}

	public void setBpi(Map<String, BitcoinDetails> bpi) {
		this.bpi = bpi;
	}

	@Override
	public String toString() {
		return "Bitcoin [time=" + time + ", disclaimer=" + disclaimer + ", bpi=" + bpi + "]";
	}

}
