package krissy.fmi.string.search.project;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Searcher implements LogError {
	private Path startPath;
	private String searchString;
	
	public void perform(int p, int c) {
		StringStorage container = new StringStorage();
		BlockingQueue<Path> files = new LinkedBlockingQueue<>();
		putFilesInQueue(files, startPath);
		for(int i = 0 ; i < p ; i++) {
			handleToProducer(files, container);
		}
		for(int i = 0 ; i < c ; i++) {
			handleToConsumer(container, searchString);
		}
		// join all producers
		// set finishedStoring to true
		// join all consumers
	}
	
	private void handleToProducer(BlockingQueue<Path> files, StringStorage container) {
		Producer producer = new Producer(container, files);
		producer.start();
	}
	
	private void handleToConsumer(StringStorage container, String searchString) {
		Consumer consumer = new Consumer(container, searchString);
		consumer.start();
	}
	
	private void putFilesInQueue(BlockingQueue<Path> files, Path path) {
		File[] paths = path.toFile().listFiles();
		for (File file : paths) {
			if(file.isFile()) {
				files.add(file.toPath());
			}
			else if (file.isDirectory()) {
				putFilesInQueue(files, file.toPath());
			}
		}
	}
	
	public Path getStartPath() {
		return startPath;
	}
	
	public void setStartPath(Path startPath) {
		this.startPath = startPath;
	}
	
	public String getSearchString() {
		return searchString;
	}
	
	public void setSearchString(String searchedString) {
		this.searchString = searchedString;
	}
	
	public Searcher(Path startPath, String searchedString) {
		super();
		this.startPath = startPath;
		this.searchString = searchedString;
	}
}
