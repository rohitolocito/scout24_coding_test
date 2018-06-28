package com.scout24.util;

import java.util.List;

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
