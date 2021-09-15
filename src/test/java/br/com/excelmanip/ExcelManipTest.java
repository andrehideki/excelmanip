package br.com.excelmanip;

import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

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
}
