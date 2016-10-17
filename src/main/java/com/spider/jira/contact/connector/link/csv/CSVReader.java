package com.spider.jira.contact.connector.link.csv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.spider.jira.contact.connector.base.BaseContactInterfacePresent;
import com.spider.jira.contact.connector.base.BaseContactInterface;
import com.spider.jira.contact.connector.data.FieldName;
import com.spider.jira.contact.connector.iface.Contact;
import com.spider.jira.contact.connector.iface.ContactFieldMapper;
import com.spider.jira.contact.connector.iface.ContactReader;
import com.spider.jira.contact.connector.util.ConnectorConstants;

public class CSVReader extends BaseContactInterfacePresent<File,String> implements ContactReader<File,String>, ConnectorConstants {

	public CSVReader(BaseContactInterface<File,String> contactInterface) {
		super(contactInterface);
	}

	@Override
	public Contact readContactById(String id) throws Exception {
		// Note: There probably a better way to do this on a CSV file, but this works for now
		Map<String,Contact> contacts = readContacts(0,1000);
		return contacts.get(id);
	}

	@Override
	public Map<String, Contact> readContactNames(int startIndex, int maxResults)
			throws Exception {
		System.err.println("METHOD NOT IMPLEMENTED. User readContacts()");
		return null;
	}
	
	@Override
	public Map<String,Contact> readContacts(int startIndex, int maxResults) throws Exception {
		Map<String,Contact> contacts = new HashMap<String,Contact>();
		File file = contactInterface.getContactConnection().getConnection();
		if (file != null) {
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line;
			ContactFieldMapper<File, String> contactFieldMapper = contactInterface.getContactFieldMapper();
			if (startIndex > 0) {
				int currentIndex = 0;
				while ((line = bufferedReader.readLine()) != null) {
					if (StringUtils.isNotBlank(line)) {
						String[] lineParts = line.split(DELIM_CSV);
						if (lineParts[0].equalsIgnoreCase(FieldName.ID.toString())) {
							continue;
						}
						currentIndex++;
					}
					if (currentIndex==startIndex) {
						break;
					}
				}
			}
			while ((line = bufferedReader.readLine()) != null) {
				if (StringUtils.isNotBlank(line)) {
					String[] lineParts = line.split(DELIM_CSV);
					if (lineParts[0].equalsIgnoreCase(FieldName.ID.toString())) {
						contactFieldMapper.init(line);
						continue;
					}
					Contact contact = contactInterface.getContactTransformer().convertToContact(line);
					if (contact != null) {
						contacts.put(contact.getId(),contact);
						if (contacts.size() >= maxResults) {
							break;
						}
					}
				}
			}
		}
		return contacts;
	}

}
