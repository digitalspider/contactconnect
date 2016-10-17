package com.spider.jira.contact.connector.base;

import com.spider.jira.contact.connector.iface.ContactConnection;
import com.spider.jira.contact.connector.iface.ContactFieldMapper;
import com.spider.jira.contact.connector.iface.ContactInterface;
import com.spider.jira.contact.connector.iface.ContactReader;
import com.spider.jira.contact.connector.iface.ContactSearcher;
import com.spider.jira.contact.connector.iface.ContactTransformer;
import com.spider.jira.contact.connector.iface.ContactWriter;

public class BaseContactInterface<ConnectionType,RawObjectType> implements ContactInterface<ConnectionType, RawObjectType> {

	public ContactConnection<ConnectionType,RawObjectType> contactConnection;
	public ContactReader<ConnectionType,RawObjectType> contactReader;
	public ContactWriter<ConnectionType,RawObjectType> contactWriter;
	public ContactTransformer<ConnectionType,RawObjectType> contactTransformer;
	public ContactFieldMapper<ConnectionType,RawObjectType> contactFieldMapper;
	public ContactSearcher<ConnectionType,RawObjectType> contactSearcher;

	public ContactConnection<ConnectionType,RawObjectType> getContactConnection() {
		return contactConnection;
	}
	public void setContactConnection(ContactConnection<ConnectionType,RawObjectType> contactConnection) {
		this.contactConnection = contactConnection;
	}
	public ContactReader<ConnectionType,RawObjectType> getContactReader() {
		return contactReader;
	}
	public void setContactReader(ContactReader<ConnectionType,RawObjectType> contactReader) {
		this.contactReader = contactReader;
	}
	public ContactWriter<ConnectionType,RawObjectType> getContactWriter() {
		return contactWriter;
	}
	public void setContactWriter(ContactWriter<ConnectionType,RawObjectType> contactWriter) {
		this.contactWriter = contactWriter;
	}
	public ContactTransformer<ConnectionType,RawObjectType> getContactTransformer() {
		return contactTransformer;
	}
	public void setContactTransformer(ContactTransformer<ConnectionType,RawObjectType> contactTransformer) {
		this.contactTransformer = contactTransformer;
	}
	public ContactFieldMapper<ConnectionType,RawObjectType> getContactFieldMapper() {
		return contactFieldMapper;
	}
	public void setContactFieldMapper(ContactFieldMapper<ConnectionType,RawObjectType> contactFieldMapper) {
		this.contactFieldMapper = contactFieldMapper;
	}
	public ContactSearcher<ConnectionType,RawObjectType> getContactSearcher() {
		return contactSearcher;
	}
	public void setContactSearcher(ContactSearcher<ConnectionType,RawObjectType> contactSearcher) {
		this.contactSearcher = contactSearcher;
	}
	
}
