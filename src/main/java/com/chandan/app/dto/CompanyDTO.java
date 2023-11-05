package com.chandan.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CompanyDTO {

	private Long id;
@NotEmpty(message = "comapny name is mandatory")
@Size(min=5,message = "company name should be at least 5 characters")
	private String companyName;
@NotEmpty(message = "Email is mandatory")
@Email(message = "Invalid email format")
	private String email;
@PositiveOrZero(message = "strength should be postive number or zero")
	private Integer strength;
	
	private String webSiteURL;
	@Size(max = 5,message = "company code length must be at 5 charaters")
	@Pattern(regexp = "^[A-Za-z]{2}[0-9]{2}[EN]",message = "Invalid company code foramt")
	private  String companyCode;
}
