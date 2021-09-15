package br.com.excelmanip.poi.converter.types;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class IntTypeConverter extends TypeConverter<Integer> {

	public IntTypeConverter(String value) {
		super(value);
	}

	@Override
	public Integer convert() {
		return value.isEmpty()? 0 : toInteger(value);
	}
	
	public Integer toInteger(String value) {
		try {
			DecimalFormatSymbols symbols = new DecimalFormatSymbols();
			symbols.setDecimalSeparator('.');
			DecimalFormat format = new DecimalFormat("0.#");
			format.setDecimalFormatSymbols(symbols);
			return format.parse(value).intValue();
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}
