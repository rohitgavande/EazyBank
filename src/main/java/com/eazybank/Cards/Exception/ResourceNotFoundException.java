package com.eazybank.Cards.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(String resourceName, String fieldName , String fieldValue){
		 super(String.format("%s not found with given input data %s: %s,", resourceName, fieldName , fieldValue));
	 }
	
	public ResourceNotFoundException(String msg) {
		super(msg);
	}
	
}
