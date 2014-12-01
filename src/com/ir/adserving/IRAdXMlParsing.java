package com.ir.adserving;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IRAdXMlParsing {
	public static void main(String[] arguments) throws IOException {
		int count = 0;
		BufferedReader br = new BufferedReader(new FileReader(new File("C:/Users/chakri/Desktop/IR/Project3/AdvertisementDataSet/webpages-dataset-1.txt")));
		String str = "", res = "";
		while ((str = br.readLine()) != null) {
			res = str.trim();
			String regex = "([1-9\\s]+)([a-z\\s]+)(http[&?=~a-z_+()\\-A-Z0-9\\.//:\\s]+)(1\\s[1-9][0]{0,}\\s)(\\p{ASCII}*2012)";
			Pattern pat = Pattern.compile(regex);
			Matcher mat = pat.matcher(res);
			if (mat.find()) {
				count++;
				String adFolder = "C:/Users/Administrator/Documents/AdXmls";
				BufferedWriter bw = new BufferedWriter(new FileWriter(new File(adFolder + count + ".xml")));
				bw.write("<add>\n<doc>\n");
				bw.write("<field name=\"" + AdFilelds.keyword + "\">" + mat.group(2).trim() + "</field>\n");
				bw.write("<field name=\"" + AdFilelds.url + "\">" + mat.group(3).trim() + "</field>\n");
				bw.write("<field name=\"" + AdFilelds.title + "\">" + mat.group(5).replace("2012", "").trim() + "</field>\n");
				bw.write("<field name=\"" + AdFilelds.bidPrice + "\">" + (int) (5 + Math.random() * 5) + "</field>\n");
				bw.write("<field name=\"" + AdFilelds.clicks + "\">" + 0 + "</field>\n");
				bw.write("</doc>\n</add>");
				bw.close();
			}
		}
		br.close();
		System.out.println("count " + count);
	}
}