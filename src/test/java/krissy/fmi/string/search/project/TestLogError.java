package krissy.fmi.string.search.project;

import static org.junit.Assert.*;

import org.junit.Test;

import krissy.fmi.string.search.project.Producer;
import krissy.fmi.string.search.project.StringStorage;

public class TestLogError {
	@Test
	public void testStringStorageCreateLogFile() {

		StringStorage tester = new StringStorage();
		assertTrue(tester.printInLogFile(new Exception()));
	}
	
	@Test
	public void testProducerLogFile() {
		Producer tester = new Producer();
		assertTrue(tester.printInLogFile(new Exception()));
		
	}
}
