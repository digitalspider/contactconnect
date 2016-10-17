package com.spider.jira.contact.connector.link.facebook;

import java.util.List;

import com.restfb.FacebookClient;
import com.restfb.types.User;
import com.spider.jira.contact.connector.base.BaseContactInterface;
import com.spider.jira.contact.connector.base.BaseContactInterfacePresent;
import com.spider.jira.contact.connector.iface.Contact;
import com.spider.jira.contact.connector.iface.ContactWriter;

public class FacebookWriter extends BaseContactInterfacePresent<FacebookClient,User> implements ContactWriter<FacebookClient,User> {

	public FacebookWriter(BaseContactInterface<FacebookClient,User> contactInterface) {
		super(contactInterface);
	}

	@Override
	public void writeContacts(List<Contact> contacts) {
		System.err.println("METHOD NOT YET IMPLEMENTED");
	}

}
