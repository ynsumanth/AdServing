package com.ir.adserving;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

public class Query {
	private Map<Document, List<String>> resultList;
	public String[] selectedFields;
	public int start;
	public Query() {
		resultList = new HashMap<Document, List<String>>();
	}
	
	@SuppressWarnings("unchecked")
	public Map<Document, List<String>> query(String userQuery) {
		HttpSolrServer server = SolrServerFactory.getConnectionForNews();
		server.setMaxRetries(1);
		SolrQuery query = new SolrQuery(userQuery);
		query.setHighlight(true);
		query.setParam("hl.fl", "content");
		
		if(selectedFields != null && selectedFields.length > 0){
			query.setParam("defType", "dismax");
			query.setParam("qf", selectedFields);
		}
		if(start>=0){
			query.setParam("start", start+10+"");
			query.setParam("rows", 10+"");
		}
		
		QueryResponse response;
		try {
			response = server.query(query);
			SolrDocumentList docs = response.getResults();
			Map<String, Map<String, List<String>>> myhighlights = response.getHighlighting();
			for(SolrDocument doc: docs) {
				String id = (String)doc.getFieldValue("id");
				List<String> snippets = myhighlights.get(id).get("content");
				ArrayList<String> tmp = (ArrayList<String>) doc.getFieldValue("title");
				String title = tmp!=null?tmp.get(0):" ";
				tmp = (ArrayList<String>) doc.getFieldValue("content");
				String content = tmp!=null?tmp.get(0):" ";
				String author = (String) (doc.getFieldValue("author")!=null?doc.getFieldValue("author"):" ");
				tmp = (ArrayList<String>) doc.getFieldValue("date");
				String date = tmp!=null?tmp.get(0):" ";
				String place = (String) (doc.getFieldValue("place")!=null?doc.getFieldValue("place"):" ");
				Document resultDoc = new Document(author, content, date, id, place, title);
				resultList.put(resultDoc, snippets);
			}
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultList;
	}
	
	@SuppressWarnings("unchecked")
	public List<Advertisement> queryAds(String userQuery) {
		HttpSolrServer server = SolrServerFactory.getConnectionForAds();
		server.setMaxRetries(1);
		SolrQuery query = new SolrQuery(userQuery);
		if(start>=0){
			query.setParam("start", start+10+"");
			query.setParam("rows", 10+"");
		}
		QueryResponse response;
		List<Advertisement> myAds = new ArrayList<Advertisement>();
		try {
			response = server.query(query);
			SolrDocumentList adResults = response.getResults();
			for(SolrDocument adDocument : adResults) {
				Advertisement ad = new Advertisement();
				ad.setBidPrice((String) adDocument.getFieldValue("bidPrice"));
				ad.setClicks((String) adDocument.getFieldValue("clicks"));
				ad.setId((String) adDocument.getFieldValue("id"));
				ad.setKeyword((String) adDocument.getFieldValue("keyword"));
				ad.setUrl((String) adDocument.getFieldValue("url"));
				List<String> myTitleList = (List<String>)adDocument.getFieldValue("title");
				if(myTitleList!=null && myTitleList.size()!=0) {
					ad.setTitle(myTitleList.get(0));
				}
				myAds.add(ad);
			}
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return myAds;
	}
}
