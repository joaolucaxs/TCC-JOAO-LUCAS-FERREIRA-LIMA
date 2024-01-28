package com.joaolucas.mapp.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Utils {

	public static LocalDate stringToLocalDate(String data) {
		DateTimeFormatter formatterInput = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate dataFormatada = LocalDate.parse(data, formatterInput);
		return dataFormatada;
	}
	
	public static boolean stringToBoolean(String input) {
		String lowerCaseInput = input.toLowerCase();
		lowerCaseInput = lowerCaseInput.replaceAll("[áàâã]", "a");
		lowerCaseInput = lowerCaseInput.replaceAll("[éèê]", "e");
		lowerCaseInput = lowerCaseInput.replaceAll("[íìî]", "i");
		lowerCaseInput = lowerCaseInput.replaceAll("[óòôõ]", "o");
		lowerCaseInput = lowerCaseInput.replaceAll("[úùû]", "u");

		return lowerCaseInput.equals("sim");
	}
}
