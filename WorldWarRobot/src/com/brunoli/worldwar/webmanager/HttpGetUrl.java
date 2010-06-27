package com.brunoli.worldwar.webmanager;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;



public class HttpGetUrl {

	private DefaultHttpClient httpclient;
	
	public HttpGetUrl() {
		httpclient = new DefaultHttpClient();
	}
	
	public StringBuilder getUrl(String url) throws Exception{
        HttpGet httpget = new HttpGet(url);

        HttpResponse response = httpclient.execute(httpget);
        HttpEntity entity = response.getEntity();
        StringBuilder page = null;
        String aux = null;
        if (entity != null) {
        	//
        	aux = EntityUtils.toString(entity);
            if(aux.equalsIgnoreCase("Invalid Request")){
            	httpclient.getConnectionManager().shutdown();
            	throw new Exception("Invalid Request");
            }
        }else{
        	httpclient.getConnectionManager().shutdown();
        	throw new Exception("ENTITY NULL");
        } 
        page = new StringBuilder(aux.replaceAll("\"", "\'"));
//        System.out.println("GET URL: "+url);
//        System.out.println("RESPONSE:");
//        System.out.println(page.toString());
        return page;
	}

	public void close() {
		// When HttpClient instance is no longer needed, 
        // shut down the connection manager to ensure
        // immediate deallocation of all system resources
        httpclient.getConnectionManager().shutdown();    
	}
}
