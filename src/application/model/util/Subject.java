package application.model.util;

import java.util.ArrayList;

import application.model.api.IObserver;
import application.model.api.ISubject;
/**
 * Knows its observers. Any number of Observer objects
 * may observe a subject. Provides an interface for attaching
 * and detaching Observer objects.
 */

public class Subject implements ISubject {

	private final ArrayList<IObserver> observers = new ArrayList<IObserver>();

	public void attach(IObserver observer) {
		observers.add(observer);
	}

	public void detach(IObserver observer) {
		observers.remove(observer);
	}

	public void notifyObservers() {
        for (IObserver observer : observers) (observer).update();
	}
}