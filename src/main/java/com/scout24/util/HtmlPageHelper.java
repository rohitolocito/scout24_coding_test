package com.scout24.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.DocumentType;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

public class HtmlPageHelper {
	
	public static String getVersion(Document document) {
		List<Node> nodes = document.childNodes();
		for(Node node : nodes)  {
			if (node instanceof DocumentType) {
				return getVersion((DocumentType)node);
			}
		}
		return "";
	}
	
	private static String getVersion(DocumentType documentType) {
		if (documentType.attributes().hasKey("publicid")) {
			String publicString = documentType.attributes().get("publicid");
			String properties[] = publicString.split(" ");
			if (properties.length >= 3)
				return properties[2];
		}
		
		return documentType.toString();
	}
	
	public static Map<String, Integer> getHeadingsCount(Document document) {
		
		Elements headings = document.select(
				Headings.h1+", "+
		        Headings.h2+", "+
				Headings.h3+", "+
		        Headings.h4+", "+
				Headings.h5+", "+
		        Headings.h6);

		Map<String, Integer> map = new HashMap<>();
		
		
		map.put(Headings.h1.name(), headings.select(Headings.h1.name()).size());
		map.put(Headings.h2.name(), headings.select(Headings.h2.name()).size());
		map.put(Headings.h3.name(), headings.select(Headings.h3.name()).size());
		map.put(Headings.h4.name(), headings.select(Headings.h4.name()).size());
		map.put(Headings.h5.name(), headings.select(Headings.h5.name()).size());
		map.put(Headings.h6.name(), headings.select(Headings.h6.name()).size());
		
		return map;
	}


	public static boolean hasLoginForm(Document document) {
		
		return document
				.getElementsByTag("input")
				.stream()
				.filter(element -> element.attr("type").equals("password"))
				.findAny()
				.isPresent();
	}

	public static Elements getAllLinks(Document document) {
		return document.select("a[href]");
	}

	public static String getLinkRef(Element element) {
		return element.attr("abs:href");
	}
}
