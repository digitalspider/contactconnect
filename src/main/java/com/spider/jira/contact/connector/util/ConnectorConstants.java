package com.spider.jira.contact.connector.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public interface ConnectorConstants {

	public static final DateFormat DATEFORMAT_DATE = new SimpleDateFormat("yyyy/MM/dd");
	public static final DateFormat DATEFORMAT_DATE_AU = new SimpleDateFormat("dd/MM/yyyy");
	public static final DateFormat DATEFORMAT_DATE_US = new SimpleDateFormat("MM/dd/yyyy");
	
	public static final DateFormat DATEFORMAT_DATETIME = new SimpleDateFormat("yyyy/MM/dd HH:mm");
	public static final DateFormat DATEFORMAT_DATETIME_AU = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	public static final DateFormat DATEFORMAT_DATETIME_US = new SimpleDateFormat("MM/dd/yyyy HH:mm");
	
	public static final DateFormat DATEFORMAT_JIRA = new SimpleDateFormat("d-MMM-yyyy");
	public static final DateFormat DATEFORMAT_JIRA_DATETIME = new SimpleDateFormat("d-MMM-yyyy HH:mm");
	
	public static final DateFormat DATEFORMAT_FACEBOOK_DATETIME = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
	
	
	
	public static final String DELIM_CSV = ",";
	public static final int SEARCH_BATCH_SIZE_LARGE = 500;
	public static final int SEARCH_BATCH_SIZE_SMALL = 20;
	
}
