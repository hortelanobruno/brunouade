package com.brunoli.worldwar.webmanager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;



public class HttpGetUrl {

	private DefaultHttpClient httpclient;
	
	public HttpGetUrl() {
		httpclient = new DefaultHttpClient();
	}
	
	public StringBuilder getUrl(String url) throws Exception{
		//CONFIGURO GET
        HttpGet httpget = new HttpGet(url);
        //EJECUTO GET
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
        return page;
	}
	
	public StringBuilder postUrl(String url, Map<String,String> params) throws Exception{
		//CONFIGURO POST
		HttpPost httpPost = new HttpPost(url);
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		for(String key : params.keySet()){
			nvps.add(new BasicNameValuePair(key, params.get(key)));
		}
		httpPost.setEntity(new UrlEncodedFormEntity(nvps,HTTP.UTF_8));
		//EJECUTO POST
		HttpResponse response = httpclient.execute(httpPost);
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
        return page;
	}

	public void close() {
		// When HttpClient instance is no longer needed, 
        // shut down the connection manager to ensure
        // immediate deallocation of all system resources
        httpclient.getConnectionManager().shutdown();    
	}
}
