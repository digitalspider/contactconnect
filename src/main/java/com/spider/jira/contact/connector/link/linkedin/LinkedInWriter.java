package com.spider.jira.contact.connector.link.linkedin;

import java.util.List;

import com.google.code.linkedinapi.client.LinkedInApiClient;
import com.google.code.linkedinapi.schema.Person;
import com.spider.jira.contact.connector.base.BaseContactInterface;
import com.spider.jira.contact.connector.base.BaseContactInterfacePresent;
import com.spider.jira.contact.connector.iface.Contact;
import com.spider.jira.contact.connector.iface.ContactWriter;

public class LinkedInWriter extends BaseContactInterfacePresent<LinkedInApiClient,Person> implements ContactWriter<LinkedInApiClient,Person> {

	public LinkedInWriter(BaseContactInterface<LinkedInApiClient,Person> contactInterface) {
		super(contactInterface);
	}

	@Override
	public void writeContacts(List<Contact> contacts) {
		System.err.println("METHOD NOT YET IMPLEMENTED");
	}

}
