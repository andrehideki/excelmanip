package br.com.excelmanip.data;

import java.time.LocalDate;

import lombok.Data;

@Data
public class Person {
	private String Nome;
	private String Endere�o;
	private String Cep;	
	private int Idade;
	private LocalDate Nascimento;
}
