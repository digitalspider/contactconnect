package com.spider.jira.contact.connector.iface;

import java.util.Date;
import java.util.Map;

import com.spider.jira.contact.connector.bean.Address;

public interface Contact {
	
	public String getId();
	public void setId(String id);
	
	public String getName();
	public void setName(String name);
	
	public String getEmail();
	public void setEmail(String email);

	public String getMobileNumber();
	public void setMobileNumber(String mobileNumber);

	public Date getDob();
	public void setDob(Date dob);

	public Date getLastUpdated();
	public void setLastUpdated(Date lastUpdated);
	
	public Address getAddress();
	public void setAddress(Address address);
	
	public Map<String, Object> getData();
	public void setData(Map<String, Object> data);

}
