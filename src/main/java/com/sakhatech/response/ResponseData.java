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
 * @param <T>
 */
public class ResponseData<T> {
		
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonProperty("statusCode")
	private Integer statusCode;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonProperty("success")
	private boolean success;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonProperty("data")
	private T data;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonProperty("error")
	private ResponseError error;

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	
	public ResponseError getError() {
		return error;
	}

	public void setError(ResponseError error) {
		this.error = error;
	}

	@Override
	public String toString() {
		return "ResponseData [ data=" + data + ", statusCode=" + statusCode + ", success=" + success
				+ "]";
	}

	/**
	 * 
	 * @author Naveen
	 * @param statusCode
	 * @param success
	 * @param data
	 * @createdDate 6-Jun-2017
	 * @modifiedDate 6-Jun-2017
	 * @return {@link ResponseEntity}
	 */
	public static <T> ResponseEntity<ResponseData<T>> getSuccessResponseObject(int statusCode
			, T data){
		
		ResponseData<T> responseData = new ResponseData<>();
		
		responseData.setStatusCode(statusCode);
		responseData.setSuccess(true);
		responseData.setData(data);
			
		return new ResponseEntity<ResponseData<T>>(responseData , HttpStatus.OK);
	}
		
}
