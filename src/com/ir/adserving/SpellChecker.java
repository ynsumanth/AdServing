package com.ir.adserving;

import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.SpellCheckResponse;
import org.apache.solr.client.solrj.response.SpellCheckResponse.Suggestion;

public class SpellChecker {
	public SpellChecker() {}
	
	public String checkIsCorrectlySpelled(String userQuery) {
		HttpSolrServer connection = SolrServerFactory.getConnectionForNews();
		SolrQuery query = new SolrQuery(userQuery);
		query.setParam("qt", "/spell");
		query.setParam("spellcheck", "on");
		QueryResponse response;
		List<String> mySpellingResults= new ArrayList<String>();;
		try {
			response = connection.query(query);
			SpellCheckResponse mySpellCheckResponse = response.getSpellCheckResponse();
			if(mySpellCheckResponse!=null && !mySpellCheckResponse.isCorrectlySpelled()) {
				for(Suggestion mySuggestion: mySpellCheckResponse.getSuggestions()) {
					mySpellingResults.addAll(mySuggestion.getAlternatives());
				}
			}
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (mySpellingResults.size()>0)?(String)mySpellingResults.get(0):" ";
	}
}
