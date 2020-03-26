package com.br.robson.core.model.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties({ "id", "gender", "enabled" })
public class PeopleVO {

	@JsonProperty("id")
	private Long id;
	private String firstName;
	private String lastName;
	private String address;
	
	@JsonProperty("gender")
	private String gender;
	
	@JsonProperty("enabled")
	private Boolean enabled;
	
}
