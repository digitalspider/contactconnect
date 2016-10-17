package com.spider.jira.contact.connector.bean;


public class ContactKey {

	private String id;
	private String name;

	public ContactKey(String id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public String toString() {
		return "ContactKey["+id+"] "+name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
