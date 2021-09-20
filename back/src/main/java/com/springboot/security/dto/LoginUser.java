package com.springboot.security.dto;

import java.util.Set;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginUser {
	
	@NotBlank
	private String userName;
	@NotBlank
	private String password;

}
