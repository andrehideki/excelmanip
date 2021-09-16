package br.com.excelmanip.data;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;

@Data
public class Person {
	private String Nome;
	private String Endereço;
	private String Cep;	
	private int Idade;
	private LocalDate Nascimento;
	private BigDecimal Salary;
	private String flagLetra;
}
