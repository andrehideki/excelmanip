package br.com.excelmanip;

import java.nio.file.Files;
import java.nio.file.Path;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import br.com.excelmanip.data.ExcelManipRead;

public class ExcelManip {

	public static void read(ExcelManipRead input) throws Exception {
		validate(input);
		Path excelPath = input.getExcelPath();
		String sheetName = input.getSheetName();
		Workbook workbook = WorkbookFactory.create(excelPath.toFile());
		Sheet sheet = workbook.getSheet(sheetName);
	}
	
	public static void validate(ExcelManipRead input) {
		if (!Files.exists(input.getExcelPath())) throw new RuntimeException(String.format("File not exists", input.getExcelPath().toString()));
	}
}
