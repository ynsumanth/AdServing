package com.ir.bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.RequestDispatcher;

import org.apache.taglibs.standard.lang.jstl.test.PageContextImpl;

import com.ir.adserving.Advertisement;
import com.ir.adserving.Document;
import com.ir.adserving.Query;
import com.ir.adserving.SpellChecker;
@ManagedBean
public class HomeBean {
	private Map<Document, List<String>> newsResult;
	private List<Advertisement> adResult;
	private String userQuery;
	private boolean pop;
	private boolean spellCheckResults;
	private List<String> spellCorrections;
	private static int start=0;
	String action;
	
	public String[] selectedCheckboxes;
	public HomeBean() {
		// TODO Auto-generated constructor stub
		newsResult = new HashMap<Document, List<String>>();
		spellCorrections = new ArrayList<String>();
	}
	
	public String[] getSelectedCheckboxes(){
		return selectedCheckboxes;
	}
	
	public void setSelectedCheckboxes(String[] selectedCheckboxes){
		this.selectedCheckboxes = selectedCheckboxes;
	}
	
	public boolean getSpellCheckResults() {
		return spellCheckResults;
	}
	public List<String> getSpellCorrections() {
		return spellCorrections;
	}
	public void setSpellCorrections(List<String> spellCorrections) {
		this.spellCorrections = spellCorrections;
	}
	public String getUserQuery() {
		return userQuery;
	}
	public void setUserQuery(String userQuery) {
		this.userQuery = userQuery;
	}
	public boolean getPop() {
		return pop;
	}
	public void setNewsResult(Map<Document, List<String>> newsResult) {
		this.newsResult = newsResult;
	}
	public Map<Document, List<String>> getNewsResult() {
		return newsResult;
	}
	public void setAdResult(List<Advertisement> adResult) {
		this.adResult = adResult;
	}
	public List<Advertisement> getAdResult() {
		return adResult;
	}
	
	public void listen(ActionEvent event) {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("queryTyped", userQuery);
		System.out.println(Arrays.toString(selectedCheckboxes));
		start = 0;
		Query query = new Query();
		query.selectedFields = selectedCheckboxes;
		query.start 		 = -1;
		SpellChecker speller = new SpellChecker();
		newsResult = query.query(userQuery);
		adResult = query.queryAds(userQuery);
		if(newsResult!=null && newsResult.size()!=0) 
			pop = true;
		if(userQuery.contains(" ")) {
			String[] strings = userQuery.split(" ");
			for(String string: strings) {
				if(string!=null && string.trim().length()!=0) {
					spellCorrections.add(speller.checkIsCorrectlySpelled(string));
				}
			}
		}
		if(spellCorrections.size()>0) {
			spellCheckResults = true;
		}
	}
	
	public void queryForSpellChecker(ActionEvent event) {
		System.out.println("inside spell");
		Query query = new Query();
		query.start = -1;
		//String queryChosen = (String)FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("spellingclicked");
		String queryChosen = (String) event.getComponent().getAttributes().get("value");
		System.out.println("query chosen:"+queryChosen);
		userQuery = queryChosen;
		newsResult = query.query(userQuery);
		adResult = query.queryAds(userQuery);
		if(newsResult!=null && newsResult.size()!=0) 
			pop = true;
	}
	
	public void adClickListener(ActionEvent event) {
		System.out.println("inside click listener");
		String action = (String)event.getComponent().getAttributes().get("action");
	}
	
	public void previous(ActionEvent event) {
		String queryChosen = (String) event.getComponent().getAttributes().get("value");
		userQuery = queryChosen;
		Query query = new Query();
		start = start-10;
		if(start >=0){
			query.start = start;
			newsResult = query.query(queryChosen);
			adResult = query.queryAds(queryChosen);
			if(newsResult!=null && newsResult.size()!=0) 
				pop = true;
		}
		
	}
	
	public void next(ActionEvent event){
		userQuery = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("queryTyped");
		System.out.println(userQuery);
		Query query = new Query();
		start = start+10;
		System.out.println("start "+start);
		query.start = start;
		newsResult = query.query(userQuery);
		adResult = query.queryAds(userQuery);
		if(newsResult!=null && newsResult.size()!=0) 
			pop = true;
	}
	
	public void editAction(String action) {
		System.out.println("val " +action);
	}
}
