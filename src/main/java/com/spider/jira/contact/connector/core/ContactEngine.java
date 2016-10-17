package com.spider.jira.contact.connector.core;

import java.util.Map;

import com.spider.jira.contact.connector.exception.ContactNotFoundException;
import com.spider.jira.contact.connector.iface.Contact;
import com.spider.jira.contact.connector.link.csv.CSVInterface;
import com.spider.jira.contact.connector.link.facebook.FacebookInterface;

public class ContactEngine {

	public static void main(String[] args) {
		System.out.println("STARTED");
		try {
			ContactEngine contactEngine = new ContactEngine();
			contactEngine.start();
			System.out.println("DONE");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void start() throws Exception {
		CSVInterface csvInterface = CSVInterface.getInstance();
		FacebookInterface facebookInterface = FacebookInterface.getInstance();
		try {
			csvInterface.getContactSearcher().setExactSearch(false);
			facebookInterface.getContactSearcher().setExactSearch(false);
//			Contact contact = facebookInterface.getContactSearcher().findByName("david",false);
//			System.out.println("Found. "+contact);
//			Map<String, Contact> results = facebookInterface.getContactSearcher().findAllByCriteria(Criteria.parse("name ~ Vittor"));
			Map<String, Contact> results = facebookInterface.getContactSearcher().findAllByName("tim",false);
			for (String key : results.keySet()) {
				System.out.println("Found. "+results.get(key));
			}
		} catch ( ContactNotFoundException e) {
			System.err.println(e.getMessage());
		}
	}
	
}
