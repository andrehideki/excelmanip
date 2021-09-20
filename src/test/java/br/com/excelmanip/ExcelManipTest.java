package br.com.excelmanip;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.nio.file.Paths;
import java.util.List;

import org.junit.jupiter.api.Test;

import br.com.excelmanip.data.Person;
import br.com.excelmanip.reader.ExcelManipOut;
import br.com.excelmanip.reader.ExcelManipRead;

public class ExcelManipTest {
	
	@Test
	public void should_read_a_xlsx() throws Exception {
		ExcelManip.read(ExcelManipRead.builder()
				.excelPath(Paths.get("src", "test", "resources", "persons.xlsx"))
				.headerLineIndex(0)
				.dataStartLineIndex(1)
				.sheetName("Planilha1")
				.build());
	}
	
	@Test
	public void should_read_from_xlsx_column_header() throws Exception {
		ExcelManipOut out = ExcelManip.read(ExcelManipRead.builder()
				.excelPath(Paths.get("src", "test", "resources", "persons.xlsx"))
				.headerLineIndex(0)
				.dataStartLineIndex(1)
				.sheetName("Planilha1")
				.build());
		assertEquals(out.getHeader().getColumn("Nome").getColumnIndex(), 0);
		assertEquals(out.getHeader().getColumn("Nascimento").getColumnIndex(), 4);
		assertEquals(out.getHeader().getSize(), 7);
	}
	
	@Test
	public void should_read_from_xlsx_columns_data() throws Exception {
		ExcelManipOut out = ExcelManip.read(ExcelManipRead.builder()
				.excelPath(Paths.get("src", "test", "resources", "persons.xlsx"))
				.headerLineIndex(0)
				.dataStartLineIndex(1)
				.sheetName("Planilha1")
				.build());
		assertEquals(out.getExcelData().getRowSize(), 3);
	}
	
	@Test
	public void should_read_from_xlsx_convert_exceldata_to_dto() throws Exception {
		ExcelManipOut out = ExcelManip.read(ExcelManipRead.builder()
				.excelPath(Paths.get("src", "test", "resources", "persons.xlsx"))
				.headerLineIndex(0)
				.dataStartLineIndex(1)
				.sheetName("Planilha1")
				.build());
		List<Person> persons = out.toDTO(Person.class);
		assertEquals(persons.size(), 3);
		assertEquals(persons.get(0).getFlagLetra(), "a");
	}
	
	@Test
	public void should_throw_error_when_file_not_exits() throws Exception {
		assertThrows(RuntimeException.class, () -> 
			ExcelManip.read(ExcelManipRead.builder()
					.excelPath(Paths.get("not_exists_excel.xlsx"))
					.build())
		);
	}
	
	@Test
	public void should_throw_error_when_sheet_not_exits() throws Exception {
		assertThrows(RuntimeException.class, () -> 
			ExcelManip.read(ExcelManipRead.builder()
					.excelPath(Paths.get("src", "test", "resources", "persons.xlsx"))
					.sheetName("NOTFOUND")
					.build())
				);
	}
}
