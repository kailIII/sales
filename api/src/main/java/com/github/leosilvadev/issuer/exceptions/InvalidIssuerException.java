package com.github.leosilvadev.issuer.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.BAD_REQUEST, reason="Invalid issuer")
public class InvalidIssuerException extends RuntimeException {

	private static final long serialVersionUID = 1L;

}
