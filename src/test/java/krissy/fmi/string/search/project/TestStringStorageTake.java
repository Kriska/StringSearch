package krissy.fmi.string.search.project;

import java.io.File;
import java.util.Optional;

import static org.junit.Assert.*;
import org.junit.Test;

import krissy.fmi.string.search.project.Product;
import krissy.fmi.string.search.project.StringStorage;

public class TestStringStorageTake {

	@Test
	public void testTake() {
		StringStorage tester = new StringStorage();
		Product testProduct = new Product("testString", new File("testFile").toPath(), 1);
		tester.getStoredString().add(testProduct);
		Optional<Product> result = tester.take();
		assertEquals(testProduct, result.get());;
	}
	@Test
	public void testTakeBelow1000() {
		StringStorage tester = new StringStorage();
		tester.getStoredString().add(new Product("testString", new File("testFile").toPath(), 1));
		tester.take();
		assertFalse(tester.isPauseWork());
	}
	@Test
	public void testTakeFinishedStoring() {
		StringStorage storage = new StringStorage();
		storage.setFinishedStoring(true);
		Optional<Product> testResult = storage.take();
		assertEquals(Optional.empty(), testResult);
	}
}
