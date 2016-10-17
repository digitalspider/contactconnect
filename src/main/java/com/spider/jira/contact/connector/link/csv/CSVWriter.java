package com.spider.jira.contact.connector.link.csv;

import java.io.File;
import java.util.List;

import com.spider.jira.contact.connector.base.BaseContactInterfacePresent;
import com.spider.jira.contact.connector.base.BaseContactInterface;
import com.spider.jira.contact.connector.iface.Contact;
import com.spider.jira.contact.connector.iface.ContactWriter;

public class CSVWriter extends BaseContactInterfacePresent<File,String> implements ContactWriter<File,String> {

	public CSVWriter(BaseContactInterface<File,String> contactInterface) {
		super(contactInterface);
	}

	@Override
	public void writeContacts(List<Contact> contacts) {
		System.err.println("METHOD NOT YET IMPLEMENTED");
	}

}
