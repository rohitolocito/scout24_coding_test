package com.scout24.util;

public enum Headings {
	
	H1,
	H2,
	H3,
	H4,
	H5,
	H6;
	
	public static boolean isHeading(String tag) {
		for (Headings heading : values()) {
			if (tag.toUpperCase().equals(heading.name()))
				return true;
		}
		
		return false;
	}
}
