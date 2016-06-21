package krissy.fmi.string.search.project;

import java.util.Optional;

public class Consumer extends Thread {
	
	private StringStorage storage;
	private String searchedString;
	
	public StringStorage getStorage() {
		return storage;
	}
	public String getSearchedString() {
		return searchedString;
	}
	public Consumer(StringStorage storage, String searchedString) {
		super();
		this.storage = storage;
		this.searchedString = searchedString;
	}
	@Override
	public void run() {
		while(true) {
			Optional<Product> current = storage.take();
			if(!current.isPresent()) {
				return;
			}
			if(current.get().getLine().contains(searchedString)) {
				System.out.println(current.get().toString());
			}
		} 
	}
}
