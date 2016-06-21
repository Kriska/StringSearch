package krissy.fmi.string.search.project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.concurrent.BlockingQueue;
public class Producer extends Thread implements LogError {
	
	private StringStorage storage;
	private BlockingQueue<Path> files;
	public boolean finished;
	public Producer(StringStorage storage, BlockingQueue<Path> files){
		this.storage = storage;
		this.files = files;
	}
	
	public Producer() {
	}

	public StringStorage getStorage() {
		return storage;
	}

	public BlockingQueue<Path> getFiles() {
		return files;
	}

	@Override
	public void run() {
		String readLine;
		while(!files.isEmpty()) {
			Path currentPath = files.poll();
			if(currentPath == null) {
				return;
			}
			try (BufferedReader buffer = new BufferedReader(new FileReader(currentPath.toFile()))) {	
				int index = 0;
				while ((readLine = buffer.readLine()) != null) {
					index++;
					storage.store(new Product(readLine, currentPath, index));
				}
			} catch (IOException e) {
				printInLogFile(e);
			} 
		}
		storage.setFinishedStoring(true);
	}
}
