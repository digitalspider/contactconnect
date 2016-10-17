package com.spider.jira.contact.connector.base;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.spider.jira.contact.connector.bean.Address;
import com.spider.jira.contact.connector.data.FieldName;
import com.spider.jira.contact.connector.iface.Contact;
import com.spider.jira.contact.connector.util.ConnectorConstants;

public class BaseContact implements Contact,ConnectorConstants {

	private String id;
	private Map<String,Object> data = new HashMap<String, Object>();
	
	@Override
	public String toString() {
		String stringValue = "Contact["+id+"]: Name="+data.get(FieldName.NAME.toString())+" email="+data.get(FieldName.EMAIL.toString())+" dob="+data.get(FieldName.DOB.toString())+" address="+data.get(FieldName.ADDRESS.toString());
		return stringValue;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return (String)data.get(FieldName.NAME.toString());
	}
	public void setName(String name) {
		data.put(FieldName.NAME.toString(), name);
	}
	
	public String getEmail() {
		return (String)data.get(FieldName.EMAIL.toString());
	}
	public void setEmail(String email) {
		data.put(FieldName.EMAIL.toString(), email);
	}
	
	public String getMobileNumber() {
		return (String)data.get(FieldName.MOBILENUMBER.toString());
	}
	public void setMobileNumber(String mobileNumber) {
		data.put(FieldName.MOBILENUMBER.toString(), mobileNumber);
	}
	
	public Date getDob() {
		return (Date)data.get(FieldName.DOB.toString());
	}
	public void setDob(Date dob) {
		data.put(FieldName.DOB.toString(), dob);
	}
	
	public Date getLastUpdated() {
		return (Date)data.get(FieldName.LASTUPDATED.toString());
	}
	public void setLastUpdated(Date lastUpdated) {
		data.put(FieldName.LASTUPDATED.toString(), lastUpdated);
	}
	
	public Address getAddress() {
		Address address = (Address)data.get(FieldName.ADDRESS.toString());
		if (address == null) {
			address = new Address();
			setAddress(address);
		}
		return address;
	}
	public void setAddress(Address address) {
		data.put(FieldName.ADDRESS.toString(), address);
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}

}
