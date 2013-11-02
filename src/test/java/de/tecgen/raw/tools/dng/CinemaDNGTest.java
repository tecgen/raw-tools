package de.tecgen.raw.tools.dng;

import static org.junit.Assert.*;

import org.junit.Test;

public class CinemaDNGTest extends CinemaDNG {

	/**
	 * expected file format after conversation of *.RAW file with RawMagic 
	 */
	@Test
	public void getFrameIndexByFileName() {
		assertTrue(getFrameIndexByFileName("test_C0000_00001") == 1);
		assertTrue(getFrameIndexByFileName("test_C0000_00010") == 10);
		assertTrue(getFrameIndexByFileName("test_C0000_00100") == 100);
		assertTrue(getFrameIndexByFileName("test_C0000_01000") == 1000);
		assertTrue(getFrameIndexByFileName("test_C0000_10000") == 10000);
		assertTrue(getFrameIndexByFileName("test_C0000_99999") == 99999);
		
		// more digits 
		assertTrue(getFrameIndexByFileName("test_C0000_0010000") == 10000);
		assertTrue(getFrameIndexByFileName("test_C0000000_1010000") == 1010000);
	}
	
	@Test
	public void getFrameIndexByFileName_just_a_index() {
		getFrameIndexByFileName("00001");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void getFrameIndexByFileName_no_index() {
		getFrameIndexByFileName("test");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void getFrameIndexByFileName_no_index_but_separator() {
		getFrameIndexByFileName("test_");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void getFrameIndexByFileName_leading_index() {
		getFrameIndexByFileName("000134_test");
	}
	
	
	
}
