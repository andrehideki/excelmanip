package br.com.excelmanip;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import br.com.excelmanip.data.ExcelManipOut;
import br.com.excelmanip.data.ExcelManipOut.Column;
import br.com.excelmanip.data.ExcelManipOut.Header;
import br.com.excelmanip.data.ExcelManipRead;

public class ExcelManip {

	public static ExcelManipOut read(ExcelManipRead input) throws Exception {
		validate(input);
		Path excelPath = input.getExcelPath();
		Workbook workbook = WorkbookFactory.create(excelPath.toFile());
		Sheet sheet = workbook.getSheet(input.getSheetName());
		return ExcelManipOut.builder()
					.header(getHeader(sheet, input.getHeaderLineIndex()))
					.build();
	}
	
	public static Header getHeader(Sheet sheet, int headerLineIndex) {
		Row headerRow = sheet.getRow(headerLineIndex);
		Iterator<Cell> cellIterator = headerRow.cellIterator();
		List<Column> columns = new ArrayList<>();  
		while (cellIterator.hasNext()) {
			Cell cell = cellIterator.next();
			columns.add(Column.builder()
							.value(cell.toString().trim())
							.columnIndex(cell.getColumnIndex())
							.rowIndex(cell.getRowIndex())
							.build());
		}
		return Header.builder()
				.columns(columns)
				.size(headerRow.getPhysicalNumberOfCells())
				.build();
	}
	
	public static void validate(ExcelManipRead input) {
		if (!Files.exists(input.getExcelPath())) throw new RuntimeException(String.format("File not exists", input.getExcelPath().toString()));
	}
}
