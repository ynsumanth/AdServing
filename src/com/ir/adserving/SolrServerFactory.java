package com.ir.adserving;

import org.apache.solr.client.solrj.impl.HttpSolrServer;

public class SolrServerFactory {
	private static HttpSolrServer connection1;
	private static HttpSolrServer connection2;
	
	public SolrServerFactory() {}
	
	public static HttpSolrServer getConnectionForNews() {
		if(connection1==null)
			connection1 = new HttpSolrServer("http://localhost:8080/solr");
		return connection1;
	}
	
	public static HttpSolrServer getConnectionForAds() {
		if(connection2==null)
			connection2 = new HttpSolrServer("http://localhost:8080/solr/collection2");
		return connection2;
	}
	
	public static void close() {
		if(connection1!=null) {
			connection1.shutdown();
		}
		if(connection2!=null) {
			connection2.shutdown();
		}
	}
}
