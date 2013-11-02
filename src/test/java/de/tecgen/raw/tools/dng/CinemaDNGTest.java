package de.tecgen.raw.tools.dng;

import static org.junit.Assert.*;

import org.junit.Test;

public class CinemaDNGTest extends CinemaDNG {

	
	@Test
	public void getFrameIndexByFileName() {
		assertTrue(getFrameIndexByFileName("test_000001") == 1);
		assertTrue(getFrameIndexByFileName("test_000010") == 10);
		assertTrue(getFrameIndexByFileName("test_000100") == 100);
		assertTrue(getFrameIndexByFileName("test_001000") == 1000);
		assertTrue(getFrameIndexByFileName("test_010000") == 10000);
		assertTrue(getFrameIndexByFileName("test_100000") == 100000);
		
		
	}
}
