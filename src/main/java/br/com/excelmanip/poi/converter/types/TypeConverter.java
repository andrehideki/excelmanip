package br.com.excelmanip.poi.converter.types;

import lombok.Data;

@Data
public abstract class TypeConverter<T> {

	protected String value;
	
	public TypeConverter(String value) {
		super();
		this.value = value != null? value : "";
	}
	
	public abstract T convert();
}
