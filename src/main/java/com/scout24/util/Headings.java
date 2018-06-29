package com.scout24.util;

public enum Headings {
	
	h1,
	h2,
	h3,
	h4,
	h5,
	h6;
	
	public static boolean isHeading(String tag) {
		for (Headings heading : values()) {
			if (tag.toLowerCase().equals(heading.name()))
				return true;
		}
		
		return false;
	}
}
