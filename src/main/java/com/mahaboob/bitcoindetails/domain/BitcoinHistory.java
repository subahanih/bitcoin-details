/**
 * File Name 		: BitcoinHistory.java 
 * Description 		: This java class is used for BitcoinHistory domain.
 * Author 			: Mahaboob Subahan J
 * Date 			: 12-Dec-2019
 * 
 * Version     Date           Modified By             Remarks
 * 0.1         12-Dec-2019    Mahaboob Subahan J      
 */
package com.mahaboob.bitcoindetails.domain;

import java.io.Serializable;
import java.util.Map;

public class BitcoinHistory implements Serializable {
	
	private static final long serialVersionUID = 3268869474498931828L;

	private Map<String, Float> bpi;

	private String disclaimer;

	private Map<String, String> time;

	public Map<String, Float> getBpi() {
		return bpi;
	}

	public void setBpi(Map<String, Float> bpi) {
		this.bpi = bpi;
	}

	public String getDisclaimer() {
		return disclaimer;
	}

	public void setDisclaimer(String disclaimer) {
		this.disclaimer = disclaimer;
	}

	public Map<String, String> getTime() {
		return time;
	}

	public void setTime(Map<String, String> time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "BitcoinHistory [bpi=" + bpi + ", disclaimer=" + disclaimer + ", time=" + time + "]";
	}

}
