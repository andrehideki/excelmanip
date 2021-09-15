package br.com.excelmanip;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import br.com.excelmanip.data.ExcelManipOut;
import br.com.excelmanip.data.ExcelManipRead;

public class ExcelManipTest {

	/* Input:
		 * Path
		 * Sheet name
		 * Header index
		 * Data start index
	 * Output:
	 	* Success 	
		 	* Header
		 	* Campos
	 	* Failure
	 		* Error message  	
	 * */
	
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
		assertEquals(out.getHeader().getSize(), 5);
	}
	
	@Test
	public void should_throw_error_when_file_not_exits() throws Exception {
		assertThrows(RuntimeException.class, () -> 
			ExcelManip.read(ExcelManipRead.builder()
					.excelPath(Paths.get("not_exists_excel.xlsx"))
					.build())
		);
	}
}
