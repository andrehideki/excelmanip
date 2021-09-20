package br.com.excelmanip.reader.column.processors;

public class DeleteFromRigthProcessor {
	
	public String process(String value, String regex) {
		return value.split(regex)[0];
	}
}
