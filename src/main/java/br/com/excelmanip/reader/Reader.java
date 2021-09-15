package br.com.excelmanip.reader;

import static java.util.stream.StreamSupport.stream;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import br.com.excelmanip.reader.ExcelManipOut.Column;
import br.com.excelmanip.reader.ExcelManipOut.ERow;
import br.com.excelmanip.reader.ExcelManipOut.ExcelData;
import br.com.excelmanip.reader.ExcelManipOut.Header;

public class Reader {
	
	public static ExcelManipOut read(ExcelManipRead input) throws Exception {
		validate(input);
		Path excelPath = input.getExcelPath();
		Workbook workbook = WorkbookFactory.create(excelPath.toFile());
		Sheet sheet = workbook.getSheet(input.getSheetName());
		ExcelManipOut excelManipOut = ExcelManipOut.builder()
					.header(getHeader(sheet, input.getHeaderLineIndex()))
					.excelData(getExcelData(sheet, input.getDataStartLineIndex()))
					.build();
		workbook.close();
		return excelManipOut;
	}
	
	public static void validate(ExcelManipRead input) {
		try {
			if (!Files.exists(input.getExcelPath())) throw new RuntimeException(String.format("File not found", input.getExcelPath().toString()));
			Workbook workbook = WorkbookFactory.create(input.getExcelPath().toFile());
			workbook.close();
			if (workbook.getSheet(input.getSheetName()) == null) throw new RuntimeException(String.format("Sheet not found", input.getExcelPath().toString()));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static Header getHeader(Sheet sheet, int headerLineIndex) {
		Row headerRow = sheet.getRow(headerLineIndex);
		List<Column> columns = new ArrayList<>();  
		for (Cell cell: headerRow) {
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
	
	public static ExcelData getExcelData(Sheet sheet, int dataStartLineIndex) {
		List<ERow> dataRows = stream(sheet.spliterator(), false).skip(dataStartLineIndex)
			.map(row -> {
				List<Column> columns = stream(row.spliterator(), false).map(cell -> {
					return Column.builder()
								.value(cell.toString().trim())
								.columnIndex(cell.getColumnIndex())
								.rowIndex(cell.getRowIndex())
								.build();
				})
				.collect(Collectors.toList());
				return ERow.builder()
							.rowIndex(row.getRowNum())
							.columns(columns)
							.build();
			})
			.collect(Collectors.toList());
		return ExcelData.builder()
				.rows(dataRows)
				.rowSize(dataRows.size())
				.build();
	}
	
}
