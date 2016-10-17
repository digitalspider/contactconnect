package com.spider.jira.contact.connector.iface;

import java.util.Collection;
import java.util.Map;

import com.spider.jira.contact.connector.search.Criteria;

public interface ContactSearcher<ConnectionType,RawObjectType> extends ContactInterfacePresent<ConnectionType,RawObjectType> {

	public void setExactSearch(boolean exactSearch);
	
	public Contact findById(String id) throws Exception;
	public Contact findById(String id, boolean exact) throws Exception;
	public Contact findByName(String name) throws Exception;
	public Contact findByName(String name, boolean exact) throws Exception;
	public Contact findByCriteria(Criteria criteria) throws Exception;

	public Contact findById(String id, Collection<Contact> contacts) throws Exception;
	public Contact findById(String id, Collection<Contact> contacts, boolean exact) throws Exception;
	public Contact findByName(String name, Collection<Contact> contacts) throws Exception;
	public Contact findByName(String name, Collection<Contact> contacts, boolean exact) throws Exception;
	public Contact findByCriteria(Criteria criteria, Collection<Contact> contacts) throws Exception;

	public Map<String,Contact> findAllByName(String name) throws Exception;
	public Map<String,Contact> findAllByName(String name, boolean exact) throws Exception;
	public Map<String,Contact> findAllByCriteria(Criteria criteria) throws Exception;

	public Map<String,Contact> findAllByName(String name, Collection<Contact> contacts) throws Exception;
	public Map<String,Contact> findAllByName(String name, Collection<Contact> contacts, boolean exact) throws Exception;
	public Map<String,Contact> findAllByCriteria(Criteria criteria, Collection<Contact> contacts) throws Exception;

}
