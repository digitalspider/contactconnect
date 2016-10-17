package com.spider.jira.contact.connector.iface;

import java.util.Map;

public interface ContactReader<ConnectionType,RawObjectType> extends ContactInterfacePresent<ConnectionType,RawObjectType> {

	public Contact readContactById(String id) throws Exception;
	
	public Map<String,Contact> readContacts(int startIndex, int maxResults) throws Exception;
	
	public Map<String,Contact> readContactNames(int startIndex, int maxResults) throws Exception;

}
