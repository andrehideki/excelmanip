package br.com.excelmanip.poi.converter.types;

import java.math.BigDecimal;

public class BigDecimalTypeConverter extends TypeConverter<BigDecimal> {

	public BigDecimalTypeConverter(String value) {
		super(value);
	}

	@Override
	public BigDecimal convert() {
		return value.isEmpty()? BigDecimal.ZERO : toBigDecimal(value);
	}
	
	public BigDecimal toBigDecimal(String value) {
		try {
			return new BigDecimal(value);
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}
