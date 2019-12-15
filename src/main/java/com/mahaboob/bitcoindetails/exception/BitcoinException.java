/**
 * File Name 		: BitcoinException.java 
 * Description 		: This Java class is used for BitcoinException Exception handling.
 * Author 			: Mahaboob Subahan J
 * Date 			: 12-Dec-2019
 * 
 * Version     Date           Modified By             Remarks
 * 0.1         12-Dec-2019    Mahaboob Subahan J      
 */
package com.mahaboob.bitcoindetails.exception;

public class BitcoinException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public BitcoinException() {
		super();
	}
	
	public BitcoinException(String message) {
		super(message);
	}
	
	public BitcoinException(Throwable throwable) {
		super(throwable);
	}
	
	public BitcoinException(String message, Throwable throwable) {
		super(message, throwable);
	}

}
