package com.springapirest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    
	private static final long serialVersionUID = 742117158189849119L;
    private final String resourceName;
    
    public ResourceNotFoundException( String resourceName, String message) {
        super(message);
        this.resourceName = resourceName;
    }

	public String getResourceName() {
		return resourceName;
	}
    
}