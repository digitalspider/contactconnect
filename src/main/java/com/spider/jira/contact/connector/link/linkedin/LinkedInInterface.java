package com.spider.jira.contact.connector.link.linkedin;

import com.google.code.linkedinapi.client.LinkedInApiClient;
import com.google.code.linkedinapi.schema.Person;
import com.spider.jira.contact.connector.base.BaseContactInterface;

public class LinkedInInterface extends BaseContactInterface<LinkedInApiClient,Person> {
	
	private static LinkedInInterface linkedInInterface;
	
	public static LinkedInInterface getInstance() {
		if (linkedInInterface == null) {
			linkedInInterface = new LinkedInInterface();
		}
		return linkedInInterface;
	}
	
	private LinkedInInterface() {
		setContactConnection(new LinkedInConnection(this));
		setContactReader(new LinkedInReader(this));
		setContactWriter(new LinkedInWriter(this));
		setContactSearcher(new LinkedInSearcher(this));
		setContactTransformer(new LinkedInTransformer(this));
		setContactFieldMapper(new LinkedInFieldMapper(this));
	}

}
