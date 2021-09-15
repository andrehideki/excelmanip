package br.com.excelmanip.poi.converter.types;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class LocalDateTypeConverter extends TypeConverter<LocalDate> {

	public LocalDateTypeConverter(String value) {
		super(value);
	}

	@Override
	public LocalDate convert() {
		return !value.isEmpty() && value.matches("\\d{1,2}-[a-z]{3}-\\d{4}")? toLocalDate(value) : null;
	}
	
	public LocalDate toLocalDate(String value) {
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
			formatter = formatter.withLocale(Locale.getDefault());
			return LocalDate.parse(value, formatter);
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}
