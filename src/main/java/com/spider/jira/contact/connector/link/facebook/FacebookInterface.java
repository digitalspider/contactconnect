package com.spider.jira.contact.connector.link.facebook;

import com.restfb.FacebookClient;
import com.restfb.types.User;
import com.spider.jira.contact.connector.base.BaseContactInterface;

public class FacebookInterface extends BaseContactInterface<FacebookClient,User> {
	
	private static FacebookInterface facebookInterface;
	
	public static FacebookInterface getInstance() {
		if (facebookInterface == null) {
			facebookInterface = new FacebookInterface();
		}
		return facebookInterface;
	}
	
	private FacebookInterface() {
		setContactConnection(new FacebookConnection(this));
		setContactReader(new FacebookReader(this));
		setContactWriter(new FacebookWriter(this));
		setContactSearcher(new FacebookSearcher(this));
		setContactTransformer(new FacebookTransformer(this));
		setContactFieldMapper(new FacebookFieldMapper(this));
	}

}
