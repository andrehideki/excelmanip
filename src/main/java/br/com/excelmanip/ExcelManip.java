package br.com.excelmanip;

import br.com.excelmanip.reader.ExcelManipOut;
import br.com.excelmanip.reader.ExcelManipRead;
import br.com.excelmanip.reader.Reader;

public class ExcelManip {

	public static ExcelManipOut read(ExcelManipRead input) throws Exception {
		return Reader.read(input);
	}
}
