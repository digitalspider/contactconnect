package com.spider.jira.contact.connector.link.linkedin;

import java.util.HashMap;
import java.util.Map;

import com.google.code.linkedinapi.client.LinkedInApiClient;
import com.google.code.linkedinapi.schema.Connections;
import com.google.code.linkedinapi.schema.Person;
import com.spider.jira.contact.connector.base.BaseContact;
import com.spider.jira.contact.connector.base.BaseContactInterface;
import com.spider.jira.contact.connector.base.BaseContactInterfacePresent;
import com.spider.jira.contact.connector.iface.Contact;
import com.spider.jira.contact.connector.iface.ContactReader;
import com.spider.jira.contact.connector.util.ConnectorConstants;

public class LinkedInReader extends BaseContactInterfacePresent<LinkedInApiClient,Person> implements ContactReader<LinkedInApiClient,Person>, ConnectorConstants {

	public LinkedInReader(BaseContactInterface<LinkedInApiClient,Person> contactInterface) {
		super(contactInterface);
	}

	@Override
	public Contact readContactById(String id) throws Exception {
		LinkedInApiClient linkedInApiClient = contactInterface.getContactConnection().getConnection();
		if (linkedInApiClient != null) {
			Person person = linkedInApiClient.getProfileById(id);
			Contact contact = contactInterface.getContactTransformer().convertToContact(person);
			return contact;
		}
		return null;
	}
	

	@Override
	public Map<String, Contact> readContactNames(int startIndex, int maxResults)
			throws Exception {
		Map<String,Contact> contacts = new HashMap<String,Contact>();
		LinkedInApiClient linkedInApiClient = contactInterface.getContactConnection().getConnection();
		if (linkedInApiClient != null) {
			Connections myFriends = linkedInApiClient.getConnectionsForCurrentUser();
			for (Person person : myFriends.getPersonList()) {
				Contact contact = new BaseContact();
				contact.setId(person.getId());
				contact.setName(person.getFirstName() +" "+person.getLastName());
				contacts.put(person.getId(), contact);
				if (contacts.size() >= maxResults) {
					break;
				}
			}
		}
		return contacts;
	}
	
	@Override
	public Map<String,Contact> readContacts(int startIndex, int maxResults) throws Exception {
		Map<String,Contact> contacts = new HashMap<String,Contact>();
		LinkedInApiClient linkedInApiClient = contactInterface.getContactConnection().getConnection();
		if (linkedInApiClient != null) {
			Map<String,Contact> contactNames = readContactNames(startIndex,maxResults);
			for (String id : contactNames.keySet()) {
				Person person = linkedInApiClient.getProfileById(id);
				Contact contact = contactInterface.getContactTransformer().convertToContact(person);
				contacts.put(contact.getId(), contact);
				if (contacts.size() >= maxResults) {
					break;
				}
			}
		}
		return contacts;
	}
	
}
