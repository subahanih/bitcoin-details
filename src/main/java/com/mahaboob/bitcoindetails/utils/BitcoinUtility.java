/**
 * File Name 		: BitcoinUtility.java 
 * Description 		: This java class is used for HttpURLConnection .
 * Author 			: Mahaboob Subahan J
 * Date 			: 13-Dec-2019
 * 
 * Version     Date           Modified By             Remarks
 * 0.1         13-Dec-2019    Mahaboob Subahan J      
 */
package com.mahaboob.bitcoindetails.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import org.springframework.stereotype.Component;
import com.mahaboob.bitcoindetails.exception.BitcoinException;


@Component
public class BitcoinUtility {

	public String callGetURL(String myURL) throws BitcoinException, IOException {
		URL url = new URL(myURL);
		StringBuilder result = new StringBuilder();
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept", "application/json");
		if (conn.getResponseCode() != 200) {
			throw new BitcoinException("Failed : HTTP Error code : " + conn.getResponseCode());
		}
		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(conn.getInputStream(), Charset.defaultCharset()));
		if (bufferedReader != null) {
			int cp;
			while ((cp = bufferedReader.read()) != -1) {
				result.append((char) cp);
			}
			bufferedReader.close();
		}
		conn.disconnect();

		return result.toString();
	}

}
