package br.com.excelmanip.poi.converter;

import java.time.LocalDate;

import br.com.excelmanip.poi.converter.types.IntTypeConverter;
import br.com.excelmanip.poi.converter.types.LocalDateTypeConverter;

public class TypeConverterFactory {
	
	public static Object convert(String value, Class<?> clazz) {
		if (clazz.equals(String.class)) {
			return value;
		} else if (clazz.equals(Integer.class) || clazz.equals(int.class)) {
			return new IntTypeConverter(value).convert();
		} else if (clazz.equals(LocalDate.class)) {
			return new LocalDateTypeConverter(value).convert();
		}
		throw new RuntimeException("Not supported type: " + clazz);
	}
}
