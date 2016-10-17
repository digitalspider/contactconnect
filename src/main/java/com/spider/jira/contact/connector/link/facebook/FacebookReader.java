package com.spider.jira.contact.connector.link.facebook;

import java.util.HashMap;
import java.util.Map;

import com.restfb.Connection;
import com.restfb.FacebookClient;
import com.restfb.types.User;
import com.spider.jira.contact.connector.base.BaseContact;
import com.spider.jira.contact.connector.base.BaseContactInterface;
import com.spider.jira.contact.connector.base.BaseContactInterfacePresent;
import com.spider.jira.contact.connector.iface.Contact;
import com.spider.jira.contact.connector.iface.ContactReader;
import com.spider.jira.contact.connector.util.ConnectorConstants;

public class FacebookReader extends BaseContactInterfacePresent<FacebookClient,User> implements ContactReader<FacebookClient,User>, ConnectorConstants {

	public FacebookReader(BaseContactInterface<FacebookClient,User> contactInterface) {
		super(contactInterface);
	}

	@Override
	public Contact readContactById(String id) throws Exception {
		FacebookClient facebookClient = contactInterface.getContactConnection().getConnection();
		if (facebookClient != null) {
			User user = facebookClient.fetchObject(id, User.class);
			Contact contact = contactInterface.getContactTransformer().convertToContact(user);
			return contact;
		}
		return null;
	}
	

	@Override
	public Map<String, Contact> readContactNames(int startIndex, int maxResults)
			throws Exception {
		Map<String,Contact> contacts = new HashMap<String,Contact>();
		FacebookClient facebookClient = contactInterface.getContactConnection().getConnection();
		if (facebookClient != null) {
			String query = "me/friends";
			Connection<User> myFriends = facebookClient.fetchConnection(query, User.class);
			for (User user : myFriends.getData()) {
				Contact contact = new BaseContact();
				contact.setId(user.getId());
				contact.setName(user.getName());
				contacts.put(user.getId(), contact);
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
		FacebookClient facebookClient = contactInterface.getContactConnection().getConnection();
		if (facebookClient != null) {
			Map<String,Contact> contactNames = readContactNames(startIndex,maxResults);
			for (String id : contactNames.keySet()) {
				User user = facebookClient.fetchObject(id, User.class);
				Contact contact = contactInterface.getContactTransformer().convertToContact(user);
				contacts.put(user.getId(), contact);
				if (contacts.size() >= maxResults) {
					break;
				}
			}
		}
		return contacts;
	}
	
}
