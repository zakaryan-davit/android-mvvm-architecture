package com.example.davit_zakaryan.mvvmapp.util;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

@Singleton
public class RxBus {

	private PublishSubject<Object> subject;

	@Inject
	public RxBus() {
		this.subject = PublishSubject.create();
	}

	/**
	 * Pass any event down to event listeners.
	 */
	public void send(Object object) {
		subject.onNext(object);
	}

	/**
	 * Subscribe to this Observable. On event, do something
	 * e.g. replace a fragment
	 */
	public Observable<Object> getEvents() {
		return subject;
	}
}
