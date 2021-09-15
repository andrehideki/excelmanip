package br.com.excelmanip.data;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExcelManipOut {
	private Header header;
	private ExcelData excelData;
	
	@Data
	@Builder
	public static class Header {
		private List<Column> columns;
		private int size;
		
		public Column getColumn(String headerName) {
			return columns.stream().filter(c -> c.value.equals(headerName))
						.findFirst()
						.orElseThrow(() -> new RuntimeException("Column not found"));
		}
	}
	
	@Data
	@Builder
	public static class ExcelData {
		private List<ERow> rows;
		private int rowSize;
	}
	
	@Data
	@Builder
	public static class Column {
		private String value;
		private int rowIndex;
		private int columnIndex;
	}
	
	@Data
	@Builder
	public static class ERow {
		private int rowIndex;
		private List<Column> columns;
	}
}
