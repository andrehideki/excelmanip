package br.com.excelmanip.poi.converter.types;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class LongTypeConverter extends TypeConverter<Long> {

	public LongTypeConverter(String value) {
		super(value);
	}

	@Override
	public Long convert() {
		return value.isEmpty()? 0 : toLong(value);
	}
	
	public Long toLong(String value) {
		try {
			DecimalFormatSymbols symbols = new DecimalFormatSymbols();
			symbols.setDecimalSeparator('.');
			DecimalFormat format = new DecimalFormat("0.#");
			format.setDecimalFormatSymbols(symbols);
			return format.parse(value).longValue();
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}
