package com.spider.jira.contact.connector.iface;

import com.spider.jira.contact.connector.data.FieldName;


public interface ContactFieldMapper<ConnectionType,RawObjectType> extends ContactInterfacePresent<ConnectionType,RawObjectType> {
	
	public void init(String data) throws Exception;
	
	public void mapField(Contact contact, FieldName fieldId, Object value) throws Exception;
	
}
