package br.com.excelmanip.reader.column.processors;

import br.com.excelmanip.reader.column.ExcelColumn;

public class ExcelColumnProcessor {

	public String process(String value, ExcelColumn excelColumn) {
		if (excelColumn.removeNonChars()) value = new RemoveNonCharsProcessor().process(value);
		if (!excelColumn.deleteFromRight().isEmpty()) value = new RemoveNonCharsProcessor().process(value);
		return value;
	}
}
