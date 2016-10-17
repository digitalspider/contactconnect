package com.spider.jira.contact.connector.exception;

public class ContactTransformException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public ContactTransformException(String message) {
		super(message);
	}

	public ContactTransformException(Exception e) {
		super(e);
	}
	
	public ContactTransformException(String message, Exception e) {
		super(message,e);
	}
}
