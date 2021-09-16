package br.com.excelmanip.poi.converter;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.excelmanip.poi.converter.types.BigDecimalTypeConverter;
import br.com.excelmanip.poi.converter.types.IntTypeConverter;
import br.com.excelmanip.poi.converter.types.LocalDateTypeConverter;
import br.com.excelmanip.poi.converter.types.LongTypeConverter;

public class TypeConverterFacade {
	
	public static Object convert(String value, Class<?> clazz) {
		if (clazz.equals(String.class)) {
			return value;
		} else if (clazz.equals(Integer.class) || clazz.equals(int.class)) {
			return new IntTypeConverter(value).convert();
		} else if (clazz.equals(LocalDate.class)) {
			return new LocalDateTypeConverter(value).convert();
		} else if (clazz.equals(BigDecimal.class)) {
			return new BigDecimalTypeConverter(value).convert();
		} else if (clazz.equals(Long.class)) {
			return new LongTypeConverter(value).convert();
		}
		throw new RuntimeException("Not supported type: " + clazz);
	}
}
