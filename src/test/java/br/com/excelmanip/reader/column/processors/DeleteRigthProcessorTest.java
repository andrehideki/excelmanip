package br.com.excelmanip.reader.column.processors;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DeleteRigthProcessorTest {
	
	@Test
	public void should_delete_regex_from_rigth() {
		assertEquals("300", new DeleteFromRigthProcessor().process("300.0", "\\."));
		assertEquals("300", new DeleteFromRigthProcessor().process("300.300.0", "\\."));
	}
}
