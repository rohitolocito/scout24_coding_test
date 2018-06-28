package com.scout24.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "URL is malformed.")
public class MalformedUrlException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public MalformedUrlException(String msg) {
		super(msg);
	}

}
