package com.sakhatech.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author Naveen
 * @createdDate 6-Jun-2017
 * @modifiedDate 6-Jun-2017
 */
public class ResponseError {

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonProperty("message")
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	/**
	 * @author Naveen
	 * @param statusCode
	 * @param errorMessage
	 * @createdDate 6-Jun-2017
	 * @modifiedDate 6-Jun-2017
	 * @return {@link ResponseError}
	 */
	public static <T> ResponseEntity<ResponseData<T>> getErrorResponseObject(int statusCode, 
			String errorMessage){	
		
		ResponseError error = new ResponseError();
		error.setMessage(errorMessage);
		
		ResponseData<T> responseData = new ResponseData<>();
		
		responseData.setError(error);
		responseData.setStatusCode(statusCode);
		responseData.setSuccess(false);
		//responseData.setData(classType.getConstructor().newInstance());
		
		return new ResponseEntity<ResponseData<T>>(responseData , HttpStatus.OK);
	}
}
