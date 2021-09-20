package br.com.excelmanip.reader.column.processors;

public class RemoveNonCharsProcessor {
	
	public String process(String value) {
		return value.replaceAll("\\W", "");
	}
}
