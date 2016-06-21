package krissy.fmi.string.search.project;

import java.nio.file.Path;

public class Product {
	String line;
	Path filePath;
	int lineNumber;
	public Product(String line, Path filePath, int lineNumber) {
		super();
		this.line = line;
		this.filePath = filePath;
		this.lineNumber = lineNumber;
	}
	public Product() {
	}
	public String getLine() {
		return line;
	}
	public Path getFilePath() {
		return filePath;
	}
	public int getLineNumber() {
		return lineNumber;
	}
	@Override
	public String toString() {
		return filePath.toFile().getName() + " " + lineNumber + "\n" + line;
	}
}
