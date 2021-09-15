package br.com.excelmanip.data;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import br.com.excelmanip.poi.converter.TypeConverterFactory;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExcelManipOut {
	private Header header;
	private ExcelData excelData;
	
	public <T> List<T> toDTO(Class<T> dtoClass) {
		try {
			List<T> dtos = new ArrayList<>();
			for (ERow row: excelData.getRows()) {
				T dtoInstance = dtoClass.newInstance();
				for (Field field: dtoClass.getDeclaredFields()) {
					String columnValue = row.getValue(header.getColumn(field.getName()).getColumnIndex());
					field.setAccessible(true);
					Object fieldValue = TypeConverterFactory.convert(columnValue, field.getType());
					field.set(dtoInstance, fieldValue);
					field.setAccessible(false);
				}
				dtos.add(dtoInstance);
			}
			
			return dtos;	
		} catch (Exception e) {
			throw new RuntimeException("Failed to create DTO of " + dtoClass.getName(), e);
		}
	}
	
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
		
		public String getValue(int column) {
			return columns.stream().filter(c -> c.getColumnIndex() == column)
					.map(c -> c.getValue())
					.findFirst()
					.orElseThrow(() -> new RuntimeException("Column not found"));
		}
	}
}
