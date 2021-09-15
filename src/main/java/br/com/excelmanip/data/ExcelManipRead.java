package br.com.excelmanip.data;

import java.nio.file.Path;

import lombok.Builder;
import lombok.Data;

@Data
public class ExcelManipRead {
	private Path excelPath;
	private String sheetName;
	private int headerLineIndex;
	private int dataStartLineIndex;

	@Builder
	public ExcelManipRead(Path excelPath, String sheetName, int headerLineIndex, int dataStartLineIndex) {
		this.excelPath = excelPath;
		this.sheetName = sheetName;
		this.headerLineIndex = headerLineIndex;
		this.dataStartLineIndex = dataStartLineIndex;
	}
	
	@Builder
	public ExcelManipRead(Path excelPath, String sheetName) {
		this.excelPath = excelPath;
		this.sheetName = sheetName;
		this.headerLineIndex = 0;
		this.dataStartLineIndex = 1;
	}
}
