package com.spider.jira.contact.connector.exception;

public class ContactNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public ContactNotFoundException(String message) {
		super(message);
	}
	
	public ContactNotFoundException(Exception e) {
		super(e);
	}
	
	public ContactNotFoundException(String message, Exception e) {
		super(message,e);
	}
}
