package eu.balev.demo.swagger.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1605770292496886338L;

	public NotFoundException(String paramString) {
		super(paramString);
	}

}
