package com.spider.jira.contact.connector.iface;

import com.spider.jira.contact.connector.iface.ContactConnection;
import com.spider.jira.contact.connector.iface.ContactFieldMapper;
import com.spider.jira.contact.connector.iface.ContactReader;
import com.spider.jira.contact.connector.iface.ContactSearcher;
import com.spider.jira.contact.connector.iface.ContactTransformer;
import com.spider.jira.contact.connector.iface.ContactWriter;

public interface ContactInterface<ConnectionType,RawObjectType> {

	public ContactConnection<ConnectionType,RawObjectType> getContactConnection();
	public void setContactConnection(ContactConnection<ConnectionType,RawObjectType> contactConnection);

	public ContactReader<ConnectionType,RawObjectType> getContactReader();
	public void setContactReader(ContactReader<ConnectionType,RawObjectType> contactReader);
	
	public ContactWriter<ConnectionType,RawObjectType> getContactWriter();
	public void setContactWriter(ContactWriter<ConnectionType,RawObjectType> contactWriter);
	
	public ContactTransformer<ConnectionType,RawObjectType> getContactTransformer();
	public void setContactTransformer(ContactTransformer<ConnectionType,RawObjectType> contactTransformer);
	
	public ContactFieldMapper<ConnectionType,RawObjectType> getContactFieldMapper();
	public void setContactFieldMapper(ContactFieldMapper<ConnectionType,RawObjectType> contactFieldMapper);
	
	public ContactSearcher<ConnectionType,RawObjectType> getContactSearcher();
	public void setContactSearcher(ContactSearcher<ConnectionType,RawObjectType> contactSearcher);
	
}
