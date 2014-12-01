package com.ir.adserving;

import java.io.File;

import org.apache.lucene.index.IndexWriter;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.request.ContentStreamUpdateRequest;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.util.NamedList;

public class Runner {
	public static String xmlFilePath = System.getProperty ("user.home") + "\\Documents\\Xmlcorpus\\";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		createXMLFiles(args[0]);
//		updateXMLFiles(xmlFilePath);
	}
	
	public static void createXMLFiles(String corpusDir){
		File corpusDirectory = new File(corpusDir);
		String[] catDirectories = corpusDirectory.list();
		String[] files;
		File dir;
		
		try {
			for (String cat : catDirectories) {
				dir = new File(corpusDir+ File.separator+ cat);
				files = dir.list();
				
				if (files == null)
					continue;
				
				for (String f : files) {
					try {
						Parser.parse(xmlFilePath,dir.getAbsolutePath() + File.separator +f);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void updateXMLFiles(String xmlCorpusDir)
	{
		File xmlCorpusDirectory = new File(xmlCorpusDir);
		String[] xmlFiles = xmlCorpusDirectory.list();
		File xmlFile;
		try {
			HttpSolrServer server = new HttpSolrServer("http://localhost:8080/solr");
			server.setMaxRetries(1);
			ContentStreamUpdateRequest csureq = new ContentStreamUpdateRequest("/update/xml");
			
			UpdateResponse res = server.deleteByQuery("*:*");
			System.out.println(res);
			server.commit();
			for(int i = 1;i<xmlFiles.length;i++){
				xmlFile = new File(xmlCorpusDir+ File.separator+ xmlFiles[i]);
				csureq.addFile(xmlFile,"text/xml");
				if(i%3000==0){
					server.commit();
					NamedList<Object> result = server.request(csureq);
					System.out.println(result);
				}
			}
			server.commit();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
}
