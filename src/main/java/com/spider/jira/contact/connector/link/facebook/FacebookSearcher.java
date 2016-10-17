package com.spider.jira.contact.connector.link.facebook;

import java.util.HashMap;
import java.util.Map;

import com.restfb.FacebookClient;
import com.restfb.types.User;
import com.spider.jira.contact.connector.base.BaseContactInterface;
import com.spider.jira.contact.connector.base.BaseContactSearcher;
import com.spider.jira.contact.connector.exception.ContactNotFoundException;
import com.spider.jira.contact.connector.iface.Contact;
import com.spider.jira.contact.connector.iface.ContactReader;
import com.spider.jira.contact.connector.search.Criteria;

public class FacebookSearcher extends BaseContactSearcher<FacebookClient,User> {

	private ContactReader<FacebookClient,User> reader;

	public FacebookSearcher(BaseContactInterface<FacebookClient,User> contactInterface) {
		super(contactInterface);
		reader = contactInterface.getContactReader();
	}

	@Override
	public Contact findById(String id, boolean exact) throws Exception {
		Contact contact = reader.readContactById(id);
		if (contact != null) {
			return contact;
		}
		throw new ContactNotFoundException("ERROR: Contact not found in findById(). id=["+id+"]");
	}

	@Override
	public Contact findByName(String name, boolean exact) throws Exception {
		int startIndex = 0;
		// TODO: Implement Batch sizing
//		while (true) {
			Map<String,Contact> contacts = reader.readContactNames(startIndex, SEARCH_BATCH_SIZE_LARGE);
			try {
				Contact contact = findByName(name,contacts.values(),exact);
				if (contact != null) {
					contact = reader.readContactById(contact.getId());
					return contact;
				}
			} catch (ContactNotFoundException e) {
				// ignore for this batch
			}
			if (contacts.size()==0) {
//				break;
			}
			startIndex += SEARCH_BATCH_SIZE_LARGE;
//		}
		throw new ContactNotFoundException("ERROR: Contact not found in findByName(). name=["+name+"]");
	}

	@Override
	public Contact findByCriteria(Criteria criteria) throws Exception {
		// TODO: Implement Batch sizing
		int startIndex = 0;
//		while (true) {
			Map<String,Contact> contacts = reader.readContacts(startIndex, SEARCH_BATCH_SIZE_SMALL);
			try {
				Contact contact = findByCriteria(criteria,contacts.values());
				return contact;
			} catch (ContactNotFoundException e) {
				// ignore for this batch
			}
			if (contacts.size()==0) {
//				break;
			}
			startIndex += SEARCH_BATCH_SIZE_SMALL;
//		}
		throw new ContactNotFoundException("ERROR: Contact not found in findByCriteria(). criteria=["+criteria+"]");
	}

	@Override
	public Map<String,Contact> findAllByName(String name, boolean exact) throws Exception {
		Map<String,Contact> results = new HashMap<String, Contact>();
		int startIndex = 0;
//		while (true) {
			Map<String,Contact> contacts = reader.readContactNames(startIndex, SEARCH_BATCH_SIZE_LARGE);
			Map<String,Contact> tempResults = findAllByName(name,contacts.values(),exact);
			if (!tempResults.isEmpty()) {
				for (String contactId : tempResults.keySet()) {
					Contact contact = reader.readContactById(contactId);
					results.put(contactId,contact);
				}
			}
			if (contacts.size()==0) {
//				break;
			}
			startIndex += SEARCH_BATCH_SIZE_LARGE;
//		}
		return results;
	}

	@Override
	public Map<String,Contact> findAllByCriteria(Criteria criteria) throws Exception {
		Map<String,Contact> results = new HashMap<String, Contact>();
		int startIndex = 0;
//		while (true) {
			Map<String,Contact> contacts = reader.readContacts(startIndex, SEARCH_BATCH_SIZE_SMALL);
			Map<String,Contact> tempResults = findAllByCriteria(criteria,contacts.values());
			if (!tempResults.isEmpty()) {
				results.putAll(tempResults);
			}
			if (contacts.size()==0) {
//				break;
			}
			startIndex += SEARCH_BATCH_SIZE_SMALL;
//		}
		return results;
	}

}
