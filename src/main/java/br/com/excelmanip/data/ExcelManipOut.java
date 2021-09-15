package br.com.excelmanip.data;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExcelManipOut {
	private Header header;
	
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
	public static class Column {
		private String value;
		private int rowIndex;
		private int columnIndex;
	}
	
	
}
