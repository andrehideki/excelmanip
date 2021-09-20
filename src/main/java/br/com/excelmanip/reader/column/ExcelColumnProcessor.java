package br.com.excelmanip.reader.column;

public class ExcelColumnProcessor {

	public String process(String value, ExcelColumn excelColumn) {
		value = value.replaceAll("\\W", "");
		return value;
	}
}
