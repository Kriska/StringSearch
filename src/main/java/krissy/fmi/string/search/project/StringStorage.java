
package krissy.fmi.string.search.project;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StringStorage implements LogError {
	private List<Product> storedString;
	private boolean pauseWork;
	private boolean finishedStoring;
	
	public synchronized void store(Product current) {
		while (isPauseWork()) {
			try {
				wait();
			} catch (InterruptedException e) {
				printInLogFile(e);
			}
		}
		storedString.add(current);
		resumeWork();
		if (storedString.size() == 1000) {
			pauseWork();
		}
	}
	public synchronized Optional<Product> take() {
		if(storedString.isEmpty() && finishedStoring) {
			return Optional.empty();
		}
		while (storedString.isEmpty()) {
			try {
				wait();
			} catch (InterruptedException e) {
				printInLogFile(e);
			}
		}
			Product toReturn = storedString.get(0);
			storedString.remove(0);
			if (storedString.size() < 1000) {
				resumeWork();
			}
			return Optional.of(toReturn);
	}
	private synchronized void resumeWork() {
		setPauseWork(false);
		notifyAll();
	}
	
	private void pauseWork() {
		setPauseWork(true);
	}
	public boolean isPauseWork() {
		return pauseWork;
	}

	private void setPauseWork(boolean pauseWork) {
		this.pauseWork = pauseWork;
	}

	public StringStorage(){
		this.storedString = new ArrayList<>();
	}
	
	public StringStorage(List<Product> storedString) {
		super();
		this.storedString = storedString;
	}
	
	public List<Product> getStoredString() {
		return storedString;
	}
	
	public boolean isFinishedStoring() {
		return finishedStoring;
	}
	
	public void setFinishedStoring(boolean finishedStoring) {
		this.finishedStoring = finishedStoring;
	}
	
}