package br.com.excelmanip.data;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.excelmanip.reader.column.ExcelColumn;
import lombok.Data;

@Data
public class Person {
	private String Nome;
	private String Endereço;
	private String Cep;
	@ExcelColumn(nonChars = false)
	private String Idade;
	private LocalDate Nascimento;
	private BigDecimal Salary;
	private String flagLetra;
}
