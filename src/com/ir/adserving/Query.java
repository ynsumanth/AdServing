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
	public Query() {
		resultList = new HashMap<Document, List<String>>();
	}
	@SuppressWarnings("unchecked")
	public Map<Document, List<String>> query(String userQuery) {
		System.out.println("entered query method");
		HttpSolrServer server = new HttpSolrServer("http://localhost:8080/solr");
		System.out.println("connected to solr");
		server.setMaxRetries(1);
		SolrQuery query = new SolrQuery(userQuery);
		query.setHighlight(true);
		query.setParam("hl.fl", "content");
		QueryResponse response;
		try {
			response = server.query(query);
			System.out.println("got response from solr");
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
				tmp = (ArrayList<String>) doc.getFieldValue("place");
				String place = tmp!=null?tmp.get(0):" ";
				Document resultDoc = new Document(author, content, date, id, place, title);
				resultList.put(resultDoc, snippets);
			}
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			System.out.println("failed finally with exception");
			e.printStackTrace();
		}
		return resultList;
	}
}
