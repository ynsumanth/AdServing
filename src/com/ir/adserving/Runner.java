package com.ir.adserving;

import java.io.File;

import com.ir.adserving.Parser;

public class Runner {
	public static String xmlFilePath = System.getProperty ("user.home") + "/Documents/MSCS/IR_project3/solr-4.10.2/xmlcorpus/";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String corpusDir = args[0];
		
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

}
