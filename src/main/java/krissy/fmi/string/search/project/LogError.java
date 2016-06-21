package krissy.fmi.string.search.project;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

public interface LogError {
	default boolean printInLogFile(Exception e) {
		String errorLogFileName = "errorLog.log";
		Writer writer = null;
		try {
			writer = new FileWriter(errorLogFileName, true);
		} catch (IOException innerExeption) {
			innerExeption.printStackTrace(new PrintWriter(new BufferedWriter(writer), true));
		}
		e.printStackTrace(new PrintWriter(new BufferedWriter(writer), true));
		return true;
	}
}