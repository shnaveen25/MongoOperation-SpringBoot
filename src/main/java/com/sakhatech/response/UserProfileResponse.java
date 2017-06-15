package com.sakhatech.response;

import java.util.Arrays;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author Naveen
 * @createdDate 13-Jun-2017
 * @modifiedDate 13-Jun-2017
 * 
 */
public class UserProfileResponse {
	
	@JsonInclude(JsonInclude.Include.NON_DEFAULT)
	@JsonProperty("name")
	public String name;
	
	@JsonInclude(JsonInclude.Include.NON_DEFAULT)
	@JsonProperty("email")
	public String email;
	
	@JsonInclude(JsonInclude.Include.NON_DEFAULT)
	@JsonProperty("mobile")
	public String mobile;
	
	@JsonInclude(JsonInclude.Include.NON_DEFAULT)
	@JsonProperty("dob")
	public Date dob;	
	
	@JsonInclude(JsonInclude.Include.NON_DEFAULT)
	@JsonProperty("profilePic")
	public byte[] profilepic;

	@Override
	public String toString() {
		return "UserProfileResponse [name=" + name + ", email=" + email + ", mobile=" + mobile + ", dob=" + dob
				+ ", profilepic=" + Arrays.toString(profilepic) + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public byte[] getProfilepic() {
		return profilepic;
	}

	public void setProfilepic(byte[] bs) {
		this.profilepic = bs;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}
	
	
	
}
