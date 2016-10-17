package com.spider.jira.contact.connector.core;

import java.util.Comparator;

import com.spider.jira.contact.connector.iface.Contact;

public class ContactComparator implements Comparator<Contact> {

	@Override
	public int compare(Contact contact1, Contact contact2) {
		if (contact1==null && contact2 == null) {
			return 0;
		}
		if (contact1==null) {
			return -1;
		}
		if (contact2==null) {
			return 1;
		}
		int idCompare = contact1.getId().compareToIgnoreCase(contact2.getId());
		int nameCompare = contact1.getName().compareToIgnoreCase(contact2.getName());
		int emailCompare = contact1.getEmail().compareToIgnoreCase(contact2.getEmail());
		int baseCompare = idCompare | nameCompare | emailCompare;
		return baseCompare;
	}

	
}
