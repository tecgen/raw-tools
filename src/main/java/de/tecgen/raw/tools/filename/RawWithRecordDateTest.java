package de.tecgen.raw.tools.filename;

import static org.junit.Assert.*;

import org.junit.Test;


public class RawWithRecordDateTest extends RawWithRecordDate {


	@Test
	public void getFileExtention() {
		assertTrue("RAW".equals(this.getFileExtention("M19-1845.RAW")));
		assertTrue("R01".equals(this.getFileExtention("M19-1845.R01")));
		assertTrue("MLV".equals(this.getFileExtention("M19-1850.MLV")));
	}
}
