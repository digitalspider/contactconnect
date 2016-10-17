package com.spider.jira.contact.connector.base;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.spider.jira.contact.connector.exception.ContactNotFoundException;
import com.spider.jira.contact.connector.iface.Contact;
import com.spider.jira.contact.connector.iface.ContactSearcher;
import com.spider.jira.contact.connector.search.Criteria;
import com.spider.jira.contact.connector.util.ConnectorConstants;

public abstract class BaseContactSearcher<ConnectionType, RawObjectType> extends BaseContactInterfacePresent<ConnectionType,RawObjectType> implements ContactSearcher<ConnectionType, RawObjectType>, ConnectorConstants {

	private boolean exactSearch = true;
	
	public BaseContactSearcher(BaseContactInterface<ConnectionType, RawObjectType> contactInterface) {
		super(contactInterface);
	}
	
	@Override
	public void setExactSearch(boolean exactSearch) {
		this.exactSearch = exactSearch;
	}

	@Override
	public Contact findById(String id) throws Exception {
		return findById(id,exactSearch);
	}

	@Override
	public Contact findById(String id, Collection<Contact> contacts) throws Exception {
		return findById(id,contacts,exactSearch);
	}

	@Override
	public Contact findByName(String name) throws Exception {
		return findByName(name,exactSearch);
	}


	@Override
	public Contact findByName(String name, Collection<Contact> contacts) throws Exception {
		return findByName(name,contacts,exactSearch);
	}
	
	@Override
	public Contact findById(String id, Collection<Contact> contacts, boolean exact) throws ContactNotFoundException {
		for (Contact contact : contacts) {
			if (id.equals(contact.getId())) {
				return contact;
			}
			if (!exact && contact.getId().startsWith(id)) {
				return contact;
			}
		}
		throw new ContactNotFoundException("ERROR: Contact not found in findById(). id=["+id+"]");
	}

	@Override
	public Contact findByName(String name, Collection<Contact> contacts, boolean exact) throws ContactNotFoundException {
		for (Contact contact : contacts) {
			if (name.equalsIgnoreCase(contact.getName())) {
				return contact;
			}
			if (!exact && contact.getName().toLowerCase().contains(name.toLowerCase())) {
				return contact;
			}
		}
		throw new ContactNotFoundException("ERROR: Contact not found in findByName(). name=["+name+"]");
	}
	
	@Override
	public Contact findByCriteria(Criteria criteria, Collection<Contact> contacts) throws Exception {
		for (Contact contact : contacts) {
			if (criteria.evaluate(contact)) {
				return contact;
			}
		}
		throw new ContactNotFoundException("ERROR: Contact not found in findByCriteria(). criteria=["+criteria+"]");
	}

	@Override
	public Map<String,Contact> findAllByName(String name) throws Exception {
		return findAllByName(name,exactSearch);
	}

	@Override
	public Map<String,Contact> findAllByName(String name, Collection<Contact> contacts) throws Exception {
		return findAllByName(name,contacts,exactSearch);
	}
	

	@Override
	public Map<String,Contact> findAllByName(String name, Collection<Contact> contacts, boolean exact) {
		Map<String,Contact> results = new HashMap<String, Contact>();
		for (Contact contact : contacts) {
			if (name.equalsIgnoreCase(contact.getName())) {
				results.put(contact.getId(),contact);
			} else if (!exact && contact.getName().toLowerCase().contains(name.toLowerCase())) {
				results.put(contact.getId(),contact);
			}
		}
		return results;
	}

	@Override
	public Map<String,Contact> findAllByCriteria(Criteria criteria, Collection<Contact> contacts) throws Exception {
		Map<String,Contact> results = new HashMap<String, Contact>();
		for (Contact contact : contacts) {
			if (criteria.evaluate(contact)) {
				results.put(contact.getId(),contact);
			}
		}
		return results;
	}

}
