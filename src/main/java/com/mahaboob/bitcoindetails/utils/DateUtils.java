/**
 * File Name 		: DateUtils.java 
 * Description 		: This java class is used for Date & Time format.
 * Author 			: Mahaboob Subahan J
 * Date 			: 13-Dec-2019
 * 
 * Version     Date           Modified By             Remarks
 * 0.1         13-Dec-2019    Mahaboob Subahan J      
 */
package com.mahaboob.bitcoindetails.utils;

import java.time.LocalDate;

public class DateUtils {

	public static LocalDate getcurrentdate() {
		return LocalDate.now();
	}

	public static LocalDate getDateBefore(int numOfDays) {
		LocalDate localDate = LocalDate.now();
		return localDate.minusDays(numOfDays);
	}

}
