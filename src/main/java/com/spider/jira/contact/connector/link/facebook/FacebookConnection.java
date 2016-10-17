package com.spider.jira.contact.connector.link.facebook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.types.User;
import com.spider.jira.contact.connector.base.BaseContactInterface;
import com.spider.jira.contact.connector.base.BaseContactInterfacePresent;
import com.spider.jira.contact.connector.exception.ConnectionException;
import com.spider.jira.contact.connector.iface.ContactConnection;

public class FacebookConnection extends BaseContactInterfacePresent<FacebookClient,User> implements ContactConnection<FacebookClient,User> {
	
	private FacebookClient facebookClient;

	public static final String FACEBOOK_GRAPHAPI_URL = "https://graph.facebook.com/";
	public static final String FACEBOOK_OAUTH_TOKEN_REQUEST = FACEBOOK_GRAPHAPI_URL + "oauth/access_token";
	
	// https://graph.facebook.com/oauth/access_token?client_id=12605646127&client_secret=7f3218f07f7d5a43762e632ebd79e619&grant_type=client_credentials
	// https://graph.facebook.com/app?access_token=12605646127|RguZJQF2wLtdIQViZZ9tloUmfRU
	
	private String FACEBOOK_APP_ID = "12605646127";
	private String FACEBOOK_APP_SECRET = "7f3218f07f7d5a43762e632ebd79e619";

	/**
	 * To get the necessary token for the below method, 
	 * you can also do the following in a web browser.
	 * 
	 * 1. Go to http://developers.facebook.com/docs/reference/rest/
	 * 2. Get Zuckerburgs Address
	 * 3. https://api.facebook.com/method/users.getInfo?uids=4&fields=name&access_token=AAAAAAITEghMBAJlED8ZBYiGRbM78djpdA1dGrALKWTpyILR3bLHGKErGvuRP8zi0W4c3ecdD7uA9MMAHf2KwgrnPmZAHCaKl0ZAPCXCZAgZDZD&format=json
	 * 4. Pull our the token string = AAAAAAITEghMBAJlED8ZBYiGRbM78djpdA1dGrALKWTpyILR3bLHGKErGvuRP8zi0W4c3ecdD7uA9MMAHf2KwgrnPmZAHCaKl0ZAPCXCZAgZDZD
	 * 
	 * After this you can make requests using:
	 * https://graph.facebook.com/me/friends?access_token=xxx
	 * https://graph.facebook.com/2527992?access_token=
	 * https://graph.facebook.com/547995314?fields=id,name&access_token=
	 * https://graph.facebook.com/me?metadata=1&access_token=
	 * https://graph.facebook.com/me/friends&access_token=
	 * 
	 * Test using the graph explorer tool
	 * http://developers.facebook.com/tools/explorer?method=GET&path=547995314%3Ffields%3Did%2Cname
	 * http://developers.facebook.com/tools/explorer?method=GET&path=me%2Ffriends
	 * 
	 */
	public FacebookConnection(BaseContactInterface<FacebookClient, User> contactInterface) {
		super(contactInterface);
	}

	@Override
	public FacebookClient getConnection() throws ConnectionException {
		try {
			if (facebookClient != null) {
				return facebookClient;
			}

			// Without OAuth - need to manually create the token here
//			String token0 = "AAAAAAITEghMBAJlED8ZBYiGRbM78djpdA1dGrALKWTpyILR3bLHGKErGvuRP8zi0W4c3ecdD7uA9MMAHf2KwgrnPmZAHCaKl0ZAPCXCZAgZDZD";
//			String url0 = getFacebookContentURL("me/friends",token0);
//			String result0 = callHttp(url0);
//			System.out.println(result0);
			
			// Make HTTP Token Request
			String url = getFacebookTokenRequestURL();
			System.out.println(url);
			String result = callHttp(url);
			System.out.println(result);
			if (result != null) {
				String[] tokenStrings = result.split("=");
				if (tokenStrings.length == 2) {
					String tokenKey = tokenStrings[0];
					String tokenValue = tokenStrings[1];
					if (tokenKey.equals("access_token")) {
						// token comes from http://developers.facebook.com/tools/explorer?method=GET&path=547995314%3Ffields%3Did%2Cname
						String token = "AAACEdEose0cBAIGu83Q1TJYjFjpTwLwgZAcAM1Euey7ZB3HpVkq5mN2wxzEs5xZCE7ZC8BklZCfZCTqyOS8menG86oQWdsetMB3jUtkMtgsQZDZD";
						//String token = tokenValue;
						facebookClient = new DefaultFacebookClient(token);
						return facebookClient;
					} else {
						throw new ConnectionException("access_token parameter not returned from URL. URL="+url);
					}
				} else {
					throw new ConnectionException("access_token parameter not returned from URL. URL="+url);
				}			
			} else {
				throw new ConnectionException("access_token parameter not returned from URL. URL="+url);
			}
		} catch (Exception e) {
			throw new ConnectionException(e);
		}
	}	

	private String getFacebookTokenRequestURL() {
		String url = FACEBOOK_OAUTH_TOKEN_REQUEST 
			+ "?client_id="+FACEBOOK_APP_ID
			+ "&client_secret="+FACEBOOK_APP_SECRET
			+ "&grant_type=client_credentials";
		return url;
	}
	
	private String getFacebookContentURL(String graphPath, String token) {
		String url = null;
		if (StringUtils.isNotBlank(graphPath) && StringUtils.isNotBlank(token)) {
			url = FACEBOOK_GRAPHAPI_URL + graphPath + "?access_token="+token;
		}
		return url;
	}
	
	public String callHttp(String url) {
		StringBuffer result = new StringBuffer();
		try {
			HttpClient httpclient = new DefaultHttpClient();
			HttpGet httpget = new HttpGet(url);
			HttpResponse response = httpclient.execute(httpget);
			System.out.println(response.getStatusLine());
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				InputStream instream = entity.getContent();
				try {
					BufferedReader reader = new BufferedReader(
							new InputStreamReader(instream));
					String line = null;
					while ((line = reader.readLine()) != null) {
						result.append(line);
					}
				} catch (IOException ex) {
					throw ex;
				} catch (RuntimeException ex) {
					httpget.abort();
					throw ex;
				} finally {
					instream.close();
				}
				httpclient.getConnectionManager().shutdown();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result.toString();
	}

}
