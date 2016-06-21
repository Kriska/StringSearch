package krissy.fmi.string.search.project;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import krissy.fmi.string.search.project.Product;
import krissy.fmi.string.search.project.StringStorage;

public class TestStringStorageStore {
	@Test
	public void testStoreCase1000() 
	{
		StringStorage tester = new StringStorage();
		for(int i = 0; i < 1000; i++) {
			tester.store(new Product());
		}
		assertTrue(tester.isPauseWork());
	}
	@Test
	public void testStore() 
	{
		StringStorage tester = new StringStorage();
		tester.store(new Product("testString", new File("testFile").toPath(), 1));
		assertNotNull(tester.getStoredString());
	}
}
