package mvc.observateur;

import java.util.ArrayList;

/******************************************************
 					Observable
 * Cours:  LOG121
 * Laboratoire: Laboratoire 3
 * @author Emerik Latour, Lucas Cimino, Philippe Tanguay-Gaudreau
 * @date 2022/04/12
 *******************************************************/

/**
 * Cette classe abstraite représente les objets observable du patron Observer.
 */
public abstract class Observable {
	private ArrayList<Observer> observers = new ArrayList<>();

	public void addObserver(Observer observer) {
		observers.add(observer);
	}

	public void removeObserver(Observer observer) {
		observers.remove(observer);
	}

	public void notifyObservers() {
		observers.forEach(Observer::update);
	}
}
